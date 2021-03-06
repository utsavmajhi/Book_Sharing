package main

import (
	"encoding/json"
	"time"

	"github.com/google/uuid"
	"github.com/hyperledger/fabric-chaincode-go/shim"
	"github.com/hyperledger/fabric-protos-go/peer"
)

func userGateway(stub shim.ChaincodeStubInterface, args []string) peer.Response {
	if len(args) < 2 {
		return shim.Error("Need email id of user and function")
	}
	user, err := getUser(stub, args[0])
	if err != nil {
		return shim.Error(err.Error())
	}
	// invoke function
	funcName := args[1]
	if len(args) >= 3 {
		newargs := args[2:]
		if funcName == "addBook" {
			return addBook(stub, newargs, user)
		}
		if funcName == "changeCover" {
			return changeCover(stub, newargs, user.Email)
		}
		if funcName == "removeBook" {
			return removeBook(stub, newargs, user)
		}
		if funcName == "requestBook" {
			return requestBook(stub, newargs, user.Email)
		}
		if funcName == "respondRequest" {
			return respondRequest(stub, newargs, user.Email)
		}
		if funcName == "transferBook" {
			return transferBook(stub, newargs, user)
		}
		if funcName == "getTheRequest" {
			return getTheRequest(stub, newargs)
		}
		if funcName == "getTheUser" {
			return getTheUser(stub, newargs)
		}
		if funcName == "getTheBook" {
			return getTheBook(stub, newargs)
		}
	}
	//query function
	if len(args) == 2 {
		if funcName == "getUser" {
			UByte, _ := json.Marshal(user)
			return shim.Success(UByte)
		}
		if funcName == "getAllOwnedBook" {
			return getAllOwnedBook(stub, user.Owned)
		}
		if funcName == "getAllCurentBook" {
			return getAllCurentBook(stub, user.Current)
		}
		// if funcName == "getAllRequest" {
		// 	return getAllRequest(stub, user.Email)
		// }
	}
	// if funcName == "getAllTheBook" {
	// 	return getAllTheBook(stub)
	// }
	return shim.Error("No funcion given")

}
func getAllTheBook(stub shim.ChaincodeStubInterface) peer.Response {
	iterator, err := stub.GetStateByPartialCompositeKey(BOOK, []string{})
	if err != nil {
		return shim.Error(err.Error())
	}
	var result []Book
	defer iterator.Close()
	for iterator.HasNext() {
		res, err := iterator.Next()
		if err != nil {
			return shim.Error(err.Error())
		}
		var temp Book
		json.Unmarshal(res.GetValue(), &temp)
		result = append(result, temp)
	}
	output, _ := json.Marshal(result)
	return shim.Success(output)
}
func getTheRequest(stub shim.ChaincodeStubInterface, args []string) peer.Response {
	//args[0]=request ID
	if len(args) != 1 {
		return shim.Error("Only one args required")
	}
	key := args[0]
	RByte, err, ok := getStateByte(stub, key)
	if ok != true {
		return shim.Error(err.Error())
	}
	return shim.Success(RByte)
}
func getTheUser(stub shim.ChaincodeStubInterface, args []string) peer.Response {
	//args[0]=email
	if len(args) != 1 {
		return shim.Error("")
	}
	key := args[0]
	RByte, err, ok := getStateByte(stub, key)
	if ok != true {
		return shim.Error(err.Error())
	}
	return shim.Success(RByte)

}
func getTheBook(stub shim.ChaincodeStubInterface, args []string) peer.Response {
	//args[0]=isbn
	if len(args) != 1 {
		return shim.Error("")
	}
	key := args[0]
	RByte, err, ok := getStateByte(stub, key)
	if ok != true {
		return shim.Error(err.Error())
	}
	return shim.Success(RByte)

}
func transferBook(stub shim.ChaincodeStubInterface, args []string, user User) peer.Response {
	// args[0]= request Id , args[1]=isbn
	if len(args) != 2 {
		return shim.Error("Require request Id and scanned isbn code")
	}
	key := args[0]
	request, err := getRequest(stub, key)
	if err != nil {
		return shim.Error(err.Error())
	}
	if request.Status != "1" {
		return shim.Error("Request is not accepted")
	}
	if request.FromEmail != user.Email {
		return shim.Error("Owner miss-match")
	}
	if request.ISBN != args[1] {
		return shim.Error("This is not the book")
	}
	isbn := request.ISBN
	book, err := getBook(stub, isbn)
	if err != nil {
		return shim.Error(err.Error())
	}
	currentOwner, err := getUser(stub, request.ToEmail)
	if err != nil {
		return shim.Error(err.Error())
	}
	delete(currentOwner.Current, isbn)
	book.Current = user.Email
	user.Current[isbn] = time.Now().Unix()

	SByte, _ := json.Marshal(currentOwner)
	stub.PutState(request.ToEmail, SByte)
	SByte, _ = json.Marshal(book)
	stub.PutState(isbn, SByte)
	SByte, _ = json.Marshal(user)
	stub.PutState(user.Email, SByte)
	stub.DelState(args[0])
	return shim.Success(nil)
}
func respondRequest(stub shim.ChaincodeStubInterface, args []string, email string) peer.Response {
	//args[0]= request Id , response = args[1]
	if len(args) != 2 {
		return shim.Error("Require one args to respond to request")
	}
	key := args[0]
	request, err := getRequest(stub, key)
	if err != nil {
		return shim.Error(err.Error())
	}
	if request.Status != "0" {
		return shim.Error("Cann't respond to this request")
	}
	if args[2] == "accept" {
		request.Status = "1"
	}
	if args[2] == "reject" {
		request.Status = "2"
	}
	RByte, _ := json.Marshal(request)
	stub.PutState(key, RByte)
	return shim.Success(nil)
}
func requestBook(stub shim.ChaincodeStubInterface, args []string, email string) peer.Response {
	// args[0]=isbn
	if len(args) != 1 {
		return shim.Error("Require 1 args to make book request")
	}
	Bookkey := args[0]
	book, err := getBook(stub, Bookkey)
	if err != nil {
		return shim.Error(err.Error())
	}
	if book.Current == email {
		return shim.Error("Cannot request own book")
	}
	requestKey := uuid.New().String()
	request := Request{
		Id:        requestKey,
		DocType:   REQUEST,
		ToEmail:   book.Current,
		ISBN:      args[0],
		FromEmail: email,
		Status:    "0",
	}
	RByte, _ := json.Marshal(request)
	stub.PutState(requestKey, RByte)
	return shim.Success(nil)
}
func removeBook(stub shim.ChaincodeStubInterface, args []string, user User) peer.Response {
	// args[0]=isbn
	if len(args) != 1 {
		return shim.Error("Require 1 args to remove book")
	}
	key := args[0]
	book, err := getBook(stub, key)
	if err != nil {
		return shim.Error(err.Error())
	}
	if book.Owner == user.Email && book.Current == user.Email {
		err = stub.DelState(key)
		if err != nil {
			return shim.Error(err.Error())
		}
		delete(user.Owned, args[0])
		delete(user.Current, args[0])
		UByte, _ := json.Marshal(user)
		stub.PutState(user.Email, UByte)
		return shim.Success(nil)
	}
	return shim.Error("Owner mis-match")
}
func changeCover(stub shim.ChaincodeStubInterface, args []string, email string) peer.Response {
	//args[0]=isbn , args[1]=image byte code
	if len(args) != 2 {
		return shim.Error("Require 2 args to add/change cover of book")
	}
	key := args[0]
	book, err := getBook(stub, key)
	if err != nil {
		return shim.Error(err.Error())
	}
	if book.Owner != email {
		return shim.Error("Owner mis-match")
	}
	book.Cover = args[1]
	BByte, _ := json.Marshal(book)
	err = stub.PutState(key, BByte)
	if err != nil {
		return shim.Error(err.Error())
	}
	return shim.Success(nil)
}
func getAllRequest(stub shim.ChaincodeStubInterface, email string) peer.Response {
	result := []Request{}
	Riterator, err := stub.GetStateByPartialCompositeKey(REQUEST, []string{email})
	if err != nil {
		return shim.Error(err.Error())
	}
	defer Riterator.Close()
	for Riterator.HasNext() {
		res, err := Riterator.Next()
		if err != nil {
			return shim.Error(err.Error())
		}
		var temp Request
		json.Unmarshal(res.Value, &temp)
		result = append(result, temp)
	}
	output, _ := json.Marshal(result)
	return shim.Success(output)
}
func getAllCurentBook(stub shim.ChaincodeStubInterface, books map[string]int64) peer.Response {
	result := []struct {
		BookDetail Book  `json:"book_details"`
		GotOn      int64 `json:"got_on"`
	}{}
	for isbn, value := range books {
		b, err := getBook(stub, isbn)
		if err != nil {
			return shim.Error(err.Error())
		}
		temp := struct {
			BookDetail Book  `json:"book_details"`
			GotOn      int64 `json:"got_on"`
		}{
			BookDetail: b,
			GotOn:      value,
		}
		result = append(result, temp)
	}
	output, _ := json.Marshal(result)
	return shim.Success(output)
}
func getAllOwnedBook(stub shim.ChaincodeStubInterface, books map[string]bool) peer.Response {
	var results []Book
	for isbn, _ := range books {
		temp, err := getBook(stub, isbn)
		if err != nil {
			return shim.Error(err.Error())
		}
		results = append(results, temp)
	}
	output, _ := json.Marshal(results)
	return shim.Success(output)
}
func addBook(stub shim.ChaincodeStubInterface, args []string, user User) peer.Response {
	// args[0]=ISBN,args[1]=Bookname,args[2]=author
	if len(args) != 3 {
		return shim.Error("Require 3 args for adding new book to platform")
	}
	key := args[0]
	_, err, ok := getStateByte(stub, key)
	if ok == true {
		return shim.Error(err.Error())
	}
	book := Book{
		DocType:  BOOK,
		Weight:   0,
		ISBN:     args[0],
		BookName: args[1],
		Author:   args[2],
		Owner:    user.Email,
		Current:  user.Email,
		Cover:    "",
		AddedOn:  time.Now().Unix(),
	}
	BByte, _ := json.Marshal(book)
	stub.PutState(key, BByte)
	user.Current[args[0]] = time.Now().Unix()
	user.Owned[args[0]] = true
	UByte, _ := json.Marshal(user)
	stub.PutState(user.Email, UByte)
	return shim.Success(nil)
}
func registerUser(stub shim.ChaincodeStubInterface, args []string) peer.Response {
	// args[0]=email , args[1]= name , args[2]= room_no , args[3]=phone_no args[4]=password (encrypted)
	if len(args) != 5 {
		return shim.Error("provide 5 args To register new user")
	}
	key := args[0]
	_, err, ok := getStateByte(stub, key)
	if ok != false {
		return shim.Error(err.Error())
	}
	user := User{
		DocType: USER,
		Email:   args[0],
		Name:    args[1],
		RoomNo:  args[2],
		PhoneNo: args[3],
		Owned:   make(map[string]bool),
		Current: make(map[string]int64),
	}
	UByte, _ := json.Marshal(user)
	err = stub.PutState(key, UByte)
	if err != nil {
		return shim.Error(err.Error())
	}
	login := Login{
		Email:    user.Email,
		Password: args[4],
	}
	LByte, _ := json.Marshal(login)
	err = stub.PutState(getLoginKey(stub, user.Email), LByte)
	if err != nil {
		return shim.Error(err.Error())
	}
	return shim.Success(nil)
}


package com.example.t1.HomeAllcurrentbookModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Book {

    @SerializedName("book_details")
    @Expose
    private BookDetails bookDetails;
    @SerializedName("got_on")
    @Expose
    private Integer gotOn;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Book() {
    }

    /**
     * 
     * @param gotOn
     * @param bookDetails
     */
    public Book(BookDetails bookDetails, Integer gotOn) {
        super();
        this.bookDetails = bookDetails;
        this.gotOn = gotOn;
    }

    public BookDetails getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(BookDetails bookDetails) {
        this.bookDetails = bookDetails;
    }

    public Integer getGotOn() {
        return gotOn;
    }

    public void setGotOn(Integer gotOn) {
        this.gotOn = gotOn;
    }

}

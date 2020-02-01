package com.example.t1;

public class mybookitem {

    String bookname;
    String Author;

    public mybookitem(String bookname, String author, String isbn) {
        this.bookname = bookname;
        Author = author;
        Isbn = isbn;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getIsbn() {
        return Isbn;
    }

    public void setIsbn(String isbn) {
        Isbn = isbn;
    }

    String Isbn;
}

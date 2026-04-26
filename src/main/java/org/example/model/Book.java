package org.example.model;
//Model Book class

public class Book {

    private String book_id;
    private String isbn_no;
    private String title;
    private int no_of_copies;
    private int availability;
    private double price;

    public Book(String book_id, String isbn_no, String title, int no_of_copies, int availability, double price) {
        this.book_id = book_id;
        this.isbn_no = isbn_no;
        this.title = title;
        this.no_of_copies = no_of_copies;
        this.availability = availability;
        this.price = price;
    }

    public String getBookId() {
        return book_id;
    }

    public void setBookId(String book_id) {
        this.book_id = book_id;
    }

    public String getIsbnNo() {
        return isbn_no;
    }

    public void setIsbnNo(String isbn_no) {
        this.isbn_no = isbn_no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNoOfCopies() {
        return no_of_copies;
    }

    public void setNoOfCopies(int no_of_copies) {
        this.no_of_copies = no_of_copies;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

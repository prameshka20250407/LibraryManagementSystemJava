package org.example.model;

public class Transaction {

    private String date;
    private String book_id;
    private String student_id;
    private int type;

    public Transaction(String date, String book_id, String student_id, int type) {
        this.date = date;
        this.book_id = book_id;
        this.student_id = student_id;
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBookId() {
        return book_id;
    }

    public void setBookId(String book_id) {
        this.book_id = book_id;
    }

    public String getStudentId() {
        return student_id;
    }

    public void setStudentId(String student_id) {
        this.student_id = student_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

package org.example.model;

public class Student {

    private String student_id;
    private String first_name;

    public Student(String student_id, String first_name) {
        this.student_id = student_id;
        this.first_name = first_name;
    }

    public String getStudentId() {
        return student_id;
    }

    public void setStudentId(String student_id) {
        this.student_id = student_id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }
}

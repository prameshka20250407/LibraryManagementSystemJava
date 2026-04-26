package org.example.util;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationTest {

    @org.junit.jupiter.api.Test
    public void isValidStudentId() {
        String studentId = "12345678";
        boolean expectedValue = true;
        boolean actualValue = Validation.isValidStudentId(studentId);
        assertEquals(expectedValue, actualValue);
    }

    @org.junit.jupiter.api.Test
    public void isInvalidStudentId() {
        String studentId = "BK00";
        boolean expectedValue = false;
        boolean actualValue = Validation.isValidStudentId(studentId);
        assertEquals(expectedValue, actualValue);
    }

    @org.junit.jupiter.api.Test
    public void isValidISBN() {
        String isbn = "9780306406157";
        boolean expectedValue = true;
        boolean actualValue = Validation.isValidISBN(isbn);
        assertEquals(expectedValue, actualValue);
    }

    @org.junit.jupiter.api.Test
    public void isInvalidISBN() {
        String isbn = "9780306406158";
        boolean expectedValue = false;
        boolean actualValue = Validation.isValidISBN(isbn);
        assertEquals(expectedValue, actualValue);
    }

    @org.junit.jupiter.api.Test
    public void isValidCopies() {
        int copies = 2;
        boolean expectedValue = true;
        boolean actualValue = Validation.isValidCopies(copies);
        assertEquals(expectedValue, actualValue);
    }

    @org.junit.jupiter.api.Test
    public void isInvalidCopies() {
        int copies = 5;
        boolean expectedValue = false;
        boolean actualValue = Validation.isValidCopies(copies);
        assertEquals(expectedValue, actualValue);
    }

    @org.junit.jupiter.api.Test
    public void isValidAvailability() {
        int availability = 1;
        int copies = 2;
        boolean expectedValue = true;
        boolean actualValue = Validation.isValidAvailability(availability, copies);
        assertEquals(expectedValue, actualValue);
    }

    @org.junit.jupiter.api.Test
    public void isInvalidAvailability() {
        int availability = 3;
        int copies = 2;
        boolean expectedValue = false;
        boolean actualValue = Validation.isValidAvailability(availability, copies);
        assertEquals(expectedValue, actualValue);
    }

    @org.junit.jupiter.api.Test
    public void isValidDate() {
        String date = "12/05/2025";
        boolean expectedValue = true;
        boolean actualValue = Validation.isValidDate(date);
        assertEquals(expectedValue, actualValue);
    }

    @org.junit.jupiter.api.Test
    public void isInvalidDate() {
        String date = "32/01/2025";
        boolean expectedValue = false;
        boolean actualValue = Validation.isValidDate(date);
        assertEquals(expectedValue, actualValue);
    }
}
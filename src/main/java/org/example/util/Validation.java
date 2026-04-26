package org.example.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validation {

    public static boolean isValidStudentId(String id) {
        return id != null && id.matches("\\d{8}");
    }

    public static boolean isValidISBN(String isbn) {
        if (isbn == null || !isbn.matches("\\d{13}"))
            return false;

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Character.getNumericValue(isbn.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }

        int check = (10 - (sum % 10)) % 10;
        int lastDigit = Character.getNumericValue(isbn.charAt(12));

        return check == lastDigit;
    }

    public static boolean isValidCopies(int copies) {
        return copies >= 0 && copies <= 2;
    }

    public static boolean isValidAvailability(int availability, int copies) {
        return availability >= 0 && availability <= copies;
    }

    public static boolean isValidDate(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return true;
        }
        catch (DateTimeParseException e) {
            return false;
        }
    }
}

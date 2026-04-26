package org.example.manager;

import org.example.model.Book;
import org.example.util.FileHandler;
import org.example.util.Validation;

import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private List<Book> books = new ArrayList<>();
    private List<String> invalidRows = new ArrayList<>();
    private List<String[]> originalRows = new ArrayList<>();
    private List<Integer> invalidIndexes = new ArrayList<>();

    public void loadBooks(String filePath) {
        books.clear();
        invalidRows.clear();
        invalidIndexes.clear();

        List<String[]> rows = FileHandler.readCSV(filePath);
        int index = 0;
        originalRows = rows;

        boolean isFirstRow = true;
        for (String[] row : rows) {
            if (isFirstRow) {
                isFirstRow = false;
                index++;
                continue;
            }
            if (row.length != 6) {
                invalidRows.add(String.join(",", row) + " -> Invalid column count");
                invalidIndexes.add(index);
                index++;
                continue;
            }
            try {
                String book_id = row[0];
                String isbn = row[1];
                String title = row[2];
                int copies = Integer.parseInt(row[3]);
                int availability = Integer.parseInt(row[4]);
                double price = Double.parseDouble(row[5]);

                if (!Validation.isValidISBN(isbn)) {
                    invalidRows.add(String.join(",", row) + " -> Invalid ISBN");
                    invalidIndexes.add(index);
                    index++;
                    continue;
                }
                if (!Validation.isValidCopies(copies)) {
                    invalidRows.add(String.join(",", row) + " -> Invalid copies");
                    invalidIndexes.add(index);
                    index++;
                    continue;
                }
                if (!Validation.isValidAvailability(availability, copies)) {
                    invalidRows.add(String.join(",", row) + " -> Invalid availability");
                    invalidIndexes.add(index);
                    index++;
                    continue;
                }
                books.add(new Book(row[0], isbn, row[2], copies, availability, Double.parseDouble(row[5])));
            }
            catch (Exception e) {
                invalidRows.add(String.join(",", row) + " -> Parsing error");
                invalidIndexes.add(index);
            }
            index++;
        }
    }

    public List<String> getInvalidRows() {
        return invalidRows;
    }

    public List<String[]> getOriginalRows() {
        return originalRows;
    }

    public List<Integer> getInvalidIndexes() {
        return invalidIndexes;
    }

    public List<Book> getBooks() {
        return books;
    }

    public double calculateAveragePrice() {
        if (books.isEmpty()) return 0;
        double sum = 0;
        for (Book b : books) {
            sum += b.getPrice();
        }
        return sum / books.size();
    }

    public List<Book> searchByTitle(String keyword) {
        List<Book> result = new ArrayList<>();
        if (keyword == null || keyword.isEmpty())
            return result;

        keyword = keyword.trim().toLowerCase();
        boolean wildcard = keyword.endsWith("*");
        String search = keyword.replace("*", "");

        for (Book b : books) {
            String title = b.getTitle().toLowerCase();
            if (wildcard) {
                if (title.startsWith(search)) {
                    result.add(b);
                }
            }
            else {
                if (title.equals(search)) {
                    result.add(b);
                }
            }
        }
        return result;
    }
}

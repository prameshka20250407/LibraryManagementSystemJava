package org.example.manager;

import org.example.model.Transaction;
import org.example.util.FileHandler;
import org.example.util.Validation;

import java.util.ArrayList;
import java.util.List;

public class TransactionManager {

    private List<Transaction> transactions = new ArrayList<>();
    private List<String[]> originalRows = new ArrayList<>();
    private List<String> invalidRows = new ArrayList<>();
    private List<Integer> invalidIndexes = new ArrayList<>();

    public void loadTransactions(String filePath) {
        transactions.clear();
        originalRows.clear();
        invalidRows.clear();
        invalidIndexes.clear();

        List<String[]> rows = FileHandler.readCSV(filePath);
        originalRows = rows;

        int index = 0;
        boolean isFirstRow = true;

        for (String[] row : rows) {
            if (isFirstRow) {
                isFirstRow = false;
                index++;
                continue;
            }
            if (row.length < 4) {
                invalidRows.add(String.join(",", row) + " -> Invalid column count");
                invalidIndexes.add(index);
                index++;
                continue;
            }
            try {
                String date = row[0];
                String bookId = row[1];
                String studentId = row[2];
                int type = Integer.parseInt(row[3]);

                if (!Validation.isValidDate(date)) {
                    invalidRows.add(String.join(",", row) + " -> Invalid date");
                    invalidIndexes.add(index);
                    index++;
                    continue;
                }

                if (!Validation.isValidStudentId(studentId)) {
                    invalidRows.add(String.join(",", row) + " -> Invalid student ID");
                    invalidIndexes.add(index);
                    index++;
                    continue;
                }

                if (type != 1 && type != 2) {
                    invalidRows.add(String.join(",", row) + " -> Invalid type");
                    invalidIndexes.add(index);
                    index++;
                    continue;
                }
                transactions.add(new Transaction(date, bookId, studentId, type));
            }
            catch (Exception e) {
                invalidRows.add(String.join(",", row) + " -> Parsing error");
                invalidIndexes.add(index);
            }
            index++;
        }
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Transaction> getTransactionsByDate(String date) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getDate().equals(date) && t.getType() == 1) {
                result.add(t);
            }
        }
        return result;
    }

    public List<String[]> getOriginalRows() {
        return originalRows;
    }

    public List<String> getInvalidRows() {
        return invalidRows;
    }

    public List<Integer> getInvalidIndexes() {
        return invalidIndexes;
    }
}

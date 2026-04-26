package org.example.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.manager.*;
import org.example.util.*;

public class ImportController {

    @FXML private Button importFilesBtn;
    @FXML private Button revalidateRecordsBtn;
    @FXML private Button exitBtn;
    @FXML private Button backBtn;
    @FXML private TextArea invalidArea;
    @FXML private TextField correctionField;
    @FXML private Label status;

    private BookManager bm = new BookManager();
    private StudentManager sm = new StudentManager();
    private TransactionManager tm = new TransactionManager();

    private final String BOOK_PATH = "C:\\Users\\Prameshka Fernando\\PycharmProjects\\LibraryManagementSystemPython\\book.csv";
    private final String STUDENT_PATH = "C:\\Users\\Prameshka Fernando\\PycharmProjects\\LibraryManagementSystemPython\\student.csv";
    private final String TRANSACTION_PATH = "C:\\Users\\Prameshka Fernando\\PycharmProjects\\LibraryManagementSystemPython\\transactions.csv";

    @FXML
    protected void onImportFilesBtnClick() {
        bm.loadBooks(BOOK_PATH);
        sm.loadStudents(STUDENT_PATH);
        tm.loadTransactions(TRANSACTION_PATH);

        invalidArea.clear();
        for (String row : bm.getInvalidRows()) {
            invalidArea.appendText("[BOOK] " + row + "\n");
        }
        for (String row : sm.getInvalidRows()) {
            invalidArea.appendText("[STUDENT] " + row + "\n");
        }
        for (String row : tm.getInvalidRows()) {
            invalidArea.appendText("[TRANSACTION] " + row + "\n");
        }
        int totalErrors = bm.getInvalidRows().size() + sm.getInvalidRows().size() + tm.getInvalidRows().size();
        int totalRecords = bm.getOriginalRows().size() + sm.getOriginalRows().size() + tm.getOriginalRows().size();
        status.setText("No of records: " + totalRecords + " | No of Errors: " + totalErrors);
    }

    @FXML
    protected void OnRevalidateRecordsBtnClick() {
        String corrected = correctionField.getText();
        if (corrected.isEmpty()) {
            invalidArea.appendText("\nEnter a record first\n");
            return;
        }
        String[] row = corrected.split(",");
        try {
            if (row.length == 2) {
                if (!Validation.isValidStudentId(row[0])) {
                    invalidArea.appendText("\nStill invalid student record\n");
                    return;
                }
                if (sm.getInvalidIndexes().isEmpty()) {
                    invalidArea.appendText("\nNo invalid student records\n");
                    return;
                }
                int index = sm.getInvalidIndexes().get(0);
                sm.getOriginalRows().set(index, row);

                FileHandler.writeCSV(STUDENT_PATH, sm.getOriginalRows());
                sm.loadStudents(STUDENT_PATH);
            }
            else if (row.length == 6) {
                int copies = Integer.parseInt(row[3]);
                int availability = Integer.parseInt(row[4]);

                if (!Validation.isValidISBN(row[1]) ||
                        !Validation.isValidCopies(copies) ||
                        !Validation.isValidAvailability(availability, copies)) {

                    invalidArea.appendText("\nStill invalid book record\n");
                    return;
                }
                if (bm.getInvalidIndexes().isEmpty()) {
                    invalidArea.appendText("\nNo invalid book records\n");
                    return;
                }
                int index = bm.getInvalidIndexes().get(0);
                bm.getOriginalRows().set(index, row);

                FileHandler.writeCSV(BOOK_PATH, bm.getOriginalRows());
                bm.loadBooks(BOOK_PATH);
            }
            else if (row.length == 4) {
                if (!Validation.isValidDate(row[0]) ||
                        !Validation.isValidStudentId(row[2])) {

                    invalidArea.appendText("\nStill invalid transaction record\n");
                    return;
                }
                if (tm.getInvalidIndexes().isEmpty()) {
                    invalidArea.appendText("\nNo invalid transaction records\n");
                    return;
                }
                int index = tm.getInvalidIndexes().get(0);
                tm.getOriginalRows().set(index, row);

                FileHandler.writeCSV(TRANSACTION_PATH, tm.getOriginalRows());
                tm.loadTransactions(TRANSACTION_PATH);
            }
            else {
                invalidArea.appendText("\nUnknown record format\n");
                return;
            }
            invalidArea.clear();
            for (String rowData : bm.getInvalidRows()) {
                invalidArea.appendText("[BOOK] " + rowData + "\n");
            }
            for (String rowData : sm.getInvalidRows()) {
                invalidArea.appendText("[STUDENT] " + rowData + "\n");
            }
            for (String rowData : tm.getInvalidRows()) {
                invalidArea.appendText("[TRANSACTION] " + rowData + "\n");
            }
            int totalErrors = bm.getInvalidRows().size() + sm.getInvalidRows().size() + tm.getInvalidRows().size();
            int totalRecords = bm.getOriginalRows().size() + sm.getOriginalRows().size() + tm.getOriginalRows().size();
            status.setText("No of records: " + totalRecords + " | No of Errors: " + totalErrors);
            correctionField.clear();
        }
        catch (Exception e) {
            invalidArea.appendText("\nError in correction\n");
        }
    }

    @FXML
    protected void onBackBtnClick() throws Exception {
        loadScreen("main_menu.fxml", backBtn);
    }

    @FXML
    protected void loadScreen(String fxml, Button btn) throws Exception {
        javafx.stage.Stage stage = (javafx.stage.Stage) btn.getScene().getWindow();
        javafx.fxml.FXMLLoader loader =
                new javafx.fxml.FXMLLoader(getClass().getResource("/org/example/" + fxml));
        stage.setScene(new javafx.scene.Scene(loader.load()));
    }

    @FXML
    protected void onExitBtnClick() {
        System.exit(0);
    }
}

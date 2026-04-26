package org.example.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.manager.TransactionManager;
import org.example.util.Validation;

public class ReportController {

    @FXML private TextField dateField;
    @FXML private TextArea resultArea;
    @FXML private Label status;
    @FXML private Button generateBtn;
    @FXML private Button backBtn;
    @FXML private Button exitBtn;

    private TransactionManager tm = new TransactionManager();
    private final String TRANSACTION_PATH = "C:\\Users\\Prameshka Fernando\\PycharmProjects\\LibraryManagementSystemPython\\transactions.csv";

    @FXML
    public void initialize() {
        tm.loadTransactions(TRANSACTION_PATH);
    }

    @FXML
    protected void onGenerateBtnClick() {
        resultArea.clear();
        String date = dateField.getText();
        if (date.isEmpty()) {
            status.setText("Enter a date first");
            return;
        }
        if (!Validation.isValidDate(date)) {
            status.setText("Invalid date format (dd/MM/yyyy)");
            return;
        }
        var results = tm.getTransactionsByDate(date);
        if (results.isEmpty()) {
            resultArea.setText("No records found for this date");
        }
        else {
            for (var t : results) {
                resultArea.appendText(
                        t.getDate() + " | " +
                                t.getBookId() + " | " +
                                t.getStudentId() + "\n"
                );
            }
        }
        status.setText("Records found: " + results.size());
    }

    @FXML
    private void onBackBtnClick() throws Exception {
        loadScreen("main_menu.fxml", backBtn);
    }

    @FXML
    protected void onExitBtnClick() {
        System.exit(0);
    }

    protected void loadScreen(String fxml, Button btn) throws Exception {
        javafx.stage.Stage stage = (javafx.stage.Stage) btn.getScene().getWindow();
        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/org/example/" + fxml));
        stage.setScene(new javafx.scene.Scene(loader.load()));
    }
}

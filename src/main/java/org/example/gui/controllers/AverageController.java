package org.example.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.manager.BookManager;

public class AverageController {

    @FXML private Label resultLabel;
    @FXML private TextArea calculateTextArea;
    @FXML private Button backBtn;

    private BookManager bm = new BookManager();
    private final String BOOK_PATH = "C:\\Users\\Prameshka Fernando\\PycharmProjects\\LibraryManagementSystemPython\\book.csv";

    @FXML
    protected void initialize() {
        bm.loadBooks(BOOK_PATH);
    }

    @FXML
    protected void onCalculateBtnClick(ActionEvent actionEvent) {
        double avg = bm.calculateAveragePrice();
        StringBuilder output = new StringBuilder();
        for (var book : bm.getBooks()) {
            output.append(book.getTitle()).append(" - ").append(String.format("%.2f", book.getPrice())).append("\n");
        }
        output.append("\nAverage Price: ").append(String.format("%.2f", avg));
        resultLabel.setText("Average Price: " + String.format("%.2f", avg));
        calculateTextArea.setText(output.toString());
    }

    @FXML
    protected void handleBack() throws Exception {
        loadScreen("main_menu.fxml", backBtn);
    }

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

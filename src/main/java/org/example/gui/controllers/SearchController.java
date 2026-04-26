package org.example.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.manager.BookManager;
import org.example.model.Book;
import java.util.List;

public class SearchController {

    @FXML private TextField searchField;
    @FXML private TextArea resultArea;
    @FXML private Label status;
    @FXML private Button searchBtn;
    @FXML private Button exportBtn;
    @FXML private Button backBtn;
    @FXML private Button exitBtn;

    private BookManager bm = new BookManager();
    private List<Book> lastResults;

    private final String BOOK_PATH = "C:\\Users\\Prameshka Fernando\\PycharmProjects\\LibraryManagementSystemPython\\book.csv";

    @FXML
    protected void initialize() {
        bm.loadBooks(BOOK_PATH);
    }

    @FXML
    protected void onSearchBtnClick() {
        resultArea.clear();
        String keyword = searchField.getText();
        if (keyword.isEmpty()) {
            status.setText("Enter a title to search");
            return;
        }
        lastResults = bm.searchByTitle(keyword);
        if (lastResults.isEmpty()) {
            resultArea.setText("No matching books found");
        }
        else {
            for (Book b : lastResults) {
                resultArea.appendText(
                        b.getTitle() + " | Available: " + b.getAvailability() + " | Price: " + String.format("%.2f", b.getPrice()) + "\n");
            }
        }
        status.setText("Results: " + lastResults.size());
    }

    @FXML
    protected void onExportBtnClick() {
        if (lastResults == null || lastResults.isEmpty()) {
            status.setText("No results to export");
            return;
        }
        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter("search_results.txt", true))) {
            for (Book b : lastResults) {
                writer.write(b.getTitle() + "," + b.getAvailability() + "," + b.getPrice());
                writer.newLine();
            }
            status.setText("Export successful (appended)");
        }
        catch (Exception e) {
            status.setText("Export failed");
        }
    }

    @FXML
    protected void onBackBtnClick() throws Exception {
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

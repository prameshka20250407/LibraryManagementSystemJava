package org.example.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class MainMenuController {

    @FXML
    private Button exitBtn;
    @FXML
    private Button importBtn;
    @FXML
    private Button searchBtn;
    @FXML
    private Button reportBtn;
    @FXML
    private Button avgBtn;

    protected void loadScreen(String fxml, Button btn) throws Exception {
        javafx.stage.Stage stage = (javafx.stage.Stage) btn.getScene().getWindow();
        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/org/example/" + fxml));
        stage.setScene(new javafx.scene.Scene(loader.load()));
    }

    @FXML
    protected void onSearchBtnClick(ActionEvent actionEvent) throws Exception {
        loadScreen("search_screen.fxml", searchBtn);
    }

    @FXML
    protected void onAvgBtnClick(ActionEvent actionEvent) throws Exception {
        loadScreen("average_screen.fxml", avgBtn);
    }

    @FXML
    protected void onReportBtnClick(ActionEvent actionEvent) throws Exception {
        loadScreen("report_screen.fxml", reportBtn);
    }

    @FXML
    protected void onImportBtnClick(ActionEvent actionEvent) throws Exception {
        loadScreen("import_screen.fxml", importBtn);
    }

    @FXML
    protected void onExitBtnClick(ActionEvent actionEvent) {
        System.exit(0);
    }

}

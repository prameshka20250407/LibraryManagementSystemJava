package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LibraryManagementSystem extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/main_menu.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Library Management System");
        stage.show();
    }
}

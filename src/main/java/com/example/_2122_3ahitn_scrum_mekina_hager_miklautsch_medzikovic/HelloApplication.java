package com.example._2122_3ahitn_scrum_mekina_hager_miklautsch_medzikovic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("startmenue.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Startmenü");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
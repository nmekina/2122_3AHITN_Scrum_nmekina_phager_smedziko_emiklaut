package com.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ControllerStartMenue {

    @FXML
    private Button btn_startgame;

    @FXML
    private Button btn_settings;

    @FXML
    private Button btn_skinshop;

    @FXML
    private Button btn_scoreboard;

    @FXML
    void press_scoreboard(ActionEvent event) throws IOException {
        change_scene("scoreboard");
    }

    @FXML
    void press_settings(ActionEvent event) throws IOException {
        change_scene("settingsmenue");
    }

    @FXML
    void press_skinshop(ActionEvent event) throws IOException {
        change_scene("skinshop");
    }

    @FXML
    void press_startgame(ActionEvent event) throws IOException {
        change_scene("username");
    }

    public void change_scene(String game) throws IOException {
        Stage stage = new Stage();

        Stage stageclose = (Stage) btn_startgame.getScene().getWindow();
        stageclose.close();

        final FXMLLoader fxmlLoader = new FXMLLoader();
        URL u = HelloApplication.class.getResource(game + ".fxml");
        System.out.println(new File("").getAbsolutePath());
        System.out.println(u);
        fxmlLoader.setLocation(u);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(game);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}

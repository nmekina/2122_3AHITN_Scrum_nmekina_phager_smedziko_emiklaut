package com.example.Controller;

import Model.ChangeScene;
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
        ChangeScene.change_scene("scoreboard", btn_startgame);
    }

    @FXML
    void press_settings(ActionEvent event) throws IOException {
        ChangeScene.change_scene("settingsmenue", btn_startgame);
    }

    @FXML
    void press_skinshop(ActionEvent event) throws IOException {
        ChangeScene.change_scene("skinshop", btn_startgame);
    }

    @FXML
    void press_startgame(ActionEvent event) throws IOException {
        ChangeScene.change_scene("gamemodes", btn_startgame);
    }

}

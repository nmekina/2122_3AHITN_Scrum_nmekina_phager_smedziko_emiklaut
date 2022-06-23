package com.example.Controller;

import Model.ChangeScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextFormatter;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author nmekina
 * Controller für das Startmenü
 */
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
    private Button btn_skillbutton;

    /**
     * @author nmekina
     * wechelt auf das Scoreboard
     */
    @FXML
    void press_scoreboard(ActionEvent event) throws IOException {
        ChangeScene.change_scene("scoreboard", btn_startgame);
    }

    /**
     * @author nmekina
     * wechelt auf den Skillshop
     */
    @FXML
    void press_skillshop(ActionEvent event) throws IOException {
        ChangeScene.change_scene("skillshop",btn_skillbutton);
    }

    /**
     * @author nmekina
     * wechelt auf die Settings
     */
    @FXML
    void press_settings(ActionEvent event) throws IOException {
        ChangeScene.change_scene("settingsmenue", btn_startgame);
    }

    /**
     * @author nmekina
     * wechelt auf den Skinshop
     */
    @FXML
    void press_skinshop(ActionEvent event) throws IOException {
        ChangeScene.change_scene("skinshop", btn_startgame);
    }

    /**
     * @author nmekina
     * wechselt auf die Startansicht des Spiels
     */
    @FXML
    void press_startgame(ActionEvent event) throws IOException {
        ChangeScene.change_scene("gamemodes", btn_startgame);
    }

}

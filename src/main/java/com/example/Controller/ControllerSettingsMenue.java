package com.example.Controller;

import Model.ChangeScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;

import java.io.IOException;

public class ControllerSettingsMenue {

    @FXML
    private Button btn_save;

    @FXML
    private RadioButton choice_highscore_on;

    @FXML
    private RadioButton choice_highscore_off;

    @FXML
    private RadioButton choice_music_on;

    @FXML
    private RadioButton choice_music_off;

    @FXML
    private ChoiceBox<?> choice_difficulty;

    @FXML
    private ChoiceBox<?> choice_image;

    @FXML
    private ChoiceBox<?> choice_music;

    @FXML
    private Button btn_credits;

    @FXML
    void btn_credits_press(ActionEvent event) throws IOException {
        //alles speichern

        ChangeScene.change_scene("credits", btn_save);
    }

    @FXML
    void btn_save_press(ActionEvent event) throws IOException {
        //alles speichernll

        ChangeScene.change_scene("startmenue", btn_save);
    }

}

package com.example.Controller;

import Model.ChangeScene;
import Model.Difficulty;
import Model.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

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
    private ChoiceBox<String> choice_difficulty;

    @FXML
    private TextField txt_image;

    @FXML
    private ChoiceBox<String> choice_music;

    @FXML
    private Button btn_credits;

    Settings settings = new Settings();

    public void initialize() {
        choice_difficulty.setValue(Settings.getDifficulty());
        choice_difficulty.getItems().add("beginner");
        choice_difficulty.getItems().add("medium");
        choice_difficulty.getItems().add("hard");
        choice_difficulty.getItems().add("hardcore");

        choice_music.setValue(settings.getMusicplaying());
        choice_music.getItems().add("Tetris");
        choice_music.getItems().add("Super Mario");
        choice_music.getItems().add("Subway Surfer");

        txt_image.setText(settings.getImage());

        choice_music_on.setSelected(settings.getMusiconoff());
        choice_music_off.setSelected(!settings.getMusiconoff());

        choice_highscore_on.setSelected(settings.getHighscoreonoff());
        choice_highscore_off.setSelected(!settings.getHighscoreonoff());
    }

    @FXML
    void btn_credits_press(ActionEvent event) throws IOException {
        //alles speichern
        settings.setDifficulty(choice_difficulty.getValue());
        settings.setImage(txt_image.getText());
        settings.setMusicplaying(choice_music.getValue());
        if (choice_highscore_on.isSelected()) {
            settings.setHighscoreonoff(true);
        } else {
            settings.setHighscoreonoff(false);
        }
        if (choice_music_on.isSelected()) {
            settings.setMusiconoff(true);
        } else {
            settings.setMusiconoff(false);
        }

        ChangeScene.change_scene("credits", btn_save);
    }

    @FXML
    void choice_highscore_press_off(ActionEvent event) {
        choice_highscore_on.setSelected(false);
    }

    @FXML
    void choice_highscore_press_on(ActionEvent event) {
        choice_highscore_off.setSelected(false);
    }

    @FXML
    void choice_music_press_off(ActionEvent event) {
        choice_music_on.setSelected(false);
    }

    @FXML
    void choice_music_press_on(ActionEvent event) {
        choice_music_off.setSelected(false);
    }

    @FXML
    void btn_save_press(ActionEvent event) throws IOException {
        //alles speichernll
        settings.setDifficulty(choice_difficulty.getValue());
        settings.setImage(txt_image.getText());
        settings.setMusicplaying(choice_music.getValue());
        if (choice_highscore_on.isSelected()) {
            settings.setHighscoreonoff(true);
        } else {
            settings.setHighscoreonoff(false);
        }
        if (choice_music_on.isSelected()) {
            settings.setMusiconoff(true);
        } else {
            settings.setMusiconoff(false);
        }

        ChangeScene.change_scene("startmenue", btn_save);
    }
}

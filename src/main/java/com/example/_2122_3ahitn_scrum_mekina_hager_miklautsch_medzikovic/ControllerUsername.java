package com.example._2122_3ahitn_scrum_mekina_hager_miklautsch_medzikovic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ControllerUsername {

    @FXML
    private Button btn_play;

    @FXML
    private TextField textfield_username;

    @FXML
    private Button btn_back;

    @FXML
    void press_back(ActionEvent event) throws IOException {
        ChangeScene.change_scene("startmenue", btn_back);
    }

    @FXML
    void press_play(ActionEvent event) throws IOException {
        //Name speichern in Klasse
        //Player player = new Player();
        //player.setNickname(textfield_username.getText());
        ChangeScene.change_scene("hello-view", btn_play);
    }

}

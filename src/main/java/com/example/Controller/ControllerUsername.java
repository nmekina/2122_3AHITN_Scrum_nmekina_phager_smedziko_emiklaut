package com.example.Controller;

import Model.ChangeScene;
import Model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ControllerUsername {

    @FXML
    private TextField txt_username;

    @FXML
    private Button btn_ok;

    @FXML
    void pressed_btn_ok(ActionEvent event) throws IOException {
        Player player = new Player(txt_username.getText());
        ChangeScene.change_scene("spielfeld", btn_ok);
    }

}
package com.example.Controller;

import Model.ChangeScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ControllerSkinShop {

    @FXML
    private Button btn_semir;

    @FXML
    private Button btn_elias;

    @FXML
    private Button btn_nico;

    @FXML
    private Button btn_rester;

    @FXML
    private Button btn_hager;

    @FXML
    void press_btn(ActionEvent event) throws IOException {
        ChangeScene.change_scene("startmenue",btn_semir);
    }

    public void initialize(){

    }

}

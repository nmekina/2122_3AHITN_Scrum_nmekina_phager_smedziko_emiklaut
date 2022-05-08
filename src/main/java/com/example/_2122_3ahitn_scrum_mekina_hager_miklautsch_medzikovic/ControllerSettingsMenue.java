package com.example._2122_3ahitn_scrum_mekina_hager_miklautsch_medzikovic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ControllerSettingsMenue {

    @FXML
    private Button btn_back;

    @FXML
    void press_back(ActionEvent event) throws IOException {
        ChangeScene.change_scene("startmenue", btn_back);
    }

}

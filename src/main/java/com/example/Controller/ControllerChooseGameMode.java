package com.example.Controller;

import Model.ChangeScene;
import Model.ObstacleGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class ControllerChooseGameMode {

        @FXML
        private Button jump_the_pet;

        @FXML
        private Label lb_normal;

        @FXML
        private Label lb_pet;

        @FXML
        private Button normal;

        @FXML
        private ImageView present_normal;

        @FXML
        private ImageView present_pet;

        public void initialize(){
                normal.setText("Normal Mode");
                jump_the_pet.setText("Pet Mode");
                lb_normal.setText("Normal");
                lb_pet.setText("Pet");
                present_normal.setImage(new Image(String.valueOf(ControllerChooseGameMode.class.getResource("preview_normal.jpg")),816,395,false,false));
                present_pet.setImage(new Image(String.valueOf(ControllerChooseGameMode.class.getResource("preview_pet.png")),816,395,false,false));
        }

        @FXML
        void normal_mode(ActionEvent event) throws IOException {
                ObstacleGenerator.setPetMode(false);
                ChangeScene.change_scene("JumpAndRun", normal);
        }

        @FXML
        void pet_mode(ActionEvent event) throws IOException {
                ObstacleGenerator.setPetMode(true);
                ChangeScene.change_scene("JumpAndRun", jump_the_pet);

        }


    }

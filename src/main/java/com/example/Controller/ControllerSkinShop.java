package com.example.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

import Model.ChangeScene;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerSkinShop {

public void start(Stage displayScreen) throws Exception {
    ListView<String> listview = new ListView<String>();
    listview.getItems().add("src/main/resources/com/example/Controller/pictures/EliasMiklautsch.jpeg");
    listview.getItems().add("src/main/resources/com/example/Controller/pictures/ManuelRester.jpeg");
    listview.getItems().add("src/main/resources/com/example/Controller/pictures/PhilippHager.jpeg");
    listview.getItems().add("src/main/resources/com/example/Controller/pictures/SemirMedzikovic.jpeg");
    listview.getItems().add("src/main/resources/com/example/Controller/pictures/NicoMekina.jpeg");

    listview.setOrientation(Orientation.HORIZONTAL);
    HBox hbox = new HBox(listview);
    Scene scene = new Scene(hbox, 400, 200);
    displayScreen.setScene(scene);
    displayScreen.show();

}


        @FXML
        private ListView<?> listviewskins;

        @FXML
        private ImageView imageview;

        @FXML
        private Button btn_choose;

        @FXML
        private Label skinname;

        @FXML
        private Label anzahlgames;

        @FXML
        void btn_pressed(ActionEvent event) throws IOException {
        ChangeScene.change_scene("startmenue",btn_choose);
        }

    }




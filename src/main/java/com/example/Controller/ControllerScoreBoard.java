package com.example.Controller;

import Model.Skin;
import Model.sortGames;
import Model.sortHighscore;
import Model.sortName;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ControllerScoreBoard {

    @FXML
    private ListView<HBox> list_score = new ListView<>();

    public void initialize() throws IOException {
        sortHighscore sortHighsore = new sortHighscore();
        sortHighsore.sort();
    }

    @FXML
    void sortbygames(ActionEvent event) throws IOException {
        sortGames sortGames = new sortGames();
        sortGames.sort();
    }

    @FXML
    void sortbyhighscore(ActionEvent event) throws IOException {
        sortHighscore sortHighsore = new sortHighscore();
        sortHighsore.sort();
    }

    @FXML
    void sortbyname(ActionEvent event) throws IOException {
        sortName sortName = new sortName();
        sortName.sort();
    }

    public void print(ArrayList<String[]> scoreboard) {

        for (String[] strings : scoreboard) {
            Label label = new Label("name: " + strings[0] + " | highscore: " + strings[1] + " | games: " + strings[2] + " | coins: " + strings[3]);
            System.out.println(label.getText());
            HBox h = new HBox();
            h.getChildren().add(label);
            h.setAlignment(Pos.CENTER);

            list_score.getItems().add(h);
        }
    }
}
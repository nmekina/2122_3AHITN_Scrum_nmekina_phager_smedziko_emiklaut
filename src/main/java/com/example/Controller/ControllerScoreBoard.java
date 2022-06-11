package com.example.Controller;

import Model.sortGames;
import Model.sortHighscore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
    private static ListView<HBox> list_score;

    sortGames sortGames = new sortGames(list_score);
    sortHighscore sortHighsore = new sortHighscore(list_score);


    public void initialize() throws IOException {
        ActionEvent actionEvent = new ActionEvent();
        sortbyhighscore(actionEvent);
    }

    @FXML
    void sortbygames(ActionEvent event) throws IOException {
        sortGames.sort();
    }

    @FXML
    void sortbyhighscore(ActionEvent event) throws IOException {
        sortHighsore.sort();
    }

    @FXML
    void sortbyname(ActionEvent event) throws InterruptedException, IOException {

    }

    public static void print(ArrayList<String[]> scoreboard) {
        for (int i = 0; i < scoreboard.size(); i++) {
            Label label = new Label("name: " + scoreboard.get(i)[0] + " | highscore: " + scoreboard.get(i)[1] + " | games: " + scoreboard.get(i)[2]);
            HBox h = new HBox();
            h.getChildren().add(label);
            h.setAlignment(Pos.CENTER);

            list_score.getItems().add(h);
        }
    }
}
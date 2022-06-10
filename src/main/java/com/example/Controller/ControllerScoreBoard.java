package com.example.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private ListView<HBox> list_score;

    ArrayList<String[]> alleSpieler = new ArrayList<>();
    ArrayList<String[]> bestenliste = new ArrayList<>();
    int highscore = 0;
    int indexfromhighscore;


    public void initialize() throws IOException {
        addSpieler();
        sort();

        for (int i = 0; i < bestenliste.size(); i++) {
                Label label = new Label("Name: " + bestenliste.get(i)[0] + " | highscore: " + bestenliste.get(i)[1] + " | games: " + bestenliste.get(i)[2]);
                HBox h = new HBox();
                h.getChildren().add(label);
                h.setAlignment(Pos.CENTER);

                list_score.getItems().add(h);
        }
    }

    private void sort() {
        while (alleSpieler.size() > 0) {
            for (int i = 0; i < alleSpieler.size(); i++) {
                if (Integer.parseInt(alleSpieler.get(i)[1]) > highscore) {
                    highscore = Integer.parseInt(alleSpieler.get(i)[1]);
                    indexfromhighscore = i;
                }
            }
            bestenliste.add(alleSpieler.get(indexfromhighscore));
            indexfromhighscore = 0;
            highscore = 0;
            alleSpieler.removeAll(bestenliste);
        }
    }

    private void addSpieler() throws IOException {
        File file = new File("src/main/java/Model/scores.json");
        String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
        JSONArray json = new JSONArray(content);

        for (int z = 0; z < json.length(); z++) {
            JSONObject getplayer = json.getJSONObject(z);
            String[] einSpieler = new String[3];
            einSpieler[0] = getplayer.getString("name");
            einSpieler[1] = String.valueOf(getplayer.getInt("highscore"));
            einSpieler[2] = String.valueOf(getplayer.getInt("games"));
            alleSpieler.add(einSpieler);
        }
    }
}
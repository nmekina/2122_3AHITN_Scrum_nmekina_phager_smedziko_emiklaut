package com.example.Controller;

import Model.ChangeScene;
import Model.getHighcorefromPlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
    private ListView<HBox> list_score = new ListView<>();

    @FXML
    private Button back_btn;

    private ArrayList<String[]> allplayers = new ArrayList<>();
    private ArrayList<String[]> scoreboard = new ArrayList<>();
    private int highscore = 0;
    private int mostgames = 0;
    private String highestname;
    private int coins = 0;
    private int indexfromplayer = 0;

    public void initialize() throws IOException {
        sortHighscore();
    }

    @FXML
    void sortbygames(ActionEvent event) throws IOException {
        sortGames();
    }

    @FXML
    void sortbyhighscore(ActionEvent event) throws IOException {
        sortHighscore();
    }

    @FXML
    void sortbyname(ActionEvent event) throws IOException {
        sortName();
    }

    @FXML
    void sortbycoins(ActionEvent event) throws IOException {
        sortCoins();
    }

    public void sortGames() throws IOException {
        addSpieler();
        while (allplayers.size() > 0) {
            for (int i = 0; i < allplayers.size(); i++) {
                if (Integer.parseInt(allplayers.get(i)[2]) > mostgames) {
                    mostgames = Integer.parseInt(allplayers.get(i)[2]);
                    indexfromplayer = i;
                }
            }
            scoreboard.add(allplayers.get(indexfromplayer));
            indexfromplayer = 0;
            mostgames = 0;
            allplayers.removeAll(scoreboard);
        }
        print(scoreboard);
    }

    public void sortHighscore() throws IOException {
        addSpieler();
        while (allplayers.size() > 0) {
            for (int i = 0; i < allplayers.size(); i++) {
                if (Integer.parseInt(allplayers.get(i)[1]) > highscore) {
                    highscore = Integer.parseInt(allplayers.get(i)[1]);
                    indexfromplayer = i;
                }
            }
            scoreboard.add(allplayers.get(indexfromplayer));
            indexfromplayer = 0;
            highscore = 0;
            allplayers.removeAll(scoreboard);
        }

        print(scoreboard);
    }

    public void sortName() throws IOException {
        addSpieler();
        while (allplayers.size() > 0) {
            highestname = allplayers.get(0)[0];
            for (int i = 0; i < allplayers.size(); i++) {
                if (highestname.compareTo(allplayers.get(i)[0]) > 0) {
                    highestname = allplayers.get(i)[0];
                    indexfromplayer = i;
                }
            }
            scoreboard.add(allplayers.get(indexfromplayer));
            allplayers.removeAll(scoreboard);
            indexfromplayer = 0;
        }
        print(scoreboard);
    }


    public void sortCoins() throws IOException {
        addSpieler();
        while (allplayers.size() > 0) {
            for (int i = 0; i < allplayers.size(); i++) {
                if (Integer.parseInt(allplayers.get(i)[3]) > coins) {
                    coins = Integer.parseInt(allplayers.get(i)[3]);
                    indexfromplayer = i;
                }
            }
            scoreboard.add(allplayers.get(indexfromplayer));
            indexfromplayer = 0;
            coins = 0;
            allplayers.removeAll(scoreboard);
        }

        print(scoreboard);
    }

    public void print(ArrayList<String[]> scoreboard) {
        list_score.getItems().clear();
        for (String[] strings : scoreboard) {
            Label label = new Label("name: " + strings[0] + " | highscore: " + strings[1] + " | games: " + strings[2] + " | coins: " + strings[3]);
            System.out.println(label.getText());

            HBox h = new HBox();
            h.getChildren().add(label);
            h.setAlignment(Pos.CENTER);

            list_score.getItems().add(h);
        }
        scoreboard.clear();
    }

    public void addSpieler() throws IOException {
        File file = new File("src/main/java/Model/scores.json");
        String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
        JSONArray json = new JSONArray(content);

        for (int z = 0; z < json.length(); z++) {
            JSONObject getplayer = json.getJSONObject(z);
            String[] player = new String[4];
            player[0] = getplayer.getString("name");
            player[1] = String.valueOf(getplayer.getInt("highscore"));
            player[2] = String.valueOf(getplayer.getInt("games"));
            player[3] = String.valueOf(getplayer.getInt("coins"));
            allplayers.add(player);
        }
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        ChangeScene.change_scene("startmenue", back_btn);
    }
}
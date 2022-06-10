package com.example.Controller;

import Model.Player;
import Model.Skin;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public class ControllerScoreBoard {

    @FXML
    private ListView<HBox> list_score;

    String einSpieler[] = new String[3];
    ArrayList<String[]> alleSpieler = new ArrayList<>();


    public void initialize() throws IOException {
        addSpieler();
/*
        list_score.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HBox>() {
            @Override
            public void changed(ObservableValue<? extends HBox> observableValue, HBox hBox, HBox t1) {
                ImageView s = (ImageView) t1.getChildren().get(0);

                Iterator<Skin> i = Skin.getSkins().iterator();
                while(i.hasNext()){
                    Skin selected = i.next();
                    if(selected.getPicture() == s.getImage()){
                        level_skin.setText("Level: " +selected.getLevel());
                        skin_name.setText("Name: " + selected.getName());

                        skinselect = selected;
                    }
                }
            }
        });


 */

        /*
        for (int i = 0; i < skins.size(); i++) {
            ImageView skin = new ImageView(skins.get(i).getPicture());
            skin.fitHeightProperty();
            skin.fitWidthProperty();

            HBox h = new HBox();
            h.getChildren().add(skin);
            h.setAlignment(Pos.CENTER);

            list_skins.getItems().add(h);
        }

        list_score.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HBox>() {
            @Override
            public void changed(ObservableValue<? extends HBox> observableValue, HBox hBox, HBox t1) {
                ImageView s = (ImageView) t1.getChildren().get(0);
                show_skin.setImage(s.getImage());

                Iterator<Skin> i = Skin.getSkins().iterator();
                while(i.hasNext()){
                    Skin selected = i.next();
                    if(selected.getPicture() == s.getImage()){
                        level_skin.setText("Level: " +selected.getLevel());
                        skin_name.setText("Name: " + selected.getName());

                        skinselect = selected;
                    }
                }
            }
        });

         */

    }

    private void addSpieler() throws IOException {
        File file = new File("src/main/java/Model/scores.json");
        String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
        JSONArray json = new JSONArray(content);

        for (int z = 0; z < json.length(); z++) {
            JSONObject getplayer = json.getJSONObject(z);
            einSpieler[0] = getplayer.getString("name");
            einSpieler[1] = getplayer.getString("score");
            einSpieler[2] = getplayer.getString("games");
            alleSpieler.add(einSpieler);
        }
    }
}

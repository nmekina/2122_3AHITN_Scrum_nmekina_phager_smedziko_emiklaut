package com.example.Controller;

import Model.Player;
import Model.Skin;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class ControllerScoreBoard {

    @FXML
    private ListView<?> list_score;

/*

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


    public int getscore() throws IOException {
        File file = new File("src/main/java/Model/scores.json");
        String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
        JSONArray json = new JSONArray(content);
        int score = 0;

        for (int z = 0; z < json.length(); z++) {
            JSONObject getplayer = json.getJSONObject(z);
            String name = getplayer.getString("name");
            if (name.equals(Player.getName())) {
                score = (int) getplayer.get("highscore");
                System.out.println(score);
            }
        }
        return score;
    }
}

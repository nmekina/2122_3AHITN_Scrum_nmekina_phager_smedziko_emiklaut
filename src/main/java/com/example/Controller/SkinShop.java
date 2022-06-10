package com.example.Controller;

import Model.Player;
import Model.Skin;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import org.json.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class SkinShop {

    @FXML
    private ListView<HBox> list_skins;

    @FXML
    private ImageView show_skin;

    @FXML
    private Label skin_name;

    @FXML
    private Label level_skin;

    @FXML
    private Button select;

    ArrayList<Skin> skins = new ArrayList<Skin>();


    Skin semir = new Skin("Semir", 0, new Image(String.valueOf(Skin.class.getResource("Hager/PhilippHager.jpeg")),200,100,false,false));
    Skin elias = new Skin("Elias", 25, new Image(String.valueOf(Skin.class.getResource("Hager/PhilippHager.jpeg")),200,100,false,false));
    Skin nico = new Skin("Nico", 50, new Image(String.valueOf(Skin.class.getResource("Hager/PhilippHager.jpeg")),200,100,false,false));
    Skin rester = new Skin("Rester", 75, new Image(String.valueOf(Skin.class.getResource("Hager/PhilippHager.jpeg")),200,100,false,false));
    Skin hager = new Skin("Hager", 100, new Image(String.valueOf(Skin.class.getResource("Hager/PhilippHager.jpeg")),200,100,false,false));

    Skin skinselect;

    public void initialize() {
        skins.add(semir);
        skins.add(elias);
        skins.add(nico);
        skins.add(rester);
        skins.add(hager);

        level_skin.setText("Level: " + skins.get(0).getLevel());
        skin_name.setText("Name: " + skins.get(0).getName());
        show_skin.setImage(skins.get(0).getPicture());

        Skin.setList(list_skins);

        for (int i = 0; i < skins.size(); i++) {
            skins.get(i).addSkin();
        }

        list_skins.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HBox>() {
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

    }

    @FXML
    void select_skin(ActionEvent event) throws IOException {

        if (skinselect.getLevel() < getLevelfromPlayer()) {
            //Skin nicht gesperrt!
            Player.setSkin(skinselect);

            Stage stage = new Stage();

            Stage stageclose = (Stage) select.getScene().getWindow();
            stageclose.close();

            final FXMLLoader fxmlLoader = new FXMLLoader();
            URL u = HelloApplication.class.getResource("startmenue.fxml");

            fxmlLoader.setLocation(u);
            Scene scene = new Scene(fxmlLoader.load());

            scene.setOnKeyPressed(e-> {
                System.out.println(e.getCode().toString());

                if(!Player.getPressed() || Objects.equals(Player.getMovement(), "S")) {
                    Player.setMovement(e.getCode().toString());
                    Player.setPressed(true);
                }else {
                    System.out.println("Already jumping");
                }
            });

            stage.setTitle("startmenue");
            stage.setScene(scene);
            stage.show();

        } else {

            //Skin gesperrt!
            Alert aler = new Alert(Alert.AlertType.NONE);
            aler.setAlertType(Alert.AlertType.INFORMATION);
            aler.setTitle("sperre");
            aler.setHeaderText("Skin ist gesperrt!");
            aler.show();
        }


    }

    private int getLevelfromPlayer() throws IOException {
        File file = new File("src/main/java/Model/scores.json");
        String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
        JSONArray json = new JSONArray(content);
        int level = 0;

        for (int z = 0; z < json.length(); z++) {
            JSONObject getplayer = json.getJSONObject(z);
            String name = getplayer.getString("name");
            if (name.equals(Player.getName())) {
                level = (int) getplayer.get("games");
                System.out.println(level);
            }
        }
        return level;
    }

}

package com.example.Controller;

import Model.Player;
import Model.PlayerScore;
import Model.Skin;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.json.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
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

    @FXML
    private ImageView coins_img;

    @FXML
    private Label coins_required;

    @FXML
    private Pane coins_pane;


    ArrayList<Skin> skins = new ArrayList<Skin>();
    Skin skin;
    ArrayList<Image> images = new ArrayList<>();

    Skin skinselect;

    public void initialize() {
        images.add(new Image(String.valueOf(Skin.class.getResource("Medzikovic/SemirMedzikovic.jpeg")),200,100,false,false));
        images.add(new Image(String.valueOf(Skin.class.getResource("Medzikovic/SemirMedzikovicDuck.png")),200,100,false,false));
        images.add(new Image(String.valueOf(Skin.class.getResource("Medzikovic/SemirMedzikovicJump.png")),200,100,false,false));
        skin = new Skin("Semir", 0,0, images);
        skins.add(skin);
        images.clear();

        images.add(new Image(String.valueOf(Skin.class.getResource("Miklautsch/EliasMiklautsch.jpeg")),200,100,false,false));
        images.add(new Image(String.valueOf(Skin.class.getResource("Miklautsch/EliasMiklautschDuck.png")),200,100,false,false));
        images.add(new Image(String.valueOf(Skin.class.getResource("Miklautsch/EliasMiklautschJump.png")),200,100,false,false));
        skin = new Skin("Elias", 25,100, images);
        skins.add(skin);
        images.clear();

        images.add(new Image(String.valueOf(Skin.class.getResource("Mekina/NicoMekina.jpeg")),200,100,false,false));
        images.add(new Image(String.valueOf(Skin.class.getResource("Mekina/NicoMekinaDuck.png")),200,100,false,false));
        images.add(new Image(String.valueOf(Skin.class.getResource("Mekina/NicoMekinaJump.png")),200,100,false,false));
        skin = new Skin("Nico", 50,200, images);
        skins.add(skin);
        images.clear();

        images.add(new Image(String.valueOf(Skin.class.getResource("Rester/ManuelRester.jpeg")),200,100,false,false));
        images.add(new Image(String.valueOf(Skin.class.getResource("Rester/ManuelResterDuck.png")),200,100,false,false));
        images.add(new Image(String.valueOf(Skin.class.getResource("Rester/ManuelResterJump.png")),200,100,false,false));
        skin = new Skin("Rester", 75,300, images);
        skins.add(skin);
        images.clear();

        images.add(new Image(String.valueOf(Skin.class.getResource("Hager/PhilippHager.jpeg")),200,100,false,false));
        images.add(new Image(String.valueOf(Skin.class.getResource("Hager/PhilippHagerDuck.png")),200,100,false,false));
        images.add(new Image(String.valueOf(Skin.class.getResource("Hager/PhilippHagerJump.png")),200,100,false,false));
        skin = new Skin("Hager", 100,400,images);
        skins.add(skin);
        images.clear();


        level_skin.setText("Level: " + skins.get(0).getLevel());
        skin_name.setText("Name: " + skins.get(0).getName());
        coins_required.setText("Coins: "+ skins.get(0).getCoins());
        show_skin.setImage(skins.get(0).getPicture().get(0));
        coins_img.setImage(new Image(String.valueOf(HelloController.class.getResource("coin.jpg"))));
        coins_img.fitWidthProperty().bind(coins_pane.widthProperty());
        coins_img.fitHeightProperty().bind(coins_pane.heightProperty());
        select.setText("Select");


        for (int i = 0; i < skins.size(); i++) {
            ImageView skin = new ImageView(skins.get(i).getPicture().get(0));
            skin.fitHeightProperty();
            skin.fitWidthProperty();

            HBox h = new HBox();
            h.getChildren().add(skin);
            h.setAlignment(Pos.CENTER);

            list_skins.getItems().add(h);
        }

        list_skins.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HBox>() {
            @Override
            public void changed(ObservableValue<? extends HBox> observableValue, HBox hBox, HBox t1) {
                ImageView s = (ImageView) t1.getChildren().get(0);
                show_skin.setImage(s.getImage());

                Iterator<Skin> i = Skin.getSkins().iterator();
                while(i.hasNext()){
                    Skin selected = i.next();
                    if(selected.getPicture().get(0) == s.getImage()){
                        level_skin.setText("Level: " +selected.getLevel());
                        skin_name.setText("Name: " + selected.getName());
                        coins_required.setText("Coins: " + selected.getCoins());

                        skinselect = selected;
                    }
                }
            }
        });

    }

    @FXML
    void select_skin(ActionEvent event) throws IOException {

        if (skinselect.getLevel() <= getDatafromPlayer("games") || skinselect.getCoins() <= getDatafromPlayer("coins")) {
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

    private int getDatafromPlayer(String choose) throws IOException {
        File file = new File("src/main/java/Model/scores.json");
        String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
        JSONArray json = new JSONArray(content);
        int data = 0;

        for (int z = 0; z < json.length(); z++) {
            JSONObject getplayer = json.getJSONObject(z);
            String name = getplayer.getString("name");
            if (name.equals(Player.getName())) {
                System.out.println(getplayer.getInt("coins"));
                data = getplayer.getInt(choose);
            }
        }
        return data;
    }

    //TODO Coins in dem File bei Kauf aktualisieren, verfügbar lassen
  /*  private void setCoinsfromPlayer() throws IOException {
        File file = new File("src/main/java/Model/scores.json");
        Gson g = new Gson();
        FileReader fr = new FileReader(file);
        FileWriter fw = new FileWriter(file);

        Type listType = new TypeToken<ArrayList<PlayerScore>>() {
        }.getType();
        ArrayList<PlayerScore> player = g.fromJson(fr, listType);

        Iterator<PlayerScore> i = player.iterator();

        int count = 0;
        while(i.hasNext()) {
            PlayerScore p = i.next();
            String name = p.getName();
            if (name.equals(Player.getName())) {
                int newcoins = p.getCoins() - skinselect.getCoins();
                player.get(count).setCoins(newcoins);
                g.toJson(player, fw);
                fw.close();
            }
            count++;
        }
    }

   */
    }


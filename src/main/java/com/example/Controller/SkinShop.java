package com.example.Controller;

import Model.Player;
import Model.Skin;
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
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/**
 * Skinshop - Klasse:
 * ist im Startmenü eine Funktion, wenn man einen anderen Charakter zum Spielen wählen möchte
 * es gibt verschiedene "Skins", die man wählen kann, wenn man genug Coins gesammelt hat.
 * Der Skin hat keine Abhängigkeit von der Spielschwierigkeit, der Standardskin ist seit Beginn dabei.
 */
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
    ArrayList<Image> images_semir = new ArrayList<>();
    ArrayList<Image> images_elias = new ArrayList<>();
    ArrayList<Image> images_nico = new ArrayList<>();
    ArrayList<Image> images_rester = new ArrayList<>();
    ArrayList<Image> images_hager = new ArrayList<>();

    Skin skinselect;
    int position = 0;

    /**
     * @author: nmekina, smedziko, emiklaut
     * es werden verschiedene Bilder geladen, die dann bei einem Befehl ("springen", "ducken")
     * geändert werden und im Spiel so angezeigt werden
     */
    public void initialize() {
        images_semir.add(new Image(String.valueOf(Skin.class.getResource("Medzikovic/SemirMedzikovic.jpeg")), 200, 100, false, false));
        images_semir.add(new Image(String.valueOf(Skin.class.getResource("Medzikovic/SemirMedzikovicDuck.png")), 200, 100, false, false));
        images_semir.add(new Image(String.valueOf(Skin.class.getResource("Medzikovic/SemirMedzikovicJump.png")), 200, 100, false, false));
        skin = new Skin("Semir", 0, 0, images_semir);
        skins.add(skin);
        skinselect = skin;

        images_elias.add(new Image(String.valueOf(Skin.class.getResource("Miklautsch/EliasMiklautsch.jpeg")), 200, 100, false, false));
        images_elias.add(new Image(String.valueOf(Skin.class.getResource("Miklautsch/EliasMiklautschDuck.png")), 200, 100, false, false));
        images_elias.add(new Image(String.valueOf(Skin.class.getResource("Miklautsch/EliasMiklautschJump.png")), 200, 100, false, false));
        skin = new Skin("Elias", 25, 100, images_elias);
        skins.add(skin);

        images_nico.add(new Image(String.valueOf(Skin.class.getResource("Mekina/NicoMekina.jpeg")), 200, 100, false, false));
        images_nico.add(new Image(String.valueOf(Skin.class.getResource("Mekina/NicoMekinaDuck.png")), 200, 100, false, false));
        images_nico.add(new Image(String.valueOf(Skin.class.getResource("Mekina/NicoMekinaJump.png")), 200, 100, false, false));
        skin = new Skin("Nico", 50, 200, images_nico);
        skins.add(skin);

        images_rester.add(new Image(String.valueOf(Skin.class.getResource("Rester/ManuelRester.jpeg")), 200, 100, false, false));
        images_rester.add(new Image(String.valueOf(Skin.class.getResource("Rester/ManuelResterDuck.png")), 200, 100, false, false));
        images_rester.add(new Image(String.valueOf(Skin.class.getResource("Rester/ManuelResterJump.png")), 200, 100, false, false));
        skin = new Skin("Rester", 75, 300, images_rester);
        skins.add(skin);

        images_hager.add(new Image(String.valueOf(Skin.class.getResource("Hager/PhilippHager.jpeg")), 200, 100, false, false));
        images_hager.add(new Image(String.valueOf(Skin.class.getResource("Hager/PhilippHagerDuck.png")), 200, 100, false, false));
        images_hager.add(new Image(String.valueOf(Skin.class.getResource("Hager/PhilippHagerJump.png")), 200, 100, false, false));
        skin = new Skin("Hager", 100, 400, images_hager);
        skins.add(skin);


        level_skin.setText("Level: " + skins.get(0).getLevel());
        skin_name.setText("Name: " + skins.get(0).getName());
        coins_required.setText("Coins: " + skins.get(0).getCoins());
        show_skin.setImage(skins.get(0).getPicture().get(0));
        coins_img.setImage(new Image(String.valueOf(HelloController.class.getResource("coin.png"))));
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
                while (i.hasNext()) {
                    Skin selected = i.next();
                    if (selected.getPicture().get(0) == s.getImage()) {
                        level_skin.setText("Level: " + selected.getLevel());
                        skin_name.setText("Name: " + selected.getName());
                        coins_required.setText("Coins: " + selected.getCoins());

                        skinselect = selected;
                        position = 0;
                    }
                }
            }
        });

    }

    /**
     * @author: nmekina, smedziko
     * in dieser Methode wird der Skin gewählt,
     * gleichzeitig wird geprüft, ob man genug Coins für die Freischaltung des
     * gewünschten Skins gesammelt hat. Falls dies der Fall ist, dann kann man einen
     * anderen Skin statt dem Standard - Skin wählen
     */
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

            scene.setOnKeyPressed(e -> {
                System.out.println(e.getCode().toString());

                if (!Player.getPressed() || Objects.equals(Player.getMovement(), "S")) {
                    Player.setMovement(e.getCode().toString());
                    Player.setPressed(true);
                } else {
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

    /**
     * @author: nmekina
     * die Daten vom Player werden aus dem JSON-File geladen
     * und die Coins, der Score,... werden dann in der Spielumgebung
     * angezeigt
     */
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

    /**
     * @author: smedziko
     * prüft die Position des aktuellen Bildes, wenn der linke Pfeil gedrückt wird,
     * dann wird das Bild -1 gerechnet und es wird ein Bild nach hinten bewegt
     * und dann angezeigt, ausgenommen man befindet sich am ersten Bild.
     */
    @FXML
    void skin_change_left(ActionEvent event) {
        if (position > 0) {
            position--;
            show_skin.setImage(skinselect.getPicture().get(position));
        }
    }

    /**
     * @author: smedziko
     * prüft die Position des aktuellen Bildes, wenn der rechte Pfeil gedrückt wird,
     * dann wird das Bild +1 gerechnet und es wird ein Bild nach vorne bewegt
     * und dann angezeigt, ausgenommen man befindet sich am letzten Bild.
     */
    @FXML
    void skin_change_right(ActionEvent event) {
        if (position < 2) {
            position++;
            show_skin.setImage(skinselect.getPicture().get(position));
        }
    }

}


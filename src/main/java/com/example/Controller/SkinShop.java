package com.example.Controller;

import Model.Player;
import Model.Skin;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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

    Skin semir = new Skin("Semir", 0, new Image(("SemirMedzikovic.jpeg"),200,100,false,false));
    Skin elias = new Skin("Elias", 25, new Image("EliasMiklautsch.jpeg",200,100,false,false));
    Skin nico = new Skin("Nico", 50, new Image("NicoMekina.jpeg",200,100,false,false));
    Skin rester = new Skin("Rester", 75, new Image("ManuelRester.jpeg",200,100,false,false));
    Skin hager = new Skin("Hager", 100, new Image("PhilippHager.jpeg",200,100,false,false));

    Skin skinselect;

    public void initialize() {

        level_skin.setText("Level: " + semir.getLevel());
        skin_name.setText("Name: " + semir.getName());
        show_skin.setImage(semir.getPicture());

        Skin.setList(list_skins);
        semir.addSkin();
        elias.addSkin();
        nico.addSkin();
        rester.addSkin();
        hager.addSkin();

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

        stage.setTitle("Skinshop");
        stage.setScene(scene);
        stage.show();
    }

}

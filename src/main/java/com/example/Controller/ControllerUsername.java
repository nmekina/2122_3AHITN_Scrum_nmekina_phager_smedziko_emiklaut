package com.example.Controller;

import Model.ChangeScene;
import Model.ImagesLoader;
import Model.Player;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * @author nmekina
 * Controller für username.fxml
 */
public class ControllerUsername {

    @FXML
    private TextField txt_username;

    @FXML
    private Button btn_ok;

    @FXML
    private ChoiceBox<String> choicebox_username;

    ArrayList<String> allplayers = new ArrayList<>();

    /**
     * @author nmekina
     * setzt die Werte der choicebox mit allen Namen die bisher gespielt haben
     * bei Auswählen des Namens, wird der ausgewählte Name in das Textfeld geschrieben
     */
    public void initialize() throws IOException {
        addSpieler();
        for (String oneplayer : allplayers) {
            choicebox_username.getItems().add(oneplayer);
        }

        ImagesLoader il = new ImagesLoader();
        Thread t = new Thread(il);
        t.start();

        choicebox_username.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                txt_username.setText(choicebox_username.getItems().get(Integer.parseInt(t1.toString())));
            }
        });
    }

    /**
     * @author nmekina
     * setzt den Namen des Spielers auf das, was im Textfeld steht
     * wechselt danach in das Startmenü
     */
    @FXML
    void pressed_btn_ok(ActionEvent event) throws IOException {
        new Player(txt_username.getText());
        ChangeScene.change_scene("startmenue", btn_ok);
    }

    /**
     * @author nmekina
     * addet alle Spielernamen in eine Arraylist aus dem scores.json File hinzu
     */
    public void addSpieler() throws IOException {
        File file = new File("src/main/java/Model/scores.json");
        String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
        JSONArray json = new JSONArray(content);

        for (int z = 0; z < json.length(); z++) {
            JSONObject getplayer = json.getJSONObject(z);
            String player = getplayer.getString("name");
            allplayers.add(player);
        }
    }

}
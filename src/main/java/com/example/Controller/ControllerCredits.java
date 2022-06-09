package com.example.Controller;

import Model.ChangeScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.zip.DataFormatException;

public class ControllerCredits {

    @FXML
    private Button btn_exit;

    @FXML
    void press_btn_exit(ActionEvent event) throws IOException, URISyntaxException {
        URL url = new URL("https://trello.com/b/Dnd2o4mc/miklautschhagermekinamedzikovic");

        Runtime runtime = Runtime.getRuntime();
        runtime.exec("start chrome " + url);

        ChangeScene.change_scene("startmenue", btn_exit);
    }


}

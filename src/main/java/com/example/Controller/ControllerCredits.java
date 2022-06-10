package com.example.Controller;

import Model.ChangeScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.Desktop;

public class ControllerCredits {

    @FXML
    private Button btn_exit;
    @FXML
    private Hyperlink hyperlinkTest;

    @FXML
    void press_btn_exit(ActionEvent event) throws IOException, URISyntaxException {
        ChangeScene.change_scene("startmenue", btn_exit);
    }

    @FXML
    private void handleHyperlinkTest(ActionEvent event) throws URISyntaxException, IOException {
        //Hyperlink link = new Hyperlink("www.htl-steyr.ac.at");
        //link.setText("www.htl-steyr.ac.at");
        Desktop.getDesktop().browse(new URI("https://www.htl-steyr.ac.at/"));


    }


}

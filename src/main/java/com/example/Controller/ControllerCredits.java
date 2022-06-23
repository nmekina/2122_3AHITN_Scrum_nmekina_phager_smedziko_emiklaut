package com.example.Controller;

import Model.ChangeScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ControllerCredits {

    @FXML
    private Button btn_exit;

    @FXML
    private WebView webviewMiklautsch;

    @FXML
    private WebView webviewMekina;

    @FXML
    private WebView webviewMedzikovic;

    @FXML
    private WebView webviewHager;


    public void initialize() throws IOException {
       webviewMiklautsch.getEngine().load("https://www.instagram.com/eliassentbygod/");
       webviewMekina.getEngine().load("https://www.instagram.com/nico.meki/");
       webviewMedzikovic.getEngine().load("https://www.instagram.com/semkomedzikovic/");
       webviewHager.getEngine().load("https://www.instagram.com/p.hagr");


    }

    @FXML
    void press_btn_exit(ActionEvent event) throws IOException, URISyntaxException {
        ChangeScene.change_scene("startmenue", btn_exit);
    }


}

package com.example.Controller;

import Model.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("startmenue.fxml"));
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

        stage.setTitle("Jump And Run");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
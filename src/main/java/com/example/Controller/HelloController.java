package com.example.Controller;

import Model.*;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class HelloController {

    //TODO
    @FXML
    private ProgressBar boss_health;

    @FXML
    private AnchorPane scene;

    @FXML
    private Label gameOver;

    @FXML
    private Button back;

    @FXML
    private Button restart;

    @FXML
    private Circle heart3;

    @FXML
    private Circle heart2;

    @FXML
    private Circle heart1;

    @FXML
    private Label sc;

    @FXML
    private Label highscore;

    @FXML
    private Pane paneplayer;


    Circle[] hearts;
    static boolean restarted = false;


    //Player Jump Power einstellen können (Skill Updates)

    AnimationTimer up = new AnimationTimer() {
        @Override
        public void handle(long now) {

            if (Objects.equals(Player.getMovement(), "S")) {
                paneplayer.setPrefHeight(28);
                paneplayer.setLayoutY(280);
                Player.setPressed(false);
            } else if (paneplayer.getPrefHeight() == (28) && !Objects.equals(Player.getMovement(), "S")) {
                paneplayer.setPrefHeight(72);
                paneplayer.setLayoutY(237);
            }

            //Bis er ganz oben ist
            if (Player.getPressed() && paneplayer.getLayoutY() > 97 && !Objects.equals(Player.getMovement(), "S")) {
                paneplayer.setLayoutY(paneplayer.getLayoutY() - 8);

            } else {
                Player.setPressed(false);
            }

            //kommt wieder runter
            if (!Player.getPressed() && paneplayer.getLayoutY() < 237) {
                paneplayer.setLayoutY(paneplayer.getLayoutY() + 8);
            }

            //Löscht movement key nach erfolgreicher bewegung
            if (paneplayer.getLayoutY() == 237 && paneplayer.getPrefHeight() == 72) {
                Player.setMovement("");
            }
        }


    };

    ObstacleGenerator og;
    ObstacleGenerator og2;
    ObstacleGenerator og3;

    Thread t;
    Thread t1;
    Thread t2;
    Thread t3;

    int pause = 2500;
    int pause2 = 2500;
    int pause3 = 2500;

    Score s;
    Player p;

    public void initialize() throws InterruptedException {


        highscore.setText("No Highscore yet");

        p = new Player();

        if(!Settings.getHighscoreonoff()){
            highscore.setVisible(false);
        }

        s = new Score(sc, highscore, p);

        p.setPlayer(paneplayer);

        heart1.setFill(Color.RED);
        heart2.setFill(Color.RED);
        heart3.setFill(Color.RED);

        hearts = new Circle[3];
        hearts[0] = heart1;
        hearts[1] = heart2;
        hearts[2] = heart3;


        ObstacleGenerator.setHearts(hearts);

        gameOver.setText("Game Over");
        back.setText("Back to Menue");
        restart.setText("Restart Game");

        gameOver.setVisible(false);
        restart.setVisible(false);
        back.setVisible(false);

        up.start();


        og = new ObstacleGenerator(scene, pause, up, Color.VIOLET);
        og2 = new ObstacleGenerator(scene, pause2, up, Color.RED);
        og3 = new ObstacleGenerator(scene, pause3, up, Color.BLACK);

        t = new Thread(og);
        t1 = new Thread(og2);
        t2 = new Thread(og3);
        t3 = new Thread(s);

        t.join();
        t1.join();
        t2.join();
        t3.join();

        t.start();
        t1.start();
        t2.start();
        t3.start();

        if(restarted){
            restartGame();
            restarted = false;
        }

    }

    @FXML
    void restartGame(){
        gameOver.setVisible(false);
        back.setVisible(false);
        restart.setVisible(false);
        ObstacleGenerator.setObstacleActive(false);
        ObstacleGenerator.resetDifficulty();

        s.start();

        highscore.setText(s.getHighscore());

        hearts[0].setFill(Color.RED);
        hearts[1].setFill(Color.RED);
        hearts[2].setFill(Color.RED);

        og.startGame();
        og2.startGame();
        og3.startGame();
    }

    @FXML
    void back_to_menue(ActionEvent event) throws IOException {
        restarted = true;
        og.stopGame();
        og2.stopGame();
        og3.stopGame();
        s.stop();
        ChangeScene.change_scene("startmenue",back);
    }


}
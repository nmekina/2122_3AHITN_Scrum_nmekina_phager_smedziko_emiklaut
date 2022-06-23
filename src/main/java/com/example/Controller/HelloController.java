package com.example.Controller;

import Model.*;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class HelloController {

    //TODO

    @FXML
    private ProgressBar boss_health;

    @FXML
    private Button back;

    @FXML
    private Label coins;

    @FXML
    private Pane coins_image;

    @FXML
    private Label gameOver;

    @FXML
    private Pane heart0;

    @FXML
    private Pane heart1;

    @FXML
    private Pane heart2;

    @FXML
    private HBox heart_box;

    @FXML
    private Label highscore;

    @FXML
    private Pane invi_pic;

    @FXML
    private Label invincible_state;

    @FXML
    private Label invincible_timer;

    @FXML
    private Pane paneplayer;

    @FXML
    private Button restart;

    @FXML
    private Label sc;

    @FXML
    private AnchorPane scene;

    @FXML
    private Label invincible_cooldown;


    Pane[] hearts;
    static boolean restarted = false;
    Player player = new Player();
    Invincibility i;

    //Player Jump Power einstellen können (Skill Updates)
    //TODO Lehrer fragen wie man API Images nicht zum lagen bringt

    AnimationTimer up = new AnimationTimer() {
        @Override
        public void handle(long now) {


            if(Objects.equals(Player.getMovement(),"I") && Player.getInvincibility()){
                Thread t = new Thread(i);
                t.start();
                Player.setInvincibility(false);
                Player.setMovement("");
            }

            if (Objects.equals(Player.getMovement(), "S")) {
                paneplayer.setPrefHeight(28);
                paneplayer.setLayoutY(280);
                player.change(1);
                Player.setPressed(false);
            } else if (paneplayer.getPrefHeight() == (28) && !Objects.equals(Player.getMovement(), "S")) {
                paneplayer.setPrefHeight(72);
                paneplayer.setLayoutY(237);
                player.change(2);
            }

            //Bis er ganz oben ist
            if (Player.getPressed() && Objects.equals(Player.getMovement(), "W") && paneplayer.getLayoutY() > Player.getJumpSkill() && !Objects.equals(Player.getMovement(), "S")) {
                paneplayer.setLayoutY(paneplayer.getLayoutY() - 8);
                player.change(1);
            } else {
                Player.setPressed(false);
            }

            //kommt wieder runter
            if (!Player.getPressed() && Objects.equals(Player.getMovement(), "W") && paneplayer.getLayoutY() < 237) {
                paneplayer.setLayoutY(paneplayer.getLayoutY() + 8);
                player.change(1);
            }

            //Löscht movement key nach erfolgreicher bewegung
            if (paneplayer.getLayoutY() == 237 && paneplayer.getPrefHeight() == 72) {
                Player.setMovement("");
                player.change(0);
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

    Score s;
    Player p;

    public void initialize() throws InterruptedException, IOException {
        startMusic startMusic = new startMusic();
        startMusic.start();


        ImageView iv = new ImageView();
        iv.setImage(new Image(String.valueOf(HelloController.class.getResource("coin.jpg"))));
        iv.fitWidthProperty().bind(coins_image.widthProperty());
        iv.fitHeightProperty().bind(coins_image.heightProperty());
        coins_image.getChildren().add(iv);

        iv.setImage(new Image(String.valueOf(HelloController.class.getResource("invincible.png"))));
        iv.fitHeightProperty().bind(invi_pic.widthProperty());
        iv.fitHeightProperty().bind(invi_pic.heightProperty());
        invi_pic.getChildren().add(iv);

        i = new Invincibility(50,200,invincible_timer,invincible_cooldown);
        highscore.setText("No Highscore yet");
        invincible_state.setText(Player.getInvincibility()+"");


        Coin c = new Coin(coins,restarted);

        p = new Player();

        if(!Settings.getHighscoreonoff()){
            highscore.setVisible(false);
        }

        s = new Score(sc, highscore, p);

        p.setPlayer(paneplayer);

        hearts = new Pane[3];
        hearts[0] = heart0;
        hearts[1] = heart1;
        hearts[2] = heart2;


        ObstacleGenerator.setHearts(hearts,heart_box);


        gameOver.setText("Game Over");
        back.setText("Back to Menue");
        restart.setText("Restart Game");

        gameOver.setVisible(false);
        restart.setVisible(false);
        back.setVisible(false);

        up.start();

        og = new ObstacleGenerator(scene, up, Color.VIOLET, c);
        og2 = new ObstacleGenerator(scene, up, Color.RED, c);
        og3 = new ObstacleGenerator(scene, up, Color.BLACK, c);

        if(restarted){
            restartGame();
            restarted = false;
        }


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

    }

    @FXML
    void restartGame(){
        gameOver.setVisible(false);
        restart.setVisible(false);
        back.setVisible(false);
        ObstacleGenerator.setObstacleActive(false);
        ObstacleGenerator.resetDifficulty();

        s.start();

        ObstacleGenerator.setHearts(hearts,heart_box);

        highscore.setText(s.getHighscore());

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
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
    private Button back;

    @FXML
    private ProgressBar boss_health;

    @FXML
    private Label break_cooldown;

    @FXML
    private ImageView break_img;

    @FXML
    private Label break_state;

    @FXML
    private Label break_timer;

    @FXML
    private Label coins;

    @FXML
    private ImageView coins_img;

    @FXML
    private Pane coins_pane;

    @FXML
    private ImageView dou_img;

    @FXML
    private Label double_cooldown;

    @FXML
    private Label double_state;

    @FXML
    private Label double_timer;

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
    private ImageView inv_image;

    @FXML
    private Pane invi_pic;

    @FXML
    private Label invincible_cooldown;

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
    private Pane break_pane;




    Pane[] hearts;
    static boolean restarted = false;
    Player player = new Player();
    CoolDown i;
    CoolDown d;
    CoolDown b;
    static final int DOUBLE = 2;
    static final int INVINCIBLE = 1;
    static final int BREAK = 3;

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

            if(Objects.equals(Player.getMovement(),"D") && Player.getDoublepoints()){
                Thread t = new Thread(d);
                t.start();
                Player.setDoublepoints(false);
                Player.setMovement("");
            }

            if(Objects.equals(Player.getMovement(),"B") && Player.getBreak_skill()){
                Thread t = new Thread(b);
                t.start();
                Player.setBreak_skill(false);
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

        getHighcorefromPlayer getHighcorefromPlayer = new getHighcorefromPlayer();

        coins_img.setImage(new Image(String.valueOf(HelloController.class.getResource("coin.png"))));
        coins_img.fitWidthProperty().bind(coins_pane.widthProperty());
        coins_img.fitHeightProperty().bind(coins_pane.heightProperty());

        inv_image.setImage(new Image(String.valueOf(HelloController.class.getResource("invincible.png"))));
        inv_image.fitHeightProperty().bind(invi_pic.widthProperty());
        inv_image.fitHeightProperty().bind(invi_pic.heightProperty());

        break_img.setImage(new Image(String.valueOf(HelloController.class.getResource("break.jpg"))));
        break_img.fitHeightProperty().bind(break_pane.widthProperty());
        break_img.fitHeightProperty().bind(break_pane.heightProperty());

        i = new CoolDown(200,invincible_timer,invincible_cooldown,INVINCIBLE);
        d = new CoolDown(200, double_timer,double_cooldown,DOUBLE);
        b = new CoolDown(200,break_timer,break_cooldown,BREAK);
        highscore.setText("Highscore: " + getHighcorefromPlayer.getHighcore());
        invincible_state.setText(Player.getInvincibility()+"");
        break_state.setText(Player.getBreak_skill()+"");
        double_state.setText(Player.getDoublepoints()+"");


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

        highscore.setText("Highscore: " + s.getHighscore());

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
package com.example.Controller;

import Model.ChangeScene;
import Model.ObstacleGenerator;
import Model.Player;
import Model.Score;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

import java.io.IOException;
import java.util.Objects;

public class HelloController {
    @FXML
    private AnchorPane scene;

    @FXML
    private Node player;

    // TODO: Replace Ellipse mit Node in den die Ellipse bzw. das Image dargestellt wird

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

    Circle[] hearts;
    

    AnimationTimer up = new AnimationTimer() {
        @Override
        public void handle(long now) {

            if(Objects.equals(Player.getMovement(), "S")){
               // player.setRadiusY(17);
                player.setLayoutY(292);
                player.setPressedKey(false);
            }else if(player.getRadiusY() == 17 && !Objects.equals(Player.getMovement(), "S")){
               // player.setRadiusY(34);
                player.setLayoutY(273);
            }

            if (Player.getPressed() && player.getLayoutY() > 133 && !Objects.equals(Player.getMovement(), "S")) {
                player.setLayoutY(player.getLayoutY() - 8);

            }else {
                Player.setPressedKey(false);
            }

            if(!Player.getPressed() && player.getLayoutY() < 273){
                player.setLayoutY(player.getLayoutY() + 8);
            }

            if(player.getLayoutY() == 273){
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


    int pause = 2000;
    int pause2 = 3000;
    int pause3 = 3500;

    Score s;
    Player p;
    boolean back1 = false;

/*
    if(back1){
        try {
            restartGame(new ActionEvent());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 */

    public void initialize() throws InterruptedException {

        Player.setPlayer(player);  // TODO:
        highscore.setText("No Highscore yet");

        p = new Player("Mekina2");
        // TODO: p.setEllipse(player);

        s = new Score(sc, highscore,p);

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
        back.setVisible(false);
        restart.setVisible(false);

        up.start();


        og = new ObstacleGenerator(scene,pause,up);
        og2 = new ObstacleGenerator(scene,pause2,up);
        og3 = new ObstacleGenerator(scene,pause3,up);

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
    void restartGame(ActionEvent event) throws InterruptedException, IOException {

        gameOver.setVisible(false);
        back.setVisible(false);
        restart.setVisible(false);

        s.checkScores();
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
    void pressed_back(ActionEvent event) throws IOException {
        back1 = true;
        ChangeScene.change_scene("startmenue", back);
    }


}
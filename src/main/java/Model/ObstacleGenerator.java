package Model;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.sleep;

public class ObstacleGenerator implements Runnable {
    AnchorPane ap;
    static int heightbig = 72;
    static int heightsmall = 28;
    int x = 776;
    int y = 237;
    Shape activeObstacle;
    static Integer generateHeart = 100;
    static boolean beaten = false;
    static boolean happend = false;
    boolean once = false;
    boolean stop = false;
    Color color;
    boolean isHeart = false;
    static AtomicBoolean obstacleActive = new AtomicBoolean(false);
    int time;
    int generatenew = 160;
    AnimationTimer playerJump;
    static Circle[] hearts = new Circle[3];
    Score s = new Score();

    AnimationTimer enemies = new AnimationTimer() {
        @Override
        public void handle(long now) {

            //317 Obstacle Limit
            //Obstacle soll nicht gestartet werden bis letzte Obstacle 317 erreicht hat, es geht runter
            if(activeObstacle.getLayoutX() < generatenew && once){
                synchronized (obstacleActive){
                    obstacleActive.set(false);
                }
                once = false;
            }

            //TODO Verbessern mit Höhe die gesprungen werden soll = Boden - Höhe des Gegners
            if (activeObstacle.getLayoutX() > -36) {

                if (activeObstacle.getLayoutX() < 120 && activeObstacle.getLayoutX() > 30 && !beaten && !happend) {
                    if (Player.getPlayer().getLayoutY() < 205 || Player.getPlayer().getPrefHeight() == 28 && activeObstacle.getLayoutBounds().getHeight() == 28) {
                        // System.out.println("geschafft");
                    } else {
                     checkDamage();
                     happend = true;
                    }
                }
            }

            if (beaten) {
                Label l = (Label) ap.getChildren().get(5);
                Button restart = (Button) ap.getChildren().get(6);
                Button back = (Button) ap.getChildren().get(7);
                l.setVisible(true);
                restart.setVisible(true);
                back.setVisible(true);
                enemies.stop();
                playerJump.stop();
                s.stop();
                stopGame();
            }

            //Width + Layout + Höhe = Position
            //36 + 14 + 72 = Ende
            // 36 + 120 + 72 = Anfang
            // System.out.println(activeObstacle.getX()-difficulty);
            activeObstacle.setLayoutX(activeObstacle.getLayoutX()-8);
        }

    };

    public ObstacleGenerator(AnchorPane ap, int time, AnimationTimer playerJump, Color s) {
        this.ap = ap;
        this.time = time;
        this.playerJump = playerJump;
        this.color = s;
    }

    public void checkDamage(){

            for(int i = 0; i < hearts.length; i++) {
                if (hearts[i].getFill() == Color.RED) {
                    hearts[i].setFill(Color.WHITE);
                    i = hearts.length;
                }
            }
            if(hearts[2].getFill() == Color.WHITE){
                beaten = true;
            }
    }

    public void generateObstacle() {
        Rectangle obstacle = new Rectangle();
        int random = getRandomNumber(0, 2);
        System.out.println(random);
        if (random == 0) {
            obstacle = new Rectangle(36, heightbig);
        }

        if (random == 1) {
            obstacle = new Rectangle(36, heightsmall);
            obstacle.setY(251);
        }
        obstacle.setY(y);
        obstacle.setX(x);
        obstacle.setFill(Color.RED);
        activeObstacle = obstacle;
        ap.getChildren().add(obstacle);

        enemies.start();
    }

    public void stopObstacle() {
        enemies.stop();
        ap.getChildren().remove(activeObstacle);
        happend = false;
    }

    public static void setHearts(Circle[] heartcollection){
        hearts = heartcollection;
    }

    public void stopGame(){
        stop = true;
    }


    public void startGame(){
        playerJump.start();
        stopObstacle();
        stop = false;
        beaten = false;
        synchronized (this){
            notify();
        }
    }

    @Override
    public void run() {
        while (!stop) {
            if (!beaten) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        generateObstacle();
                    }
                });
                try {
                    sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(!beaten) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            stopObstacle();
                        }
                    });
                }
            }
            if(stop) {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        }


    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}


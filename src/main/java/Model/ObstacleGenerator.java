package Model;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static java.lang.Thread.sleep;

public class ObstacleGenerator implements Runnable {
    AnchorPane ap;
    int big = 72;
    int small = 28;
    int x = 776;
    int y = 237;
    Rectangle activeObstacle;
    int difficulty = 10;
    static boolean beaten = false;
    static boolean damage = false;
    boolean stop = false;
    int time;
    AnimationTimer playerJump;
    static Circle[] hearts = new Circle[3];
    Score s = new Score();

    AnimationTimer enemies = new AnimationTimer() {
        @Override
        public void handle(long now) {




            if (beaten) {
                Label l = (Label) ap.getChildren().get(6);
                Button restart = (Button) ap.getChildren().get(7);
                Button back = (Button) ap.getChildren().get(8);
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
            activeObstacle.setX(activeObstacle.getX() - difficulty);
        }

    };

    public ObstacleGenerator(AnchorPane ap, int time, AnimationTimer playerJump) {
        this.ap = ap;
        this.time = time;
        this.playerJump = playerJump;
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
            obstacle = new Rectangle(36, big);
        }

        if (random == 1) {
            obstacle = new Rectangle(36, small);
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
        damage = false;
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


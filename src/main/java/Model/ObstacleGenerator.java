package Model;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.sleep;

public class ObstacleGenerator implements Runnable {
    AnchorPane ap;
    static int heightbig = 72;
    static int heightsmall = 28;
    int x = 776;
    int y = 237;
    Obstacle activeObstacle;
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
    static Difficulty d = new Difficulty();
    static boolean petmode;
    ProgressBar progress;

    AnimationTimer enemies = new AnimationTimer() {
        @Override
        public void handle(long now) {

            //317 Obstacle Limit
            //Obstacle soll nicht gestartet werden bis letzte Obstacle 317 erreicht hat, es geht runter
            updateDifficulty();
            //Nie erreichbar bei lag - die Position
            if (activeObstacle.getRunning().getLayoutX() < generatenew && once) {
                synchronized (obstacleActive) {
                    obstacleActive.set(false);
                }
                once = false;
            }

            //TODO Verbessern mit Höhe die gesprungen werden soll = Boden - Höhe des Gegners
            if (activeObstacle.getRunning().getLayoutX() > -36) {

                if (activeObstacle.getRunning().getLayoutX() < 120 && activeObstacle.getRunning().getLayoutX() > 30 && !beaten && !happend) {
                    if (Player.getPlayer().getLayoutY() < 205 || Player.getPlayer().getPrefHeight() == 28 && activeObstacle.getRunning().getLayoutBounds().getHeight() == 28) {
                        // System.out.println("geschafft");
                    } else {
                        if (!isHeart) {
                            checkDamage();
                            happend = true;
                        } else {
                            heal();
                            happend = true;
                        }
                    }
                }
            }

            if (beaten) {
                try {
                    s.checkScores();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
            activeObstacle.getRunning().setLayoutX(activeObstacle.getRunning().getLayoutX() - d.base);
        }

    };


    public static void setObstacleActive(boolean state) {
        obstacleActive.set(state);
    }


    public ObstacleGenerator(AnchorPane ap, int time, AnimationTimer playerJump, Color s) {
        this.ap = ap;
        this.time = time;
        this.playerJump = playerJump;
        this.color = s;
        d.setDifficulty();
    }

    public void checkDamage() {

        for (int i = 0; i < hearts.length; i++) {
            if (hearts[i].getFill() == Color.RED) {
                hearts[i].setFill(Color.WHITE);
                i = hearts.length;
            }
        }
        if (hearts[2].getFill() == Color.WHITE) {
            beaten = true;
        }
    }

    public void heal() {
        for (int i = hearts.length - 1; i >= 0; i--) {
            if (hearts[i].getFill() == Color.WHITE) {
                hearts[i].setFill(Color.RED);
                i = 0;
            }
        }
    }

    public void generateObstacle() {
        once = true;
        Shape obstacle = new Ellipse();
        int random = getRandomNumber(0, 2);


        synchronized (generateHeart) {
            if (Score.score > generateHeart) {
                obstacle = new Ellipse(36, heightbig);
                generateHeart = generateHeart * 2;
                isHeart = true;
                obstacle.setLayoutY(y);
                obstacle.setLayoutX(x);
                activeObstacle = new Obstacle(obstacle,false);
                ap.getChildren().add(obstacle);
            }
        }
            if (!petmode && !isHeart) {

                    if (random == 0) {
                        obstacle = new Rectangle(36, heightbig);
                    }

                    if (random == 1) {
                        obstacle = new Rectangle(36, heightsmall);
                    }

                obstacle.setFill(color);
                obstacle.setLayoutY(y);
                obstacle.setLayoutX(x);
                activeObstacle = new Obstacle(obstacle, petmode);
                ap.getChildren().add(obstacle);

        } else if(!isHeart){
            Pane pets = new Pane();

            if(random == 0) {
                pets.setPrefHeight(heightbig);
            }else {
                pets.setPrefHeight(heightsmall);
            }

            pets.setPrefWidth(36);
            ImageView iv = new ImageView(loadImage());
            iv.fitWidthProperty().bind(pets.widthProperty());
            iv.fitHeightProperty().bind(pets.heightProperty());
            pets.getChildren().add(iv);
            pets.setLayoutY(y);
            pets.setLayoutX(x);

            activeObstacle = new Obstacle(pets,petmode);
            ap.getChildren().add(pets);
        }


        enemies.start();
    }

    public void updateDifficulty() {
        if (Score.score == d.countDifficulty) {
            synchronized (d) {
                d.base = d.base + d.chosen;

                if(!petmode) {
                    time = time - 50;
                }

                generatenew = generatenew + 10;
            }
            System.out.println(d.countDifficulty);
            d.countDifficulty = d.countDifficulty + 100;
        }
    }

    public static void resetDifficulty() {
        d.base = 8;
        d.countDifficulty = 100;
    }


    public void stopObstacle() {
        enemies.stop();
        ap.getChildren().remove(activeObstacle.getRunning());
        happend = false;
        isHeart = false;

        synchronized (obstacleActive) {
            if(once){
                obstacleActive.set(false);
                once = false;
            }
        }


    }

    public static void setHearts(Circle[] heartcollection) {
        hearts = heartcollection;
    }

    public void stopGame() {
        stop = true;
    }

    public static void setPetMode(boolean mode) {
        petmode = mode;
    }

    public Image loadImage(){
        int r = getRandomNumber(0,2);
        String url;
        int x = (int) (Math.random() * (100) + 0);
        int y = (int) (Math.random() * (100) + 0);
        System.out.println(r);
        if(r == 0){
            url = "http://placekitten.com/" + x + "/"+y;
        }else {
            url = "https://randomfox.ca/images/" + x + ".jpg";
        }

        Image i = new Image(url,200,200,false,false);
        if (i.isError()) {
            i = new Image(String.valueOf(Skin.class.getResource("loading_error.jpg")), 200, 200, false, false);
            System.out.println("Error loading image");
        }

        return i;
    }

    public void startGame() {
        playerJump.start();
        if(activeObstacle != null) {
            stopObstacle();
        }
        stop = false;
        beaten = false;
        synchronized (this) {
            notify();
        }
    }

    @Override
    public void run() {
        while (!stop) {
            if (!beaten) {
                synchronized (obstacleActive) {
                    if (!obstacleActive.get()) {
                        obstacleActive.set(true);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                generateObstacle();
                            }
                        });
                    }
                }
                try {
                    sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //TODO Nicht stoppen wenn nicht gestartet wurde
                if (!beaten && activeObstacle != null) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    stopObstacle();
                                }
                            });
                        }
                }

            if (stop) {
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


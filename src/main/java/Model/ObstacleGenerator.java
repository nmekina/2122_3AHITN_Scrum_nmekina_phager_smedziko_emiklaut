package Model;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.io.IOException;
import java.util.ArrayList;
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
    static Integer time = 2500;
    static Integer generatenew = 160;
    AnimationTimer playerJump;
    static ArrayList<Pane> hearts = new ArrayList<>();
    Score s = new Score();
    CoolDown i = new CoolDown();
    CoolDown double_p = new CoolDown();
    Coin c;
    static Difficulty d = new Difficulty();
    static boolean petmode;
    ProgressBar progress;
    static Integer coins = 0;

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
            //TODO Copy
            if (activeObstacle.getRunning().getLayoutX() > -36) {

                if (activeObstacle.getRunning().getLayoutX() < 116 && activeObstacle.getRunning().getLayoutX() > 20 && !beaten && !happend) {
                    if (Player.getPlayer().getLayoutY() < 168 || Player.getPlayer().getPrefHeight() == 28 && activeObstacle.getRunning().getLayoutBounds().getHeight() == 28) {
                    } else {
                        if (!isHeart) {
                            if(!CoolDown.inviactivated) {
                                checkDamage();
                            }
                            happend = true;
                        } else {
                            heal();
                            happend = true;
                        }

                    }
                }

                if(!happend && activeObstacle.getRunning().getLayoutX() < 20){
                    synchronized (coins) {
                        System.out.println("Aktuelle Coins: " + coins);
                        coins = coins + 5;
                        c.setCoins(coins);
                        happend = true;
                    }
                }

            }

            if (beaten) {
                try {
                    synchronized (coins) {
                        s.checkScores(coins);
                    }
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
                i.stop();
                double_p.stop();
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


    public ObstacleGenerator(AnchorPane ap, AnimationTimer playerJump, Color s, Coin c) {
        this.ap = ap;
        this.playerJump = playerJump;
        this.color = s;
        this.c = c;
        d.setDifficulty();
    }

    public void checkDamage() {
        for (int j = 0; j < hearts.size(); j++) {
            if (isRedHeart(hearts.get(j).getChildren())) {
                setHeart(hearts.get(j), "empty_heart.jpg");
                j = hearts.size();
            }
            if(!isRedHeart(hearts.get(hearts.size()-1).getChildren())){
                beaten = true;
            }
        }
    }

    public void heal() {

        for (int i = hearts.size()-1; i >=0; i--) {
            if (!isRedHeart(hearts.get(i).getChildren())) {
                setHeart(hearts.get(i), "heart.jpg");
                i = 0;
            }
        }
    }


    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void generateObstacle() {
        once = true;
        Shape obstacle = new Ellipse();
        int random = getRandomNumber(0, 2);


        synchronized (generateHeart) {
            if (Score.score > generateHeart) {
                Pane heart = new Pane();
                generateHeart = generateHeart * 2;
                isHeart = true;
                heart.setPrefHeight(heightbig);
                heart.setPrefWidth(60);
                heart.setLayoutY(y);
                heart.setLayoutX(x);
                heart = setHeart(heart,"heart.jpg");
                activeObstacle = new Obstacle(heart,true);
                ap.getChildren().add(heart);
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
            ImageView iv = new ImageView(ImagesLoader.getRandomImage());
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
                    synchronized (time) {
                        time = time - 50;
                    }
                }

                synchronized (generatenew) {
                    generatenew = generatenew + 10;
                }
            }
            d.countDifficulty = d.countDifficulty + 100;
        }
    }

    public static void resetDifficulty() {
        d.base = 8;
        d.countDifficulty = 100;
        ObstacleGenerator.generateHeart = 100;
        generatenew = 100;
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

    public static void setHearts(Pane[] heartcollection, HBox heart_box) {


        if(hearts.size() < 3) {
            for (int j = 0; j < heartcollection.length; j++) {
                hearts.add(j, heartcollection[j]);
            }
        }

        for (int i = 0; i<hearts.size();i++){
            Image j = new Image(String.valueOf(Skin.class.getResource("heart.jpg")), 200, 200, false, false);
            ImageView iv = new ImageView(j);
            iv.fitHeightProperty().bind(hearts.get(i).heightProperty());
            iv.fitWidthProperty().bind(hearts.get(i).widthProperty());
            hearts.get(i).getChildren().clear();
            hearts.get(i).getChildren().add(iv);
        }

        heart_box.getChildren().removeAll();
        heart_box.getChildren().clear();
        for(int i = hearts.size()-1; i>=0 ;i--){
            heart_box.getChildren().add(hearts.get(i));
        }

    }

    public Pane setHeart(Pane h, String s){
        ImageView iv = new ImageView();
        iv.setImage(new Image(String.valueOf(Skin.class.getResource(s)), 200, 200, false, false));
        iv.fitHeightProperty().bind(h.heightProperty());
        iv.fitWidthProperty().bind(h.widthProperty());
        h.getChildren().clear();
        h.getChildren().add(iv);
        return h;
    }

    public boolean isRedHeart(ObservableList<Node> children) {
        boolean r = true;
        ImageView iv = (ImageView) children.get(0);
        ImageView compare = new ImageView();
        Image image = new Image(String.valueOf(Skin.class.getResource("heart.jpg")), 200, 200, false, false);
        compare.setImage(image);

        for (int x = 0; x < iv.getImage().getWidth(); x++) {
            for (int y = 0; y < iv.getImage().getHeight(); y++) {
                int firstArgb = iv.getImage().getPixelReader().getArgb(x, y);
                int secondArgb = compare.getImage().getPixelReader().getArgb(x, y);

                if (firstArgb != secondArgb){
                    r = false;
                }
            }
        }
        return r;
    }

    public void stopGame() {
        stop = true;
        happend = false;
        isHeart = false;

    }

    public static void setPetMode(boolean mode) {
        petmode = mode;
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

    public static void main(String[] args) {
        //    String url =  "https://randomfox.ca/images/" + x + ".jpg";

        //    Image i = new Image(url,200,200,false,false);
    }
}
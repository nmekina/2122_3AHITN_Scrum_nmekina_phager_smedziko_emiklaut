package Model;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import static java.lang.Thread.sleep;

public class Player{
    static String chosenMovement = "";
    static boolean keypressed;
    private static Ellipse player;
    static boolean stop = false;
    public String name;
    public static boolean getPressed() {
        return keypressed;
    }

    public static void setPressed(boolean pressed) {
        keypressed = pressed;
    }

    public static String getMovement() {
        return chosenMovement;
    }

    public static void setMovement(String movement) {
        chosenMovement = movement;
    }

    public static void setPlayer(Ellipse p){
        player = p;
    }

    public static Ellipse getPlayer(){
        return player;
    }

    public Player(String name){
        this.name = name;
    }
}
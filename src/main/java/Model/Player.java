package Model;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import static java.lang.Thread.sleep;

public class Player extends Ellipse{
    static String chosenMovement = "";
    static boolean keypressed;
    private static Node player;
    static boolean stop = false;
    public String name;
    public static boolean getPressed() {
        return keypressed;
    }

    // TODO: überall ändern
    public static void setPressedKey(boolean pressed) {
        keypressed = pressed;
    }

    public static String getMovement() {
        return chosenMovement;
    }

    public static void setMovement(String movement) {
        chosenMovement = movement;
    }

    public static void setPlayer(Node p){
        player = p;
    }

    //TODO: getRadiusY abändern auf Node/Pane
    public static Node getPlayer(){
        return player;
    }

    public Player(String name){
        this.name = name;
    }
}
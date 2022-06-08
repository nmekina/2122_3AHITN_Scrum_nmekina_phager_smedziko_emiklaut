package Model;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class Player{
    static String chosenMovement = "";
    static boolean keypressed;
    static Skin skin;
    static Pane p;
    String name;
    static boolean stop = false;

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

    //Benötigt um den ausgewählten Skin vom Shop in die Spiel Scene zu übertragen
    public static void setSkin(Skin s){
        skin = s;
    }

    public void setPlayer(Pane p){
        Player.p = p;
        ImageView iv = new ImageView(skin.getPicture());
        iv.fitWidthProperty().bind(Player.p.widthProperty());
        iv.fitHeightProperty().bind(Player.p.heightProperty());
        Player.p.getChildren().add(iv);
    }

    //TODO Make it unstatic
    public static Pane getPlayer(){
        return p;
    }


    public Player(String name){
        this.name = name;
    }
}








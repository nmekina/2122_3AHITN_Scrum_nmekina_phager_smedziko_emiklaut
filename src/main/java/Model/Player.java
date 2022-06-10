package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Player {
    static String chosenMovement = "";
    static boolean keypressed;
    static Skin skin;
    static Pane p;
    static String name;
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

    public static void setSkin(Skin s){
        skin = s;
    }

    public void setPlayer(Pane p){

        if(skin == null){
            skin = new Skin("Semir",0,new Image(("Model/Medzikovic/SemirMedzikovic.jpeg"),200,100,false,false));
        }

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
    public Player(){}

    public static String getName() {
        return name;
    }
}







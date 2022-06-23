package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Player {
    static String chosenMovement = "";
    static boolean keypressed;
    static Skin skin;
    static Pane p;
    static String name;
    static float jumpskill = 97;
    ImageView iv = new ImageView();

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

    public static double getJumpSkill() {
        return jumpskill;
    }

    public static void setJumpPower(Integer jump) {
        jumpskill = jump;
    }

    public void setPlayer(Pane p){

        if(skin == null){
            ArrayList<Image> images = new ArrayList<>();
            images.add(new Image(String.valueOf(Skin.class.getResource("Medzikovic/SemirMedzikovic.jpeg")),200,100,false,false));
            images.add(new Image(String.valueOf(Skin.class.getResource("Medzikovic/SemirMedzikovicDuck.png")),200,100,false,false));
            images.add(new Image(String.valueOf(Skin.class.getResource("Medzikovic/SemirMedzikovicJump.png")),200,100,false,false));
            skin = new Skin("Semir", 0,0, images);
        }

        Player.p = p;
        iv.setImage(skin.getPicture().get(0));
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

    public void change(int number) {
        iv.setImage(skin.getPicture().get(number));
        iv.fitWidthProperty().bind(Player.p.widthProperty());
        iv.fitHeightProperty().bind(Player.p.heightProperty());
        Player.p.getChildren().clear();
        Player.p.getChildren().add(iv);
    }
}







package Model;

import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Skin {
    String name;
    int level;
    Image picture;
    static ListView<HBox> skins = new ListView<>();
    static ArrayList<Skin> arr = new ArrayList<>();
    Image array[] = new Image[2];

    public Skin(String name, int level, Image picture) {
        this.name = name;
        this.level = level;
        this.picture = picture;
        arr.add(this);
    }

    public static ArrayList<Skin> getSkins(){
        return arr;
    }

    public void getListOfImages(){

    }


    public static void setList(ListView<HBox> show_skins){
        skins = show_skins;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public Image getPicture() {
        return picture;
    }

    public void addSkin(){
        ImageView skin = new ImageView(picture);
        skin.fitHeightProperty();
        skin.fitWidthProperty();

        HBox h = new HBox();
        h.getChildren().add(skin);
        h.setAlignment(Pos.CENTER);

        skins.getItems().add(h);
    }
}

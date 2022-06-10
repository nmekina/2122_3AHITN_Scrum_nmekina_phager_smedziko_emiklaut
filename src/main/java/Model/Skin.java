package Model;

import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.lang.reflect.Array;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Vector;


public class Skin {
    public static final int NORMAL = 0;
    public static final int LOWER = 1;
    public static final int JUMP = 2;   // s.getImage(Skin.JUMP)

    String name;
    int level;
    Image picture;
    static ArrayList<Skin> arr = new ArrayList<>();
    Image[] array = new Image[2];
    Vector<Image> imgArray = new Vector<>();

    public Skin(String name, int level, Image picture) {
        this.name = name;
        this.level = level;
        this.picture = picture;
        arr.add(this);
        imgArray.add(picture);
        array[0] = picture;
    }

    public static ArrayList<Skin> getSkins() {
        return arr;
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

}

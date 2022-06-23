package Model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Vector;

public class Skin {

    public static final int NORMAL = 0;
    public static final int LOWER = 1;
    public static final int JUMP = 2;   // s.getImage(Skin.JUMP)

    String name;
    int level;
    ArrayList<Image> pictures;
    int coins;
    static ArrayList<Skin> arr = new ArrayList<>();

    public Skin(String name, int level, int coins, ArrayList<Image> pictures) {
        this.name = name;
        this.level = level;
        this.pictures = pictures;
        arr.add(this);
        this.coins = coins;
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

    public ArrayList<Image> getPicture() {
        return pictures;
    }

    public int getCoins() {
        return coins;
    }

}


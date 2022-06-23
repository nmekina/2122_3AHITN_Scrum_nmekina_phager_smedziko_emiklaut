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
        Image picture;
        int coins;
        static ArrayList<Skin> arr = new ArrayList<>();
        Image[] array = new Image[2];
        Vector<Image> imgArray = new Vector<>();

        public Skin(String name, int level, int coins, Image picture) {
            this.name = name;
            this.level = level;
            this.picture = picture;
            arr.add(this);
            imgArray.add(picture);
            array[0] = picture;
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

        public Image getPicture() {
            return picture;
        }

        public int getCoins(){
            return coins;
        }

    }


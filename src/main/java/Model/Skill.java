package Model;

import com.example.Controller.HelloController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Objects;

import static Model.ObstacleGenerator.hearts;

public class Skill {

    static final int MINLEVEL = 3;
    static final int NOTSAVE = -1;

    static int heightbig = 72;
    static final int x = 776;
    static final int y = 237;

    ImageView[] skills = new ImageView[3];
    ImageView skill_img;
    static boolean savedstate_invi = false;
    static boolean savedstate_double = false;
    String image;
    Integer[] intensity = new Integer[4];
    static Integer[] savedIntensity = {3, 3};

    public Skill(ImageView skill_1, ImageView skill_2, ImageView skill_3, ImageView skill_img, int min, int low, int mid, int max, String image) {
        skills[0] = skill_1;
        skills[1] = skill_2;
        skills[2] = skill_3;
        this.skill_img = skill_img;
        this.image = image;
        intensity[0] = low;
        intensity[1] = mid;
        intensity[2] = max;
        intensity[3] = min;
    }

    public Skill(ImageView skill_1, ImageView skill_img, String image) {
        skills[0] = skill_1;
        this.skill_img = skill_img;
        this.image = image;
    }

    public static int getSaved(String name) {
        int saved = 0;
        if (Objects.equals(name, "jump")) {
            saved = savedIntensity[0];
        } else if (Objects.equals(name, "heart")) {
            saved = savedIntensity[1];
        }
        return saved;
    }

    public void setImage(ImageView i) {
        i.setImage(new Image(String.valueOf(HelloController.class.getResource(image))));
        i.fitHeightProperty();
        i.fitWidthProperty();
    }

    public int checkSkillLevel(String name) {
        int skillnumber = 0;
        for (int i = 0; i < skills.length; i++) {
            if (skills[i].getImage() == null) {
                skillnumber = i;
                i = skills.length;
            } else if (i == skills.length - 1) {
                skillnumber = MINLEVEL;
            }
        }

        setSaved(name, skillnumber);
        return skillnumber;
    }

    private void setSaved(String name, int skillnumber) {
        if (Objects.equals(name, "jump")) {
            savedIntensity[0] = skillnumber;
        } else if (Objects.equals(name, "heart")) {
            savedIntensity[1] = skillnumber;
        }

    }

    public void updateImage(int skillevel) {
        if (skillevel != MINLEVEL) {
            setImage(skills[skillevel]);
        } else {
            for (int i = 0; i < skills.length; i++) {
                if (skills[i].getImage() != null) {
                    skills[i].imageProperty().set(null);
                }
            }
        }
    }

    public void changeActivate(String name){
        if(Objects.equals(name, "invincible")) {
            if (savedstate_invi) {
                setImage(skills[0]);
                Player.invincibility = true;
            } else {
                skills[0].imageProperty().set(null);
            }
        }else if(Objects.equals(name, "double")){
            if (savedstate_double) {
                setImage(skills[0]);
                Player.doublepoints = true;
            } else {
                skills[0].imageProperty().set(null);
            }
        }
    }

    public void changeState(String name){
        if(Objects.equals(name, "invincible")) {
            savedstate_invi = !savedstate_invi;
        }else if (Objects.equals(name, "double")){
            savedstate_double= !savedstate_double;
        }
    }

    public void updateSkill(int skillevel, String skillname) {
        if (Objects.equals(skillname, "jump")) {
            Player.setJumpPower(intensity[skillevel]);
        }

        if (Objects.equals(skillname, "heart")) {

            hearts.clear();

            if(skillevel != MINLEVEL){
                skillevel +=4;
            }

            while (hearts.size() < skillevel) {
                Pane heart = new Pane();
                heart.setPrefHeight(28);
                heart.setPrefWidth(26);
                heart.setLayoutY(y);
                heart.setLayoutX(x);

                Image j = new Image(String.valueOf(Skin.class.getResource("heart.jpg")), 200, 200, false, false);
                ImageView iv = new ImageView(j);
                iv.fitHeightProperty().bind(heart.heightProperty());
                iv.fitWidthProperty().bind(heart.widthProperty());

                hearts.add(heart);
            }
        }
    }
}
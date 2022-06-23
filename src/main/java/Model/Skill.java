package Model;

import com.example.Controller.HelloController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Skill {
    static final int MINLEVEL = 0;
    static final int MAXLEVEL = 3;
    static final int NOTSAVE = -1;
    ImageView[] skills = new ImageView[3];
    ImageView skill_img;
    String image;
    Integer[] intensity = new Integer[4];
    static int savedIntensity = NOTSAVE;

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

    public static int getSaved(){
        return savedIntensity;
    }

    public void setImage(ImageView i){
        i.setImage(new Image(String.valueOf(HelloController.class.getResource(image))));
        i.fitHeightProperty();
        i.fitWidthProperty();
    }

    public int checkSkillLevel(){
        int skillnumber = 0;
        for(int i = 0; i < skills.length; i++){
            if(skills[i].getImage() == null){
                skillnumber = i;
                i = skills.length;
            }else if(i == skills.length-1){
                skillnumber = MAXLEVEL;
            }
        }

        savedIntensity = skillnumber;
        return skillnumber;
    }

    public void updateImage(int skillevel) {
        if(skillevel != MAXLEVEL){
            setImage(skills[skillevel]);
        }else {
            for (int i = 0;i < skills.length;i++){
                if(skills[i].getImage()!=null){
                    skills[i].imageProperty().set(null);
                }
            }
        }
    }

    public void updateSkill(int skillevel) {
        Player.setJumpPower(intensity[skillevel]);
    }
}

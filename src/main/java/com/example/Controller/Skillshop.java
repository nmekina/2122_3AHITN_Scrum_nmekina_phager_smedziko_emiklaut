package com.example.Controller;

import Model.ChangeScene;
import Model.Skill;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class Skillshop {


    @FXML
    private Label jump_power_lb;

    @FXML
    private ImageView jump_power_picture;

    @FXML
    private Button select_jumppower;

    @FXML
    private ImageView jump_1;

    @FXML
    private ImageView jump_2;

    @FXML
    private ImageView jump_3;

    @FXML
    private Button go_to_start;

    @FXML
    private ImageView heart_1;

    @FXML
    private ImageView heart_2;

    @FXML
    private ImageView heart_3;

    @FXML
    private Label heart_label;

    @FXML
    private ImageView heart_show;

    @FXML
    private Button select_heart;

    Skill jump;
    Skill heart;

    static final int MINJUMP = 97;
    static final int LOWJUMP = 70;
    static final int MIDJUMP = 60;
    static final int MAXJUMP = 40;

    static final int MINHEARTS = 3;
    static final int LOWHEARTS = 4;
    static final int MIDHEARTS = 5;
    static final int MAXHEARTS = 6;


    public void initialize() {

        go_to_start.setText("Menue");
        jump_power_lb.setText("Jump");
        heart_label.setText("Heart");

        select_heart.setText("Select");
        select_jumppower.setText("Select");

        jump = new Skill(jump_1, jump_2, jump_3, jump_power_picture, MINJUMP, LOWJUMP, MIDJUMP, MAXJUMP, "jump-power.png");
        heart = new Skill(heart_1,heart_2,heart_3,heart_show, MINHEARTS, LOWHEARTS, MIDHEARTS, MAXHEARTS, "heart_skill.jpg");

        jump.setImage(jump_power_picture);
        heart.setImage(heart_show);
        heart.setImage(heart_1);
        heart.setImage(heart_2);
        heart.setImage(heart_3);


        for (int i = 0; i <= Skill.getSaved();i++){
            jump.updateImage(i);
        }

    }

    @FXML
    void jumppower_select(ActionEvent event) {
        int skillevel = jump.checkSkillLevel();
        System.out.println(skillevel);
        jump.updateImage(skillevel);
        jump.updateSkill(skillevel);

    }

    @FXML
    void start(ActionEvent event) throws IOException {
        ChangeScene.change_scene("startmenue", select_jumppower);
    }

    @FXML
    void heart_selected(ActionEvent event) {

    }


}


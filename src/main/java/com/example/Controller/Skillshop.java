package com.example.Controller;

import Model.ChangeScene;
import Model.Skill;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class Skillshop {

    @FXML
    private ImageView break_1;

    @FXML
    private Label break_label;

    @FXML
    private ImageView break_picture;

    @FXML
    private ImageView double_1;

    @FXML
    private ImageView double_img;

    @FXML
    private Label double_label;

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
    private ImageView invi_1;

    @FXML
    private Label invincible_label;

    @FXML
    private ImageView invincible_show;

    @FXML
    private ImageView jump_1;

    @FXML
    private ImageView jump_2;

    @FXML
    private ImageView jump_3;


    @FXML
    private Label jump_power_lb;

    @FXML
    private ImageView jump_power_picture;

    @FXML
    private Button select_break;

    @FXML
    private Button select_double;

    @FXML
    private Button select_heart;

    @FXML
    private Button select_invincible;

    @FXML
    private Button select_jumppower;



    Skill jump;
    Skill heart;
    Skill invincible;
    Skill double_points;
    Skill break_skill;

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
        invincible_label.setText("invincible");
        double_label.setText("Double Points");
        break_label.setText("Break");

        select_heart.setText("Select");
        select_jumppower.setText("Select");
        select_invincible.setText("Select");
        select_double.setText("Select");
        select_break.setText("Select");

        jump = new Skill(jump_1, jump_2, jump_3, jump_power_picture, MINJUMP, LOWJUMP, MIDJUMP, MAXJUMP, "jump-power.png");
        heart = new Skill(heart_1,heart_2,heart_3,heart_show, MINHEARTS, LOWHEARTS, MIDHEARTS, MAXHEARTS, "heart_skill.jpg");
        invincible = new Skill(invi_1,invincible_show,"invincible.png");
        double_points = new Skill(double_1,double_img,"double_points.png");
        break_skill = new Skill(break_1,break_picture,"break.jpg");

        jump.setImage(jump_power_picture);
        heart.setImage(heart_show);
        invincible.setImage(invincible_show);
        double_points.setImage(double_img);
        break_skill.setImage(break_picture);



        for (int i = 0; i <= Skill.getSaved("jump");i++){
            jump.updateImage(i);
        }

        for (int i = 0; i <= Skill.getSaved("heart");i++){
            heart.updateImage(i);
        }

        double_points.changeActivate("double");
        invincible.changeActivate("invincible");
        break_skill.changeActivate("break");



    }

    @FXML
    void select(ActionEvent event) {
        int skillevel;
        if(event.getSource() == select_jumppower) {
            skillevel = jump.checkSkillLevel("jump");
            jump.updateImage(skillevel);
            jump.updateSkill(skillevel, "jump");
        }

        if(event.getSource() == select_heart){
            skillevel = heart.checkSkillLevel("heart");
            heart.updateImage(skillevel);
            heart.updateSkill(skillevel, "heart");
        }

        if(event.getSource() == select_invincible){
            invincible.changeState("invincible");
            invincible.changeActivate("invincible");
        }

        if(event.getSource() == select_double){
            double_points.changeState("double");
            double_points.changeActivate("double");
        }

        if(event.getSource() == select_break){
            break_skill.changeState("break");
            break_skill.changeActivate("break");
        }

    }

    @FXML
    void start(ActionEvent event) throws IOException {
        ChangeScene.change_scene("startmenue", select_jumppower);
    }


}
package com.example.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    AnimationTimer up = new AnimationTimer() {
        @Override
        public void handle(long now) {

            if(Objects.equals(Player.getMovement(), "S")){
                player.setRadiusY(17);
                player.setLayoutY(292);
                Player.setPressed(false);
            }else if(player.getRadiusY() == 17 && !Objects.equals(Player.getMovement(), "S")){
                player.setRadiusY(34);
                player.setLayoutY(273);
            }

            if (Player.getPressed() && player.getLayoutY() > 133 && !Objects.equals(Player.getMovement(), "S")) {
                player.setLayoutY(player.getLayoutY() - 8);

            }else {
                Player.setPressed(false);
            }

            if(!Player.getPressed() && player.getLayoutY() < 273){
                player.setLayoutY(player.getLayoutY() + 8);
            }

            if(player.getLayoutY() == 273){
                Player.setMovement("");
            }
        }


    };
}
package Model;

import javafx.scene.control.Label;

public class Coin {
    Label coins_label;
    static String saved_state;

    public Coin(Label coins_label, boolean restarted) {
        this.coins_label = coins_label;
        if(!restarted) {
            coins_label.setText("00000");
        }else {
            coins_label.setText(saved_state);
        }
    }

    public void setCoins(int coins){
        String coinsText;
        if (coins < 10) {
            coinsText = "0000" + coins;
        } else if (coins < 100) {
            coinsText = "000" + coins;
        } else if (coins < 1000) {
            coinsText = "Score: 00" + coins;
        } else if (coins < 10000) {
            coinsText = "Score: 0" + coins;
        } else {
            coinsText = String.valueOf(coins);
        }

        saved_state = coinsText;

        coins_label.setText(coinsText);
    }


}

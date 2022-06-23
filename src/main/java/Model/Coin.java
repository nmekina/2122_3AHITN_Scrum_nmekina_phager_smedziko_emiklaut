package Model;

import javafx.scene.control.Label;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class Coin {
    Label coins_label;
    static String saved_state;
    static int coins;
    boolean found = false;

    public Coin(Label coins_label, boolean restarted) throws IOException {

        this.coins_label = coins_label;

        if(restarted) {
            coins_label.setText(saved_state);
        }else {
            File file = new File("src/main/java/Model/scores.json");
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONArray json = new JSONArray(content);

            for (int z = 0; z < json.length(); z++) {
                JSONObject getplayer = json.getJSONObject(z);

                if (Objects.equals(getplayer.getString("name"), Player.name)) {
                    setCoins(getplayer.getInt("coins"));
                    coins = getplayer.getInt("coins");
                    found = true;
                }
            }

            if(!found){
                coins_label.setText("00000");
            }
        }

    }

    public void setCoins(int coins){
        String coinsText;
        coins = Coin.coins + coins;
        System.out.println(coins);
        if (coins < 10) {
            coinsText = "0000" + coins;
        } else if (coins < 100) {
            coinsText = "000" + coins;
        } else if (coins < 1000) {
            coinsText = "00" + coins;
        } else if (coins < 10000) {
            coinsText = "0" + coins;
        } else {
            coinsText = String.valueOf(coins);
        }

        saved_state = coinsText;

        coins_label.setText(coinsText);
    }


}

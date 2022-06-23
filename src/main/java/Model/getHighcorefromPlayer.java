package Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class getHighcorefromPlayer {
    private int highscore;

    public int getHighcore() throws IOException {
        File file = new File("src/main/java/Model/scores.json");
        String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
        JSONArray json = new JSONArray(content);

        for (int z = 0; z < json.length(); z++) {
            JSONObject getplayer = json.getJSONObject(z);
            String name = getplayer.getString("name");
            if (name.equals(Player.getName())) {
                highscore = getplayer.getInt("highscore");
            }
        }

        return highscore;
    }
}

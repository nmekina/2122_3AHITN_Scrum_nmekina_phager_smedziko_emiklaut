package Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author nmekina
 * soll den Highscore des aktuellem Spielers setzen und zurückgeben können
 */
public class getHighcorefromPlayer {
    private int highscore = 0;

    /**
     * @author nmekina
     * holt den Highscore des aktuellem Spielers aus der scores.json datei
     * wenn der Spieler noch kein Spiel gespielt hat, wird der highscore auf 0 gesetzt
     */
    public getHighcorefromPlayer() throws IOException {
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
    }

    /**
     * @author nmekina
     * gibt den Highscore des aktuellem Spielers zurück
     */
    public int getHighcore() {
        return highscore;
    }
}

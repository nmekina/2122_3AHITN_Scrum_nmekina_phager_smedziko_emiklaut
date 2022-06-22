package Model;

import com.example.Controller.ControllerScoreBoard;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class sortHighscore {
    private ArrayList<String[]> allplayers = new ArrayList<>();
    private ArrayList<String[]> scoreboard = new ArrayList<>();
    private int highscore = 0;
    private int indexfromplayer = 0;



    public void sort() throws IOException {
        addSpieler();
        while (allplayers.size() > 0) {
            for (int i = 0; i < allplayers.size(); i++) {
                if (Integer.parseInt(allplayers.get(i)[1]) > highscore) {
                    highscore = Integer.parseInt(allplayers.get(i)[1]);
                    indexfromplayer = i;
                }
            }
            scoreboard.add(allplayers.get(indexfromplayer));
            indexfromplayer = 0;
            highscore = 0;
            allplayers.removeAll(scoreboard);
        }
        ControllerScoreBoard controllerScoreBoard = new ControllerScoreBoard();
        controllerScoreBoard.print(scoreboard);
    }

    public void addSpieler() throws IOException {
        File file = new File("src/main/java/Model/scores.json");
        String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
        JSONArray json = new JSONArray(content);

        for (int z = 0; z < json.length(); z++) {
            JSONObject getplayer = json.getJSONObject(z);
            String[] player = new String[4];
            player[0] = getplayer.getString("name");
            player[1] = String.valueOf(getplayer.getInt("highscore"));
            player[2] = String.valueOf(getplayer.getInt("games"));
            player[3] = String.valueOf(getplayer.getInt("coins"));
            allplayers.add(player);
        }
    }
}

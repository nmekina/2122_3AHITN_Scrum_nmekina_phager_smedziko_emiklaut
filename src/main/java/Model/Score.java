package Model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Score implements Runnable {
    String scoreText;
    static int score;
    int coins;
    Label showScore;
    int highscore;
    Label showHighscore;
    static boolean stop = false;
    Player pl;
    boolean check = true;
    getHighcorefromPlayer getHighcorefromPlayer;

    {
        try {
            getHighcorefromPlayer = new getHighcorefromPlayer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Score(Label showScore, Label showHighscore, Player p){
        this.showScore = showScore;
        this.showHighscore = showHighscore;
        pl = p;

        highscore = getHighcorefromPlayer.getHighcore();
    }

    public Score(){

    }

    public int getHighscore(){
        return highscore;
    }

    public void stop(){
        stop = true;
    }

    public void start(){
        score = 0;
        stop = false;
        showScore.setText("Score: 00000");

        synchronized (this) {
            notify();
        }
    }
    public void checkScores(int coins) throws IOException {
        File f = new File("src/main/java/Model/scores.json");
        Scanner s = new Scanner(f);
        FileReader fr = new FileReader(f);
        StringBuilder sb = new StringBuilder();

        while (s.hasNextLine()) {
            sb.append(s.nextLine());
        }

        Gson g = new Gson();


        Type listType = new TypeToken<ArrayList<PlayerScore>>() {
        }.getType();
        ArrayList<PlayerScore> player = g.fromJson(fr, listType);

        Iterator<PlayerScore> i = player.iterator();

        int count = 0;
        while (i.hasNext()) {
            count++;
            PlayerScore p = i.next();
            if (Objects.equals(Player.name, p.name)) {
                check = false;
                p.games++;
                p.coins = coins + p.coins;
                if(Settings.getHighscoreonoff()) {
                    if (score > p.highscore) {
                        player.get(count - 1).highscore = score;
                    }
                }
            }
        }

        if (check) {
            PlayerScore playerScore = new PlayerScore();
            if(Settings.getHighscoreonoff()) {
                playerScore.highscore = score;
            }else {
                playerScore.highscore = 0;
            }
            playerScore.name = Player.name;
            playerScore.games++;
            playerScore.coins = coins;
            player.add(playerScore);
        }
        FileWriter fw = new FileWriter(f);
        g.toJson(player, fw);
        fw.close();
    }

    @Override
    public void run() {
        while(!stop) {
            if (score < 10) {
                scoreText = "Score: 0000" + score;
            } else if (score < 100) {
                scoreText = "Score: 000" + score;
            } else if (score < 1000) {
                scoreText = "Score: 00" + score;
            } else if (score < 10000) {
                scoreText = "Score: 0" + score;
            } else {
                scoreText = String.valueOf(score);
            }

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    showScore.setText(scoreText);
                    if (highscore < score) {
                        showHighscore.setText("Highscore: " + score);
                    }
                }

            });

            try {
                if(CoolDown.double_active) {
                    System.out.println("Doublepoints");
                    sleep(50);
                }else {
                    sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            if (stop) {
                try {
                    synchronized (this) {
                        wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            score++;

        }
    }
}
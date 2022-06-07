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
    int score;
    Label showScore;
    int highscore;
    Label showHighscore;
    static boolean stop = false;
    Player pl;
    boolean update = false;

    public Score(Label showScore, Label showHighscore, Player p){
        this.showScore = showScore;
        this.showHighscore = showHighscore;
        pl = p;
    }

    public Score(){

    }

    public String getHighscore(){
        return "Highscore: " + highscore;
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
    public void checkScores() throws IOException {

        if(score > highscore){
            highscore = score;
        }

        File f = new File("src/main/java/Model/scores.json");
        Scanner s = new Scanner(f);
        FileReader fr = new FileReader(f);
        StringBuilder sb = new StringBuilder();

        while(s.hasNextLine()){
           sb.append(s.nextLine());
        }

        Gson g = new Gson();



        Type listType = new TypeToken<ArrayList<PlayerScore>>(){}.getType();
        ArrayList<PlayerScore> player = g.fromJson( fr, listType);

        Iterator<PlayerScore> i = player.iterator();

        int count = 0;
        while(i.hasNext()){
            count++;
            PlayerScore p = i.next();

            if(Objects.equals(pl.name, p.name)){
                p.games++;
                update = true;
                if(score > p.highscore){
                    player.get(count-1).highscore = score;

                }

            }

        }


        if(!update){
            PlayerScore newPlayer = new PlayerScore();
            newPlayer.highscore = score;
            newPlayer.name = pl.name;
            newPlayer.games = 1;
            player.add(newPlayer);


            FileWriter fw = new FileWriter(f);
            g.toJson(player,fw);
            fw.close();
        }

        FileWriter fw = new FileWriter(f);
        g.toJson(player,fw);
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
                }

            });

            try {
                sleep(100);
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
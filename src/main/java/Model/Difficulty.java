package Model;

import java.util.Objects;

public class Difficulty {
    //bei 0.075, olle 30 milisekunden updaten hard 1
    //bei 0.0525 medium updates 0,7
    //bei 0.0375 leicht 0,5
    //0.1125 hardcore 1,5
    //Ergebnis: Bei 400 verschiedenes
    float base = 8;
    float beginner = 0.05f;
    float medium = 0.0700f;
    float hard = 0.085f;
    float hardcore = 0.425f;
    float chosen;
    int countDifficulty = 100;

    public void setDifficulty(){
        if(Objects.equals(Settings.getDifficulty(), "beginner")){
            chosen = beginner;
        }else if(Objects.equals(Settings.getDifficulty(), "medium")){
            chosen = medium;
        }else if(Objects.equals(Settings.getDifficulty(), "hard")){
            chosen = hard;
        }else if(Objects.equals(Settings.getDifficulty(), "hardcore")){
            chosen = hardcore;
        }
    }
}

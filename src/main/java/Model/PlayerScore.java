package Model;

public class PlayerScore {
    String name;
    int highscore;
    int games;
    int coins;

    public int getHighscore(){
        return highscore;
    }

    public String getName(){return name;}

    public int getGames(){return games;}

    public int getCoins(){return coins;}

    public void setCoins(int coins){this.coins = coins;}
}
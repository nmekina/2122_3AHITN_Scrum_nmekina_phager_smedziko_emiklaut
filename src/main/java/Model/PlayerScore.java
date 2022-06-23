package Model;

/**
 * @author nmekina
 * Speichert für einen Spieler:
 * String namen: den Namen des Spielers
 * int highscore: den Highscore des Spielers
 * int games: die Anzahl der gespielten Spiele des Spielers
 * int coins: die Anzahl der gesammelten Coins des Spielers
 */
public class PlayerScore {
    String name;
    int highscore;
    int games;
    int coins;

    /**
     * @author nmekina
     * gibt den Highscore des Spielers zurück
     */
    public int getHighscore(){
        return highscore;
    }

    /**
     * @author nmekina
     * gibt den Namen des Spielers zurück
     */
    public String getName(){return name;}

    /**
     * @author nmekina
     * gibt die Anzahl der gespielten Spiele des Spielers zurück
     */
    public int getGames(){return games;}

    /**
     * @author phager
     * gibt die Anzahl der gesammelten Coins des Spielers zurück
     */
    public int getCoins(){return coins;}

    /**
     * @author phager
     * setzt die Anzahl der gesammelten Coins des Spielers
     */
    public void setCoins(int coins){this.coins = coins;}
}
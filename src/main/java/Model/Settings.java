package Model;

/**
 * @author nmekina
 * speichert die Einstellungen, die im Settingsmenü geändert werden können
 * private static boolean highscoreonoff = true;: speichert ob der Highscore ein oder ausgeschaltet sein soll
 * private static boolean musiconoff = true;: speichert ob die Musik ein oder ausgschaltet sein soll
 * private static String difficulty = "medium";: speichert die Schwierigkeit des Spiels
 * private static String image = "Hintergrund.jpeg";: speichert das Hintergrundbild des Spiels
 * private static String musicplaying = "music";: speichert welche Musik abgespielt werden soll
 */
public class Settings {
    private static boolean highscoreonoff = true;
    private static boolean musiconoff = true;
    private static String difficulty = "medium";
    private static String image = "Hintergrund.jpeg";
    private static String musicplaying = "music";

    /**
     * @author nmekina
     * getter und setter für die oben angegebenen Attribute
     */
    public static boolean getHighscoreonoff() {
        return highscoreonoff;
    }

    public void setHighscoreonoff(boolean highscoreonoff) {
        Settings.highscoreonoff = highscoreonoff;
    }

    public static boolean getMusiconoff() {
        return musiconoff;
    }

    public void setMusiconoff(boolean musiconoff) {
        Settings.musiconoff = musiconoff;
    }

    public static String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        Settings.difficulty = difficulty;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        Settings.image = image;
    }

    public String getMusicplaying() {
        return musicplaying;
    }

    public void setMusicplaying(String musicplaying) {
        Settings.musicplaying = musicplaying;
    }
}
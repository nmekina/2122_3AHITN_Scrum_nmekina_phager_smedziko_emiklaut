package Model;

public class Settings {
    private static boolean highscoreonoff = true;
    private static boolean musiconoff = true;
    private static String difficulty = "medium";
    private static String image = "Hintergrund.jpeg";
    private static String musicplaying = "music";

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
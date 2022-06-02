package Model;

public class Settings {
    private static boolean highscoreonoff = true;
    private static boolean musiconoff = true;
    private static String difficulty = "medium";
    private static String image = "SemirMedzikovic.jpeg";
    private static String musicplaying = "music";

    public boolean getHighscoreonoff() {
        return highscoreonoff;
    }

    public void setHighscoreonoff(boolean highscoreonoff) {
        this.highscoreonoff = highscoreonoff;
    }

    public boolean getMusiconoff() {
        return musiconoff;
    }

    public void setMusiconoff(boolean musiconoff) {
        this.musiconoff = musiconoff;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMusicplaying() {
        return musicplaying;
    }

    public void setMusicplaying(String musicplaying) {
        this.musicplaying = musicplaying;
    }
}
package Model;

public class musicThread implements Runnable {
    private String tone;

    public musicThread(String s) {
        tone = s;
    }

    @Override
    public void run() {
        MusicPlayer musicplayer = new MusicPlayer();
        musicplayer.playFile(tone);
    }
}

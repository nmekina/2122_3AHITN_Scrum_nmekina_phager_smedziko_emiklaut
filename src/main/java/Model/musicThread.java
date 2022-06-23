package Model;

/**
 * @author nmekina
 * spielt musik ab
 */
public class musicThread implements Runnable {
    private String tone;

    /**
     * @author nmekina
     * setzt das file für die Musik
     */
    public musicThread(String s) {
        tone = s;
    }

    /**
     * @author nmekina
     * startet den musicplayer
     */
    @Override
    public void run() {
        MusicPlayer musicplayer = new MusicPlayer();
        musicplayer.playFile(tone);
    }
}

package Model;

/**
 * @author nmekina
 * startet die Musik, bei Aufruf der start()-Funktion
 */
public class startMusic {
    musicThread thread = new musicThread("src/Tetris_1.mp3");
    Thread thread2 = new Thread(thread);

    /**
     * @author nmekina
     * startet die Musik, aber nur wenn sie eingeschaltet ist in den Settings
     */
    public void start() {
        if (Settings.getMusiconoff()) {
            thread2.start();
        }
    }
}

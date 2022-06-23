package Model;

public class startMusic {
    musicThread thread = new musicThread("src/Tetris_1.mp3");
    Thread thread2 = new Thread(thread);

    public void start() {
        if (Settings.getMusiconoff()) {
            thread2.start();
        }
    }
}

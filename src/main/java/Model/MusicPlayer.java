package Model;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author nmekina
 * startet die Musik
 */
public class MusicPlayer {
    private AdvancedPlayer player;

    public MusicPlayer() {
        player = null;
    }

    /**
     * @author nmekina
     * spielt die musik ab, wird aufgefangen, wenn es Probleme gibt
     */
    public void playFile(String filename) {
        try {
            preparePlayer(filename);
            player.play();
        } catch (JavaLayerException e) {
            System.out.println("Error");
        }
    }

    /**
     * @author nmekina
     * setzt den player, der dann das spiel starten soll, fängt auf bei fehler
     */
    private void preparePlayer(String filename) {
        try {
            InputStream is = getInputStream(filename);
            player = new AdvancedPlayer(is, createAudioDevice());
        } catch (IOException | JavaLayerException e) {
            System.out.println("Error");
        }
    }

    /**
     * @author nmekina
     * setzt den InputStream
     */
    private InputStream getInputStream(String filename)
            throws IOException {
        return new BufferedInputStream(
                new FileInputStream(filename));
    }

    /**
     * @author nmekina
     * erstellt ein Audio Device
     */
    private AudioDevice createAudioDevice()
            throws JavaLayerException {
        return FactoryRegistry.systemRegistry().createAudioDevice();
    }
}

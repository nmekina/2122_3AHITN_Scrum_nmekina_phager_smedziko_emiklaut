package Model;

import javafx.application.Platform;
import javafx.scene.control.Label;

import static java.lang.Thread.sleep;

public class Invincibility implements Runnable {

    int time;
    int cooldown;
    Label showTimer;
    Label showCooldown;
    boolean stop = false;
    String secs;
    Label show;
    int sec;
    boolean stopped = false;
    static boolean activated = false;

    public Invincibility(int time, int cooldown, Label showTimer, Label showCooldown) {
        this.time = time;
        sec = time;
        this.cooldown = cooldown;
        stop = false;
        this.showTimer = showTimer;
        show = showTimer;
        this.showCooldown = showCooldown;
        showTimer.setText("Timer");
        showCooldown.setText("Cooldown");

        synchronized (this) {
            notify();
        }
    }

    public Invincibility() {
    }

    public void stop(){
        sec = 0;
        stop = true;
    }

    @Override
    public void run() {
        while (!stop) {

            activated = show.idProperty().equals(showTimer.idProperty());

            if (sec < 10) {
                secs = "Sec: 0000" + sec;
            } else if (sec < 100) {
                secs = "Sec: 000" + sec;
            } else if(sec < 1000){
                secs = "Sec: 00"+sec;
            }else {
                secs = String.valueOf(sec);
            }

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    show.setText(secs);
                }

            });

            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(sec == 0){
                stop = true;
                if(show.idProperty().equals(showTimer.idProperty()) || !stopped){
                    sec = cooldown;
                    show = showCooldown;
                    Thread t = new Thread(this);
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    t.start();
                }else {
                    show = showTimer;
                    sec = time;
                    Player.setInvincibility(true);
                }
            }
            sec--;
        }
        stop = false;
    }
}

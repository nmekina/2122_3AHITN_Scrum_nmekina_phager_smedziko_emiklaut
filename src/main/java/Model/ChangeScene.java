package Model;

import com.example.Controller.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 * @author nmekina
 * soll in eine andere Scene wechseln
 */
public class ChangeScene {

    /**
     * @author nmekina
     * wechselt in eine andere Scene
     * String game: gibt an in welche Scene man wechseln soll
     * Button button: zur hilfestellung, um zu wissen welches die aktuelle Scene ist und um sie zu schließen
     */
    public static void change_scene(String game, Button button) throws IOException {
        Stage stage = new Stage();

        Stage stageclose = (Stage) button.getScene().getWindow();
        stageclose.close();

        final FXMLLoader fxmlLoader = new FXMLLoader();
        URL u = HelloApplication.class.getResource(game + ".fxml");

        fxmlLoader.setLocation(u);
        Scene scene = new Scene(fxmlLoader.load());

        if(game.equals("JumpAndRun")) {
            scene.setOnKeyPressed(e -> {
                if (Objects.equals(Player.getMovement(), "") || Objects.equals(Player.getMovement(), "S")) {
                    if (Objects.equals(e.getCode().toString(), "W") || Objects.equals(e.getCode().toString(),"S")|| Player.invincibility && Objects.equals(e.getCode().toString(), "I") || Player.doublepoints && Objects.equals(e.getCode().toString(),"D") || Player.break_skill && Objects.equals(e.getCode().toString(),"B")) {
                        Player.setMovement(e.getCode().toString());
                        Player.setPressed(true);
                    } else {
                        System.out.println("Wrong Key");
                    }
                }else {
                    System.out.println("Already jumping");
                }
            });
        }


        stage.setTitle(game);
        stage.setScene(scene);
        stage.show();
    }
}

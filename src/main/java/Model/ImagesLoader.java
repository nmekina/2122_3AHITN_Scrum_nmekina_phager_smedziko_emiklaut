package Model;


import javafx.scene.control.ListView;
import javafx.scene.image.Image;

public class ImagesLoader implements Runnable {
    static ListView<Image> images = new ListView();
    boolean stop = false;

    public void stop() {
        stop = true;
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    static public Image getRandomImage(){
        int r = getRandomNumber(0,images.getItems().size()-1);
        return images.getItems().get(r);
    }



    @Override
    public void run() {
        while (!stop) {
            int r = getRandomNumber(0, 2);
            String url;
            int x = (int) (Math.random() * (100) + 0);
            int y = (int) (Math.random() * (100) + 0);

            if (r == 0) {
                url = "http://placekitten.com/" + x + "/" + y;
            } else {
                url = "https://randomfox.ca/images/" + x + ".jpg";
            }

            Image i = new Image(url, 200, 200, false, false);
            if (i.isError()) {
                i = new Image(String.valueOf(Skin.class.getResource("loading_error.jpg")), 200, 200, false, false);
                System.out.println("Error loading image");
            }

            System.out.println("new image");
            images.getItems().add(i);
        }
    }
}

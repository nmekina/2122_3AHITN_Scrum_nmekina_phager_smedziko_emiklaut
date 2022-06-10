package Model;


import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class Obstacle {
    Shape shape;
    Pane pet;
    Image skin;
    int type;

    public Obstacle(Node n, boolean petmode) {
        if(petmode){
            pet = (Pane) n;
        }else {
            shape = (Shape) n;
        }
    }

    public Obstacle(Image skin) {
        this.skin = skin;
    }

    public Node getRunning(){
        Node r;
        if(pet != null){
            r = pet;
        }else {
            r = shape;
        }
        return r;
    }

    public Pane getPet(){
        return pet;
    }

    public Shape getShape(){
        return shape;
    }

}

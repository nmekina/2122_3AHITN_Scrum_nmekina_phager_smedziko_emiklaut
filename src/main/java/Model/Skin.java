package Model;

import java.io.File;

public class Skin {
    String name;
    private String image;
    Integer freischaltung;
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getFreischaltung() {
        return freischaltung;
    }

    public void setFreischaltung(Integer freischaltung) {
        this.freischaltung = freischaltung;
    }
}

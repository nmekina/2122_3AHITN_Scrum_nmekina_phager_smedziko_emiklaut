package Model;

import java.io.File;

public class Skin {
    private String name;
    private String image;
    int freischaltung;

    public Skin(String name, int freischaltung, String image){
        this.name = name;
        this.freischaltung = freischaltung;
        this.image = image;
    }

    Skin semir = new Skin("Semir",0,"SemirMedzikovic.jpeg");
    Skin elias = new Skin("Elias", 25, "EliasMiklautsch.jpeg");
    Skin nico = new Skin("Nico",50,"NicoMekina.jpeg");
    Skin rester = new Skin("Rester",75,"ManuelRester.jpeg");
    Skin hager = new Skin("Hager",100,"PhilippHager.jpeg");



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

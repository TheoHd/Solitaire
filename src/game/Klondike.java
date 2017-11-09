package game;

import java.util.ArrayList;

public class Klondike {

    private final ArrayList<Card> cartes;

    public Klondike(){
        Pioche pioche = new Pioche();
        ArrayList<Card> cartes = pioche.getPioche();
        this.cartes = cartes;
    }

    public void launch() {
        System.out.println(this.cartes);
    }
}

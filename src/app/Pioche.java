package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Pioche {

    private ArrayList<String> forme;
    private ArrayList<Integer> valeur;
    private ArrayList<Card> cartes;

    /**
     * Initialise les tableau pour les valeaurs et les types
     */
    public Pioche(){
        String[] formeArray = {"♦","♥","♣","♠"};
        this.forme = new ArrayList<>(Arrays.asList(formeArray));

        Integer[] valeurArray = {1, 2, 3, 4, 5, 7, 6, 8, 9, 10, 11, 12, 13};
        this.valeur = new ArrayList<>(Arrays.asList(valeurArray));

        this.assembleArray();
    }

    /**
     * Multiplie 2 talbeau entre eux , Valeur et Type
     */
    private void assembleArray(){
        this.cartes = new ArrayList<>();
        for (int f = 0; f < this.forme.size() ; f++) {
            for (int v = 0; v < this.valeur.size(); v++) {
                String formeValue = this.forme.get(f);
                Integer valeurValue = this.valeur.get(v);
                Card carte = new Card(valeurValue, formeValue, f);
                this.cartes.add(carte);
            }
        }
        this.shuffleCards();
    }

    /**
     * Melange un tableau (Array Collection)
     */
    private void shuffleCards(){
        Collections.shuffle(this.cartes);
    }

    public ArrayList<Card> getPioche(){
        return this.cartes;
    }

    public int getLastValue() {
        return this.valeur.get( this.valeur.size() - 1 );
    }
}

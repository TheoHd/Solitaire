package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Pioche {

    private ArrayList<String> forme;
    private ArrayList<String> valeur;
    private ArrayList<Card> cartes;

    public Pioche(){
        String[] formeArray = {"♦","♥","♣","♠"};
        this.forme = new ArrayList<>(Arrays.asList(formeArray));

        String[] valeurArray = {"A", "2", "3", "4", "5", "7", "6", "8", "9", "10", "V", "D", "R"};
        this.valeur = new ArrayList<>(Arrays.asList(valeurArray));

        this.assembleArray();
    }

    private void assembleArray(){

        this.cartes = new ArrayList<>();

        for (int f = 0; f < this.forme.size() ; f++) {
            for (int v = 0; v < this.valeur.size(); v++) {
                String formeValue = this.forme.get(f);
                String valeurValue = this.valeur.get(v);

                Card carte = new Card(valeurValue, formeValue);

                this.cartes.add(carte);
            }
        }

        this.shuffleCards();
    }

    private void shuffleCards(){
        Collections.shuffle(this.cartes);
    }

    public ArrayList<Card> getPioche(){
        return this.cartes;
    }




}

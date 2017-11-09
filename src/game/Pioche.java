package game;

import java.util.ArrayList;

public class Pioche {

        private ArrayList<String> forme;
        private ArrayList<String> valeur;

        public void pioche(){
            this.forme.toArray(new String[]{"♦", "♥", "♣", "♠"});
            this.valeur.toArray(new String[]{"A", "2", "3", "4", "5", "7", "6", "8", "9", "10", "V", "D", "R"});
        }

        public String[] assembleArray(){

            cartes = new ArrayList<String>();

            for (int f = 0; f < this.forme.size() ; f++) {
                for (int v = 0; v < this.valeur.size(); v++) {
                    formeValue = this.forme.get(f);
                    valeurValue = this.valeur.get(f);
                    cartes.add(formeValue + valeurValue);

                }
            }

            return cartes;
        }



}

package game;

import java.util.Scanner;

public class Menu {








    public void displayMenu(){
        Scanner in = new Scanner(System.in);

        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("-------------------------------------------------------");
        System.out.println("Tapez \"d\" pour deplacer une carte ou une pile");
        System.out.println("Tapez \"n\" pour commenter une nouvelle partie");
        System.out.println("Tapez \"q\" pour quitter le jeu");
        System.out.print("> Choix : ");
        String choix = in.next().toLowerCase();
        
        this.getCorrespondantAction(choix);
    }

    private void getCorrespondantAction(String choix) {
        System.out.println(choix);
    }
}

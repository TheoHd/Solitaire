package game;

import java.util.Scanner;

public class Menu {
	
	public void displayMenu(){
        @SuppressWarnings("resource")
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
        System.out.println("");
        System.out.println("");
        System.out.println("");
        
        this.getCorrespondantAction(choix);
    }

    private void getCorrespondantAction(String choix) {
        if( choix.equals("d") ){

            Stack stack = new Stack();
            stack.displayAllStaks();

            System.out.println("deplacer");

        }else if( choix.equals("n") ){
            Klondike klondike = new Klondike();
            klondike.launch();

        } if( choix.equals("q") ){
            System.exit(0);
        }
    }
}

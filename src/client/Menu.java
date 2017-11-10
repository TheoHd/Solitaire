
package client;

import static app.Helper.verifInt;

import java.util.Scanner;

import app.Klondike;
import app.Stack;

public class Menu {
	private Scanner sc = new Scanner(System.in);
	
	public void displayMenuDuringGame(){
        System.out.println("-------------------------------------------------------");
        System.out.print("> Déplacement : ");

        String choix = sc.next().toLowerCase();
        this.getCorrespondantAction(choix);
    }
	
	public void displayStartupMenu() {
        System.out.println("****** MENU DU JEU ********");
        System.out.println("d: Démarrer une partie");
        System.out.println("n: Rédemarrer le jeu");
        System.out.println("q: Quitter");
        System.out.print("> Choix : ");
        String select = this.sc.next();

        this.getCorrespondantAction(select);
    }

    private void getCorrespondantAction(String choix) {

        if( choix.equals("d") ){

            Display display = new Display();
            display.displayAllStacks();

        }else if( choix.equals("n") ){

            Klondike klondike = new Klondike();
            klondike.launch();


        } if( choix.equals("q") ){
            System.exit(0);
        }
    }
}
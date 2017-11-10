package client;

import java.util.Scanner;

import app.Klondike;
import app.Stack;

public class Menu {

    private Stack stack;

    public Menu(Stack stack){
        this.stack = stack;
    }
	private Scanner sc = new Scanner(System.in);
	
	public Integer displayMenuDuringGame(){
	    System.out.println("");
	    System.out.println("");
	    System.out.println("-------------------------------------------------");
        System.out.print("> Déplacement : ");

        Integer choix = sc.nextInt();

        while(choix < 0 || choix > stack.getNbCols() - 1){
            System.out.print("> Déplacement : ");
            choix = sc.nextInt();
        }

        return choix;
    }
	
	public Integer displayStartupMenu() {
        System.out.println("****** MENU DU JEU ********");
        System.out.println("1: Démarrer une partie");
        System.out.println("2: Rédemarrer le jeu");
        System.out.println("3: Quitter");
        System.out.print("> Choix : ");

        Integer choix = sc.nextInt();

        while(choix < 1 || choix > 3){
            System.out.print("> Choix : ");
            choix = sc.nextInt();
        }

        return choix;
    }

    public void getCorrespondantAction(Integer choix) {
        if( choix == 1 ){

            System.out.println("\n\n\n");

        }else if( choix == 2 ){

            Klondike klondike = new Klondike();
            klondike.launch();

        }else if( choix == 3 ){

            System.exit(0);

        }
    }
}

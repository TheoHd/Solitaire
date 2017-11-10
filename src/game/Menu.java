package game;

import static game.Helper.verifInt;

import java.util.Scanner;

public class Menu {
	private Scanner sc = new Scanner(System.in);
	
	public void displayMenuAccueil(){
		//In is used for choice later
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("-------------------------------------------------------");
        System.out.println("Tapez \"d\" pour deplacer une carte ou une pile");
        System.out.println("Tapez \"n\" pour commenter une nouvelle partie");
        System.out.println("Tapez \"q\" pour quitter le jeu");
        System.out.print("> Choix : ");
        String choix = sc.next().toLowerCase();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        
        this.getCorrespondantAction(choix);
    }
	
	public void displayOptions() {
	       char select = 0;
	       Stack stack_action = new Stack();
	       do{
	          System.out.println("****** MENU DU JEU ********");
	          System.out.println("1: Déplacer une carte/colonne");
	          System.out.println("2: Piocher une carte");
	          System.out.println("0: Quitter");
	          do{
		          System.out.print("> Choix : ");
		          select = this.sc.nextLine().charAt(0);
	          }while(!verifInt(48, 54, select));
	          
	          switch (select) {
	            case '1' : stack_action.deplacer(); break;
	            case '2' : stack_action.piocher(); break;
	         }
	       }while(select != 0);
	       System.out.println("***************************");
		}

    private void getCorrespondantAction(String choix) {
        if( choix.equals("d") ){

            System.out.println("deplacer");

        }else if( choix.equals("n") ){
            Klondike klondike = new Klondike();
            klondike.launch();

        } if( choix.equals("q") ){
            System.exit(0);
        }
    }
}

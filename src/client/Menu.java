
package client;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import app.Helper;
import app.Klondike;
import app.Stack;

public class Menu {

    private Helper helper;
    private Stack stack;

    public Menu(Stack stack){
        this.helper = new Helper();
        this.stack = stack;
    }

	private Scanner sc = new Scanner(System.in);

    /*
     * Menu qui s'affiche pour déplacer une carte , quand le plateau est visible
     */
	public String displayMenuDuringGame(){
	    System.out.println("");
	    System.out.println("-------------------------------------------------");
        System.out.print("> Choix : ");

        String choix = sc.next().toLowerCase();

        return choix;
    }

    /*
     * Menu qui s'affiche lors du lancement du jeu
     */
	public Integer displayStartupMenu() {
        System.out.println("****** MENU DU JEU ********");
        System.out.println("1: Démarrer une partie");
        System.out.println("2: Rédemarrer le jeu");
        System.out.println("3: Quitter");

        System.out.print("> Choix : ");
        String choix = sc.nextLine();

        while( !this.helper.verifInt(1, 3, choix) ){
            System.out.print("> Choix : ");
            choix = sc.next();
        }

        int integer = Integer.parseInt(choix);
        return Character.getNumericValue(integer);
    }

    /*
     * Actionne une method qui correspond à la saisie d'un utilisateur, (menu de démarrage)
     */
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

    /*
     * Actionne une method qui correspond à la saisie d'un utilisateur, (lors d'une partie)
     */
    public void getGameAction(String choixFrom) {

	    if(Objects.equals(choixFrom, "*")){

            String choix = "0";
            int intColTo = -1;
            while( !this.helper.verifInt(1, 11, choix) ){
                System.out.print("> Mettre la carte de la pioche sur la pile n° (* pour changer de carte) : ");
                choix = sc.next().toLowerCase();;

                if(Objects.equals(choix, "*")){
                    choix = "-1";
                    break;
                }
            }
            intColTo = Integer.parseInt(choix);

            if(this.stack.getPioche().size() > 0){

                if(Objects.equals(intColTo, -1)){
                    stack.changePiocheCard();
                }else{
                    stack.addPiocheCardOnPile( intColTo - 1);
                }

            }else{
                System.out.println("La pioche est vide ! impossible de piocher une carte !");
            }

        }else if(Objects.equals(choixFrom, "<")){

            if( !stack.undo() ){ System.out.println("Impossible de revenir en arrière"); }

        }else if(Objects.equals(choixFrom, ">")){

            if( !stack.redo() ){ System.out.println("Impossible d'effectuer l'opération"); }

        }else{

            String choix1 = choixFrom;
            while( !this.helper.verifInt(1, 11, choix1) ){
                System.out.print("> Choix : ");
                choix1 = sc.next();
            }
            int intColFrom = Integer.parseInt(choix1);


            String choix2 = "0";
            while( !this.helper.verifInt(1, 11, choix2) ){
                System.out.print("> Vers : ");
                choix2 = sc.next();
            }
            int intColTo = Integer.parseInt(choix2);

            stack.move(intColFrom - 1, intColTo - 1);  // -1 car l'indexation commence à zéro
        }
    }
}
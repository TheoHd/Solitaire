package app;

import client.Display;
import client.Menu;

public class Klondike {

    public void launch() {

        Stack stack = new Stack();
        Menu menu = new Menu(stack);
        Display display = new Display(stack);

        //Integer startupChoice = menu.displayStartupMenu();
        Integer startupChoice = 1; // A enlever plus tard
        menu.getCorrespondantAction(startupChoice);

        while ( !stack.hasWin() ){
            display.displayGameBoard();

            String inGameColChoice = menu.displayMenuDuringGame();
            menu.getGameAction( inGameColChoice );

            display.setMatrix( stack.getMatrix() );
        }

        System.out.println("Fin du jeu ............");
        System.exit(0);
    }

}

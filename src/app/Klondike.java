package app;

import client.Display;
import client.Menu;

public class Klondike {

    private Stack stack;

    public void launch() {

        this.stack = new Stack();
        Menu menu = new Menu(stack);
        Display display = new Display(stack);

        //Integer startupChoice = menu.displayStartupMenu();
        Integer startupChoice = 1; // A enlever plus tard
        menu.getCorrespondantAction(startupChoice);

        while ( !this.stack.hasWin() ){
            display.displayGameBoard();

            String inGameColChoice = menu.displayMenuDuringGame();
            menu.getGameAction( inGameColChoice );

            display.setMatrix( stack.getMatrix() );
        }

        System.out.println("Fin du jeu ............");
    }

}

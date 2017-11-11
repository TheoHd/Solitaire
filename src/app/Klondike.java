package app;

import client.Display;
import client.Menu;

public class Klondike {

    private boolean hasWin = false;

    public void launch() {

    	// TODO Change architecture, separate app(couche métier) and client(affichage)
        // TODO : add posibility to move card on empty stack

        Stack stack = new Stack();
        Menu menu = new Menu(stack);
        Display display = new Display(stack);

        //Integer startupChoice = menu.displayStartupMenu();
        Integer startupChoice = 1; // A enlever plus tard
        menu.getCorrespondantAction(startupChoice);

        while (!this.hasWin){
            display.displayGameBoard();

            String inGameColChoice = menu.displayMenuDuringGame();
            menu.getGameAction( inGameColChoice );

            display.setMatrix( stack.getMatrix() );
        }

    }
}

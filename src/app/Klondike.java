package app;

import client.Display;
import client.Menu;

public class Klondike {
	
    public void launch() {
    	// TODO Change architecture, separate app(couche métier) and client(affichage)

        Menu menu = new Menu();
        menu.displayStartupMenu();

    }
}

package app;

import client.Display;
import client.Menu;

public class Klondike {
	
    public void launch() {
    	// TODO Change architecture, separate app(couche métier) and client(affichage)
        Display display = new Display();
        Menu menu = new Menu();
        display.displayAllStacks();
        menu.displayOptions();

    }
}

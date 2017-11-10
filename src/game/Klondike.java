package game;

public class Klondike {
	
    public void launch() {
    	// TODO Change architecture, separate app(couche métier) and client(affichage)
        Stack stack = new Stack();
        Menu menu = new Menu();
        stack.displayAllStaks();
        menu.displayOptions();

    }
}

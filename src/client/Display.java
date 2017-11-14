package client;

import app.Card;
import app.Stack;

import java.util.ArrayList;

public class Display {

	private String[][] matrix;
	private Stack stack;

	public Display(Stack stack){
		this.stack = stack;
		this.matrix = stack.getMatrix();
	}

	public void setMatrix(String[][] matrix) { this.matrix = matrix; }

	private void displayCardRow(int row) {
		for (int col = 0; col < stack.getNbCols(); col++) { // pour chaques colonnes
			if( this.matrix[row][col] == null ){
				System.out.print("          ");
			}else{
				System.out.print("   " + matrix[row][col]  + "   |");
			}
		}
	}

	public void displayGameBoard(){
		this.changeInterface();

		int nbCols = stack.getNbCols();
		for (int i = 0; i < stack.getNbRows(); i++) {
			this.displayCardRow(i);

			if(i % 2 == 0 && i < 6){
				System.out.print("          1 ♥  <- [ " + (nbCols += 1) + " ]");
			}

			System.out.println("");
		}
		this.displayNumberRow();
		this.displayPioche();
	}

	private void changeInterface() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
	}

	private void displayPioche() {
		ArrayList<Card> cards = this.stack.getPioche();

		System.out.println();

		if (cards.size() > 0) {
			Card card = cards.get(0);

			card.setVisible(true);
			System.out.print("Pour piocher une carte tapez (*) | ");
			System.out.print("Cartes restantes : " + cards.size());
			System.out.println(" | Prochaine carte : " + card.toString());
		}else{
			System.out.println("La pioche est vide !");
		}
	}

	private void displayNumberRow() {
		for (int col = 0; col < stack.getNbCols(); col++) { // pour chaques colonnes
			System.out.print("   [ " + (col+1) + " ]  ");
		}
		System.out.println("");
	}


}

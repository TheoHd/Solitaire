package client;

import app.Card;
import app.Stack;

import java.util.ArrayList;

public class Display {

	private String[][] matrix;
	private Stack stack;
	private Integer nbCols;

	public Display(Stack stack){
		this.stack = stack;
		this.matrix = stack.getMatrix();
	}

	public void setMatrix(String[][] matrix) { this.matrix = matrix; }


	/*
	 * Affiche une colonne (rangé) du plateau
	 */
	private void displayCardRow(int row) {
		for (int col = 0; col < stack.getNbCols(); col++) { // pour chaques colonnes
			if( this.matrix[row][col] == null ){
				System.out.print("          ");
			}else{
				System.out.print("   " + matrix[row][col]  + "   |");
			}
		}
	}

	/*
	 * Affiche tout les elements necessaires pour voir le plateau de jeu complet
	 */
	public void displayGameBoard(){
		this.changeInterface();

		this.nbCols = stack.getNbCols();

		this.displayNumberRow();
		System.out.println();
		for (int i = 0; i < stack.getNbRows(); i++) {

			this.displayCardRow(i);
			this.displayWinPile(i);

			System.out.println("");
		}
		this.displayPioche();
	}

	/*
	 * Affiche les piles necessaires pour gagner une partie
	 */
	private void displayWinPile(int i) {
		if(i % 2 == 0 && i < 8){

			this.nbCols += 1;
			ArrayList<Card> col = this.stack.getCol(nbCols - 1);

			if(col.size() != 0){
				Card lastCard = col.get(col.size() - 1);
				System.out.print("          " + lastCard.toString() + "  <- [ " + (nbCols) + " ]");
			}else{
				System.out.print("          VIDE  <- [ " + (nbCols) + " ]");
			}
		}
	}

	/*
	 * Saute plein de ligne pour "simuler" un changement de plateau
	 */
	private void changeInterface() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
	}


	/*
	 * Affiche la première carte de la pioche
	 */
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


	/*
	 * Affiche une ligne de carte pour chaques colonnes
	 */
	private void displayNumberRow() {
		for (int col = 0; col < stack.getNbCols(); col++) { // pour chaques colonnes
			System.out.print("  [ " + (col+1) + " ]   ");
		}
		System.out.println("");
	}


}

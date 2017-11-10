package app;

import static app.Helper.verifInt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringJoiner;

public class Stack {

	private final ArrayList<Card> cartes;
	private ArrayList<ArrayList> cols = new ArrayList<>();
	private String[][] matrix;
	private Scanner sc = new Scanner(System.in);

	private Integer nbCols = 7;
	private Integer nbRows = 19;

	public Stack() {
		Pioche pioche = new Pioche();
		ArrayList<Card> cartes = pioche.getPioche();

		for (int i = 0; i < this.nbCols; i++) {
			ArrayList<Card> col = new ArrayList<>();
			this.cols.add(i, col);
		}

		this.cartes = cartes;
		this.initializeStacks();
		this.matrix = this.createMatrice( this.cols );
	}

	public Integer getNbCols() { return nbCols; }
	public Integer getNbRows() { return nbRows; }
	public String[][] getMatrix() { return matrix; }
	
	private void initializeStacks(){
		for (int i = 0; i < 7; i++){

			for (int j = 0; j <= i-1; j++){
				Card hidden_card = this.cartes.get(j);
				hidden_card.setVisible(false);

				this.cols.get(i).add(hidden_card);
				this.cartes.remove(j);
			}

			Card visible_card = this.cartes.get(i);
			visible_card.setVisible(true);

			this.cols.get(i).add(visible_card);
			this.cartes.remove(i);
		}

	}

	public String[][] createMatrice(ArrayList<ArrayList> cols) {

		String[][] matrix = new String[this.nbRows][this.nbCols];

		for (int i = 0; i < this.nbCols; i++) {
			for (int j = 0; j < this.nbRows; j++) {
				matrix[j][i] = null;
				if (cols.get(i).size() > j) {
					matrix[j][i] = cols.get(i).get(j).toString();
				}
			}
		}
		return matrix;
	}


	public void getCol(Integer nbRow){

		ArrayList<Card> col = this.cols.get(nbRow);

		Card last = col.get(col.size() - 1);

		// TODO

		//System.out.println("Résultat : " + last.toString());

	}
	
	
	
	public void deplacer() {
		
	}
	public void piocher() {
		
	}


}

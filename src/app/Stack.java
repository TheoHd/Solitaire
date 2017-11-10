package app;

import static app.Helper.verifInt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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

		for (int i = 0; i < this.getNbCols(); i++) {
			ArrayList<Card> col = new ArrayList<>();
			this.cols.add(i, col);
		}

		this.cartes = cartes;
		this.initializeStacks();
	}	
	
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

	public String[][] createMatrice(Integer col, Integer row){
		String[][] matrix = new String[row][col];

		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				matrix[j][i] = null;
				if(this.cols.get(i).size() > j){
					matrix[j][i] = this.cols.get(i).get(j).toString();
				}
			}
		}

		return matrix;
	}	
	
	
	
	public void deplacer() {
		
	}
	public void piocher() {
		
	}

	public Integer getNbCols() {
		return nbCols;
	}

	public void setNbCols(Integer nbCols) {
		this.nbCols = nbCols;
	}

	public String[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(String[][] matrix) {
		this.matrix = matrix;
	}

	public Integer getNbRows() {
		return nbRows;
	}

	public void setNbRows(Integer nbRows) {
		this.nbRows = nbRows;
	}
	
}

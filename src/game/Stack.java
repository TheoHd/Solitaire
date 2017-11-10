package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import static game.Helper.verifInt;

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


	public void displayAllStaks(){

		this.matrix = this.createMatrice( this.nbCols, this.nbRows);

		for (int i = 0; i < this.nbRows; i++) {
			this.displayCardRow(i);
		}

		this.displayNumberRow();
	}

	private void displayNumberRow() {
		for (int col = 0; col < this.nbCols; col++) { // pour chaques colonnes
			System.out.print("   [ " + col + " ]  ");
		}
		System.out.println("");
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
	
	private void displayCardRow(int row) {

		for (int col = 0; col < this.nbCols; col++) { // pour chaques colonnes
			if( matrix[row][col] == null ){
				System.out.print("          ");
			}else{
				System.out.print("   " + matrix[row][col]  + "   |");
			}
		}
		System.out.println("");
	}
	
	void deplacer() {
		
	}
	void piocher() {
		
	}
	
}

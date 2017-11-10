package game;

import java.util.ArrayList;
import java.util.Collections;

public class Stack {

	private final ArrayList<Card> cartes;
	private ArrayList<ArrayList> cols = new ArrayList<>();
	private String[][] matrix;

	private Integer nbCols = 7;
	private Integer nbRows = 12;


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
			System.out.println();
		}

	}

	public String[][] createMatrice(Integer col, Integer row){
		String[][] matrix = new String[row][col];

		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				matrix[j][i] = "  ";
				if(this.cols.get(i).size() > j){
					matrix[j][i] = this.cols.get(i).get(j).toString();
				}
			}
		}

		return matrix;
	}


	private void displayCardRow(int row) {

		ArrayList<String> shape = new ArrayList<>();
		shape.add(0, "|--------|  ");
		shape.add(1, "|        |  ");
		shape.add(2, "|   XX   |  ");
		shape.add(3, "|        |  ");
		shape.add(4, "|--------|  ");

		for (int shapeNb = 0; shapeNb < shape.size(); shapeNb++) { // pour chaque "shape"

			for (int col = 0; col < this.nbCols; col++) { // pour chaques colonnes

				if(shapeNb == 2){ // Si c'est le shape ou on affiche la carte
					//shape.remove(2);
					System.out.print("|   " + matrix[row][col]  + "   |  ");
				}
				System.out.print(shape.get(shapeNb));

			}

			System.out.println("");

		}
	}


}

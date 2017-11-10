package app;

import java.util.ArrayList;

public class Stack {

	private final ArrayList<Card> cartes;
	private ArrayList<ArrayList<Card>> cols = new ArrayList<>();
	private String[][] matrix;

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
		this.matrix = this.createMatrice( this.getNbCols(), this.getNbRows() );
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

	public String[][] createMatrice(Integer col, Integer row) {
		String[][] matrix = new String[row][col];

		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				matrix[j][i] = null;
				if (this.cols.get(i).size() > j) {
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

	public void setNbCols(Integer nbCols) {
		this.nbCols = nbCols;
	}

	public void setMatrix(String[][] matrix) {
		this.matrix = matrix;
	}


	public void setNbRows(Integer nbRows) {
		this.nbRows = nbRows;
	}
}

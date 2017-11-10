package client;

import app.Stack;

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
		for (int i = 0; i < stack.getNbRows(); i++) {
			this.displayCardRow(i);
			System.out.println("");
		}
		this.displayNumberRow();
	}

	private void displayNumberRow() {
		for (int col = 0; col < stack.getNbCols(); col++) { // pour chaques colonnes
			System.out.print("   [ " + col + " ]  ");
		}
		System.out.println("");
	}
}

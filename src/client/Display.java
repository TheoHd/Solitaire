package client;

import app.Stack;

public class Display {
	private final String[][] matrix;
	private Stack stack = new Stack();

	public Display(){
		this.matrix = stack.getMatrix();
	}

	private void displayCardRow(int row) {
		for (int col = 0; col < stack.getNbCols(); col++) { // pour chaques colonnes
			if( this.matrix[row][col] == null ){
				System.out.print("          ");
			}else{
				System.out.print("   " + matrix[row][col]  + "   |");
			}
		}
		System.out.println("");
	}

	public void displayAllStacks(){
		for (int i = 0; i < stack.getNbRows(); i++) {
			this.displayCardRow(i);
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

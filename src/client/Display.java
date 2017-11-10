package client;

import app.Stack;

public class Display {
	
	private Stack stack = new Stack();	
	
	private void displayCardRow(int row) {

		for (int col = 0; col < stack.getNbCols(); col++) { // pour chaques colonnes
			if( stack.getMatrix()[row][col] == null ){
				System.out.print("          ");
			}else{
				System.out.print("   " + stack.getMatrix()[row][col]  + "   |");
			}
		}
		System.out.println("");
	}
	public void displayAllStacks(){

		this.matrix = this.createMatrice( stack.getNbCols, stack.getNbRows);

		for (int i = 0; i < this.nbRows; i++) {
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

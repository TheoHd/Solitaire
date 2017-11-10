package app;

import client.Display;

import java.util.ArrayList;
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


	private ArrayList<Card> getCol(Integer nbRow){
		return (ArrayList<Card>) this.cols.get(nbRow);
	}

	private void setCol(Integer nbRow, ArrayList<Card> col){
		this.cols.set(nbRow, col);
	}

	private Integer findElementsToMoves(ArrayList<Card> col){

		Integer firstElement = col.size();

		for (int i = 0; i < col.size() - 1 ; i++) {
			if(col.get(i).isVisible()){

				Card compare1 = col.get(i);
				Card compare2 = col.get(i + 1);

				if(compare1.getValue() > compare2.getValue()){
					firstElement = i;
					break;
				}
			}
		}
		return firstElement - 1; // - 1 car un arrayCollection commence à z&ro
	}

	private void setDisplayedBeforeCardWhenMove(ArrayList<Card> fromCol, Integer idElementToMove) {
		if(idElementToMove - 1 >= 0){
			fromCol.get(idElementToMove - 1).setVisible(true);
		}
	}
	
	public void move(Integer from, Integer to) {

		ArrayList<Card> fromCol = this.getCol(from);
		ArrayList<Card> toCol = this.getCol(to);

		Integer idElementToMove = this.findElementsToMoves(fromCol);

		for (int i = idElementToMove; i < fromCol.size(); i++) {

			Card elementToMove = fromCol.get(i);
			Card lastElement = toCol.get(toCol.size() - 1);

			if( ( lastElement.getValue() > elementToMove.getValue() ) && ( lastElement.getValue() - elementToMove.getValue() == 1 ) ){
				toCol.add(elementToMove);
				fromCol.remove(i);

				this.setDisplayedBeforeCardWhenMove(fromCol, idElementToMove);
			}else{
				System.out.println("");
				System.out.println("-------------------------------------------------");
				System.out.println("> Impossile de déplacer la carte : " + elementToMove.getValue() + " - " + elementToMove.toString() + " !");
				System.out.println("-------------------------------------------------");
				System.out.println("");
			}
		}

		this.setCol(from, fromCol);
		this.setCol(to, toCol);

		this.matrix = this.createMatrice( this.cols );
	}

}

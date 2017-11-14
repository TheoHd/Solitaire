package app;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Stack {

	private final ArrayList<Card> pioche;
	private ArrayList<ArrayList> cols = new ArrayList<>();
	private String[][] matrix;

	private Integer nbCols = 7;
	private Integer nbRows = 19;

	public Stack() {
		Pioche pioche = new Pioche();
		ArrayList<Card> cartes = pioche.getPioche();

		for (int i = 0; i < this.nbCols; i++) {
			ArrayList<Card> col = new ArrayList<>();
			this.cols.add(i, col);
		}

		this.pioche = cartes;
		this.initializeStacks();
		this.matrix = this.createMatrice( this.cols );
	}

	public Integer getNbCols() { return nbCols; }
	public Integer getNbRows() { return nbRows; }
	public String[][] getMatrix() { return matrix; }
	
	private void initializeStacks(){
		for (int i = 0; i < 7; i++){

			for (int j = 0; j <= i-1; j++){
				Card hidden_card = this.pioche.get(j);
				hidden_card.setVisible(false);

				this.cols.get(i).add(hidden_card);
				this.pioche.remove(j);
			}

			Card visible_card = this.pioche.get(i);
			visible_card.setVisible(true);

			this.cols.get(i).add(visible_card);
			this.pioche.remove(i);
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

	public ArrayList<Card> getPioche(){
		return this.pioche;
	}

	private Integer findFirstElementNumberToMove(ArrayList<Card> col){

		Integer firstElement = col.size() - 1; // - 1 car un arrayCollection commence à z&ro

		for (int i = 0; i < col.size() - 1 ; i++) {
			if(col.get(i).isVisible()){

				Card compare1 = col.get(i);
				Card compare2 = col.get(i + 1);

				if(compare1.getValue() > compare2.getValue() && ( compare1.getValue() - compare2.getValue() ) == 1){
					firstElement = i;
					break;
				}
			}
		}
		return firstElement;
	}

	private void setBeforeMovePreviousCardToVisble(ArrayList<Card> fromCol, Integer idElementToMove) {
		if(idElementToMove - 1 >= 0){
			fromCol.get(idElementToMove - 1).setVisible(true);
		}
	}
	
	public void move(Integer from, Integer to) {

		ArrayList<Card> fromCol = this.getCol(from);
		ArrayList<Card> toCol = this.getCol(to);

		boolean result = false;

		Integer idElementToMove = this.findFirstElementNumberToMove(fromCol);

		if(!this.isPileCard(fromCol)){
			result = this.moveOneCard(fromCol, toCol, idElementToMove);
		}else{
			result = this.moveStack(fromCol, toCol, idElementToMove);
		}

		if(result){
			this.setCol(from, fromCol);
			this.setCol(to, toCol);
			this.matrix = this.createMatrice( this.cols );
		}
	}

	public Boolean addPiocheCardOnPile(){

		Random rand = new Random();
		Integer randomColNumber = rand.nextInt(this.nbCols);

		ArrayList<Card> col = this.getCol(randomColNumber);

		if (this.pioche.size() > 0) {

			Card card = this.pioche.get(0);

			col.add(card);
			this.pioche.remove(0);
			this.setCol(randomColNumber, col);
			this.matrix = this.createMatrice( this.cols );

			return true;
		}

		return false;
	}

	private boolean isPileCard(ArrayList<Card> fromCol) {

		Integer Nbfirst = this.findFirstElementNumberToMove(fromCol);
		Integer NbLast = fromCol.size() - 1;

		return Nbfirst != NbLast;
	}


	public boolean moveStack(ArrayList<Card> fromCol, ArrayList<Card> toCol, int idElementToMove){

		for (int i = idElementToMove; i <= fromCol.size(); i++) {
			this.moveOneCard(fromCol, toCol, idElementToMove);
		}

		return true;
	}

	public Boolean moveOneCard(ArrayList<Card> fromCol, ArrayList<Card> toCol, int idElementToMove){

		//System.out.println("Carte à déplacer : " + (idElementToMove) + " - " + fromCol.get(idElementToMove));

		Card elementToMove = fromCol.get(idElementToMove);

		boolean moveAuthorisation = false;

		if(toCol.size() > 0){
			Card lastElement = toCol.get(toCol.size() - 1);
			if( ( lastElement.getValue() > elementToMove.getValue() ) && ( lastElement.getValue() - elementToMove.getValue() == 1 ) ){
				moveAuthorisation = true;
			}
		}else {
			moveAuthorisation = true;
		}

		if(moveAuthorisation){
			toCol.add(elementToMove);
			fromCol.remove(idElementToMove);

			this.setBeforeMovePreviousCardToVisble(fromCol, idElementToMove);
			return true;
		}else{
			System.out.println("");
			System.out.println("-------------------------------------------------");
			System.out.println("> Impossile de déplacer la carte : " + elementToMove.getValue() + " - " + elementToMove.toString() + " !");
			System.out.println("-------------------------------------------------");
			System.out.println("");
			return false;
		}
	}

}

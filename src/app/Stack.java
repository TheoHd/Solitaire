package app;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Stack {

	private final ArrayList<Card> pioche;
    private final int lastValue;
    public ArrayList<ArrayList> cols = new ArrayList<>();
	private String[][] matrix;

	private Integer nbCols = 7;
	private Integer nbRows = 19;
	private Integer nbWinPile = 4;

	public Integer getNbCols() { return nbCols; }
	public Integer getNbWinPile() { return nbWinPile; }
	public Integer getNbRows() { return nbRows; }
	public String[][] getMatrix() { return matrix; }
	public ArrayList getCol(Integer nbRow){ return this.cols.get(nbRow); }
	private void setCol(Integer nbRow, ArrayList<Card> col){ this.cols.set(nbRow, col); }
	public ArrayList<Card> getPioche(){ return this.pioche; }


	/*
	* Constructeur, initialise toutes les piles du jeu (sur le plateau et les piles pour gagner)
	*/
	public Stack() {
		Pioche pioche = new Pioche();
		ArrayList<Card> cartes = pioche.getPioche();
        this.lastValue = pioche.getLastValue();

		for (int i = 0; i < this.nbCols + this.nbWinPile; i++) {
			ArrayList<Card> col = new ArrayList<>();
			this.cols.add(i, col);
		}

		this.pioche = cartes;
		this.initializeStacks();
		this.matrix = this.createMatrice( this.cols );
	}

	/*
	 * Remplie les colonnes du plateau avec des cartes cachées et retournées.
	 */
	private void initializeStacks(){
		for (int i = 0; i < this.nbCols ; i++){

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

	/*
	 * Creer une matrice qui servira pour l'affichage du plateau
	 */
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


	/*
	 * Trouve le premier element à déplacer dans une pile
	 */
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

	/*
	 * Rend visible la carte précédent la carte qui va être bougé
	 */
	private void setBeforeMovePreviousCardToVisble(ArrayList<Card> fromCol, Integer idElementToMove) {
		if(idElementToMove - 1 >= 0){
			fromCol.get(idElementToMove - 1).setVisible(true);
		}
	}

	/*
	 * Method qui va initialisé le déplacement d'une ou plusieurs cartes
	 */
	public void move(Integer idFrom, Integer idTo) {

		ArrayList<Card> fromCol = this.getCol(idFrom);
		ArrayList<Card> toCol = this.getCol(idTo);

		boolean result = false;

		Integer idElementToMove = this.findFirstElementNumberToMove(fromCol);

		if(!this.isPileCard(idFrom)){
			result = this.moveOneCard(idFrom, idTo, idElementToMove);
		}else{
			result = this.moveStack(idFrom, idTo, idElementToMove);
		}

		if(result){
			this.setCol(idFrom, fromCol);
			this.setCol(idTo, toCol);
			this.matrix = this.createMatrice( this.cols );
		}
	}

	/*
	 * Ajoute une carte de la pioche dans une pile random du tableau
	 */
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

	/*
	 * Retourne true si plusieurs cartes doivent être déplacer
	 */
	private boolean isPileCard(int idFrom) {

		ArrayList<Card> fromCol = this.getCol(idFrom);

		int Nbfirst = this.findFirstElementNumberToMove(fromCol);
		int NbLast = fromCol.size() - 1;

		return Nbfirst != NbLast;
	}


	/*
	 * Déplace un ensemble de carte
	 */
	public boolean moveStack(int idFrom, int idTo, int idElementToMove){

		ArrayList<Card> fromCol = this.getCol(idFrom);

		for (int i = idElementToMove; i <= fromCol.size(); i++) {
			this.moveOneCard(idFrom, idTo, idElementToMove);
		}

		return true;
	}

	/*
	 * Déplace une carte
	 */
	public Boolean moveOneCard(int idFrom, int idTo, int idElementToMove){

		ArrayList<Card> fromCol = this.getCol(idFrom);
		ArrayList<Card> toCol = this.getCol(idTo);

		Card elementToMove = fromCol.get(idElementToMove);

		boolean moveAuthorisation = false;

		int totalCols = this.nbCols + this.nbWinPile;
		ArrayList<Integer> listWinPileId = new ArrayList<>();
		for (int i = this.nbCols; i <= totalCols; i++) {
			listWinPileId.add(i);
		}

		if(toCol.size() > 0){

			if(listWinPileId.contains(idTo)){
				if( fromCol.get(idElementToMove).getValue() - 1 == toCol.get( toCol.size() - 1).getValue() ){
					moveAuthorisation = true;
				}
			}else{
				Card lastElement = toCol.get(toCol.size() - 1);
				if( lastElement.getValue() - elementToMove.getValue() == 1 ){
					moveAuthorisation = true;
				}
			}

		}else {

			if(listWinPileId.contains(idTo)){
				if( fromCol.get(idElementToMove).getValue() == 1 ){
					moveAuthorisation = true;
				}
			}else{
				moveAuthorisation = true;
			}
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


	/*
	* Methode qui vérifies ques les piles sont bien remplie.
	*/

	public boolean hasWin(){

        int totalCols = this.nbCols + this.nbWinPile;
        for (int i = this.nbCols; i < totalCols; i++) {

            ArrayList<Card> col = this.getCol(i);
            if(col.size() > 0){

                Card lastElement = col.get( col.size() - 1 );
                if( lastElement.getValue() == this.lastValue ){
                    return false;
                }

            }else{
                return false;
            }
        }

        return true;
    }

}

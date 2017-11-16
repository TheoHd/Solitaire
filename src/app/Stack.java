package app;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Stack {

	private final ArrayList<Card> pioche;
    private final int lastValue;
    public ArrayList<ArrayList> cols = new ArrayList<>();
	private String[][] matrix;
	private ArrayList<Integer> listWinPileId = new ArrayList<>();

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
		int totalCols = this.nbCols + this.nbWinPile;

		for (int i = 0; i < totalCols; i++) {
			ArrayList<Card> col = new ArrayList<>();
			this.cols.add(i, col);
		}

		for (int i = this.nbCols; i <= totalCols; i++) {
			this.listWinPileId.add(i);
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
	private Integer findFirstElementNumberToMove(ArrayList<Card> toCol, ArrayList<Card> fromCol){

		Integer firstElement;
		if( this.listWinPileId.contains(fromCol) ){
			firstElement = toCol.size() - 1;
		}else{

			firstElement = toCol.size() - 1; // - 1 car un arrayCollection commence à z&ro

			for (int i = 0; i < toCol.size() - 1 ; i++) {
				if(toCol.get(i).isVisible()){

					Card compare1 = toCol.get(i);
					Card compare2 = toCol.get(i + 1);

					if( (compare1.getValue() - compare2.getValue() ) == 1){
						firstElement = i;
						break;
					}
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

		Integer idElementToMove = this.findFirstElementNumberToMove(fromCol, toCol);

		if(!this.isPileCard(idFrom, idTo)){
			result = this.moveOneCard(idFrom, idTo, idElementToMove);
		}else{

			if( this.listWinPileId.contains(idTo) ){
				result = this.moveStackOnWinPileCard(idFrom, idTo, idElementToMove);
			}else{
				result = this.moveStack(idFrom, idTo, idElementToMove);
			}
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
	private boolean isPileCard(int idFrom, int idTo) {

		ArrayList<Card> fromCol = this.getCol(idFrom);
		ArrayList<Card> toCol = this.getCol(idTo);

		int Nbfirst = this.findFirstElementNumberToMove(fromCol, toCol);
		int NbLast = fromCol.size() - 1;

		return Nbfirst != NbLast;
	}

	/*
	 * Déplace un ensemble de carte
	 */
	public boolean moveStack(int idFrom, int idTo, int idElementToMove){

		ArrayList<Card> fromCol = this.getCol(idFrom);

		System.out.println(idElementToMove);
		System.out.println(fromCol.size());
		System.out.println("");

		int nbTour = fromCol.size() - idElementToMove;
		System.out.println(nbTour);

		for (int i = 0; i <= nbTour - 1; i++) {
			this.moveOneCard(idFrom, idTo, idElementToMove);
		}

		return true;
	}

	/*
	 * Déplace un ensemble de carte
	 */
	public boolean moveStackOnWinPileCard(int idFrom, int idTo, int idElementToMove){

		ArrayList<Card> fromCol = this.getCol(idFrom);

		for (int i = fromCol.size(); i >= idElementToMove ; i--) {
			this.moveOneCard(idFrom, idTo, i);
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

		if(toCol.size() > 0){

			if(this.listWinPileId.contains(idTo)){
				if( fromCol.get(idElementToMove).getValue() - 1 == toCol.get( toCol.size() - 1).getValue() ){
					moveAuthorisation = true;
				}
			}else{
				Card lastElement = toCol.get(toCol.size() - 1);
				if( lastElement.getValue() - elementToMove.getValue() == 1 ){
					moveAuthorisation = true;
				}
			}

		}else{

			if(this.listWinPileId.contains(idTo)){
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

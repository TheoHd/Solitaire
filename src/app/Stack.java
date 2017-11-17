package app;

import java.util.ArrayList;

public class Stack {

	private ArrayList<Card> pioche;
    private final int lastValue;
    public ArrayList<ArrayList> cols = new ArrayList<>();
	private String[][] matrix;
	private ArrayList<Integer> listWinPileId = new ArrayList<>();

	private Integer nbCols = 7;
	private Integer nbRows = 19;
	private Integer nbWinPile = 4;

	private ArrayList<ArrayList> history = new ArrayList<>();
	private Integer currentHistoryVersion = 0;

	public Integer getNbCols() { return nbCols; }
	public Integer getCurrentHistoryVersion() { return currentHistoryVersion; }
	public Integer getNbRows() { return nbRows; }
	public String[][] getMatrix() { return matrix; }
	public ArrayList<Card> getCol(Integer nbRow){ return this.cols.get(nbRow); }
	private void setCol(Integer nbRow, ArrayList<Card> col){ this.cols.set(nbRow, col); }
	public ArrayList<Card> getPioche(){ return this.pioche; }

	/*
	* Constructeur, initialise toutes les piles du jeu (sur le plateau et les piles pour gagner)
	*/
	public Stack() {
		Pioche pioche = new Pioche();
		this.pioche = pioche.getPioche();
        this.lastValue = pioche.getLastValue();
		int totalCols = this.nbCols + this.nbWinPile;

		for (int i = 0; i < totalCols; i++) {
			ArrayList<Card> col = new ArrayList<>();
			this.cols.add(i, col);
		}

		for (int i = this.nbCols; i <= totalCols; i++) {
			this.listWinPileId.add(i);
		}

		this.initializeStacks();
		this.matrix = this.createMatrice( this.cols );
	}

	/*
	 * Remplie les colonnes du plateau avec des cartes cachées et retournées.
	 */
	private void initializeStacks(){
		for (int i = 0; i < this.nbCols ; i++){

			for (int j = 0; j <= i-1; j++){
				Card hidden_card = this.pioche.get(0);
				hidden_card.setVisible(false);

				this.cols.get(i).add(hidden_card);
				this.pioche.remove(0);
			}

			Card visible_card = this.pioche.get(0);
			visible_card.setVisible(true);

			this.cols.get(i).add(visible_card);
			this.pioche.remove(0);
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

	/**
	 * Method qui va initialisé le déplacement d'une ou plusieurs cartes
	 */
	public boolean move(Integer idFrom, Integer idTo) {

		ArrayList<Card> fromCol = this.getCol(idFrom);
		ArrayList<Card> toCol = this.getCol(idTo);

		boolean result = false;

		Integer idElementToMove = this.findFirstElementNumberToMove(fromCol, toCol);

		if(!this.isPileCard(idFrom, idTo)){
			result = this.moveOneCard(idFrom, idTo, idElementToMove, false);
		}else{

			if( this.listWinPileId.contains(idTo) ){
				result = this.moveStackOnWinPileCard(idFrom, idTo, idElementToMove);
			}else{
				result = this.moveStack(idFrom, idTo, idElementToMove);
			}
		}

		if(result){
			this.matrix = this.createMatrice( this.cols );
			return true;
		}

		return false;
	}

	/**
	 * Ajoute une carte de la pioche dans une pile
	 */
	public Boolean addPiocheCardOnPile(Integer intColTo){

		if (this.pioche.size() > 0) {
			this.moveOneCard(0, intColTo, 0, true);
			this.matrix = this.createMatrice( this.cols );
			return true;
		}

		return false;
	}

	/**
	 * Change la première carte de la pioche et le mets en dernière position
	 */
	public void changePiocheCard(){
		Card card = this.pioche.get(0);
		this.pioche.remove(0);
		this.pioche.add(card);
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

		int nbTour = fromCol.size() - idElementToMove;

		for (int i = 0; i <= nbTour - 1; i++) {
			this.moveOneCard(idFrom, idTo, idElementToMove, false);
		}

		return true;
	}

	/*
	 * Déplace un ensemble de carte
	 */
	public boolean moveStackOnWinPileCard(int idFrom, int idTo, int idElementToMove){

		ArrayList<Card> fromCol = this.getCol(idFrom);

		for (int i = fromCol.size() - 1; i >= idElementToMove ; i--) {
			this.moveOneCard(idFrom, idTo, i, false);
		}

		return true;
	}

	/*
	 * Déplace une carte
	 */
	public Boolean moveOneCard(int idFrom, int idTo, int idElementToMove, boolean getOnPileCard){

		ArrayList<Card> fromCol = this.getCol(idFrom);
		if(getOnPileCard) {
			fromCol = this.pioche;
		}


		ArrayList<Card> toCol = this.getCol(idTo);
		Card elementToMove = fromCol.get(idElementToMove);

		boolean moveAuthorisation = false;

		int compareToShape = -1;
		if(fromCol.get(idElementToMove).getShapeId() == 0){ compareToShape = 1; }
		if(fromCol.get(idElementToMove).getShapeId() == 1){ compareToShape = 0; }
		if(fromCol.get(idElementToMove).getShapeId() == 2){ compareToShape = 3; }
		if(fromCol.get(idElementToMove).getShapeId() == 3){ compareToShape = 2; }

		if(toCol.size() > 0){

			if(this.listWinPileId.contains(idTo)){
				Card lastElement = toCol.get(toCol.size() - 1);
				if( ( fromCol.get(idElementToMove).getValue() - 1 == lastElement.getValue() ) && ( compareToShape == lastElement.getShapeId() ) ){
					moveAuthorisation = true;
				}
			}else{
				Card lastElement = toCol.get(toCol.size() - 1);
				if( ( lastElement.getValue() - elementToMove.getValue() == 1 ) && ( compareToShape != lastElement.getShapeId() && compareToShape != elementToMove.getShapeId() ) ){
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

			this.addCurrentMouvementToHistory( this.getCurrentHistoryVersion() + 1 );
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
                if( lastElement.getValue() != this.lastValue ){
                    return false;
                }

            }else{
                return false;
            }
        }

        return true;
    }

	/*
	 * Methode pour undo un ou des déplacements
	 */
	public void addCurrentMouvementToHistory(Integer idHistory){
		ArrayList<ArrayList> mouvement = new ArrayList<>();
		mouvement.add(this.cols);
		mouvement.add(this.pioche);
		this.history.add(idHistory, mouvement);

		this.currentHistoryVersion = idHistory;

		System.out.println("Added version " + idHistory + " - " + this.cols);
	}

	public Boolean undo(){
		if(currentHistoryVersion >= 0) {

			//ArrayList<ArrayList> version = this.history.get( this.currentHistoryVersion-1 );

			//this.cols = version.get(0);
			//this.pioche = version.get(1);

			//System.out.println( "Retour à la version : #" + ( this.currentHistoryVersion-1 ) );

			//System.out.println( " | Current version :" + this.cols.toString() );
			//System.out.println( " | Previous version:" + version.get(0).toString() );

			//this.matrix = this.createMatrice( version.get(0) );
			//this.currentHistoryVersion -= 1;
			return true;
		}
		return false;
	}

	public boolean redo() {
		return false;
	}
}

/*
 *
 * TODO : Redo & Undo
 *
 */
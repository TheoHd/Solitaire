package game;

import java.util.ArrayList;

public class Stack {

	private final ArrayList<Card> cartes;
	private final ArrayList<Card> row1 = null;
	private final ArrayList<Card> row2 = null;
	private final ArrayList<Card> row3 = null;
	private final ArrayList<Card> row4 = null;
	private final ArrayList<Card> row5 = null;
	private final ArrayList<Card> row6 = null;
	private final ArrayList<Card> row7 = null;
	
	public Stack() {

		Pioche pioche = new Pioche();
		ArrayList<Card> cartes = pioche.getPioche();
		this.cartes = cartes;

	}	
	
	public void initializeStacks(){
		for (int i = 0; i < 7; i++){

			Card visile_card = this.cartes.get(i);
			visile_card.setVisible(1);
			this.row1.add(visile_card);
			this.cartes.remove(i);

			for (int j = 0; j <= i-1; j++){

				Card hidden_card = this.cartes.get(j);
				hidden_card.setVisible(0);
				if(i == 0) {
					this.row1.add(hidden_card);
				}
				if(i == 1) {
					this.row2.add(hidden_card);
				}
				if(i == 2) {
					this.row3.add(hidden_card);
				}
				if(i == 3) {
					this.row4.add(hidden_card);
				}
				if(i == 4) {
					this.row5.add(hidden_card);
				}
				if(i == 5) {
					this.row6.add(hidden_card);
				}
				if(i == 6) {
					this.row7.add(hidden_card);
				}
				this.cartes.remove(j);
			}
		}
		
	}



	
}

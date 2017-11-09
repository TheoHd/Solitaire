package game;

import java.util.ArrayList;

public class Stack {

	private final ArrayList<Card> cartes;
	
	private final ArrayList<Card> row1 = null;
	
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
				this.row1.add(hidden_card);

				this.cartes.remove(j);
			}
		}
		
	}



	
}

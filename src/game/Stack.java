package game;

import java.util.ArrayList;

public class Stack {

	private final ArrayList<Card> cartes;
	private ArrayList<Card> row1;
	private ArrayList<Card> row2;
	private ArrayList<Card> row3;
	private ArrayList<Card> row4;
	private ArrayList<Card> row5;
	private ArrayList<Card> row6;
	private ArrayList<Card> row7;
	
	public Stack() {
		Pioche pioche = new Pioche();
		ArrayList<Card> cartes = pioche.getPioche();
		this.cartes = cartes;

		this.initializeStacks();
	}	
	
	private void initializeStacks(){
		for (int i = 0; i < 7; i++){

			Card visile_card = this.cartes.get(i);
			visile_card.setVisible(1);
			if(i == 0) { this.row1.add(visile_card); }
			if(i == 1) { this.row2.add(visile_card); }
			if(i == 2) { this.row3.add(visile_card); }
			if(i == 3) { this.row4.add(visile_card); }
			if(i == 4) { this.row5.add(visile_card); }
			if(i == 5) { this.row6.add(visile_card); }
			if(i == 6) { this.row7.add(visile_card); }
			this.cartes.remove(i);

			for (int j = 0; j <= i-1; j++){

				Card hidden_card = this.cartes.get(j);
				hidden_card.setVisible(0);
				if(i == 0) { this.row1.add(hidden_card); }
				if(i == 1) { this.row2.add(hidden_card); }
				if(i == 2) { this.row3.add(hidden_card); }
				if(i == 3) { this.row4.add(hidden_card); }
				if(i == 4) { this.row5.add(hidden_card); }
				if(i == 5) { this.row6.add(hidden_card); }
				if(i == 6) { this.row7.add(hidden_card); }
				this.cartes.remove(j);
			}
		}

		this.displayAllStaks();
		
	}

	public void displayAllStaks(){

		System.out.println(this.row1);
		System.out.println(this.row2);
		System.out.println(this.row3);
		System.out.println(this.row4);
		System.out.println(this.row5);
		System.out.println(this.row6);
		System.out.println(this.row7);
	}



	
}

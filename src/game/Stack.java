package game;

import java.util.ArrayList;

public class Stack {

	private final ArrayList<Card> cartes;
	private ArrayList<Card> col1 = new ArrayList<>();
	private ArrayList<Card> col2 = new ArrayList<>();
	private ArrayList<Card> col3 = new ArrayList<>();
	private ArrayList<Card> col4 = new ArrayList<>();
	private ArrayList<Card> col5 = new ArrayList<>();
	private ArrayList<Card> col6 = new ArrayList<>();
	private ArrayList<Card> col7 = new ArrayList<>();
	
	public Stack() {
		Pioche pioche = new Pioche();
		ArrayList<Card> cartes = pioche.getPioche();
		this.cartes = cartes;

		this.initializeStacks();
	}	
	
	private void initializeStacks(){
		for (int i = 0; i < 7; i++){

			Card visible_card = this.cartes.get(i);
			visible_card.setVisible(1);
			if(i == 0) { this.col1.add(visible_card); }
			if(i == 1) { this.col2.add(visible_card); }
			if(i == 2) { this.col3.add(visible_card); }
			if(i == 3) { this.col4.add(visible_card); }
			if(i == 4) { this.col5.add(visible_card); }
			if(i == 5) { this.col6.add(visible_card); }
			if(i == 6) { this.col7.add(visible_card); }
			this.cartes.remove(i);

			for (int j = 0; j <= i-1; j++){

				Card hidden_card = this.cartes.get(j);
				hidden_card.setVisible(0);
				if(i == 0) { this.col1.add(hidden_card); }
				if(i == 1) { this.col2.add(hidden_card); }
				if(i == 2) { this.col3.add(hidden_card); }
				if(i == 3) { this.col4.add(hidden_card); }
				if(i == 4) { this.col5.add(hidden_card); }
				if(i == 5) { this.col6.add(hidden_card); }
				if(i == 6) { this.col7.add(hidden_card); }
				this.cartes.remove(j);
			}
		}

		//this.displayAllStaks();
		
	}

	public void displayAllStaks(){

		System.out.println(this.col1);
		System.out.println(this.col2);
		System.out.println(this.col3);
		System.out.println(this.col4);
		System.out.println(this.col5);
		System.out.println(this.col6);
		System.out.println(this.col7);
	}



	
}

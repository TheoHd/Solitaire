package app;

class Card {
	
	private int value;
	private String shape;
	
	private Card(int value, String shape){
		this.value = 0;
		this.shape = "";
	}
	
	int getValue() {
		return this.value;
	}
	String getColor() {
		return this.shape;
	}
	@Override
	public String toString() {
		return "Prout";
	}
}

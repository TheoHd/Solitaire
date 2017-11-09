package app;

class Card {
	
	private String[] value = {"1","2","3","4","5","6","7","8","9","10","V","R","D"};
	private String[] shape = {"♥","♠","♦","♣"};
	
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
		return "Test";
	}
}

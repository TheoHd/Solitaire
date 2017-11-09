package game;

class Card {
	
	private String[] value = {"1","2","3","4","5","6","7","8","9","10","V","D","R"};
	private String[] shape = {"♥","♠","♦","♣"};
	
	private Card(String [] value, String [] shape) {
		this.value = value;
		this.shape = shape;
	}
}

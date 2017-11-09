package game;

public class Card {
	
	private String[] value = {"1","2","3","4","5","6","7","8","9","10","V","D","R"};
	private String[] shape = {"♥","♠","♦","♣"};
	
	int getValue() {
		return this.value;
	}
	String getColor() {
		return this.shape;
	}
	@Override
	public String toString() {
		System.out.println(this.value);
		System.out.println(this.shape);
	}
}

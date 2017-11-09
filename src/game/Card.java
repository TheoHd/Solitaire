package game;

class Card {

	private final String value;
	private final String shape;

	public Card(String value, String shape) {
		this.value = value;
		this.shape = shape;
	}

	@Override
	public String toString() {
		return this.value + this.shape;
	}
}

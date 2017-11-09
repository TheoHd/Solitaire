package game;

class Card {

	private final String value;
	private final String shape;
	private Integer visible;

	public Card(String value, String shape) {
		this.value = value;
		this.shape = shape;
	}

    public Card setVisible(Integer visible) { this.visible = visible; return this; }
    public Integer getVisible(){ return this.visible; }

	@Override
	public String toString() {
		return this.value + this.shape;
	}
}

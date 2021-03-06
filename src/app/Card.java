package app;

public class Card {

	private final Integer value;
	private final String shape;
	private final Integer shapeId;
	private Boolean visible = false;

	public Card(Integer value, String shape, Integer shapeId) {
		this.value = value;
		this.shape = shape;
		this.shapeId = shapeId;
	}

	public Card setVisible(Boolean visible) {
		this.visible = visible;
		return this;
	}

	public Integer getValue() { return value; }
	public Integer getShapeId() { return shapeId; }
	public Boolean isVisible() {
		return this.visible;
	}

	@Override
	public String toString() {
		String val;

		if (this.visible) {
			val = this.getStringValue() + this.shape;
		} else {
			val = "***";
		}

		return val;
	}


	public String getStringValue() {
		if (this.value == 1) {
			return "A ";
		} else if (this.value == 10) {
			return "10";
		}else if (this.value == 11) {
			return "V ";
		} else if (this.value == 12) {
			return "D ";
		} else if (this.value == 13) {
			return "R ";
		} else {
			return String.valueOf(this.value) + ' ';
		}

	}

}

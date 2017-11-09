package app;

import java.util.ArrayList;

public class Stack {
	
	private int id;
	private int length;
	private ArrayList stack;
	
	public Stack(int id, int length) {
		this.id = 0;
		this.length = 0;
		this.stack = new ArrayList();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
}

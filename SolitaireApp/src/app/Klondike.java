package app;

import app.Card;
import app.Stack;

public class Klondike {
	
	//First line
	private Stack recto_stack;
	private Stack verso_stack;
	private Stack slot_one_card;
	private Stack slot_second_card;
	private Stack slot_third_card;
	private Stack slot_fourth_card;
	
	//Second line
	private Stack stack_first;
	private Stack stack_second;
	private Stack stack_third;
	private Stack stack_fourth;
	
	private Klondike(Stack recto_stack, Stack verso_stack
			, Stack slot_one_card, Stack slot_second_card
			, Stack slot_third_card, Stack slot_fourth_card
			, Stack stack_first, Stack stack_second
			, Stack stack_third, Stack stack_fourth) {
		this.recto_stack = new Stack(0,52);
		this.verso_stack = new Stack(0,52);
		this.slot_one_card = new Stack(0,52);
		this.slot_second_card = new Stack(0,52);
		this.slot_third_card = new Stack(0,52);
		this.slot_fourth_card = new Stack(0,52);
		this.stack_first = new Stack(0,52);
		this.stack_second = new Stack(0,52);
		this.stack_third = new Stack(0,52);
		this.stack_fourth = new Stack(0,52);
	}

	public Stack getRecto_stack() {
		return recto_stack;
	}

	public void setRecto_stack(Stack recto_stack) {
		this.recto_stack = recto_stack;
	}

	public Stack getVerso_stack() {
		return verso_stack;
	}

	public void setVerso_stack(Stack verso_stack) {
		this.verso_stack = verso_stack;
	}

	public Stack getSlot_one_card() {
		return slot_one_card;
	}

	public void setSlot_one_card(Stack slot_one_card) {
		this.slot_one_card = slot_one_card;
	}

	public Stack getSlot_second_card() {
		return slot_second_card;
	}

	public void setSlot_second_card(Stack slot_second_card) {
		this.slot_second_card = slot_second_card;
	}

	public Stack getSlot_third_card() {
		return slot_third_card;
	}

	public void setSlot_third_card(Stack slot_third_card) {
		this.slot_third_card = slot_third_card;
	}

	public Stack getSlot_fourth_card() {
		return slot_fourth_card;
	}

	public void setSlot_fourth_card(Stack slot_fourth_card) {
		this.slot_fourth_card = slot_fourth_card;
	}

	public Stack getStack_first() {
		return stack_first;
	}

	public void setStack_first(Stack stack_first) {
		this.stack_first = stack_first;
	}

	public Stack getStack_second() {
		return stack_second;
	}

	public void setStack_second(Stack stack_second) {
		this.stack_second = stack_second;
	}

	public Stack getStack_third() {
		return stack_third;
	}

	public void setStack_third(Stack stack_third) {
		this.stack_third = stack_third;
	}

	public Stack getStack_fourth() {
		return stack_fourth;
	}

	public void setStack_fourth(Stack stack_fourth) {
		this.stack_fourth = stack_fourth;
	}
}

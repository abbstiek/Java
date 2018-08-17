/*******Abygail Stiekman********/
/****COP 3252 Adv Java**********/
/******Semester Proj************/
/***********aes15d**************/


/**
 * Card object held in decks and hands.
 */

public class Card {
	
	private int value;
	private int suit;
	
	/**
	 * Constructor
	 * @param val
	 * @param su
	 */
	public Card(int val, int su) {
		this.value = val;
		this.suit = su;
	}
	
	/**
	 * Value getter
	 * @return value
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Suit getter
	 * @return suit
	 */
	public int getSuit() {
		return suit;
	}
	
	public String toString() {
		if (this.suit == 1) {
			return "S" + value;
		}
		else if (this.suit == 2) {
			return "C" + value;
		}
		else if (this.suit == 3) {
			return "D" + value;
		}
		else {
			return "H" + value;
		}
	}
}

import java.util.ArrayList;
import java.util.Random;

/**
 * Deck object holding array of 52 cards.
 * 
 * @authors Nathan Brazee, Kyle Button
 */

public class Deck {
	
	private ArrayList<Card> deck;
	
	/**
	 * Constructor
	 */
	public Deck() {
		deck = new ArrayList<Card>();
		// generate card values and add to new deck
		for (int n = 0; n < 4; n++) {
			for (int i = 2; i <= 14; i++) {
				deck.add(new Card(i, n));
			}
		}
	}
	
	/**
	 * Removes 1 card from the deck and returns it.
	 * @return dealtCard
	 */
	public Card deal() {
		
		Card dealtCard;
		// conceptually pulling a random card from the list of ordered cards
		// as opposed to having a shuffle algorithm and pulling from top
		// subject to change
		long seed = System.currentTimeMillis();
		Random random = new Random(seed);
		int i = random.nextInt(deck.size()-1);
		
		dealtCard = deck.get(i);
		deck.remove(i);
		return dealtCard;
	}
	
	/**
	 * Burn 1 card off the deck.
	 */
	public void burn() {
		long seed = System.currentTimeMillis();
		Random random = new Random(seed);
		int i = random.nextInt(deck.size());
	
		deck.remove(i);
	}
}

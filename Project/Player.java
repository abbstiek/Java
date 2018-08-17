import java.util.ArrayList;

/**
 * Player object knows of a hand of cards, including the
 * 5 community cards as well as his own 2 cards. Also holds
 * money data for betting
 */

 /*******Abygail Stiekman********/
/****COP 3252 Adv Java**********/
/******Semester Proj************/
/***********aes15d**************/
 
public class Player {
	private ArrayList<Card> community, hand, all;
	private int money, currentBet;
	
	/**
	 * Constructor
	 */
	public Player() {
		this.community = new ArrayList<Card>();
		this.hand = new ArrayList<Card>();
		this.all = new ArrayList<Card>();
		this.money = 500; // players start each session with $500 to work with
		this.currentBet = 0;
	}
	
	/**
	 * Called for flop, turn, and river.
	 * @param card
	 */
	public void addCommunityCard(Card card) {
		community.add(card);
		all.add(card);
	}
	
	/**
	 * Setter for player's hand.
	 * @param card1
	 * @param card2
	 */
	public void setHand(Card card1, Card card2) {
		hand.add(card1);
		hand.add(card2);
		all.add(card1);
		all.add(card2);
	}
	
	/**
	 * Takes a hand of 5 cards and generates a numerical value
	 * representing the hand's worth in regards to straight,
	 * flush, etc. and high card.
	 * @param hand
	 * @return handValue
	 */
	public int[] generateHandValue(ArrayList<Card> hand) {
		/**
		 * Key for handValue indexes:
		 * 0 		= Straight+Flush	= high card
		 * 1-2		= 4 of a Kind 		= 4match/kicker
		 * 3-4		= Full House		= 3match/2match
		 * 5-9		= Flush				= 1st/2nd/3rd/4th/5th
		 * 10		= Straight			= high card
		 * 11-13	= 3 of a Kind		= 3match/kicker/kicker
		 * 14-16	= 2 Pair			= 2match/2match/kicker
		 * 17-20	= Pair				= 2match/kicker/kicker/kicker
		 * 21-25	= High Hand			= 1st/2nd/3rd/4th/5th
		 */
		int[] handValue = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		ArrayList<Card> sortedHand = new ArrayList<Card>();
		
		// loop through the hand and sort them cards decreasing order (suit included)
		// the result will be sortedHand
		while (hand.size() > 0) {
			Card maxCard = hand.get(0);
			for (int i = 1; i < hand.size(); i++) {
				if (hand.get(i).getValue() > maxCard.getValue() || (hand.get(i).getValue() == maxCard.getValue() && hand.get(i).getSuit() > maxCard.getSuit())) {
	               maxCard = hand.get(i);
				}
			}
			hand.remove(maxCard);
            sortedHand.add(maxCard);
		}
		
		// Checking for flush - only true if all 5 cards share the same suit
		boolean flush = false;
		if (sortedHand.get(0).getSuit() == sortedHand.get(1).getSuit() 
				&& sortedHand.get(1).getSuit() == sortedHand.get(2).getSuit()
                && sortedHand.get(1).getSuit() == sortedHand.get(3).getSuit() 
                && sortedHand.get(1).getSuit() == sortedHand.get(4).getSuit()) {
			flush = true;
			handValue[5] = sortedHand.get(0).getValue();
			handValue[6] = sortedHand.get(1).getValue();
			handValue[7] = sortedHand.get(2).getValue();
			handValue[8] = sortedHand.get(3).getValue();
			handValue[9] = sortedHand.get(4).getValue();
		}
		
		// Checking for straight - only true if all 5 card numbers are sequential
		boolean straight = false;
		// check for the specific case 1,2,3,4,5 with 1 being an ace
		if (sortedHand.get(0).getValue() == 14 && sortedHand.get(1).getValue() == 5
                && sortedHand.get(2).getValue() == 4 && sortedHand.get(3).getValue() == 3
                && sortedHand.get(4).getValue() == 2 ) {
             straight = true;
             // ace gets moved to end since it is now being treated as a 1
             sortedHand.add(sortedHand.remove(0));
             handValue[10] = sortedHand.get(0).getValue();
		}
		// check for normal straight
		else if (sortedHand.get(0).getValue() == sortedHand.get(1).getValue() + 1
                      && sortedHand.get(1).getValue() == sortedHand.get(2).getValue() + 1
                      && sortedHand.get(2).getValue() == sortedHand.get(3).getValue() + 1
                      && sortedHand.get(3).getValue() == sortedHand.get(4).getValue() + 1) {
			straight = true;
			handValue[10] = sortedHand.get(0).getValue();
		}
		
		// Update the handValue if it is a straight+flush
		if (straight == true && flush == true) {
			handValue[0] = sortedHand.get(0).getValue();
		}
		
		// Checking for 4 of a kind
		boolean fourOfAKind = false;
		// check if first 4 match
		if (sortedHand.get(0).getValue() == sortedHand.get(1).getValue()
                && sortedHand.get(1).getValue() == sortedHand.get(2).getValue()
                && sortedHand.get(2).getValue() == sortedHand.get(3).getValue() ) {
			fourOfAKind = true;
			handValue[1] = sortedHand.get(0).getValue();
			handValue[2] = sortedHand.get(4).getValue();
		}
		// check if last 4 match
		else if (sortedHand.get(1).getValue() == sortedHand.get(2).getValue()
                && sortedHand.get(2).getValue() == sortedHand.get(3).getValue()
                && sortedHand.get(3).getValue() == sortedHand.get(4).getValue()) {
			fourOfAKind = true;
			sortedHand.add(sortedHand.remove(0));
			handValue[1] = sortedHand.get(0).getValue();
			handValue[2] = sortedHand.get(4).getValue();
		}
		
		// Checking for 3 of a kind
		int tripleValue = 0; 
        //int tripleLocation = -1;
        // don't continue if we already have a straight/4ofakind
        if (straight == false && fourOfAKind == false) {
	        for (int i = 0; i <= 2; i++) {
	        	if (sortedHand.get(i).getValue() == sortedHand.get(i+1).getValue()
	                 && sortedHand.get(i+1).getValue() == sortedHand.get(i+2).getValue()) {
	        		//tripleLocation = i;
	        		tripleValue = sortedHand.get(i).getValue();
	        		handValue[11] = tripleValue;
	        		if (i == 0) {
	        			handValue[12] = sortedHand.get(3).getValue();
	        			handValue[13] = sortedHand.get(4).getValue();
	        		}
	        		else if (i == 1) {
	        			handValue[12] = sortedHand.get(0).getValue();
	        			handValue[13] = sortedHand.get(4).getValue();
	        		}
	        		else if (i == 2) {
	        			handValue[12] = sortedHand.get(0).getValue();
	        			handValue[13] = sortedHand.get(1).getValue();
	        		}
	        		break; // stop looking
	           }
        	}
        }
		
        // Checking for pairs (1 or 2)
        int pairValue1 = 0;
        int pairLoc1 = -1;
        int pairValue2 = 0;
        int pairLoc2 = -1; 
        if (straight == false && fourOfAKind == false) {
	        for (int i = 0; i <= 3; i++) {
	        	// look for first pair
	        	if (sortedHand.get(i).getValue() == sortedHand.get(i+1).getValue() && sortedHand.get(i).getValue() != tripleValue) {
	        		pairValue1 = sortedHand.get(i).getValue();
	        		pairLoc1 = i;
	        		for (int j = i+2; j <= 3; j++) {
	        			// look for second pair
	        			if (sortedHand.get(j).getValue() == sortedHand.get(j+1).getValue() && sortedHand.get(j).getValue() != tripleValue) {
	        				pairValue2 = sortedHand.get(j).getValue();
	        				pairLoc2 = j;
	        				handValue[14] = pairValue1;
	        				handValue[15] = pairValue2;
	        				// find the kicker and add it to the handValue
	        				if (pairLoc1 == 0 && pairLoc2 == 2) {
	        					handValue[16] = sortedHand.get(4).getValue();
	        				}
	        				else if (pairLoc1 == 0 && pairLoc2 == 3) {
	        					handValue[16] = sortedHand.get(2).getValue();
	        				}
	        				else if (pairLoc1 == 1 && pairLoc2 == 3) {
	        					handValue[16] = sortedHand.get(0).getValue();
	        				}
	        				break;
	        			}
	        		}
	        		// handValue for single pair
	        		handValue[17] = pairValue1;
	        		if (pairLoc1 == 0) {
	        			handValue[18] = sortedHand.get(2).getValue();
	        			handValue[19] = sortedHand.get(3).getValue();
	        			handValue[20] = sortedHand.get(4).getValue();
	        		}
	        		else if (pairLoc1 == 1) {
	        			handValue[18] = sortedHand.get(0).getValue();
	        			handValue[19] = sortedHand.get(3).getValue();
	        			handValue[20] = sortedHand.get(4).getValue();
	        		}
	        		else if (pairLoc1 == 2) {
	        			handValue[18] = sortedHand.get(0).getValue();
	        			handValue[19] = sortedHand.get(1).getValue();
	        			handValue[20] = sortedHand.get(4).getValue();
	        		}
	        		else if (pairLoc1 == 3) {
	        			handValue[18] = sortedHand.get(0).getValue();
	        			handValue[19] = sortedHand.get(1).getValue();
	        			handValue[20] = sortedHand.get(2).getValue();
	        		}
	        		break;
	        	}
	        }
        }
        
        // Update the handValue if it is a Full House
        if (tripleValue != 0 && pairValue1 !=0) {
        	handValue[3] = tripleValue;
        	handValue[4] = pairValue1;
        }
        
        // In the event that no hands are made, hand values would be needed
        for (int i = 0; i < 5; i++) {
        	handValue[21] = sortedHand.get(0).getValue();
        	handValue[22] = sortedHand.get(1).getValue();
        	handValue[23] = sortedHand.get(2).getValue();
        	handValue[24] = sortedHand.get(3).getValue();
        	handValue[25] = sortedHand.get(4).getValue();
        }
        
		return handValue;
	}
	
	/**
	 * Grabs the highest possible valued hand out of all potential
	 * 5 card hands available to the player.
	 * @return highestHandValue
	 */
	public int[] getHighestHand() {
		int[] highestHandValue = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		int n = 7;
		int[] potentialScore;
		ArrayList<Card> potentialHand = new ArrayList<Card>();
		// Combinatoric loops for the (7 choose 5) = 21 possible hands which player can create
		for (int i = 0; i < n; i++) {
	        for (int j = i + 1; j < n; j++) {
	            for (int k = j + 1; k < n; k++) {
	                for (int l = k + 1; l < n; l++) {
	                    for (int m = l + 1; m < n; m++) {
	                    	potentialHand.clear();
	                    	// the five cards for this potential hand of the 21 possible
	                    	potentialHand.add(all.get(i));
	                    	potentialHand.add(all.get(j));
	                    	potentialHand.add(all.get(k));
	                    	potentialHand.add(all.get(l));
	                    	potentialHand.add(all.get(m));
	                    	// call ranking algorithm and check to see if it is new highest score
	                    	potentialScore = generateHandValue(potentialHand);
	                    	for(int p = 0; p < 26; p++) {
	                    		if (potentialScore[p] < highestHandValue[p]) {
	                    			break; // weaker hand; move on to next potential hand
	                    		}
	                    		else if (potentialScore[p] > highestHandValue[p]) {
	                    			highestHandValue = potentialScore;
	                    			break; // stronger hand; keep it temporarily, but check other potential hands
	                    		}
	                    	}
	                    }
	                }
	            }
	        }
	    }
		return highestHandValue;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void matchBet(int bet) {
		if (bet <= money) {
			currentBet = bet;
			money = money - currentBet;
		}
		else{
			money += 200;
			currentBet = bet;
			money = money - currentBet;
		}
	}
	
	public ArrayList<Card> getCards() {
		return hand;
	}
	
	public ArrayList<Card> getCommunity() {
		return community;
	}
	
	public void giveMoney(int winnings) {
		money += winnings;
	}
	public void clearHand(){
		hand.clear();
		community.clear();
		all.clear();
	}
}

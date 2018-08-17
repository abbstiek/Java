/*******Abygail Stiekman********/
/****COP 3252 Adv Java**********/
/******Semester Proj************/
/***********aes15d**************/

import java.io.IOException;
import java.util.ArrayList;

/**
 * HoldemModel is the object which holds and communicates
 * with the board representation
 */
public class HoldemModel implements ViewListener {

	private Board board = new Board();
	private ArrayList<ModelListener> listeners = new ArrayList<ModelListener>();
	private int allUp;
	private int raiseFlag;
	
	/**
	 * Constructor method
	 */
	public HoldemModel() {
		allUp = 0;
		raiseFlag = 0;
	}

	/**
	 * Joining a session
	 * @param proxy - corresponding ViewProxy
	 * @param session - string of the corresponding socket
	 */
	public void join(ViewProxy proxy, String session) {
		
	}
	
	/**
	 * Set the listener to this model
	 * @param  modelListener - the given listener
	 * @throws IOException 
	 */
	public synchronized void addModelListener (ModelListener modelListener) throws IOException{
		// do not continue if there are already 2 players
		if (board.isFull() == false) {
			board.addPlayer();
			listeners.add(modelListener);
			if (board.isFull() == true) {
				board.advanceStage();
				
				// Update both views with the new board information
				int playerTurn = board.getPlayerTurn();
				int playerView = 1;
				String infoString;
				for (ModelListener m : listeners) {
					
					// Board[1] - Dealer
					int isDealer = 0;
					if (board.getDealer() == playerView) {
						isDealer = 1;
					}
					infoString = isDealer + " ";
					
					// Board[2] - Turn
					int turn = 0;
					if (playerView == playerTurn) {
						turn = 1;
					}
					infoString +=  turn + " ";
					
					// Board[3] - Raise/Bet
					int isBet = 0;
					if (board.getCurrentBet() == 0) {
						isBet = 1;
					}
					infoString += isBet + " ";
									
					// Board[4] - Call/Check
					int isCall = 1;
					if (board.getCurrentBet() == 0) {
						isCall = 0;
					}
					infoString += isCall + " ";
					
					// Board[5] - Opponent $$$
					int opponent = 1;
					if (playerView == 1) {
						opponent = 2;
					}
					infoString += board.getPlayer(opponent).getMoney() + " ";
					
					// Board[6-7] - Player Cards		
					ArrayList<Card> p1Cards = board.getPlayerCard(playerView);
					for (Card c : p1Cards) {
						infoString += c.toString() + " ";
					}
					
					// Board[8-12] - Community Cards
					ArrayList<Card> communityCards = board.getCommunityCards();
					for (int i = 0; i < communityCards.size(); i++) {
						infoString += communityCards.get(i).toString() + " ";
					}
					for (int i = (5-communityCards.size()); i > 0; i--) {
						infoString += "NONE ";	
					}
					// Board[13-14] - Opponent Cards
					ArrayList<Card> p2Cards = board.getPlayerCard(opponent);
					for (Card c : p2Cards) {
						infoString += c.toString() + " ";
					}
					
					// Board[15] - Player $$$
					infoString += board.getPlayer(playerView).getMoney() + " ";
					
					// Board[16] - Pot $$$
					infoString += board.getPot() + "";
					
					m.boardUpdate(infoString);
					playerView++;
				}
			}
		}
	}

	/**
	 * 
	 * @exception  IOException
	 */
	public synchronized void playerFold() throws IOException {
		int currentPlayer = board.getPlayerTurn(); // 1 or 2
		int playerView = 1;
		String infoString;
		int w;
		if (currentPlayer == 1){
			w = 2;
		}
		else {
			w = 1;
		}
		board.givePot(w);
		
		if(currentPlayer == 1){
			listeners.get(0).gameResult(0);
			listeners.get(1).gameResult(1);
		}
		else{
			listeners.get(0).gameResult(1);
			listeners.get(1).gameResult(0);
		}
		
		board.aFoldHappened();
		
		//board.advanceStage();
		//board.advanceStage();
		board.newHand();
		
		for (ModelListener m : listeners) {
			
			// Board[1] - Dealer
			int isDealer = 0;
			if (board.getDealer() == playerView) {
				isDealer = 1;
			}
			infoString = isDealer + " ";
			
			// Board[2] - Turn
			int turn = 1;
			if (currentPlayer == playerView) {
				turn = 0; // no longer this players turn
			}
			infoString +=  turn + " ";
			
			// Board[3] - Raise/Bet
			int isBet = 0;
			if (board.getCurrentBet() == 0) {
				isBet = 1;
			}
			infoString += isBet + " ";
							
			// Board[4] - Call/Check
			int isCall = 1;
			if (board.getCurrentBet() == 0) {
				isCall = 0;
			}
			infoString += isCall + " ";
			
			// Board[5] - Opponent $$$
			int opponent = 1;
			if (playerView == 1) {
				opponent = 2;
			}
			infoString += board.getPlayer(opponent).getMoney() + " ";
			
			// Board[6-7] - Player Cards
			
			ArrayList<Card> p1Cards = board.getPlayerCard(playerView);
			for (Card c : p1Cards) {
				infoString += c.toString() + " ";
			}
			
			// Board[8-12] - Community Cards
			ArrayList<Card> communityCards = board.getCommunityCards();
			for (int i = 0; i < communityCards.size(); i++) {
				infoString += communityCards.get(i).toString() + " ";
			}
			for (int i = (5-communityCards.size()); i > 0; i--) {
				infoString += "NONE ";	
			}
			// Board[13-14] - Opponent Cards
			ArrayList<Card> p2Cards = board.getPlayerCard(opponent);
			for (Card c : p2Cards) {
				infoString += c.toString() + " ";
			}
			
			// Board[15] - Player $$$
			infoString += board.getPlayer(playerView).getMoney() + " ";
			
			// Board[16] - Pot $$$
			infoString += board.getPot() + "";
			
			m.boardUpdate(infoString);
			playerView++;
		}
	}

	public synchronized void raiseBet(int amount) throws IOException {
		int currentPlayer = board.getPlayerTurn(); // 1 or 2, the player whose is matching the bet
		int playerView = 1;
		String infoString;
		if(board.getCurrentBet() == 0){
			allUp++;
			
		}
		if(board.getCurrentBet() != 0){
			raiseFlag = 1;
		}
		
		// raise the current bet
		board.getPlayer(currentPlayer).matchBet(amount);
		board.raiseBet(amount);
		if(raiseFlag==1){
			board.getPlayer(currentPlayer).matchBet(amount);
			raiseFlag = 0;
		}
		
		// Update both views with the new board information
				for (ModelListener m : listeners) {
					
					// Board[1] - Dealer
					int isDealer = 0;
					if (board.getDealer() == playerView) {
						isDealer = 1;
					}
					infoString = isDealer + " ";
					
					// Board[2] - Turn
					int turn = 1;
					if (currentPlayer == playerView) {
						turn = 0; // no longer this players turn
					}
					infoString +=  turn + " ";
					
					// Board[3] - Raise/Bet
					int isBet = 0;
					if (board.getCurrentBet() == 0) {
						isBet = 1;
					}
					infoString += isBet + " ";
									
					// Board[4] - Call/Check
					int isCall = 1;
					if (board.getCurrentBet() == 0) {
						isCall = 0;
					}
					infoString += isCall + " ";
					
					// Board[5] - Opponent $$$
					int opponent = 1;
					if (playerView == 1) {
						opponent = 2;
					}
					infoString += board.getPlayer(opponent).getMoney() + " ";
					
					// Board[6-7] - Player Cards
					
					ArrayList<Card> p1Cards = board.getPlayerCard(playerView);
					for (Card c : p1Cards) {
						infoString += c.toString() + " ";
					}
					
					// Board[8-12] - Community Cards
					ArrayList<Card> communityCards = board.getCommunityCards();
					for (int i = 0; i < communityCards.size(); i++) {
						infoString += communityCards.get(i).toString() + " ";
					}
					for (int i = (5-communityCards.size()); i > 0; i--) {
						infoString += "NONE ";	
					}
					// Board[13-14] - Opponent Cards
					ArrayList<Card> p2Cards = board.getPlayerCard(opponent);
					for (Card c : p2Cards) {
						infoString += c.toString() + " ";
					}
					
					// Board[15] - Player $$$
					infoString += board.getPlayer(playerView).getMoney() + " ";
					
					// Board[16] - Pot $$$
					infoString += board.getPot() + "";
					
					m.boardUpdate(infoString);
					playerView++;
				}

		// next players turn
		board.nextTurn();
		
	}

	public synchronized void checkCall() throws IOException {
		int currentPlayer = board.getPlayerTurn(); // 1 or 2, the player whose is matching the bet
		int bet = board.getCurrentBet(); // currentBet highest bet held by the board, can be 0
		int playerView = 1;
		allUp++;
		String infoString;
		int w;
		//if(board.getStage() == 3 && allUp >= 2) {board.advanceStage();}
		//System.out.println("boardStage =" + board.getStage());
		
		
		if (bet!=0){
			board.matchBet(currentPlayer, bet);
		}
		//System.out.println("AllUP:"+allUp);
		if(allUp >= 2){
			board.advanceStage();
			allUp = 0;
		}
		if (board.isComplete()) {
			w = board.getWinner();
			board.givePot(w);
			//board.newHand();
		}
		else { 
			w = -100;
		}
		
for (ModelListener m : listeners) {
			
			// Board[1] - Dealer
			int isDealer = 0;
			if (board.getDealer() == playerView) {
				isDealer = 1;
			}
			infoString = isDealer + " ";
			
			// Board[2] - Turn
			int turn = 1;
			if (currentPlayer == playerView) {
				turn = 0; // no longer this players turn
			}
			infoString +=  turn + " ";
			
			// Board[3] - Raise/Bet
			int isBet = 0;
			if (board.getCurrentBet() == 0) {
				isBet = 1;
			}
			infoString += isBet + " ";
							
			// Board[4] - Call/Check
			int isCall = 1;
			if (board.getCurrentBet() == 0) {
				isCall = 0;
			}
			infoString += isCall + " ";
			
			// Board[5] - Opponent $$$
			int opponent = 1;
			if (playerView == 1) {
				opponent = 2;
			}
			infoString += board.getPlayer(opponent).getMoney() + " ";
			
			// Board[6-7] - Player Cards
			
			ArrayList<Card> p1Cards = board.getPlayerCard(playerView);
			for (Card c : p1Cards) {
				infoString += c.toString() + " ";
			}
			
			// Board[8-12] - Community Cards
			ArrayList<Card> communityCards = board.getCommunityCards();
			for (int i = 0; i < communityCards.size(); i++) {
				infoString += communityCards.get(i).toString() + " ";
			}
			for (int i = (5-communityCards.size()); i > 0; i--) {
				infoString += "NONE ";	
			}
			// Board[13-14] - Opponent Cards
			ArrayList<Card> p2Cards = board.getPlayerCard(opponent);
			for (Card c : p2Cards) {
				infoString += c.toString() + " ";
			}
			
			// Board[15] - Player $$$
			infoString += board.getPlayer(playerView).getMoney() + " ";
			
			// Board[16] - Pot $$$
			infoString += board.getPot() + "";
			
			m.boardUpdate(infoString);
			playerView++;
		}
		
		if (board.isComplete()) {
			playerView = 1;
			board.givePot(w);
			for (ModelListener m : listeners) {
				if (playerView == w) {
					m.gameResult(1);
				}
				else if (w == 0) {
					m.gameResult(-1);
				}
				else {
					m.gameResult(0);
				}
				playerView++;
			}
			board.newHand();
			currentPlayer = board.getPlayerTurn();
			playerView = 1;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			for (ModelListener m : listeners) {
				
				// Board[1] - Dealer
				int isDealer = 0;
				if (board.getDealer() == playerView) {
					isDealer = 1;
				}
				infoString = isDealer + " ";
				
				// Board[2] - Turn
				int turn = 1;
				if (currentPlayer == playerView) {
					turn = 0; // no longer this players turn
				}
				infoString +=  turn + " ";
				
				// Board[3] - Raise/Bet
				int isBet = 0;
				if (board.getCurrentBet() == 0) {
					isBet = 1;
				}
				infoString += isBet + " ";
								
				// Board[4] - Call/Check
				int isCall = 1;
				if (board.getCurrentBet() == 0) {
					isCall = 0;
				}
				infoString += isCall + " ";
				
				// Board[5] - Opponent $$$
				int opponent = 1;
				if (playerView == 1) {
					opponent = 2;
				}
				infoString += board.getPlayer(opponent).getMoney() + " ";
				
				// Board[6-7] - Player Cards
				
				ArrayList<Card> p1Cards = board.getPlayerCard(playerView);
				for (Card c : p1Cards) {
					infoString += c.toString() + " ";
				}
				
				// Board[8-12] - Community Cards
				ArrayList<Card> communityCards = board.getCommunityCards();
				for (int i = 0; i < communityCards.size(); i++) {
					infoString += communityCards.get(i).toString() + " ";
				}
				for (int i = (5-communityCards.size()); i > 0; i--) {
					infoString += "NONE ";	
				}
				// Board[13-14] - Opponent Cards
				ArrayList<Card> p2Cards = board.getPlayerCard(opponent);
				for (Card c : p2Cards) {
					infoString += c.toString() + " ";
				}
				
				// Board[15] - Player $$$
				infoString += board.getPlayer(playerView).getMoney() + " ";
				
				// Board[16] - Pot $$$
				infoString += board.getPot() + "";
				
				m.boardUpdate(infoString);
				playerView++;
			}
		}
		
		// next players turn
		board.nextTurn();
	}
	
	
}
/*******Abygail Stiekman********/
/****COP 3252 Adv Java**********/
/******Semester Proj************/
/***********aes15d**************/


import java.util.ArrayList;

/**
 * Board is the overarching class for game functionality. It holds
 * a deck and 2 players, as well as manages information such as dealer
 * and whose turn it is.
 */
public class Board {
	
	private Deck deck;
	private Player player1, player2;
	private int numPlayers, dealer, turn, currentBet, stage, pot;
	boolean check;
	
	public Board() {
		deck = new Deck();
		numPlayers = 0;
		currentBet = 0;
		dealer = 1;
		turn = 2;
		stage = 0;
		pot = 0;
	}
	
	public boolean isFull() {
		return (numPlayers == 2);
	}
	
	public void matchBet(int player, int bet) {
		pot += bet;
		if (player == 1) {
			player1.matchBet(bet);
		}
		else {
			player2.matchBet(bet);
		}
		
	}
	
	public void addPlayer() {
		if (numPlayers == 0) {
			player1 = new Player();
			numPlayers++;
		}
		else if (numPlayers == 1) {
			player2 = new Player();
			numPlayers++;
		}
	}
	
	public void turnTaken() {
		if (turn == 1) {
			turn++;
		}
		else if (turn == 2) {
			turn--;
		}
	}
	
	public Player getPlayer(int playerNumber) {
		if (playerNumber == 1) {
			return player1;
		}
		return player2;
	}
	
	public int getPlayerTurn() {
		return turn;
	}
	
	public void nextTurn() {
		if (turn == 1) {
			turn = 2;
		}
		else {
			turn = 1;
		}
	}
	
	public int getDealer() {
		return dealer;
	}
	
	public int getCurrentBet() {
		return currentBet;
	}
	
	public void advanceStage() {
		if (stage == 0) {
			player2.setHand(deck.deal(), deck.deal());
			player1.setHand(deck.deal(), deck.deal());
		}
		else if (stage == 1) {
			for (int i = 0; i < 3; i++) {
				Card card = deck.deal();
				player1.addCommunityCard(card);
				player2.addCommunityCard(card);
				currentBet = 0;
			}
		}
		else if (stage == 2 || stage == 3) {
			Card card = deck.deal();
			player1.addCommunityCard(card);
			player2.addCommunityCard(card);
			currentBet = 0;
		}
		else {};
		
		//
		if (stage < 5){
			stage++;
		}
		else{
			newHand();
			
		}
	}
	
	public void newHand(){
		stage = 0;
		deck = new Deck();
		player1.clearHand();
		player2.clearHand();
		if (dealer == 1){
			dealer = 2;
			turn = 2;
		}
		else {
			dealer = 1;
			turn = 1;}
		pot = 0;
		advanceStage();
		
	}
	
	public void raiseBet(int amount) {
		currentBet = amount;
		pot += amount;
	}
	
	public ArrayList<Card> getCommunityCards() {
		return player1.getCommunity();
	}
	
	public ArrayList<Card> getPlayerCard(int player) {
		if (player == 1) {
			return player1.getCards();
		}
		return player2.getCards();
	}
	
	public boolean isComplete() {
		if (stage == 4) {
			return true;
		}
		return false;
	}
	public void givePot(int winner) {
		if (winner == 1) {
			player1.giveMoney(pot);
		}
		else if (winner == 2) {
			player2.giveMoney(pot);
		}
		else {
			player1.giveMoney(pot);
			player2.giveMoney(pot);
		}
		pot = 0;
	}
	
	/**
	 * Returns winner, 1 for player1, 2 for player2, 0 for draw
	 * @return
	 */
	public int getWinner() {
		int[] player1Score = player1.getHighestHand();
		int[] player2Score = player2.getHighestHand();
		for(int i = 0; i < 26; i++) {
			if (player1Score[i] > player2Score[i]) {
				return 1;
    		}
			else if ((player1Score[i] < player2Score[i])) {
				return 2;
			}
    	}
		return 0;
	}
	
	public int getPot() {
		return pot;
	}
	
	public void checkFlag() {
		check = true;
	}
	
	public boolean isCheckFlagged() {
		return check;
	}
	
	public void aFoldHappened(){
		stage = 4;
	}

	public int getStage() {
		// TODO Auto-generated method stub
		return stage;
	}
}

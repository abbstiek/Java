/*******Abygail Stiekman********/
/****COP 3252 Adv Java**********/
/******Semester Proj************/
/***********aes15d**************/


/**
 * The GUI for the Texas holdem application
 */

//import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Font;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class HoldemUI 
	implements ModelListener{

	private JFrame frmNetworkTexasHoldem;
	private ViewListener viewListener;
	
	//GUI Elements
	JLabel yourCash;
	JButton betRaise;
	JButton checkCall;
	JButton Fold;
	JLabel dealerLabel;
	JLabel lblOpponentsCardOne;
	JLabel lblOpponenetCardTwo;
	JLabel opponentsCash;
	JLabel cardOne;
	JLabel cardTwo;
	JLabel potSize;
	JLabel flopOne;
	JLabel flopTwo;
	JLabel flopThree;
	JLabel theTurn;
	JLabel theRiver;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HoldemUI window = new HoldemUI();
					window.frmNetworkTexasHoldem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HoldemUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNetworkTexasHoldem = new JFrame();
		frmNetworkTexasHoldem.setTitle("Network Texas Hold-Em");
		frmNetworkTexasHoldem.setBounds(100, 100, 450, 300);
		frmNetworkTexasHoldem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmNetworkTexasHoldem.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		yourCash = new JLabel("Money");
		yourCash.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(yourCash);
		
		betRaise = new JButton("Bet/Raise");
		betRaise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Call up a JOptionPane for betting
			String[] choices = {"5", "10", "25"};
			int response = JOptionPane.showOptionDialog(frmNetworkTexasHoldem, "How Much?", "Betting Window", 
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, 
					null, choices, "Error");
			try{
				if(response == 0){
					viewListener.raiseBet(5);
				
				}else if(response == 1){
					viewListener.raiseBet(10);
				}else if(response == 2){
					viewListener.raiseBet(25);
				}
				else{}
			}catch(Exception e){}
			
			//System.out.println(response);
			}
			
		});
		panel.add(betRaise);
		
		checkCall = new JButton("Check/Call");
		checkCall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					viewListener.checkCall();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
		});
		panel.add(checkCall);
		
		Fold = new JButton("Fold");
		Fold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					viewListener.playerFold();
				}catch (IOException e){}
			}
		});
		panel.add(Fold);
		
		dealerLabel = new JLabel("Dealer: You/Opponent");
		panel.add(dealerLabel);
		
		JPanel panel_1 = new JPanel();
		frmNetworkTexasHoldem.getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_1.add(panel_8, BorderLayout.SOUTH);
		
		lblOpponentsCardOne = new JLabel("Opponents Card 1");
		panel_8.add(lblOpponentsCardOne);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		panel_8.add(horizontalStrut_5);
		
		lblOpponenetCardTwo = new JLabel("Opponenet Cards 2");
		panel_8.add(lblOpponenetCardTwo);
		
		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7, BorderLayout.NORTH);
		
		opponentsCash = new JLabel("Opponents Money:");
		panel_7.add(opponentsCash);
		opponentsCash.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JPanel panel_2 = new JPanel();
		frmNetworkTexasHoldem.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.SOUTH);
		
		cardOne = new JLabel("Card One");
		panel_3.add(cardOne);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_3.add(horizontalStrut);
		
		cardTwo = new JLabel("Card Two");
		panel_3.add(cardTwo);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.SOUTH);
		
		potSize = new JLabel("Pot Size");
		panel_5.add(potSize);
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		flopOne = new JLabel("Flop Card One");
		panel_6.add(flopOne);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel_6.add(horizontalStrut_1);
		
		flopTwo = new JLabel("Flop Card Two");
		panel_6.add(flopTwo);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panel_6.add(horizontalStrut_2);
		
		flopThree = new JLabel("Flop Card Three");
		panel_6.add(flopThree);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		panel_6.add(horizontalStrut_3);
		
		theTurn = new JLabel("The Turn");
		panel_6.add(theTurn);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		panel_6.add(horizontalStrut_4);
		
		theRiver = new JLabel("The River");
		panel_6.add(theRiver);
		
		
		this.frmNetworkTexasHoldem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.frmNetworkTexasHoldem.pack();
		
		this.frmNetworkTexasHoldem.setVisible(true);
		
	}

	/**
	 * This Function updates the UI with the information from the server.
	 */
	public void boardUpdate(String info) throws IOException {
		String[] board  = info.split(" ");
				
		//Dealer Label If flag is 1 (one) then this client is the dealer
		if (board[1].equals("1")){dealerLabel.setText("Dealer:You");}
		else {dealerLabel.setText("Dealer:Opponent");}
		
		this.frmNetworkTexasHoldem.validate();
		
		//Unlock Flag - if this is one then it is this clients turn to play
		if (board[2].equals("1")){
			betRaise.setEnabled(true);
			checkCall.setEnabled(true);
			Fold.setEnabled(true);
		}
		else{
			betRaise.setEnabled(false);
			checkCall.setEnabled(false);
			Fold.setEnabled(false);
		}
		
		//Bet Flag, If This is 1 then the player is the first to be, 0 for raising.
		if (board[3].equals("1")){
			betRaise.setText("Bet");
		}else betRaise.setText("Raise");
		
		//Call Flag -- If this flag is one then the other player has bet and this player can call
		//if 0 then Check is the option.
		
		if (board[4].equals("1")){
			checkCall.setText("Call");
		}else checkCall.setText("Check");
		
		//Opponents money
		opponentsCash.setText("Opponents Cash $ " + board[5]);
		
		cardOne.setText(getCard(board[6]));
		
		cardTwo.setText(getCard(board[7]));
		
		flopOne.setText(getCard(board[8]));
	
		flopTwo.setText(getCard(board[9]));
		
		flopThree.setText(getCard(board[10]));
		
		theTurn.setText(getCard(board[11]));
		
		theRiver.setText(getCard(board[12]));
		
		
		if(theRiver.getText().equals("Not On Table")){
			lblOpponentsCardOne.setText("Opponents Card");
			lblOpponenetCardTwo.setText("Opponents Card");
		}
		else{
			lblOpponentsCardOne.setText(getCard(board[13]));
			lblOpponenetCardTwo.setText(getCard(board[14]));
		}
		
		yourCash.setText("Your Money: $" + board[15]);
		
		this.potSize.setText("Pot Size: $" + board[16]);
		
		frmNetworkTexasHoldem.pack();
		
		frmNetworkTexasHoldem.validate();
	
	
	
	
	}
	
		

	 /**
	  * Private Method for getting the correct text to dispaly for a Card. 
	  * @param string the String representation of a card.
	  * @return a string to put in a JLabel in the GUI
	  */
	private String getCard(String string) {
		if (string.equals("H2")) return "2 of Hearts";
		else if (string.equals("H3")) return "3 of Hearts";
		else if (string.equals("H4")) return "4 of Hearts";
		else if (string.equals("H5")) return "5 of Hearts";
		else if (string.equals("H6")) return "6 of Hearts";
		else if (string.equals("H7")) return "7 of Hearts";
		else if (string.equals("H8")) return "8 of Hearts";
		else if (string.equals("H9")) return "9 of Hearts";
		else if (string.equals("H10")) return "10 of Hearts";
		else if (string.equals("H11")) return "Jack of Hearts";
		else if (string.equals("H12")) return "Queen of Hearts";
		else if (string.equals("H13")) return "King of Hearts";
		else if (string.equals("H14")) return "Ace of Hearts";
		else if (string.equals("C2")) return "2 of Clubs";
		else if (string.equals("C3")) return "3 of Clubs";
		else if (string.equals("C4")) return "4 of Clubs";
		else if (string.equals("C5")) return "5 of Clubs";
		else if (string.equals("C6")) return "6 of Clubs";
		else if (string.equals("C7")) return "7 of Clubs";
		else if (string.equals("C8")) return "8 of Clubs";
		else if (string.equals("C9")) return "9 of Clubs";
		else if (string.equals("C10")) return "10 of Clubs";
		else if (string.equals("C11")) return "Jack of Club";
		else if (string.equals("C12")) return "Queen of Clubs";
		else if (string.equals("C13")) return "King of Clubs";
		else if (string.equals("C14")) return "Ace of Clubs";
		else if (string.equals("S2")) return "2 of Spades";
		else if (string.equals("S3")) return "3 of Spades";
		else if (string.equals("S4")) return "4 of Spades";
		else if (string.equals("S5")) return "5 of Spades";
		else if (string.equals("S6")) return "6 of Spades";
		else if (string.equals("S7")) return "7 of Spades";
		else if (string.equals("S8")) return "8 of Spades";
		else if (string.equals("S9")) return "9 of Spades";
		else if (string.equals("S10")) return "10 of Spades";
		else if (string.equals("S11")) return "Jack of Spades";
		else if (string.equals("S12")) return "Queen of Spades";
		else if (string.equals("S13")) return "King of Spades";
		else if (string.equals("S14")) return "Ace of Spades";
		else if (string.equals("D2")) return "2 of Diamonds";
		else if (string.equals("D3")) return "3 of Diamonds";
		else if (string.equals("D4")) return "4 of Diamonds";
		else if (string.equals("D5")) return "5 of Diamonds";
		else if (string.equals("D6")) return "6 of Diamonds";
		else if (string.equals("D7")) return "7 of Diamonds";
		else if (string.equals("D8")) return "8 of Diamonds";
		else if (string.equals("D9")) return "9 of Diamonds";
		else if (string.equals("D10")) return "10 of Diamonds";
		else if (string.equals("D11")) return "Jack of Diamonds";
		else if (string.equals("D12")) return "Queen of Diamonds";
		else if (string.equals("D13")) return "King of Diamonds";
		else if (string.equals("D14")) return "Ace of Diamonds";
		else if (string.equals("NONE"))return "Not On Table";
		else if (string.equals("OPP")) return "Opponenets Card";
		else return "error";
	}

	public void gameResult(int winner) throws IOException {
		if (winner == 0){
			JOptionPane.showMessageDialog(frmNetworkTexasHoldem,
			    "Your hand lost.",
			    "You Lose",
			    JOptionPane.ERROR_MESSAGE, 
			    null);
		}else if (winner == -1){
			JOptionPane.showMessageDialog(frmNetworkTexasHoldem,
				    "It's a Draw", 
				    "Draw",
				    JOptionPane.PLAIN_MESSAGE, 
				    null);
		}else{
			JOptionPane.showMessageDialog(frmNetworkTexasHoldem,
				    "Your hand won",
				    "Win",
				    JOptionPane.INFORMATION_MESSAGE, 
				    null);

		}
		
	}

	public void setViewListener(ViewListener proxy) {
		this.viewListener = proxy;
		
	}

}

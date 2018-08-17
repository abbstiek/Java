//Abygail Stiekman 10/07/2016
//COP3252 HW #3

import java.util.Scanner;

public class TicTacToe {
    static final int empty = 0; //if board is empty, continues loop
    static final int none = 0;
    static final int user = 1; //user value
	static final int user2 = 4; //for user vs comp
    static final int comp = 2; //computer value
	static final int comp2 = 5; // for comp vs comp
    static final int draw = 3;// if there is a tie

public static void main(String[] args) {
	// Data objects
	// 1 = user, 2 = computer
	int turn = user;
	
	Scanner input = new Scanner(System.in);

	// We will represent the board as nine cells 
        // 0 = empty, 1 = user, 2 = computer
	int[][] board = new int[3][3];

	// move: 1-9 representing ul through lr
	int move;

	// winner: 0 = none, 1 = user, 2 = computer, 3 = draw
	int winner = 0;

	// Print Instructions

	System.out.println("Enter 1-9 to indicate your move");

	// Print the board
	Board.print_board(board);

if (args.length == 1){
if (args[0].equals("-c")) // two computers against each other
{
	while(true) {
	    if(turn == comp) 
		{//two computer moves 
		move = Board.computer_move(board);
		System.out.println("Computer move: " + move);
	    }
		else 
		{
		move = Board.computer_move(board);
		System.out.println("Computer 2 move: " + move);
		}

	    // Update the board
		move-=1; //because array is 0-8
	    board[(int)(move/3)][move%3] = turn;

	    // Print the board
	    Board.print_board(board);

	    // if game is over
	    winner = Board.checkWinner(board);

	    if(winner != none)
		break;

	    // switch turn
	    if(turn == comp) {
		turn = comp2;
	    } else {
		turn = comp;
	    }
}	
}
else { //prints message if there is any other argument 
	System.out.println("Invalid Argument");
	System.exit(0);
}
}

if(args.length==2){ // user is 1st player, computer is 2nd
if (args[1].equals("2"))
{
	// While (game not over)
	while(true) {
	    if(turn == user) {
		move = 0;
		while (move<=0 || move>=9 || board[move/3][move%3] != empty) {
		    System.out.print("Player 1, please enter a move(1-9): ");
		    move = input.nextInt();
			System.out.print("\n");
		}
	    } else { //prints computer's move
		move = Board.computer_move(board);
		System.out.println("Computer move: " + move);
	    }

	    // Update the board
		move-=1; // because array is 0-8
	    board[(int)(move/3)][move%3] = turn;

	    // Print the board
	    Board.print_board(board);

	    // if game is over
	    winner = Board.checkWinner(board);

	    if(winner != none)
		break;

	    // switch turn
	    if(turn == user) {
		turn = comp;
	    } else {
		turn = user;
	    }

	}
}
else if(args[1].equals("1"))
{	turn = comp; // computer is 1st player, user is 2nd player
	while(true) {
	    if(turn == comp) 
		{
		move = Board.computer_move(board);
		System.out.println("Computer move: " + move);
	    }
		else  //random computer move 
		{move = 0;
		while (move<=0 || move>=9 || board[move/3][move%3] != empty) {
		    System.out.print("Player 2, please enter a move(1-9): ");
		    move = input.nextInt();
			System.out.print("\n");
		}
		}

	    // Update the board
		move-=1;
	    board[(int)(move/3)][move%3] = turn;

	    // Print the board
	    Board.print_board(board);

	    // if game is over
	    winner = Board.checkWinner(board);

	    if(winner != none)
		break;

	    // switch turn
	    if(turn == comp) {
		turn = user;
	    } else {
		turn = comp;
	    }
}
}
else {
	System.out.print("Invalid argument");
	System.exit(0);
	}	
}
if (args.length ==0) 
{
	
while(true) { //twp users playing against each other 
	    if(turn == user) {
		move = 0; 
		while (move<=0 || move>=9 || board[move/3][move%3] != empty) {
		    System.out.print("Player 1, please enter a move(1-9): ");
		    move = input.nextInt();
			System.out.print("\n");
			
		}
	    } else {
		move = 0;
		while (move<=0 || move>=9 || board[move/3][move%3] != empty) {
		    System.out.print("Player 2, please enter a move(1-9): ");
		    move = input.nextInt();
			System.out.print("\n");
		}
	    }

	    // Update the board
			move-=1; // because array is 0-8
	    board[(int)(move/3)][move%3] = turn;
		
	    // Print the board
	    Board.print_board(board);

	    // if game is over
	    winner = Board.checkWinner(board);

	    if(winner != none)
		break;

	    // switch turn
	    if(turn == user) {
		turn = user2;
	    } else {
		turn = user;
	    }

	}
}


	// Print out the outcome
	switch(winner) {
	case user:
	    System.out.println("Player 1 won!");
	    break;
	case user2:
		System.out.println("Player 2 won!");
		break;
	case comp: 
	    System.out.println("Computer won!");
	    break;
	case comp2:
		System.out.println("Computer 2 won!");
		break;
	default:
	    System.out.println("Tie!");
	    break;
	}
}
}

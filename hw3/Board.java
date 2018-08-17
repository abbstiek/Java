//Abygail Stiekman 10/07/2016
//COP3252 HW #3


public class Board {
// Print the board
    public static void print_board(int[][] board) {
		System.out.print("Game Board: ");
		System.out.println("\t\t\tPositions: ");
	System.out.print(printChar(board[0][0]));
	System.out.print(" | ");
	System.out.print(printChar(board[0][1]));
	System.out.print(" | ");
	System.out.print(printChar(board[0][2]));
	System.out.println("\t\t\t1 | 2 | 3");
	System.out.print("----------");
	System.out.println("\t\t\t----------");
	System.out.print(printChar(board[1][0]));
	System.out.print(" | ");
	System.out.print(printChar(board[1][1]));
	System.out.print(" | ");
	System.out.print(printChar(board[1][2]));
	System.out.println("\t\t\t4 | 5 | 6");
	System.out.print("----------");
		System.out.println("\t\t\t----------");
	System.out.print(printChar(board[2][0]));
	System.out.print(" | ");
	System.out.print(printChar(board[2][1]));
	System.out.print(" | ");
	System.out.print(printChar(board[2][2]));
	System.out.println("\t\t\t7 | 8 | 9");
	
	
    }

    // Return an X or O, depending upon whose move it was
	//and which option is selected in the command line
	//argument. varies depending on which option is selected
    public static char printChar(int b) {
	switch(b) {
	case TicTacToe.empty:
	    return ' ';
	case TicTacToe.user:
	    return 'X';
	case TicTacToe.user2:
		return 'O';
	case TicTacToe.comp:
	    return 'O';
	case TicTacToe.comp2: 
		return 'X';
	}
	return ' ';
    }

    // See if the game is over
    public static int checkWinner(int[][] board) {
	// Check if someone won
	// Check horizontals

	// top row
	if((board[0][0] == board[0][1]) && (board[0][1] == board[0][2]))
	    return board[0][0];

	// middle row
	if((board[1][0] == board[1][1]) && (board[1][1] == board[1][2]))
	    return board[1][0];

	// bottom row
	if((board[2][0] == board[2][1]) && (board[2][1] == board[2][2]))
	    return board[2][0];

	// Check verticals

	// left column
	if((board[0][0] == board[1][0]) && (board[1][0] == board[2][0]))
	    return board[0][0];

	// middle column
	if((board[0][1] == board[1][1]) && (board[1][1] == board[2][1]))
	    return board[0][1];

	// right column
	if((board[0][2] == board[1][2]) && (board[1][2] == board[2][2]))
	    return board[0][2];

	// Check diagonals
	// one diagonal
	if((board[0][0] == board[1][1]) && (board[1][1] == board[2][2]))
	    return board[0][0];

	// the other diagonal
	if((board[0][2] == board[1][1]) && (board[1][1] == board[2][0]))
	    return board[0][2];

	// Check if the board is full
	if(board[0][0] == TicTacToe.empty || board[0][1] == TicTacToe.empty || board[0][2] == TicTacToe.empty || 
	   board[1][0] == TicTacToe.empty || board[1][1] == TicTacToe.empty || board[1][2] == TicTacToe.empty ||
	   board[2][0] == TicTacToe.empty || board[2][1] == TicTacToe.empty || board[2][2] == TicTacToe.empty)
	    return TicTacToe.none;

	return TicTacToe.draw;
    }

    // Generate a random computer move
    public static int computer_move(int[][] board) {
	int move = (int)(Math.random()*9);

	while(board[move/3][move%3] != TicTacToe.empty) 
	    move = (int)(Math.random()*9);
	move+=1; // because board array is 0-8
	return move;
    }
}
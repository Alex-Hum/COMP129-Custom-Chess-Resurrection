package starter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javafx.util.Pair;
import java.util.Scanner;

public class Piece {
	
	private int row = 0;
	private int col = 0;
	private PieceType type;
	private boolean color; //1 = White, 0 = Black
	private int cost;
	private ArrayList<Pair<Integer, Integer>> PossibleMoves = new ArrayList<Pair<Integer, Integer>>(); //Pair(row, col)
	private boolean hasMoved;
	
	//Constructor to create a Piece object with the given parameters.
	public Piece(int row, int col, PieceType type, boolean color)
	{
		this.row = row;
		this.col = col;
		this.type = type;
		this.color = color;
		switch(type)
		{
		case PAWN:
			cost = 1;
		case KNIGHT:
			cost = 3;
		case BISHOP:
			cost = 3;
		case ROOK:
			cost = 4;
		case QUEEN: 
			cost = 7;
		case KING: 
			cost = 0; //Each player MUST have a King and automatically starts with one
		case CUSTOM:
			// If a file exists for a custom chess piece, read the file and assign the cost
			File custom_file = new File("Custom_Piece.txt");
			if (custom_file.exists()) {
				try {
					Scanner read_file = new Scanner(custom_file);
					while (read_file.hasNextLine() ) {
						String line = read_file.nextLine();
						if (line.matches(".*Cost.*")) {
							String parse[] = line.split("\s"); // File format: Cost 1
							cost = Integer.parseInt(parse[1]);
							read_file.close();
							break;
						}
					}
				} catch (FileNotFoundException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}
			}
			else {
				cost = 0; // If no custom file exists, automatically set Custom piece to 0 cost
			}
		}
		setPossibleMoves(type);
	}
	
	public void setRow(int row) //Sets row for Piece
	{
		this.row = row;
	}
	
	public int getRow() //Gets row for Piece
	{
		return row;
	}
	
	public void setCol(int col) //Sets Column for Piece.
	{
		this.col = col;
	}
	
	public int getCol() //Gets Column for Piece
	{
		return col;
	}
	
	public void setType(PieceType type) //Sets Piece type
	{
		this.type = type;
	}
	
	public PieceType getType() //Gets Piece type
	{
		return type;
	}
	
	private ArrayList<Pair<Integer, Integer>> calcMovement(String movement_string)
	{
		ArrayList<Pair<Integer, Integer>> movement = new ArrayList<Pair<Integer, Integer>>();
		String spaces[] = movement_string.split("\s"); // Format: Vertical 1 -> movement number is always spaces[1]
		
		for (int i = 1; i <= Integer.parseInt(spaces[1]); i++) {
			if (spaces[0].matches(".*Vertical.*")) {
				Pair<Integer, Integer> foward_move = new Pair<Integer, Integer> (-i, 0); // Going forward in a straight line
				Pair<Integer, Integer> backward_move = new Pair<Integer, Integer> (i, 0); // Going backwards in a straight line
				movement.add(foward_move);
				movement.add(backward_move);
			}
			else if (spaces[0].matches(".*Horizontal.*")) {
				Pair<Integer, Integer> right_move = new Pair<Integer, Integer> (0, -i); // Going to the right in a straight line
				Pair<Integer, Integer> left_move = new Pair<Integer, Integer> (0, i); // Going to the left in a straight line
				movement.add(right_move);
				movement.add(left_move);
			}
			else {
				Pair<Integer, Integer> diagonal_right = new Pair<Integer, Integer> (-i, -i); // Going forward diagonally to the right
				Pair<Integer, Integer> diagonal_left = new Pair<Integer, Integer> (-i, i); // Going forward diagonally to the left
				Pair<Integer, Integer> diagonal_back_right = new Pair<Integer, Integer> (i, -i); // Going backwards diagonally to the right
				Pair<Integer, Integer> diagonal_back_left = new Pair<Integer, Integer> (i, i); // Going backwards diagonally to the right
				movement.add(diagonal_right);
				movement.add(diagonal_left);
				movement.add(diagonal_back_right);
				movement.add(diagonal_back_left);
			}
		}
		
		return movement;
	}
	
	public void setPossibleMoves(PieceType type) //Sets Possible Moves for each Piece
	{
		switch(type)
		{
		case PAWN: 
			//Total Number of moves: 4; Range: 1 or 2 Spaces
			Pair<Integer, Integer> pmove1 = new Pair<Integer, Integer> (-2, 0); //Going forward 2 spaces IF pawn has yet to be moved
			Pair<Integer, Integer> pmove2 = new Pair<Integer, Integer> (-1, 0); //Going forward 1 space
			Pair<Integer, Integer> pmove3 = new Pair<Integer, Integer> (-1, -1); //Going forward diagonally to the right for capturing a piece
			Pair<Integer, Integer> pmove4 = new Pair<Integer, Integer> (-1, 1); //Going forward diagonally to the left for capturing a piece
			
			//BELOW: backwards movement for pawns will be used during a check in Board.java to see if board orientation affects which direction the pawn moves
			Pair<Integer, Integer> pmove5 = new Pair<Integer, Integer> (2, 0); //Going backward 2 spaces IF pawn has yet to be moved
			Pair<Integer, Integer> pmove6 = new Pair<Integer, Integer> (1, 0); //Going backward 1 space
			Pair<Integer, Integer> pmove7 = new Pair<Integer, Integer> (1, -1); //Going backward diagonally to the right for capturing a piece
			Pair<Integer, Integer> pmove8 = new Pair<Integer, Integer> (1, 1); //Going backward diagonally to the left for capturing a piece
			PossibleMoves.add(pmove1);
			PossibleMoves.add(pmove2);
			PossibleMoves.add(pmove3);
			PossibleMoves.add(pmove4);
			PossibleMoves.add(pmove5);
			PossibleMoves.add(pmove6);
			PossibleMoves.add(pmove7);
			PossibleMoves.add(pmove8);
			break;
		case KNIGHT:
			//Total Number of moves: 8; Range: 1 space
			Pair<Integer, Integer> kmove1 = new Pair<Integer, Integer> (-2, -1); //Going forward 2 spaces, 1 right
			Pair<Integer, Integer> kmove2 = new Pair<Integer, Integer> (-2, 1); //Going forward 2 spaces, 1 left
			Pair<Integer, Integer> kmove3 = new Pair<Integer, Integer> (2, -1); //Going backwards 2 spaces, 1 right
			Pair<Integer, Integer> kmove4 = new Pair<Integer, Integer> (2, 1); //Going backwards 2 spaces, 1 left
			Pair<Integer, Integer> kmove5 = new Pair<Integer, Integer> (-1, -2); //Going right 2 spaces, forward 1
			Pair<Integer, Integer> kmove6 = new Pair<Integer, Integer> (1, -2); //Going right 2 spaces, backwards 1
			Pair<Integer, Integer> kmove7 = new Pair<Integer, Integer> (-1, 2); //Going left 2 spaces, forward 1
			Pair<Integer, Integer> kmove8 = new Pair<Integer, Integer> (1, 2); //Going left 2 spaces, backward 1
			PossibleMoves.add(kmove1);
			PossibleMoves.add(kmove2);
			PossibleMoves.add(kmove3);
			PossibleMoves.add(kmove4);
			PossibleMoves.add(kmove5);
			PossibleMoves.add(kmove6);
			PossibleMoves.add(kmove7);
			PossibleMoves.add(kmove8);
			break;
		case BISHOP:
			//Total number of moves: 28; Range: 1-7 spaces
			for(int i = 1; i <= 7; i++) //Longest possible movement range is 7 in a straight line
			{
				Pair<Integer, Integer> bmove1 = new Pair<Integer, Integer> (-i, -i); //Going forward diagonally to the right in a straight line ranging from 1-7 spaces
				Pair<Integer, Integer> bmove2 = new Pair<Integer, Integer> (-i, i); //Going forward diagonally to the left in a straight line ranging from 1-7 spaces
				Pair<Integer, Integer> bmove3 = new Pair<Integer, Integer> (i, -i); //Going backwards diagonally to the right in a straight line ranging from 1-7 spaces
				Pair<Integer, Integer> bmove4 = new Pair<Integer, Integer> (i, i); //Going backwards diagonally to the left in a straight line ranging from 1-7 spaces
				PossibleMoves.add(bmove1);
				PossibleMoves.add(bmove2);
				PossibleMoves.add(bmove3);
				PossibleMoves.add(bmove4);
			}
			break;
		case ROOK:
			//Total number of moves: 28; Range: 1-7 spaces
			for(int i = 1; i <= 7; i++) //Longest possible movement range is 7 in a straight line
			{
				Pair<Integer, Integer> rmove1 = new Pair<Integer, Integer> (-i, 0); //Going forward in a straight line ranging from 1-7 spaces
				Pair<Integer, Integer> rmove2 = new Pair<Integer, Integer> (i, 0); //Going backwards in a straight line ranging from 1-7 spaces
				Pair<Integer, Integer> rmove3 = new Pair<Integer, Integer> (0, -i); //Going to the right in a straight line ranging from 1-7 spaces
				Pair<Integer, Integer> rmove4 = new Pair<Integer, Integer> (0, i); //Going to the left in a straight line ranging from 1-7 spaces
				PossibleMoves.add(rmove1);
				PossibleMoves.add(rmove2);
				PossibleMoves.add(rmove3);
				PossibleMoves.add(rmove4);
			}
			break;
		case QUEEN:
			//Total Number of moves: 56; Range: 1-7 spaces
			for(int i = 1; i <= 7; i++) //Longest possible movement range is 7 in a straight line
			{
				Pair<Integer, Integer> qmove1 = new Pair<Integer, Integer> (-i, 0); //Going forward in a straight line ranging from 1-7 spaces
				Pair<Integer, Integer> qmove2 = new Pair<Integer, Integer> (i, 0); //Going backwards in a straight line ranging from 1-7 spaces
				Pair<Integer, Integer> qmove3 = new Pair<Integer, Integer> (0, -i); //Going to the right in a straight line ranging from 1-7 spaces
				Pair<Integer, Integer> qmove4 = new Pair<Integer, Integer> (0, i); //Going to the left in a straight line ranging from 1-7 spaces
				PossibleMoves.add(qmove1);
				PossibleMoves.add(qmove2);
				PossibleMoves.add(qmove3);
				PossibleMoves.add(qmove4);
			}
			for(int i = 1; i <= 7; i++) //Longest possible movement range is 7 in a straight line
			{
				Pair<Integer, Integer> qmove5 = new Pair<Integer, Integer> (-i, -i); //Going forward diagonally to the right in a straight line ranging from 1-7 spaces
				Pair<Integer, Integer> qmove6 = new Pair<Integer, Integer> (-i, i); //Going forward diagonally to the left in a straight line ranging from 1-7 spaces
				Pair<Integer, Integer> qmove7 = new Pair<Integer, Integer> (i, -i); //Going backwards diagonally to the right in a straight line ranging from 1-7 spaces
				Pair<Integer, Integer> qmove8 = new Pair<Integer, Integer> (i, i); //Going backwards diagonally to the left in a straight line ranging from 1-7 spaces
				PossibleMoves.add(qmove5);
				PossibleMoves.add(qmove6);
				PossibleMoves.add(qmove7);
				PossibleMoves.add(qmove8);
			}
			break;
		case KING:
			//Total Number of moves: 8; Range: 1 space
			Pair<Integer, Integer> move1 = new Pair<Integer, Integer> (-1, 0); //Going forward 1 space
			Pair<Integer, Integer> move2 = new Pair<Integer, Integer> (1, 0); //Going backward 1 space
			Pair<Integer, Integer> move3 = new Pair<Integer, Integer> (0, -1); //Going to the right 1 space
			Pair<Integer, Integer> move4 = new Pair<Integer, Integer> (0, 1); //Going to the left 1 space
			Pair<Integer, Integer> move5 = new Pair<Integer, Integer> (-1, -1); //Going forward diagonally to the right 1 space
			Pair<Integer, Integer> move6 = new Pair<Integer, Integer> (-1, 1); //Going forward diagonally to the left 1 space
			Pair<Integer, Integer> move7 = new Pair<Integer, Integer> (1, -1); //Going backwards diagonally to the right 1 space
			Pair<Integer, Integer> move8 = new Pair<Integer, Integer> (1, 1); //Going backwards diagonally to the left 1 space
			PossibleMoves.add(move1);
			PossibleMoves.add(move2);
			PossibleMoves.add(move3);
			PossibleMoves.add(move4);
			PossibleMoves.add(move5);
			PossibleMoves.add(move6);
			PossibleMoves.add(move7);
			PossibleMoves.add(move8);
			break;
		case CUSTOM:
			File custom_file = new File("Custom_Piece.txt");
			if (custom_file.exists()) {
				try {
					Scanner read_file = new Scanner(custom_file);
					while (read_file.hasNextLine()) {
						String line = read_file.nextLine();
						if (line.matches(".*Cost.*")) {
							read_file.close();
							break;
						}
						else {
							PossibleMoves.addAll(calcMovement(line));
						}
					}
				}
				catch (FileNotFoundException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}
			}
		}
	}
	
	public ArrayList<Pair<Integer, Integer>> getPossibleMoves() // Gets Possible moves for each Piece
	{
		return PossibleMoves;
	}
	
	public void setColor(boolean color) // Sets Color for each Piece.
	{
		this.color = color;
	}
	
	public boolean getColor() //Gets each Pieces color
	{
		return color;
	}
	
	public int getCost() //Gets Cost for each Piece.
	{
		return cost;
	}
	
	public void setHasMoved(boolean hasMoved) //Sets that a piece has moved
	{
		this.hasMoved = hasMoved;
	}
	
	public boolean getHasMoved() //Gets information that piece has moved
	{
		return hasMoved;
	}
}

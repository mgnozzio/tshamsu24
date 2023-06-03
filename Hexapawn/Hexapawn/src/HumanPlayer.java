import structure.*; 
import java.util.Scanner;
import java.util.ArrayList;

public class HumanPlayer implements Player {
	
	private HexBoard board; 
	private char side; 
	private Player winner; 
	
	public HumanPlayer(HexBoard b, char s) {
		board = b; 
		side = s;
	}
	
	public char getSide() {
		return side; 
	}
	
	public Player play(GameTree node, Player opp) {
		Scanner input = new Scanner(System.in); 
		
		System.out.println("please enter a start position (0 is the topleft corner, 5 is the bottom right"); 
		int start = input.nextInt(); 
		System.out.println("please enter an end position (0 is the topleft corner, 5 is the bottom right"); 
		int end = input.nextInt(); 
		
		HexMove player_move = new HexMove(start,end,3); 
		ArrayList<GameTree> temp = node.getChildren(); 
		int index = -2; 
		Vector moves = node.getBoard().moves(side);
		
		//check to see which move it is
		for(int i = 0; i < moves.size(); ++i) {
			if(player_move.equals((HexMove)(moves.get(i)))) {
				index = i; 					
			}
		}
		if(index >= 0) {
			
			board = temp.get(index).getBoard(); 
			System.out.println(board); 
			if(board.win(side)) winner = this; 
			else {
				winner = opp.play(temp.get(index),this); 
			}
			return winner; 
		
		}
		else {
			System.out.println("invalid entry"); 
			return this.play(node, opp); 
		}

	
	}
	
	
}

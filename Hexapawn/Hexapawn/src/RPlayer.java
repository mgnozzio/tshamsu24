import structure.Vector;
import java.util.ArrayList;


public class RPlayer implements Player {
	private HexBoard b; 
	private char side; 

	
	public RPlayer(HexBoard board, char c) {
		b = board; 
		side = c; 
	}
	
	public char getSide() {
		return side; 
	}
	
	public Player play(GameTree node, Player opp) {
		Player winner = null; 
		//Generate random move
		ArrayList<GameTree> temp = node.getChildren();
		int rand = (int)(Math.random() * temp.size()); 
		b = temp.get(rand).getBoard(); 
		System.out.println(b); 
		if(b.win(side)) {
			winner = this; 
		}
		else {
			winner = opp.play(temp.get(rand), this); 
		}
		return winner; 
	}
}
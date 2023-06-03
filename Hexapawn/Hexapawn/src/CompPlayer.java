import java.util.ArrayList;

public class CompPlayer implements Player {
	private HexBoard board;
	private char side; 
	private Player winner; 
	private GameTree recent;
	
	
	
	public CompPlayer(HexBoard b, char c) {
		board = b;
		side = c; 
	}
	
	public char getSide(){
		return side; 
	}
	
	public Player play(GameTree node, Player opp) {
		
		//checking if won after making move and updating board
		ArrayList<GameTree> temp = node.getChildren(); 
		int rand = (int)(Math.random()*temp.size());
		
		System.out.println(temp); 
		System.out.println("the number " + rand); 
		
		
		recent = temp.get(rand); 
		board = temp.get(rand).getBoard(); 
		
		System.out.println(board); 
		
		if(board.win(side)) {
			winner = this; 
		}
		else {
			
			winner = opp.play(temp.get(rand), this);
			
			//takeout most recent
			if(winner != this) {
				
				(node.getParent()).removeChild(recent);
			}
		}
		return winner; 
	}
	

}

import structure.*;
import java.util.ArrayList; 
import java.awt.Color; 
import java.util.Scanner; 

public class GameTree {
	/*
	 * THOUGHT QUESTIONS
	 * 1. How many board positions are there for the 3 × 4 board?
	 * Can you determine how many moves there are for a 3 × 5 board?
	 *  Using a static variable that increases each time a new gametree
	 *  is made I found a 3x4 board has 6003 positions and a 3x5 has 215150.
	 * 
	 * 2. If you implement the learning machine, pit two machines against each other. 
	 * Gardner called the computer to move first H.I.M., and the machine to move
	 *  second H.E.R. Will H.I.M. or H.E.R. ultimately win more frequently? Explain
	 *   your reasoning in a short write-up. What happens for larger boards?
	 *   HER would win more on 3x3 just because the second player is better able to
	 *   determine what the gametree will look like and it can also learn(after a couple
	 *   of rounds) how to almost always make the right moves. When the board gets bigger
	 *   however it takes more time for  the second player to figure out the optimal moves 
	 *   so how frequently each wins will be less drastic of a difference than in a 3x3 
	 *   where HER will dominate.
	 *   
	 * 3. In Gardner’s original description of the game, each matchbox represented a 
	 * board state and its reflection. What modifications to HexBoard and HexMove would
	 *  be necessary to support this collapsing of the game tree?
	 *  In order to use this idea of reflection and possibly make it half the memory
	 *  I would make a boolean that checks if the left and right columns are identical,
	 *  then a method which checks if this boolean is true or not, would make the GameTree
	 *  node reference the mirror GameTree node if it's true.
	 * 
	 * */
	
	private HexBoard board; 
	private ArrayList<GameTree> nextBoards; 
	private GameTree parent; 
	private char player; 
	private static int count; 
	
	public GameTree(HexBoard b, char c) {
			GameTree child; 
			
			board = b; 
			player = c; 
			nextBoards = new ArrayList<GameTree>(); 
			
			//first few
			char opp;
			if(c == '*') opp = 'o'; 
			else { opp = '*'; } 
			
			//check then make more gametrees by getting moves
			if (b.win(opp)) {
				check(); 
			}
			
			else {
			
				Vector temp = b.moves(c);
				for(int i = 0; i < temp.size(); ++i) {
					HexBoard next = new HexBoard(b,(HexMove)temp.get(i));
					if(c == '*') {
						child = new GameTree(next,'o'); 
						child.setParent(this);
						nextBoards.add(child);
					}
					else { 
						child = new GameTree(next,'*'); 
						child.setParent(this);
						nextBoards.add(child); 
				}
				
				if(parent == null) parent = this; 
			}
		}
	}
	
	private void setParent(GameTree g) {
		this.parent = g; 
	}
	
	public GameTree getParent() {
		return parent; 
	}
	
	
	private static void check(){
		count++; 
	}
	
	public ArrayList<GameTree> getChildren(){
		return nextBoards; 
	}
	public HexBoard getBoard() {
		return board; 
	}
	public char getPlayer() {
		return player; 
	}
	
	public void removeChild(GameTree g) { //
		
		
		if(this.equals(g)) {
			g.getParent().nextBoards.remove(this); 
		}
		
		else {
			for(int i = 0; i < nextBoards.size(); ++i) {
				nextBoards.get(i).removeChild(g); 
			}
		}
		
	}
	public void removeParent() {
		parent = null; 
		
	}
	public int getSize() {
		
		return 0; 
	}
	public int numLeaves() {
		return count; 
	}
	
	//Print the Children
	public static void printChildren(GameTree node) {
		System.out.println("Parent");
		System.out.println(node.getBoard()); 
		System.out.println(); 
		ArrayList<GameTree> children = node.getChildren(); 
		for(int i = 0; i < children.size(); ++i) {
			System.out.println("Child " + i);
			System.out.println(children.get(i).getBoard()); 
		}
	}
	
	public static void treeBrowser(GameTree gt)
	{
		Scanner s = new Scanner(System.in);
		int p = 0;
		do {
			printChildren(gt);
			System.out.println("Enter a node to display further children (-1 for parent):");
			p = s.nextInt();
			if(p == -1) {
				
				gt = gt.getParent();
			} else if(p >= 0) {
			
				gt = (GameTree) gt.getChildren().get(p);
			}
		} while (p >= -1);
		printChildren(gt);
	}
	
	
	
	
	public static void main(String[] args) {
        HexBoard b = new HexBoard(3,3); 
        GameTree g = new GameTree(b,'*');
        System.out.println("number of positions " + g.numLeaves());
      
        
        //cplayer
        GameTree comp_tree = new GameTree(b,'*'); 
        GameTree normal_tree = new GameTree(b,'*'); 
        CompPlayer p = new CompPlayer(b,'*'); 
        CompPlayer r = new CompPlayer(b,'o'); 
        System.out.println(p.play(comp_tree,r).getSide());
      	GameTree.treeBrowser(comp_tree);

      	//humanopid and randomoid
        System.out.println(b);
        HumanPlayer h = new HumanPlayer(b,'*');
        HumanPlayer c = new HumanPlayer(b,'o'); 
        System.out.println("PLayer " + h.play(g, c).getSide() + " has won");  
        
	}
		
}


import java.awt.Color;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import objectdraw.*;

//THOUGHT QUESTIONS
//1) The onMouseEnter method is used to describe a certain action you want your program
//to do as the mouse enters the canvas. So on example could be if you want the colors of your canvas
//to be different depending on your user's attention, then you can trigger a change by using onMouseEnter.
// The onMouseExit method is the opposite of the onMouseEnter method as it is used to describe
//actions to be taken when the mouse leaves the canvas. Like the previous example you can make a change
//depending on where the mouse is, so you can use the onMouseExit method to decrease the brightness as
// the mouse leaves the canvas.
// The onMouseMove is a method used to trigger some sort of action as the mouse is moving within the canvas.
// An example of it's use 
//2) FramedRect is a class under the Rect class and it is used to create a grahic that outline a rectangle
//based on the given attributes. The constructor can take in 5 attributes: starting x, starting y, width, height and canvas.
//3) The removeFromCanvas and hide methods are drastically different because while the first one
//, removeFromCanvas, permanently deletes the object from the canvas the hide method only temporarily hides it(but it
//can be shown at any point later using the show method). The removeFromCanvas method should be used for objects that will no longer
//be used by the program and aren't necessary in the future and the hide method should be used for things that we want to hide temporarily
//but will later want to show(like what's in this program where initially the gallows and man are hidden but eventually
//come into view)




public class Hangman2 extends WindowController  implements KeyListener{
	
	//String for underscores in puzzle and right or wrong boolean
	private String underScore = "";
	boolean right = false;
	
	protected String word = "";
	protected char[] letters;
	protected char[] puzzleLetters;
	protected boolean setup;
	protected int lettersRemaining;
	protected int playerNum = 0;
	
	
	// GUI Elements
	protected Text label;
	protected Text buttonText;
	protected FramedRect button;
	protected Text puzzle;
	protected Gallows gallows;
	//trying for smile
	protected Smile smile;
	
	protected static final int WINDOW_SIZE = 600;
	protected static final int TEXT_OFFSET = 10;
	protected static final int PUZZLE_OFFSET = 120;
	protected static final int BUTTON_WIDTH = 200;
	protected static final int BUTTON_HEIGHT = 40;

	
    public void begin()
    {
            // Get ready to handle key focuses
            requestFocus();
            addKeyListener(this);
            canvas.addKeyListener(this);
            
            // Set up the GUI for Player to enter the target word.
            label = new Text("Player " + getPlayerNum() + ", please enter a word.", TEXT_OFFSET, TEXT_OFFSET, canvas);
            label.setFontSize(20);
            
            setup = true;
            
            button = new FramedRect(
            		WINDOW_SIZE/2 - BUTTON_WIDTH/2,
            		WINDOW_SIZE/2 - BUTTON_HEIGHT,
            		BUTTON_WIDTH,
            		BUTTON_HEIGHT,
            		canvas);
            button.setColor(Color.RED);
            button.hide();
            
            buttonText = new Text("Click when finished.", 
            		button.getX() + BUTTON_WIDTH/2, 
            		button.getY() + BUTTON_HEIGHT/2, 
            		canvas);
            buttonText.move(buttonText.getWidth()/-2.0, buttonText.getHeight()/-2.0);
            buttonText.hide();
            
            puzzle = new Text("Puzzle to Solve: ", WINDOW_SIZE/2, WINDOW_SIZE - PUZZLE_OFFSET, canvas);
            puzzle.setFontSize(30);
    		puzzle.moveTo(WINDOW_SIZE/2-puzzle.getWidth()/2, puzzle.getY());

    }
 
    
    // Required by KeyListener Interface.
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    
    public void keyTyped(KeyEvent e)
    {
    	if(setup) {
    		if(word.isEmpty()) puzzle.setText("Puzzle to Solve: ");
    		char letter = Character.toUpperCase(e.getKeyChar());
    		if (Character.isLetter(letter)) {
	    		/* TODO: Update the puzzle text with the letter
	    		 * that was just entered.
	    		 */
    			//below is literally word = word + String.valueOf(letter); so we're adding letter to
    			//to the already existing word
    			word += String.valueOf(letter);
    			//after new letter add underscore and space
    			underScore = "";
    			for(int i = 0; i < word.length(); i++) {
    				underScore += "_ ";
    			}
    			//use setText to reset puzzle to include underScore
    			puzzle.setText("Puzzle to Solve: " + underScore);
    			
	    		puzzle.moveTo(WINDOW_SIZE/2-puzzle.getWidth()/2, puzzle.getY());
	    		if(word.length() == 1) {
	    			button.show();
	    			buttonText.show();
	    		}
    		} else if (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE && ! word.isEmpty()) {
    			/* TODO: Add logic to process the delete key having 
    			 * been pressed, adjusting the position of the puzzle
    			 * text accordingly.  Hide the "Click when finished" button 
    			 * if the word has been deleted entirely. 
    			 */
    				word = word.substring(0, word.length() - 1);
    				//Set underScore to equal new word length
    				underScore = "";
    				for (int i = 0; i < word.length(); i++) {
    				underScore += "_ ";
    				}
    				//Reset text and position 
    				puzzle.setText("Puzzle to Solve: " + underScore);
    				puzzle.moveTo(WINDOW_SIZE/2-puzzle.getWidth()/2, puzzle.getY());
    			
    				//Hide "Click when finished" button if there is no word
    				if (word.isEmpty()) {
    					button.hide();
    					buttonText.hide();
    					}
    				}
    		}
    	else  if (gallows.isAlive() ){ 
    		char guessedLetter =  Character.toUpperCase(e.getKeyChar());
    		/* TODO: Add logic to check if the letter
    		 * is in the word. Update the guess word
    		 * if the letter is found, otherwise hang
    		 * the man.
    		 */
    		for(int i = 0; i< letters.length; i++) {
    			if(letters[i] == guessedLetter) {
    				//Set right boolean to true
    				right = true;
    				//Use updateGuessWord to add in guessedLetter
    				updateGuessWord(guessedLetter);
    				//break loop
    				break;
    		}else {
    			right = false;
    		}
    			}
    		if (!right) {
    			gallows.hang();
    		}
    		//change underScore to puzzleLetters
    		underScore = "";
    		for(int i = 0; i<puzzleLetters.length; i++) {
    			underScore += puzzleLetters[i];
    			underScore += " ";
    		}
    		//Change puzzle text to the newly updated 
    		puzzle.setText("Puzzle to Solve: " + underScore);
    		}
    	else {
    		//means g is not alive to increase playerNum
    		playerNum++;
    		//Display game over and use getPlayerNum to show who wins
    		label.setText("Game over! Player " + getPlayerNum() + " wins.");
    		//change the underScore to letters
    		//maybe underScore = ""; but I dont think it's necessary
    		for(int i = 0; i<letters.length ; i++) {
    			underScore += Character.toLowerCase(letters[i]);
    		}
    		//Reveal answer
    		puzzle.setText("The word was: " + underScore);
    	}
    	}	
    
    
    public void onMousePress(Location point) {
    	if (button.contains(point) && !button.isHidden() && ! word.isEmpty()) {
    		/* TODO:  Add logic to exit setup mode and
    		 * start gameplay
    		 */
    		//Set setup boolean false, increase PlayerNum because it's the next player's turn and hide button with text
    		setup = false;
    		playerNum ++;
    		button.hide();
    			buttonText.hide();
    		//Ask new player to guess letter
    		label.setText("Player " + getPlayerNum() + ", please type a key to guess a letter.");
    		label.setFontSize(20);
    		
    		//Make gallows and request focus
    		requestFocus();
    		gallows = new Gallows(WINDOW_SIZE/4.0, WINDOW_SIZE * 2.0/3, canvas);
    		
    		//make letters the char array of the word initially inputed by the user 
    		letters = word.toCharArray();
    		//make puzzleLetters a char array with the size of the length of letters
    		puzzleLetters = new char[letters.length];
    		//initially fill puzzleLetters with underscores
    		for(int i = 0; i<letters.length; i++) {
    			puzzleLetters[i] = '_';
    		}
    		
    	}
    }
    
    public int getPlayerNum() {
    	//if even number return 1 if odd return 2
    	if(playerNum % 2 == 0) { 
    		return 1;
    	}
    	return 2;
    }
	
  
    
    public void updateGuessWord(char guessedLetter) {
    	/* TODO:  Add logic to update the guessed word.
    	 * Also include logic to test if the puzzle has
    	 * been solved (allowing the user to enter a new
    	 * word for their opponent if the puzzle is complete). 
    	 */	
    	//lettersRemaining = 0;
    	lettersRemaining = word.length();
    	//set puzzleLetters to correct guessedLetter
    	for(int i = 0; i < puzzleLetters.length; i++) {
    		if(letters[i] == guessedLetter) {
    			puzzleLetters[i] = guessedLetter;
    		}
    		//increase letters remaining for underscores present
    		if(puzzleLetters[i] != '_') {
    			lettersRemaining--;
    			
    		}
    	}
    	
    	//print congratulations when lettersRemaining equals zero because it means player has won
    	if(lettersRemaining == 0) {
    		//no more dying :)
    		gallows.clear();
    		//new stuff
    		requestFocus();
    		smile = new Smile(300, 300, canvas);
    		
    		
    		label.setText("Congratulations! You solved the puzzle. Enter a new word");
    		
    		//new setup and new word
    		setup = true;
    		word = "";
    	}
    	
    	
    }
    
    public static void main(String[] args) { 
        new Hangman2().startController(WINDOW_SIZE, WINDOW_SIZE); 
	}
	
}
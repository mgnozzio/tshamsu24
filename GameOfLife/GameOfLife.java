import java.awt.Color;
import java.awt.Container;
import javax.swing.JRootPane;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import objectdraw.*;
/*
 * 1. Describe the behavior of the simulator when you hit the “g" key. Why is this pattern significant?
  	It makes it so that there are two triangle/pyramid shaped animations going back and forth and as they
  	collide another animation of four blocks that go down. It's significant because it never actually stops
  	creating black cells, life, from a finite number of cells.
  	
	2. An important feature of Life is that all cells change concurrently. In other words, it is “turn
based." How did this fact impact the amount of memory that we need to play the game?
	It's kind of similar to the history of humans because with every generation a new boolean array is 
	created in the run(), because the grid had to be updated every time. This makes it take more space
	up and accumulate more memory like we do with the study of history.

	3. An alternative implementation of this simulator might make the Cell class responsible for
the drawing of FilledRect and FramedRect objects. What are the pros and cons of
taking this alternative approach?
	Cons -- I suppose it would take more memory because each box would have to be a cell object
	and that also has to get updated each time the grid starts back.
	Pros -- We could use the methods of FilledRect and FramedRect, which are helpful in themselves
	but they also make it so that other developers could access our code more easily.

	4. Our simulator plays the Game of Life on a fixed size board. Suppose we wanted to support
a board that extended infinitely in all directions. How might you go about doing this? (Don’t
worry about the graphics, just describe the underlying data structures.)
	If we implemented an ArrayList of ArrayList(2D-ArrayList) that can use the add method whenever
	the window size becomes too small, then it would add more columns and more rows, increasing
	the window size and making it able to extend infinitely.

*/

public class GameOfLife extends WindowController implements KeyListener {
	
	protected static final int WINDOW_SIZE = 616;
	protected static final int BOX_SIZE = 15;
	protected Cell lastToggledCell;
	
	protected Grid grid;
	
	public void begin() {
		int yoffset = 0;
		
		/* The coordinate system of the grid is thrown off slightly by
		 * the existence of the system menu bar.  The code below figures out
		 * the height of the menu bar. The call to resize at the end of this
		 * method takes this offset into account when making the whole grid
		 * visible. 
		 */
		Container c = this;
		while(! (c instanceof JRootPane)) {
			yoffset += (int)(c.getParent().getY());
			c = c.getParent();
		}
		grid = new Grid(WINDOW_SIZE, BOX_SIZE, canvas);
        requestFocus();
        addKeyListener(this);
        canvas.addKeyListener(this);
        lastToggledCell = null;
        resize(WINDOW_SIZE, WINDOW_SIZE + yoffset);
	}
	
	public void onMousePress(Location point) {
		/* TODO: Toggle the cell that was clicked on
		 * and keep track of what cell you just 
		 * changed. 
		 */
		lastToggledCell = grid.toggle(point);
	}
	
	public void onMouseDrag(Location point) {
		/* TODO: Toggle the cell under the mouse if
		 * it wasn't the last cell to be toggled. 
		 */
		
		//make cell c by getcell of point
				Cell c = grid.getCell(point);
				//if c is an actual cell that is not lastToggledCell, toggle the cell and set it
				//to lastToggledCell
				if (c != null && !c.equals(lastToggledCell)) {
					lastToggledCell = grid.toggle(point);
				}
	}
	
    // Required by KeyListener Interface.
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    
    public void keyTyped(KeyEvent e)
    {
    	char letter = e.getKeyChar();
    	if(letter == 'g' && lastToggledCell != null) {
    		grid.gliderGun(lastToggledCell.getRow(), lastToggledCell.getCol());
    	} else if (letter == 'c') {
    		/* TODO: Clear the grid */
    		grid.clear();
    	}
    	else {
    		/* TODO: Toggle whether the grid is running */
    		grid.toggleRunning();
    	}
    }
	
    public static void main(String[] args) { 
        new GameOfLife().startController(WINDOW_SIZE, WINDOW_SIZE); 
	}

}

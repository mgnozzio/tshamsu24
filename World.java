import structure5.*;

/**
 * This module includes the functions necessary to keep track of the creatures
 * in a two-dimensional world. In order for the design to be general, the
 * interface adopts the following design:
 * <p>1. The contents are unspecified objects.
 * <p>2. The dimensions of the world array are specified by the client. <p>
 * There are many ways to implement this structure. HINT:
 * look at the structure.Matrix class. You should need to add no more than 
 * about ten lines of code to this file.
 */

public class World<E>
{
	
	/**
	 * This function creates a new world consisting of width 
	 * columns and height rows, each of which is numbered beginning at 0. 
	 * A newly created world contains no objects.
	 * @param w The width of the world that is to be created
	 * @param h The height of the world that is to be created
	 * @pre w > 0
	 * @pre h > 0
	 */
	private Matrix<E> world;
	
	
	public World(int w, int h)  {
		
		world = new Matrix<E>(w,h);
	}

	/**
	 * Returns the height of the world.
	 */
	public int height() {
		return world.height();
	}

	/**
	 * Returns the width of the world.
	 */
	public int width() {
		return world.width();
	}

	/**
	 * Returns whether pos is in the world or not. 
	 * @pre pos is a non-null position. 
	 * @post returns true if pos is an (x,y) location in the bounds of
	 *       the board.
	 */
	boolean inRange(Position pos)  {
		if(pos.getX() >= 0 && pos.getX() <= width() || pos.getY() >= 0 && pos.getY() <= height()) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Sets a position on the board to contain c.
	 * @param c The object that is to be added
	 * @param pos Where c is to be added
	 * @pre pos is a non-null position on the board.
	 */
	public void set(Position pos, E c) {
		
		world.set(pos.getX(), pos.getY(), c);
	}
	

	/**
	 * Return the contents of a position on the board. 
	 * @pre pos is a non-null position on the board.
	 */
	public E get(Position pos) {
		
		return world.get(pos.getX(), pos.getY());
	}

	public static void main(String[] args) {
		
		World<String> checkw = new World<String>(10, 10);
		
		Position p = new Position(5, 5);
		
		System.out.println(checkw.inRange(p));
		String s = "it's working";
		
		checkw.set(p, s);
		
		System.out.println(checkw.get(p));
	}
}

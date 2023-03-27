
public class Cell {

	protected int row;
	protected int col;
	
	/* TODO: Initialize instance variables */
	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	/* TODO: Write the following accessor methods */
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	/* @returns true if o is a Cell and o has the same
	 * row and col as the current cell
	 * Hint: Recall that java has an instanceof keyword
	 */
	public boolean equals(Object o) {
		if(o instanceof Cell) {
			Cell c = (Cell) o;
			//checks if its rows and columns are the same as currrent cell
			if(row == c.getRow() && col == c.getCol()) {
				return true;
			}
		}
		//if o is not an instance of cell return false
		return false;
	}
}

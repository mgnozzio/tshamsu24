import objectdraw.*;

/*
 * The methods supported by all scribble classes.
 */
public interface ScribbleInterface {

  // returns whether point is contained in scribble
  public boolean contains(Location point);

  // move scribble by dx in x-direction and dy in y-direction
  public void move(double xOffset, double yOffset);
  
  //making a setcolor method that accepts a string
  public void setColor(String s);
  
  //to erase
  public void erase();
  
  //to check if drawing is hidden or not
  public boolean isHidden();

}

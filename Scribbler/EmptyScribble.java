
import objectdraw.*;

/*
 * Class representing an empty scribble.
 */
public class EmptyScribble implements ScribbleInterface {

  public EmptyScribble() {
  }

  // point is never in an empty scribble!
  public boolean contains(Location point) {
    return false;
  }

  // nothing to move, so do nothing!
  public void move(double xOffset, double yOffset) {
  }
  
  //has nothing to set color to
  public void setColor(String s) {
	  
  }
  
  //set to true considering there is nothing there
  public boolean isHidden() {
	  return true;
  }

  //has nothing to erase
  public void erase() {
  }
  
}
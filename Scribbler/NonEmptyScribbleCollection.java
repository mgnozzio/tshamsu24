import objectdraw.*;

public class NonEmptyScribbleCollection implements ScribbleCollectionInterface {

	ScribbleInterface newDrawing;
	  ScribbleCollectionInterface background;
	  
  public NonEmptyScribbleCollection(ScribbleInterface addedScribble,
                                    ScribbleCollectionInterface theRest) {
	  newDrawing = addedScribble;
	  background = theRest;
  }

  // pre:
  // post: returns the scribble that contains the point;
  //    if none contain it, returns an empty scribble
  public ScribbleInterface scribbleSelected(Location point) {
    //return null;  // change if necessary!
	  
	  // is the new drawing null? == returns null
 	  if(newDrawing == null) return null;
 	  //is the new drawing not hidden and has a point? draw new one
 	  else if (!newDrawing.isHidden() && newDrawing.contains(point)) { 
 		  return newDrawing;
 		  }
  	  else {
 		  return background.scribbleSelected(point);
 		  }
  }

  // pre:
  // post: returns the first scribble in the list;
  //   returns null if the list is empty.
  public ScribbleInterface getFirst() {
    return newDrawing;   // change if necessary!
  }

  // pre:
  // post: returns the list of scribbles excluding the first.
  //   returns an empty scribble collection if the list is empty.
  public ScribbleCollectionInterface getRest() {
    return background;   // change if necessary!
  }
  
  public ScribbleInterface erase() {
	  
	  ScribbleInterface current = null;
	  //Only erase if there is something to erase
	  if(newDrawing != null) {
		  // erase current
		  current = newDrawing;
		  newDrawing.erase();
		  // new drawing is now the first from the background
		  newDrawing = background.getFirst();
		  // everything but the first that is now the new drawing is the rest
		  background = background.getRest();
	  } 
	  return current;
  }
  
  
}
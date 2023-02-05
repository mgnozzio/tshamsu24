import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import objectdraw.*;
import java.awt.*;
import javax.swing.*;
import objectdraw.*;

/*
 * A very simple drawing program.
 */

/*THOUGHT QUESTIONS
 * 1. So setting the two equations to each other gave me the solutions x = -0.001, 0.001 and 29.71
 * the first two can be ignored for the most part since -0.001 is negative and negative time gives 
 * back to the future vibes(formally: we can't have negative time) and we can ignore 0.001 because
 * it's so close to 0. The last one is more relevant through because after x=29.71 the parabolic 
 * equation of B is no longer above A's graph. So this means that A takes less time than B for
 * 0.001 < n < 29.71.
 * 
 * 2. To make a new string with n amount of characters the time complexity, i.e how many times the
 * program executes it would be O(n) because it goes through each character and adds them one by one.
 * So if we're making a string that is a+b long then the time complexity would be O(a+b).
 * 
 * 3. public int binaryConverter(int i) {
 * 		if(i == 0){
 * 			return 0;
 * 		} else{
 * 			return i % 2 + 10 * binary(i/2);
 * 		}
 *    }
 * 
 */
public class Scribbler extends WindowController implements ActionListener {

  // User modes for what operation is selected.
  // We are using ints rather than boolean to allow for extension to
  // other modes
  private static final int DRAWING = 1;
  private static final int MOVING = 2;
  private static final int COLORING = 3;
  private static final int WINDOW_SIZE = 600;
  
  
  // the current scribble
  private ScribbleInterface currentScribble;

  // the collection of scribbles
  private ScribbleCollectionInterface scribbles;
  
 //making a collection of erased scribbles
 private ScribbleCollectionInterface erasedScribbles;

  // stores last point for drawing or dragging
  private Location lastPoint;

  // the scribble that is selected
  private ScribbleInterface selectedScribble;
 

  // buttons that allow user to select modes
  private JButton setDraw, setMove, setErase, setColor;

  // Choice JButton to select color
  private JComboBox chooseColor;

  // new color for scribble
  private Color newColor;

  // label indicating current mode
  private JLabel modeLabel;

  // the current mode -- drawing mode by default
  private int chosenAction = DRAWING;

  // create and hook up the user interface components
  public void begin() {

    setDraw = new JButton("Draw");
    setMove = new JButton("Move");
    setColor = new JButton("Color");

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(setDraw);
    buttonPanel.add(setMove);
    buttonPanel.add(setColor);

    chooseColor = new JComboBox();
    chooseColor.addItem("red");
    chooseColor.addItem("blue");
    chooseColor.addItem("green");
    chooseColor.addItem("yellow");
    chooseColor.addItem("magenta");
    chooseColor.addItem("cyan");

    setErase = new JButton("Erase last");
    JPanel choicePanel = new JPanel();
    choicePanel.add(setErase);
    
    //add choosing color rase?
    choicePanel.add(chooseColor);

    JPanel controlPanel = new JPanel(new GridLayout(3,1));
    modeLabel = new JLabel("Current mode: drawing");
    controlPanel.add(modeLabel);
    controlPanel.add(buttonPanel);
    controlPanel.add(choicePanel);

    Container contentPane = this.getContentPane();
    contentPane.add(controlPanel, BorderLayout.SOUTH);

    // add listeners
    setDraw.addActionListener(this);
    setMove.addActionListener(this);
    setErase.addActionListener(this);
    setColor.addActionListener(this);

    // make the current scribble empty
    currentScribble = new EmptyScribble();
    
    //making a scribble collection
    scribbles = new EmptyScribbleCollection();
    
    // makes erasedScribbles empty
    erasedScribbles = new EmptyScribbleCollection();

    this.validate();
  }

  /*
   * If in drawing mode then start with empty scribble.
   * If in moving mode then prepare to move.
   */
  public void onMousePress(Location point) {
    if (chosenAction == DRAWING) {
      // start with an empty scribble for drawing
      currentScribble = new EmptyScribble();

    } else if (chosenAction == MOVING) {
      // check if user clicked on current scribble
      selectedScribble = scribbles.scribbleSelected(point);
      
    }else if (chosenAction == COLORING) {
    	
    
    	selectedScribble = scribbles.scribbleSelected(point);
    	
        if(selectedScribble != null) {
        	
      	  // change color of selected scribble
      	  selectedScribble.setColor((String) chooseColor.getSelectedItem());//(String) chooseColor.getSelectedItem(
        }
      }


    // remember point of press for drawing or moving
    lastPoint = point;
  }

  /*
   * If in drawing mode, add a new segment to scribble.
   * If in moving mode then move it.
   */
  public void onMouseDrag(Location point) {
    if (chosenAction == DRAWING) {
      // add new line segment to current scribble
      Line newSegment = new Line(lastPoint, point, canvas);
      

      currentScribble = new NonEmptyScribble(newSegment, currentScribble);
    		  
      new NonEmptyScribble(newSegment, currentScribble);
    } else if (chosenAction == MOVING) {
      if (selectedScribble != null) {
        // move current scribble
        currentScribble.move(point.getX() - lastPoint.getX(),
          point.getY() - lastPoint.getY());
        
      } else if (chosenAction == COLORING ) {
         
              // move current scribble
              currentScribble.setColor((String) chooseColor.getSelectedItem());
              
            
    }
    
    }
    lastPoint = point;//if you dont change you have circles 
  }

  public void onMouseRelease(Location point) {
    // Add code when have collection of scribbles
	  
	  if(chosenAction == DRAWING) {
		  scribbles = new NonEmptyScribbleCollection(currentScribble, scribbles);
	  }
  }

  /*
   * Set mode according to JButton pressed.
   */
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == setDraw) {
      chosenAction = DRAWING;
      modeLabel.setText("Current mode: drawing");
    } else if (e.getSource() == setMove) {
      chosenAction = MOVING;
      modeLabel.setText("Current mode: moving");
    } else if (e.getSource() == setColor) {
        chosenAction = COLORING;
        modeLabel.setText("Current mode: coloring");
    } else if (e.getSource() == setErase) {
    	// erase and add scribble to erasedScribbles if not null
    	ScribbleInterface s = scribbles.erase();
    	
    	if(s != null) {
            erasedScribbles = new NonEmptyScribbleCollection(s, erasedScribbles);
            } else {
            	}
            }

    	
  }
  
  public static void main(String[] args) { 
      new Scribbler().startController(WINDOW_SIZE, WINDOW_SIZE);
	}
}

import objectdraw.*;
import java.lang.Object;
import java.awt.Color;


public class Smile {
	protected FilledOval face;
	protected DrawableInterface[] faceParts;
	
	
  
    public Smile(double xPos, double yPos, DrawingCanvas canvas){
        faceParts = new DrawableInterface[3];
        face = new FilledOval(xPos -150, yPos - 150, 300, 300, canvas);
        face.setColor(Color.yellow);


        faceParts[0] = face;
		faceParts[1] = new FilledOval(xPos-80, yPos-90, xPos-220, yPos-180, canvas);
		faceParts[2] = new FilledOval(xPos+10, yPos-90 , xPos -220 , yPos -180, canvas);
		
		
		faceParts[3] = new FilledArc(xPos-85, yPos+15 , 165 , 110, -180, 180, canvas);
		
		
		
		
		

		faceParts[1].setColor(Color.black);
		faceParts[2].setColor(Color.black);
		Color w = new Color(255,255,255);
		faceParts[3].setColor(w);
		
		

    }

}

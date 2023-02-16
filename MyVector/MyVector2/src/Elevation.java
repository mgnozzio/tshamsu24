import java.util.Comparator;


public class Elevation implements Comparator<Volcanoes>{
	public int compare(Volcanoes a, Volcanoes b) {
		
		return a.getElevation() - b.getElevation(); 
		
	}
}
import java.util.Comparator;

public class ByID implements Comparator<Volcanoes>{
	public int compare(Volcanoes a, Volcanoes b) {
		if(a.getVolID() > b.getVolID()) {
			return (int)a.getVolID();
			
		} else {
			
			return (int)b.getVolID(); 
		}
	}
}
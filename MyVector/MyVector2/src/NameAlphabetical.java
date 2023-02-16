import java.util.Comparator;

public class NameAlphabetical implements Comparator<Volcanoes>{
	public int compare(Volcanoes a, Volcanoes b) {
		return a.getName().compareTo(b.getName());
	}
}
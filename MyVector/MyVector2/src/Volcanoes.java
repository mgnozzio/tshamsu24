
public class Volcanoes {
	
	private int volcanoid;
	private String name;
	private String location;
	private String status;
	private int elev;

	
	//VOLCANOID,NAME,LOCATION,STATUS,ELEV
	
	public Volcanoes(int volid, String nam, String loc, String stat, int el) {
		volcanoid =	volid;
		 name = nam;
		 location = loc ;
		status = stat;
		elev = el;
		
	}
	
	//making methods that get the different elements of a volcano
	public int getVolID() {
		return volcanoid;
	}

	
	public String getName() {
		return name; 
	}
	
	public String getLocation() {
		return location; 
	}
	
	public String getStatus() {
		return status;
	}
	
	public int getElevation() {
		return elev;
	}
	
	
	
	
	public String toString() {
		//specifying what element it is inside of the the string that gets printed in the end
		return "VOLCANX020: " + volcanoid + " NAME: " + name + " LOCATION: "+ location +
				" STATUS: "+ status + " ELEV: " + elev ; 
	}
	
}


public class Events implements Comparable<Events> {
	private int time; 
	private char type; 
	
	
	//making instance of teller bc multiqueue need a background teller 
	//to process customers by line size
	private Teller ref; 
	public Events(int t, char c) {
		time = t; 
		type = c; 
	}
	public int getTime() {
		return time;
	}
	
	public void setTime(int t) {
		time = t; 
	}
	
	public char getType() {
		return type; 
	}
	
	
	public void serve(Customer q) {
		this.time += q.getService() + q.getArrival(); 

	}
	public String toString() {
		return time + ""; 
	}
	public void attach(Teller t) {
		ref = t; 
	}
	public Teller getTeller() {
		return ref;
	}
	
	//Using time to sort
	public int compareTo(Events other) {
		Events t = (Events)other; 
		if(this.time<t.time) return -1; 
		else if(this.time == t.time) return 0; 
		else return 1; 
	}
}

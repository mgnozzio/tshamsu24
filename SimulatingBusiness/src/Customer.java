
public class Customer  implements Comparable<Customer> {
	
	private int arrival; 
	private int service; 
	
	public Customer(int a) {
		arrival = a; 
		service = (int)(Math.random()*240); 
	}
	public int getArrival() {
		return arrival;
	}
	
	public int getService() {
		return service;
	}
	
	public String toString() {
		return "arrival " + arrival + " service " + service; 
	}
	
	
	public int compareTo(Customer c) {
		Customer t = (Customer)c; 
		if(this.getArrival()<t.getArrival()) { 
			return -1; 
			}
		else if(this.arrival == t.getArrival()) { 
			return 0; 
			}
		else return 1; 
	}
	

}

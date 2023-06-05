
import java.util.PriorityQueue;
import java.util.*;
import java.util.ArrayList;
import java.util.PriorityQueue; 
import structure5.*;


public class Teller implements Comparable<Teller> {
	
	private PriorityVector<Customer> customers; 
	
	
	private int time; 
	
	public Teller() {
		customers = new PriorityVector<Customer>(); 
		
		time = 0; 
	}
	
	
	
	
	public void getInLine(Customer c) {
		customers.add(c);
	}
	
	public int getTime() {
		return time; 
	}
	//Returns the waiting time of the customer
	public int serve(int global) {
		int wait = 0; 
		if(!customers.isEmpty()) {
			Customer c = customers.remove();
			wait = global-c.getArrival(); 
			//next time Teller is free is after servicing the customre.
			time += c.getService(); 
		}
		return wait; 
	}
	
	public int getLineSize() {
		return customers.size(); 
	}
	
	//Sort by line size first, then by time
	public int compareTo(Teller other) {
		Teller t = (Teller)other; 
		int temp = this.customers.size(); 
		int a = t.customers.size(); 
		if(temp<a) return -1; 
		else if(temp == a) {
			if(this.time < t.time) return -1; 
			else if(this.time == t.time)return 0; 
			else {
				return 1; 
			}
		}
		else return 1; 
	}


}
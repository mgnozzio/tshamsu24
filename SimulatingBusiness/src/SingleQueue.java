
import java.util.ArrayList;

import structure5.*;

public class SingleQueue {
	private PriorityQueue<Events> EventsQueue; 
	private PriorityQueue<Customer> customerQueue; 
	private ArrayList<Integer> wait; 
	private int globalTime;
	
	
	public SingleQueue() {
		
		EventsQueue = new PriorityVector();
		customerQueue = new PriorityVector(); 
		wait = new ArrayList<Integer>(); 
		globalTime = 0; 
		//making 3 tellers
		for(int i = 0; i < 3; ++i) {
			Events e = new Events(0,'t'); 
			EventsQueue.add(e);
		}
		//random customers
		for(int i = 0; i < 20; ++i) {
			Customer c = new Customer((int)(Math.random() * 240)); 
			customerQueue.add(c);
		}
		
	}
	public String printCustomers() {
		ArrayList<Customer> debug = new ArrayList<Customer>(); 
		
		String temp = "Customer list: "; 
		while(!customerQueue.isEmpty()) {
			Customer c = customerQueue.remove(); 
			debug.add(c);
			temp += "Customer " + c + "\n"; 
		}
		while(!debug.isEmpty()) {
			customerQueue.add(debug.remove(0));
		}
		return temp; 
		
	}
	//for simulating businesses
	public SingleQueue(PriorityQueue<Customer> c) {
		EventsQueue = new PriorityVector();
		wait = new ArrayList<Integer>(); 
		globalTime = 0; 
		//three tellers 
		for(int i = 0; i < 3; ++i) {
			Events e = new Events(0,'t'); 
			EventsQueue.add(e);
		}
		customerQueue = c; 

	}
	
	
	public void simulate() {
		int waitTime = 0; 
		while(!customerQueue.isEmpty()) {
			Customer c = customerQueue.remove();
			//when customer has arrived
			if(c.getArrival() <= globalTime) {
					Events temp = EventsQueue.remove(); 
					
					if(temp.getTime() < globalTime || temp.getTime() == 0) {
						
						wait.add(waitTime);
						waitTime = 0; 
						//reset if teller time lags
						temp.setTime(globalTime); 
						temp.serve(c);
					}
					else {
						//when teller is occupied add customer to queue
						customerQueue.add(c);
						waitTime++; 
					}
				
					EventsQueue.add(temp);
			}
			
			else {
				customerQueue.add(c);
			}
		globalTime++; 
		}
	
		int max = 0; 
		while(!EventsQueue.isEmpty()) {
			Events t = EventsQueue.remove(); 
			if(max < t.getTime()) {
				max = t.getTime(); 
			}
		}
		System.out.println("The max time for Single Queue is: " + max); 
		
		int avg = 0; 
		for(int i = 0; i < wait.size(); ++i) {
			avg += wait.get(i); 
		}
		avg /= wait.size();
		System.out.println("The average wait time is: " + avg); 
	}
	
	public ArrayList<Integer> waitingTimes(){
		return wait; 
	}
	
	public static void main(String[] args) {
		SingleQueue s = new SingleQueue(); 
		s.simulate(); 

	}
	

}

import java.util.*;
import java.util.PriorityQueue;

import structure.*;


public class MultipleQueue {

	    //time
		private PriorityVector EventsQueue; 
		//arrival time
		private PriorityVector customerQueue; 
		// Line length
		private PriorityVector tellerLineQueue; 
		private ArrayList<Integer> waitTimes; 
		private int globalTime; 	
		public MultipleQueue(){
			//Initialize
			EventsQueue = new PriorityVector();
			customerQueue = new PriorityVector(); 
			tellerLineQueue = new PriorityVector(); 
			waitTimes = new ArrayList<Integer>(); 
			globalTime = 0; 
	
			for(int i = 0; i < 3; ++i) {
				Events e = new Events(0,'t'); 
				Teller t = new Teller(); 
				e.attach(t); 
				EventsQueue.add(e);
				tellerLineQueue.add(t); 
			}
			
			for(int i = 0; i < 20; ++i) {
				Customer c = new Customer((int)(Math.random() * 240)); 
				customerQueue.add(c);
			}
		}
		public String printTeller() {
			ArrayList<Teller> debug = new ArrayList<Teller>(); 
			String temp = "Teller list: "; 
			int count = 1; 
			while(!tellerLineQueue.isEmpty()) {
				Teller c = (Teller) tellerLineQueue.remove(); 
				debug.add(c);
				temp += "Teller " + count + " " +  c + "\n"; 
				count++; 
			}
			while(!debug.isEmpty()) {
				tellerLineQueue.add(debug.remove(0));
			}
			return temp + "\n"; 
		}
		
		public String printCustomers() {
			ArrayList<Customer> debug = new ArrayList<Customer>(); 
			String temp = "Customer list: "; 
			while(!customerQueue.isEmpty()) {
				Customer c = (Customer) customerQueue.remove(); 
				debug.add(c);
				temp += "Customer " + c + "\n"; 
			}
			while(!debug.isEmpty()) {
				customerQueue.add(debug.remove(0));
			}
			return temp; 
		}
		
		
		public MultipleQueue(PriorityVector c) {
			waitTimes = new ArrayList<Integer>(); 
			EventsQueue = new PriorityVector();
			tellerLineQueue = new PriorityVector(); 
			globalTime = 0; 
		
			for(int i = 0; i < 3; ++i) {
				Events e = new Events(0,'t'); 
				Teller t = new Teller(); 
				e.attach(t); 
				EventsQueue.add(e);
				tellerLineQueue.add(t); 
			}
			customerQueue = c; 
		}
		
		public void simulate() {
			while(!EventsQueue.isEmpty()) {
			
				if(!customerQueue.isEmpty()) {
					Customer c = (Customer) customerQueue.remove();
			
					if(c.getArrival() <= globalTime) {
						Teller t = (Teller) tellerLineQueue.remove(); 
						
						t.getInLine(c); 
						tellerLineQueue.add(t);
					}
					else {
						customerQueue.add(c);
					}
				}
				Events temp = (Events) EventsQueue.remove();
					//if a teller is free
					if(temp.getTime() < globalTime || temp.getTime() == 0) {
						Teller t = temp.getTeller();
						//serve and record waiting time
						waitTimes.add(t.serve(globalTime)); 
						//for the next time teller is free
						temp.setTime(t.getTime());
					}					
					EventsQueue.add(temp); 
					
				if(customerQueue.isEmpty()) {
					Events e = (Events) EventsQueue.remove(); 
					if(e.getTeller().getLineSize() == 0) {
						
					}
					else {
						EventsQueue.add(e); 
					}
				}
				globalTime++; 
			}	
			//checks which teller took longest
			int max = 0; 
			while(!tellerLineQueue.isEmpty()) {
				Teller t = (Teller) tellerLineQueue.remove(); 
				if(max < t.getTime()) {
					max = t.getTime(); 
				}
			}
			System.out.println("The max time for multiple queue is: " + max); 
			
			//FINDING AVERAGE FOR THE WAIT
			int avg = 0; 
			for(int i = 0; i < waitTimes.size(); ++i) {
				avg += waitTimes.get(i); 
			}
			avg /= waitTimes.size();
			System.out.println("The average wait time is: " + avg); 
		}
		
		public ArrayList<Integer> getWaitTimes(){
			return waitTimes; 
		}
		public static void main(String[] args) {
			MultipleQueue test = new MultipleQueue(); 
			test.simulate(); 
		}
		
		
}

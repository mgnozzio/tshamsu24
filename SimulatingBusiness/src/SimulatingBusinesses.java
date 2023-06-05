import java.util.Scanner;
import structure.*;
import java.util.PriorityQueue;


public class SimulatingBusinesses {
	/*
	 * THOUGHT QUESTIONS
	 * 1.Run several simulations of both types of queues. 
	 * Which queue strategy seems to process all the customers fastest?
	 * The multiplequeue usually takes longer to process because the multiple
	 * queue only equals the time of the single queue when customers take the
	 * shortest line AND the one that finishes quickest.
	 * 
	 * 2.Is their a difference between the average wait time for customers
	 *  between the two techniques?
	 *  Yes, going with the first question, since the single queue usually takes 
	 *  shorter time the average wait time for single is almost always(except
	 *  for the condition stated in 1) shorter.

	 * 
	 * 3.Suppose you simulated the ability to jump between lines in a multiple
	 *  line simulation. When a line has two or more customers than another line, 
	 *  customers move from the end one line to another until the lines are fairly even.
	 *   You see this behavior frequently at grocery stores. Does this change the type of 
	 *   underlying structure you use to keep customers in line?
	 *   Yes, since I'm using priority queues the way I'm accessing the customers is by
	 *   referencing the very first element I can't find every single customer in a 
	 *   tellers line.
	 *   
	 *   
	 *  4. Suppose lines were dedicated to serving customers of varying lengths 
	 *    of service times. Would this improve the average wait time for a customer?
	 *    It would increase because although customers that have short service times
	 *    will finish quicker the total would increase because the line that serves the 
	 *    longest service customers will have too much and time will be more concentrated
	 *    than it is now.
	 * 
	 * 
	 * */

	public static void main(String[] args) {
		
		PriorityVector customerQueue = new PriorityVector(); 
		PriorityVector customerQueue2 = new PriorityVector(); 
		for(int i = 0; i < 20; ++i) {
		
			Customer c = new Customer((int)(Math.random() * 240)); 
			customerQueue.add(c);
			customerQueue2.add(c);
		}
	
		SingleQueue s = new SingleQueue(); 
		MultipleQueue m = new MultipleQueue(customerQueue2); 
		s.simulate();
		m.simulate(); 

	}
}

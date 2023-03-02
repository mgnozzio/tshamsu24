import java.util.ArrayList;
import structure5.*;

public class SubIterator extends AbstractIterator<ArrayList<Integer>>{

	private ArrayList<Integer> v;
	private long counter;
	private static int n;
	
	public SubIterator(ArrayList<Integer> v) {
		counter = 0;
		this.v = v;
		n = v.size();
		
	}
	
	public boolean hasNext() {
		
			return counter + 1 <= Math.pow(2, n);
		
	}
	
	
	/*Write a get method that returns a new Vector of values that are 
	 * part of the current subset. If bit i of the current counter is 1,
	 *  element i of the Vector is included in the resulting subset Vector
	
	*/public ArrayList<Integer> get() {
		
		ArrayList<Integer> subset = new ArrayList<Integer>();

		for(int i = 0; i < n; i++) {
			
			if((counter & 1L<<i) > 0) {
				subset.add(v.get(i));
			}
			
			}
		
			return subset;
	}
	
	
	public ArrayList<Integer> next() {
	
		ArrayList<Integer> current = (ArrayList<Integer>) get();
		counter++;
		return current;
		}
	
	public void reset() {
		
		counter = 0;
	}
	
	public long giveCounter() {
		return counter;
	}
	

}
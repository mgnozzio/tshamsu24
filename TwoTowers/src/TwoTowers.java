import java.util.ArrayList;

/* QUESTIONS
 * 1. What is the best solution to the 15-block problem?
 * 
 * 2. This method of exhaustively checking the subsets of blocks will not work for very large problems. Consider, 
 * for example, the problem with 50 blocks: there are 250 different subsets. One approach is to repeatedly pick 
 * and evaluate random subsets of blocks (stop the computation after 1 second of elapsed time, printing the best
 *  subset found). How would you implement randomSubset, a new SubsetIterator method that returns a random subset
 */

/*THOUGHT QUESTIONS ANSWERS
 * 
 * 1. The best solution is what my TwoTowers prints, which is [4, 5, 6, 10, 11, 12, 13].
 * 
 * 2. To implement randomSubset, you need to generate a random number between 0 and 2^n - 1, where n is the
 * number of blocks, instead of the traditional counter in my current Iterator class. You would generate this
 * random number every time in the get method and use this number to calculate the subset. The possibility of
 * repeats is negligible since we would only use  randomSubset when n is very large.
 *
 * 
 * 2. Inside my SubIterator class instead of having my counter set to 0 at first and then add one to it every 
 * time next is called, I would make my counter set to a random number(using math.random()) with a range from 
 * 1 to 2^n -1. 
 * 
 */

public class TwoTowers {
	
	private static int  blocknum = 15;
	
	public static void main(String[] args) {
		
		
		ArrayList<Integer> blocks = new ArrayList<Integer>();
		 
		for(int i = 1; i <= blocknum ; i++) {
			blocks.add(i);
		}
		
		//making a long that starts at 0
		long iterate = 0;	
		
		//letting user know what the total height of the blocks is
		double totalHeight = totalHeight(blocks);
		System.out.println( totalHeight + " is the height of all blocks together");
				
		double halfHeight = totalHeight/2.0;
		System.out.println("The height of the half subset we create shouldn't be higher"
				+ " than half the total height which is " + halfHeight );
		System.out.println();
				
		
				
		SubIterator i = new SubIterator(blocks);
		ArrayList<Integer> firstBlocks = i.next();
				
		double maxHeight = totalHeight(firstBlocks);
		//sorted blocks are the best sorted
		ArrayList<Integer> sortedBlocks = firstBlocks;
				
//		double secondHeighest = maxHeight;
//		ArrayList<Integer> secondBestBlocks = firstBlocks;
//		
		
			
		while(i.hasNext()) {
			
			ArrayList<Integer> currentBlocks = i.next();
			double newSubsetHeight = totalHeight(currentBlocks);
			
			
			//check if the height of the new subset is greater than the secondheighest and its lower than
			//the maximum height
//			if(newSubsetHeight > secondHeighest && newSubsetHeight < maxHeight) {
//				//if yes set it to the second highest and change second best to current
//				secondHeighest = newSubsetHeight;
//				secondBestBlocks = currentBlocks;
//			}
			
			
			//check if the newsubset has a smaller height that the possible half height and if
			// the new subset has a larger height than the max
			if(newSubsetHeight < halfHeight && newSubsetHeight > maxHeight) {
				//yes: the sorted become our current
				sortedBlocks = currentBlocks;
				//newsubset has the max height now
				maxHeight = newSubsetHeight;
				//makes the long equal the counter
				iterate = i.giveCounter();
			}
			
		}
		


		System.out.println("So the " + sortedBlocks + " is the best solution for 15 blocks" +
							" and they have a height of " + maxHeight + " inches.");
		
		System.out.println("The tower is " + (halfHeight - maxHeight) + " away from a perfect half.");
		
		System.out.println(iterate + "is the iteration long we got to the answer on");
		System.out.println("The two subsets differ by " + (totalHeight - 2 * maxHeight) + " inches.");
		
	}
	
	//adds up the height of all the blocks and gives it as a double
	public static double totalHeight(ArrayList<Integer> v) {
		double d = 0;
		
		for(int i : v) {
			d += Math.sqrt((double)i)/10.0;
		}
		return d;
	}
	}


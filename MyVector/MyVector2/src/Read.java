import java.io.FileReader; 
 
import java.io.IOException; 
import java.io.*; 
import java.util.*;



public class Read{
	

	/*THOUGHT QUESTIONS
	 * 1. Since the compare method in the RevComparator class returns the negative of base.compare, 
	 * which would have done it in ascending order, the opposite of that would make it give the reverse order.
	 * 
	 * 2.By making protected data types for them then adding/intializing these within the RevConstructor.
	 * Then we can make methods that use these data types to sort the way we want and change the return of
	 * the compare method.
	 * 
	 * 
	 */
     
		private static MyVector<Volcanoes> v;
		public static void main (String[] args){
			
			MyVector<Volcanoes> v = new MyVector<Volcanoes>(); 
			try {
				//making a new scanner that reads in the file
				File f = new File("Volcanoes.txt");
				String line = "";
				Scanner s = new Scanner(f);
				String txtSplitOn = ",";
        	
				
            while ( s.hasNextLine())   {
            	line = s.nextLine();
            	String[] temp = line.split(txtSplitOn);
            	
            		//int volid, String nam, String loc, String stat, int el
            		
            		v.add(new Volcanoes(Integer.parseInt(temp[0]),temp[1],temp[2], temp[3], Integer.parseInt(temp[4])));
	
            	
            	
            }
        
             
           
            System.out.print(v);
            
            //letting user know that the first order is alphabetical
            System.out.println("\n\n\n");
            System.out.println("sorted by the alphabetical order of their name"); 
            
            //Sorting the volcanoes first by the alphabetical order of their name
            //using the NameAlphabetical class
	        v.sort(new NameAlphabetical());
	        for(int i= 0; i < v.size(); i++) {
	        	System.out.println(v.get(i)); 
	        }
	        
	        //letting user know the next order is by their ID number
	        System.out.println("\n\n\n");
	        System.out.println("sorted by their Volcano ID"); 
	        
	        
	        //Sorting the volcanoes by ID number using the ByID class
	        v.sort(new ByID());
	        for(int j= 0; j < v.size(); ++j) {
	        	System.out.println(v.get(j)); 
	        }
	        
	      //letting user know the next order is by their elevation
	        System.out.println("\n\n\n");
	        System.out.println("sorted by Elevation");
	        
	        //Sorting the volcanoes first by their elevation using the Elevation class
	        v.sort(new Elevation());
	        for(int j = 0; j < v.size(); ++j) {
	        	System.out.println(v.get(j)); 
	        }
        
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
		public static void sort(Comparator<Volcanoes> c) {
			mergeSort(0, v.size()-1, c);
			
		}
		
		//making a merge sort algorithm because the data is long
		public static void mergeSort(int start, int end, Comparator<Volcanoes> c) {
			if(start < end) {
				int mid = (start+end)/2;
				//recursively calling the function again till it splits list to halves continuously
				//"left side"
				mergeSort(start, mid, c);
				//"right side"
				mergeSort(mid + 1, end, c);
				//calling the merge method
				merge(start, mid, end, c);
			}
			
		}

		public static void merge(int start, int mid, int end, Comparator<Volcanoes> c) {
			
			int start2 = mid + 1;
			
			if(c.compare(v.get(mid), v.get(start2)) <=0) {
				return;
			}
			while(start <= mid && start2 <= end) {
			
				if(c.compare(v.get(start), v.get(start2)) <= 0) {
					start++;
				} else {
					
					Volcanoes temp = v.get(start2);
					int ind = start2;
					
					while(ind != start) {
						v.set(ind, v.get(ind-1));
						ind--;
					}
					
					v.set(start,  temp);
					start++;
					mid++;
					start2++;
				}
			}
		}
		
	
	

		}

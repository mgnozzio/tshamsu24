import structure5.*;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;

public class MyVector<Volcanoes> extends Vector<Volcanoes> {

	public MyVector(){
		
		super();
		
	}
	
	public void sort(Comparator<Volcanoes> volcs) {
		
		
		
		for (int i = 0; i < this.size(); i++) 
        {  
        	// Find the minimum element in unsorted array 
            int min_idx = i; 
            for (int j = i+1; j < this.size(); j++) 
                if (volcs.compare(this.get(j), this.get(min_idx)) < 0)
                    min_idx = j; 
            // Swap the found minimum element with the first 
            // element 
            Volcanoes temp = this.get(min_idx); 
            this.set(min_idx, this.get(i));
            this.set(i,temp);
        } 
		
	}
	
	public static void main (String[] args){
	
		
	}
}

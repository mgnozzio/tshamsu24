import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

// Thought Questions 
// 3.4 The implementation of java.util.Vector provides a method setSize. This method explicitly sets the size of the Vector.
// Why is this useful? Is it possible to set the size of the Vector without using this method?
//		Explicitly setting the size of the Vector using the method setSize is useful
//     to have a Vector initialized with a specific size you want to fill later.
// 3.6 Write a class called BitVector that has an interface similar to Vector, but the values stored within the BitVector 
//are all known to be boolean (the primitive type). What is the primary advantage of having a special-purpose vector, like BitVector?
//		The BitVector class is attached. By making it a special-purpose vector like a boolean vector you create uniformity for your
//      so if at some other point you are accessing this vector everything that is not a boolean will be cast as an error and also you can later
//      use operations that are specific to your special-purpose vector(like increasing and decreasing order for an integer vector)
// 3.8 Suppose that a precisely sized array is used to hold data, and that each time the array size is to be increased, 
//it is increased by exactly one and the data are copied over. Prove that, in the process of growing an array incrementally 
//from size 0 to size n, approximately n2 values must be copied.
//      The value that would be copied over would be the sum of all values between 0 and n (i.e 0 values to be copied + 1 values to be copied ... + n values to be copied)
//      which equals the total sum equation of ((n^2) + n)/2. For a sufficiently large n(i.e as n approaches infinity) the limit goes to n^2
//      which proves that the number of values that will be copied in the array will be approximately n^2.
//     

public class megarev {

    public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
        if(args.length > 0){
            
            try{
                File f = new File(args[0]);
                s = new Scanner(f);
            } catch(Exception e){
                System.out.println("File not found");
                System.exit(1);
            }

        }
        ArrayList<String> lines  = new ArrayList<String>();
        
        while(s.hasNextLine()){
            String line = s.nextLine();
            lines.add(line);
        }

        for (int i=lines.size()-1; i>=0; i--) {
            String line = lines.get(i);
            String[] words = line.split(" ");
            for(int j=words.length-1; j>=0; j--){
                System.out.print(words[j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        
    
        
    }

    
}
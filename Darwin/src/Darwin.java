import structure5.*;
import java.util.ArrayList;
import java.util.*;

/**
* This class controls the simulation. The design is entirely up to
* you. You should include a main method that takes the array of
* species file names passed in and populates a world with species of 
* each type.
* <p>
* Be sure to call WorldMap.pause every time
* through the main simulation loop or else the simulation will be too fast
* and keyboard / mouse input will be slow. For example: 
* <pre>
*	public void simulate() {
*	for (int rounds = 0; rounds < numRounds; rounds++) {
*           giveEachCreatureOneTurn();
*           WorldMap.pause(100);
*         }
*	}
* </pre>
*/


/** THOUGHT QUESTIONS
 * 1. First I'll make an array whose size is the same as the number of species and each
 * species has a specific index associated to it. Then I can make a loop that checks the 
 * species of each creature and if all the answers are the same(i.e. each creature in the
 * loop return the same species) then the program knows that species has won. To count this 
 * win I'll increment the number in the Array at the index of that species.
 * No, it won't work in case where species basically reach a stale mate and they're
 * just in their own corner not interacting with each other.
 * 
 * 
 * 2. A Just-In-Time compilers as its name hints compiles the code while the program is running.
 *  As opposed static compilers, like ones used for c, that compile all the code and turn it
 *  into machine code before the program runs, a JIT compiler however compiles during the
 *  runtime and can make optimized versions/representations of patterns(like loops) because
 *  it can have a dynamic analysis of the program.
 *  
 *  3. I read the letter and I think that Djikstra has a point. Most of the errors I have
 *  encountered in this project have been in my creatures class and making sure that it was
 *  getting the right instruction was confusing. I had to use the debugger on Eclipse multiple
 *  times and with no debugger like that at the time I definitely would have been annoyed by 
 *  go to as Djikstra was. A point in favor of it though is that it makes the txt files much 
 *  shorter, but then again if you mix up lines it get annoying.
 *
 *
 */

class Darwin {
	
	
	
	
	/**
	* The array passed into main will include the arguments that 
	* appeared on the command line. For example, running "java 
	* Darwin Hop.txt Rover.txt" will call the main method with s 
	* being an array of two strings: "Hop.txt" and "Rover.txt". 
	*/
	public static void main(String s[]) {
		
		//Making a 20 by 20 grid world and refresh
		WorldMap.createWorldMap(20,20); 
		WorldMap.pause(3); 
		
		World<Creature> world = new World<Creature>(20,20);
		ArrayList<Creature> creatures = new ArrayList<Creature>();
		

		for(String fileName : s) {
			//making a new species for inputed file
			Species species = new Species(fileName);
			int numPopulated = 0;
			while(numPopulated < 10) {
				//using math.randomreate  to generate a random position and 
				//direction for the creatures
				Position pos = new Position((int) (Math.random()*19), (int) (Math.random()*19));
				int dir = (int) (Math.random()*4);
				//If random spot in world not taken, populate it with a creature
				if(world.get(pos) == null) {
					//making a creature with above statements
					//out the creature in world and increase population size
					Creature c = new Creature(species, world, pos, dir);
					creatures.add(c);
					world.set(pos, c);
					numPopulated++;
				}
			}
		}
		//randomize which creature
		while(true) {
			Collections.shuffle(creatures);
	    	for(Creature creature : creatures) {
	    		creature.takeOneTurn();
	    		WorldMap.pause(3);
	    	}
		}
		}
	}
	

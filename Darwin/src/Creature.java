/**
 * This class represents one creature on the board.
 * Each creature remembers its species, position, direction,
 * and the world in which it is living.
 * <p>
 * In addition, the Creature remembers the next instruction
 * out of its program to execute.
 * <p>
 * The creature is also responsible for making itself appear in
 * the WorldMap.  In fact, you should only update the WorldMap from
 * inside the Creature class. 
 */

public class Creature {
	
	private Species s;
	private World<Creature> w;
	private Position p;
	private int d;
	private int step;
	private int creaturemem; //creature's memory
	
	
    /**
     * Create a creature of the given species, with the indicated
     * position and direction.  Note that we also pass in the
     * world-- remember this world, so that you can check what
     * is in front of the creature and to update the board
     * when the creature moves.
	 * @param species The species of the creature
	 * @param world The world in which the creature lives
	 * @param pos The position of the creature
	 * @param dir The direction the creature is facing
	 * @pre species, world, and pos must be non-null
	 * @pre pos must be within the bounds of world
	 * @pre dir must be one of: Position.NORTH, Position.SOUTH, Position.EAST
	 *                          or Positon.WEST
	 * @pre the world map must have been created
	 * @post creates a creature of species species in world world at position
	 *       pos facing direction dir
	 * @post initializes instance variables so that the creature knows what
	 *		 instruction to begin executing
	 * @post displays the creature on the world map
     */
    public Creature(Species species, World<Creature> world, Position pos, int dir) {
    	s = species;
    	w = world;
    	p = pos;
    	d = dir;
    	
    	step = 1;
    	WorldMap.displaySquare(pos, Character.toUpperCase(species.getName().charAt(0)), dir, species.getColor());
	}

    /**
     * Return the species of the creature.
     */
    public Species species() {
    	return s;
	}

    /**
     * Return the current direction of the creature.
     */
    public int direction() {
    	return d;
    }

    /**
     * Return the position of the creature.
     */
    public Position position() {
    	return p;
    }
    
    //Return's creature's memory
    public int creaturemem() {
    	return creaturemem;
    }

    /**
     * Execute steps from the Creature's program until 
     * a hop, left, right, or infect instruction is executed.
	 * @post Creature takes one turn's worth of instructions
	 * @post display is updated to reflect movement of this creature
	 *
     */
    public void takeOneTurn() {
    	//to change turn boolean
    	boolean go = true;
    	
    	long start = System.currentTimeMillis();
    	
    	while(step <= s.programSize() && go) {
    		
    		if(System.currentTimeMillis() - start > 10){
    			System.out.println();
    			
    		}
    		Instruction currentStep = s.programStep(step);
    		Creature target = null;
    		Position adjacent = p.getAdjacent(d);
    		if(w.inRange(adjacent)) {
    			
    		target = w.get(adjacent);
    		}
    			
    		
    		switch(currentStep.getOpcode()) {
    		case Instruction.HOP: 
    			if(w.inRange(adjacent) && w.get(adjacent) == null) {
    				WorldMap.displaySquare(p, ' ', d, s.getColor());
    				w.set(p, null);
    	    		p = p.getAdjacent(d);
    	    		w.set(p, this);
    			}
    			go = false;
    			break;
    		case Instruction.LEFT: 
    			d=(d+3)%4;
    			go = false;
    			break;
    		case Instruction.RIGHT: 
    			d=(d+1)%4;
    			go = false;
    			break;
    		case Instruction.INFECT: 
    			//For all cases check if adjacent square is in range of world
    			//And also check whether adjacent square is null (no creature) or not
    			//This prevents NullPointerException
    			if(w.inRange(adjacent) && w.get(adjacent) != null) {
    				target = w.get(adjacent);
    	    		if(!target.s.equals(s)) {
    	    			//Change species of step number of victim
    	    			target.s = s;
    	    			if(currentStep.getAddress() == 0) target.step = 1;
    	    			//Need to subtract -1 because step increments at end of while loop
    	    			else w.get(adjacent).step = currentStep.getAddress()-1;
    	    			//Display new square for victim to represent species change
    	    			WorldMap.displaySquare(adjacent, s.getName().charAt(0), w.get(adjacent).direction(), s.getColor());
    	    		}
    			}
    			go = false;
    			break;
    		case Instruction.IFEMPTY: 
    			//Need Assert statement to make sure that it doesn't skip to a step the species doesn't have
    			
    			if(w.inRange(adjacent) && w.get(adjacent) == null) {
    				step = currentStep.getAddress()-1;
    			
    			}
    			go = false;
    			break;
    		case Instruction.IFWALL: 
    			
    			if(!w.inRange(adjacent)) step = currentStep.getAddress()-1;
    			go = false;
    			break;
    		case Instruction.IFSAME: 
    			
    			target = w.get(adjacent);
    			if(w.inRange(adjacent) && target != null) {
    				
    				if(target.s.equals(s)) {
    	    			step = currentStep.getAddress()-1;
    	    		}	
    			}	
    			go = false;
    			break;
    		case Instruction.IFENEMY: 
    			
    			if(w.inRange(adjacent) && w.get(adjacent) != null) {
    				
    				if(!target.s.equals(s)) {
            			step = currentStep.getAddress()-1;
    				}
    			}
    			go = false;
    			break;
    		case Instruction.IFRANDOM: 
    			
    			if(Math.random()<0.5) step = currentStep.getAddress()-1;
    			go = false;
    			break;
    		case Instruction.GO: 
    			
    			step = currentStep.getAddress()-1; //-1 bc step is incremented at end of while loop	
    			go = false;
    			break;
    		case Instruction.IFTWOENEMY: 
    			
    			Position twoAhead = adjacent.getAdjacent(d);
    			if(w.inRange(twoAhead) && w.get(twoAhead) != null) {
    				
    				
    				if(!target.s.equals(s)) {
            			step = currentStep.getAddress()-1;
    				}
    			}
    			go = false;
    			break;
    		
    			
    		case Instruction.IFEQ: 
    			
    			if(creaturemem == currentStep.getTestCase()) {
    				step = currentStep.getAddress()-1;
    		
    			}
    			break;
    		case Instruction.INC: 
    			creaturemem--;
    			break;
    		case Instruction.DEC:
    			creaturemem++;
    			break;
    		case Instruction.SET: 
    			creaturemem = currentStep.getAddress();
    			break;
    			
    		
    		case Instruction.IFLEFT: 
    			
    			
    			adjacent = p.getAdjacent((d+3)%4);
    			if(w.inRange(adjacent) && w.get(adjacent) != null) {
    				
    				if(!target.s.equals(s)) {
            			step = currentStep.getAddress()-1;
    				}
    			}
    			break;
    		case Instruction.IFRIGHT: 
    			
    			
    			adjacent = p.getAdjacent((d+1)%4);
    			if(w.inRange(adjacent) && w.get(adjacent) != null) {
    		
    				if(!target.s.equals(s)) {
            			step = currentStep.getAddress()-1;
    				}
    			}
    			break;
    		
    		case Instruction.IFMEMEQ :  
    			
    			//If adjacent creature's memory is v, go to n
    			if(w.inRange(adjacent) && w.get(adjacent) != null) {
    				if(w.get(adjacent).creaturemem() == currentStep.getTestCase()) {
    					step = currentStep.getAddress()-1;
        			}
    			}
    			break;
    		case Instruction.SAMEMEME:  //--yes, meme and not memory because why not?
    			//Actually just gets the same memory/ makes a copy
    			if(w.inRange(adjacent) && w.get(adjacent) != null) {
    				creaturemem = w.get(adjacent).creaturemem();
    			}
    			break;
    		}
    		step++;
		}
    	//Show the brand new world after one turn has been taken and has inevitably changed everything in life, yay!
    	WorldMap.displaySquare(p, s.getName().charAt(0), d, s.getColor()); 
	}
    
    public static void main(String[] args) {
    	WorldMap.createWorldMap(30, 30);
    	
    	Position p = new Position(15,20);
    	Position p2 = new Position(1,2);
    	World<Creature> w = new World<Creature>(30,30);
    	
    	
    	Species s = new Species("Flytrap.txt");
    	
    	
    	Creature c = new Creature(s, w, p, Position.NORTH);
    	Creature c2 = new Creature(s, w, p2, Position.SOUTH);
    	for(int i =0; i<6; i++) {
    	c.takeOneTurn();
    	WorldMap.pause(100);
    	c2.takeOneTurn();
    	WorldMap.pause(100);
    	}
    	
 
    }

} 

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * The individual creatures in the world are all representatives of some
 * species class and share certain common characteristics, such as the species
 * name and the program they execute. Rather than copy this information into
 * each creature, this data is recorded once as part of the description for
 * a species and then each creature can simply include the appropriate species
 * pointer as part of its internal data structure.
 * <p>
 * 
 * To encapsulate all of the operations operating on a species within this
 * abstraction, this provides a constructor that will read a file containing
 * the name of the creature and its program, as described in the earlier part
 * of this assignment. To make the folder structure more manageable, the
 * species files for each creature are stored in a subfolder named Creatures.
 * Thus, creating the Species for the file Hop.txt will causes the program to
 * read in "Creatures/Hop.txt".
 * 
 * <p>
 * 
 * Note: The instruction addresses start at one, not zero.
 */

public class Species {
	
	/**
	 * Create a species for the given file. 
	 * @param fileName the name of the file containing the data for the species
	 * @pre fileName exists in the Creature subdirectory.
	 */
	private String name;
	private String color;
	
	//private ArrayList<String> code;
	private ArrayList<Instruction> newInstructions;
	public Species(String fileName) {
		
		try {
		File f = new File("Creatures/"+fileName);
		//code = new ArrayList<String>();
		
		Scanner s = new Scanner(f);
		
		name = s.nextLine();
		color = s.nextLine();
		
		newInstructions = new ArrayList<Instruction>() ;
		while(s.hasNextLine()) {
			
			String line = s.nextLine();
			//code.add(line);
			
			if(line.equals(".")|| line.equals("")) { 
				break;
			
			
			}else {
				//string claSS Has split method and there is substring
				//need string op and address
				//make new intrwuction and add to array
				//parse int 
				
//				for(int i = 0; i<= code.size() -2 ; i++) {
//					String jump = code.get(i+1);
//					String[] branch = jump.split(" ");
//					
//					int address = 0;
//					int testNum = 0;
//					
//					//Get address in form of integer
//					if(branch.length == 2) {
//						address = Integer.parseInt(branch[1]);
//					} else if (branch.length == 3) {
//						address = Integer.parseInt(branch[2]);
//						testNum = Integer.parseInt(branch[1]);
//					}
					
				String[] branch = line.split(" ");
				int address = 0;
				int testNum = 0;
				
				//Get address in form of integer
				if(branch.length == 2) {
					address = Integer.parseInt(branch[1]);
				} else if (branch.length == 3) {
					address = Integer.parseInt(branch[2]);
					testNum = Integer.parseInt(branch[1]);
				}
				
					//Convert function into opcode. Use upperCase for standardization
					switch(branch[0].toUpperCase()){
					case "HOP":
						newInstructions.add(new Instruction(1, address));
						break;
					case "LEFT":
						newInstructions.add(new Instruction(2, address));
						break;
					case "RIGHT":
						newInstructions.add(new Instruction(3, address));
						break;
					case "INFECT":
						newInstructions.add(new Instruction(4, address));
						break;
					case "IFEMPTY":
						newInstructions.add(new Instruction(5, address));
						break;
					case "IFWALL":
						newInstructions.add(new Instruction(6, address));
						break;
					case "IFSAME":
						newInstructions.add(new Instruction(7, address));
						break;
					case "IFENEMY":
						newInstructions.add(new Instruction(8, address));
						break;
					case "IFRANDOM":
						newInstructions.add(new Instruction(9, address));
						break;
					case "GO":
						newInstructions.add(new Instruction(10, address));
						break;
					case "IFTWOENEMY":
						newInstructions.add(new Instruction(11, address));
						break;
					case "IFEQ":
						newInstructions.add(new Instruction(12, testNum, address));
						break;
					case "INC":
						newInstructions.add(new Instruction(13, address));
						break;
					case "DEC":
						newInstructions.add(new Instruction(14, address));
						break;
					case "SET":
						newInstructions.add(new Instruction(15, address));
						break;
					case "IFENEMYLEFT":
						newInstructions.add(new Instruction(16, address));
						break;
					case "IFENEMYRIGHT":
						newInstructions.add(new Instruction(17, address));
						break;
					case "IFMEMEQ":
						newInstructions.add(new Instruction(18, testNum, address));
						break;
					case "COPYMEM":
						newInstructions.add(new Instruction(19, address));
						break;
					default :
						newInstructions.add(new Instruction(0, address));
						break;
					}
					
				
			}
			
		}
		
		
		}catch (Exception e) {
			System.out.print("File does not exist!");
			System.exit(1);
		}
		 
		
	}
	

	/**
	 * Return the name of the species.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Return the color of the species.
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Return the number of instructions in the program.
	 */
	public int programSize() {
		return newInstructions.size();
	}

	/**
	 * Return an instruction from the program. 
	 * @pre 1 <= i <= programSize().
	 * @post returns instruction i of the program.
	 */
	public Instruction programStep(int i) {
		return newInstructions.get(i-1);
	}

	/**
	 * Return a String representation of the program.
	 */
	public String programToString() {
		String s = "";
		for(int i = 0; i<newInstructions.size(); i++) {
			
			s += (i+1)+ " " + newInstructions.get(i).toString() + "\n";
			
			
		}
		return s;
	}
	
	
	//
	public static void main(String[] args) {
		
		Species flytrap = new Species("Flytrap.txt");
	//	ArrayList<String> program = flytrap.code;
		
		
		System.out.println(flytrap.getName());
		
		System.out.println(flytrap.getColor());
		
		System.out.println(flytrap.programSize());
		
		for(int i = 1; i <= flytrap.programSize(); i++) {
			String step = flytrap.programStep(i).toString();
			int opCode = flytrap.programStep(i).getOpcode();
			
			if(opCode==4) {
				step = step.substring(0, step.length()-2);
			}
			
			System.out.println(flytrap.programStep(i));
		}
		System.out.println(flytrap.programToString());
	}
	
}



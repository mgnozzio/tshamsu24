import java.io.Console;
import java.util.Scanner;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//Thought Questions
//1. Uniformity, in case the user types in different caitalization and it's easier for the program to receive something
//that is all in the same
//2. The methods in gallows like makeBeam(), makeBase();, makeRope();
//3. If sort is 
//4.It's redundant to have it in gallows as well because the value doesn't change so for the sake
//of efficieny and clarity it is better to have it in one place

public class Hangman {
	
	public static boolean win(char[] dashes){
		for(int i = 0; i < dashes.length; i++){
			if(dashes[i] == '_'){
				return false;
				
			}
		}
		return true;
		}
	public static boolean samelet(char w, char[] letters){
		for(int i = 0; i < letters.length; i++){
			if(w == letters[i]){
				return true;
				
			}
		}
		return false;
	}

	

	


	public static void main(String[] args) {
		System.out.println("Welcome to the ASCII Version of Hangman!");
		
		Console c = System.console();
		Scanner s = new Scanner(System.in);
		// char[] letters;
		// int firsttry = 1;
		// int wrongcount = 0;
		ArrayList<String> words = Read.getValues();
      	
		String word = words.get((int) (Math.random() * words.size()));
		char[] letters;
		letters = word.toCharArray();
		
      	
		// String word = words[(int) Math.random() * words.length];
		// char[] letters;
		// letters = word.toCharArray();
		
		
		// String prompt = "Please enter a secret word: ";
		// if(c != null) {
		// 	letters = c.readPassword(prompt);
			for(int i=0; i<letters.length; i++) {
				letters[i] = Character.toUpperCase(letters[i]);
			}
		// } else {
		// 	System.out.println("For best results, please run this from the command line.");
		// 	System.out.print(prompt);
		// 	letters = s.nextLine().trim().toUpperCase().toCharArray();
		// 	for(int i=0; i<10000; i++) System.out.println();
		// }
		
		// TODO: Write the code to play Hangman here.
		Gallows g = new Gallows();
	
		char[] dashes = new char[letters.length];
		for(int i = 0; i< dashes.length; i++){
			dashes[i] = '_';
		}
		
		
		System.out.println();
		System.out.print(g);
		
		while(g.isAlive() && !win(dashes)){ 
			
			
			System.out.print("Puzzle to solve: ");
			for(int i = 0; i < dashes.length; i++){
				System.out.print(dashes[i] + " ");
			}
			
			System.out.print("\nPlease guess a letter: ");
		
			boolean inword = false;
			char w = s.nextLine().toUpperCase().charAt(0);
			System.out.println(w);
			

			
			
			for(int i = 0; i < letters.length; i++){
				if(w == letters[i]){
					inword = true;
					dashes[i] = w;
				}
				
			}
			if(!inword ){
				System.out.println();
				g.hang();
				System.out.print(g);
				// wrongcount++;
			}
			// firsttry++;
			


	}
	
	if(win(dashes)){
		System.out.println("Success!  Player 2 wins!");
	}else{
		System.out.println("Game over! Player 1 wins!");
	}
	
	
}
}
			

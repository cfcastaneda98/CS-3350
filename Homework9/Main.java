/** 
Author: Carlos Fernando Castaneda
Class : CS 3350
Date Modified: November 10, 2020
Instructor: Vladik Kreinovich 
Assingment: Homework 9
TA: Ricardo R. Sillas
Purpose: to implement an alogrithm that stimulates the
process of a turing machine.
*/

//Imports utilities needed to run the code
import java.util.Arrays;

//Main Class of the program. There is only one needed for this program.
public class Main{
	
	public static Boolean check(int[] userTape, char[][] lr, int[][] symbols, int[][] states){
        System.out.println("Turing Machine Simulation beginning:");
		System.out.println(" ");
		//Symbols as seen in the paper from the website
		int location= 0;
		int head = 0;
		int currentSymbol;
		//Will read the states 
		while(head < states.length - 2){
			switch(head){
				case 0:
				System.out.print("State = S: position:-> ");
				break;
				
				case 1:
				System.out.print("State = M: position:-> ");
				break;
				
				case 2:
				System.out.print("State = B: position:-> ");
				break;
				
				case 3:
				System.out.print("State = H: position:-> ");
				break;
				
				case 4:
				System.out.print("State = accept: position:-> ");
				break;
				
				case 5:
				System.out.print("State = reject: position:-> ");
				break;
				
			}
			//Prints out the head of the current index head of the state
			System.out.println(head);
			//Everything below is from the pseudocode privided by Dr. Kreinovich's paper
			currentSymbol = userTape[location];
			userTape[location] = symbols[head][currentSymbol];
			int newHead = states[head][currentSymbol];
			if(lr[head][currentSymbol] == 'R' ){
				location ++;
			}
			else if(lr[head][currentSymbol]=='L'){
				location--;
			}
			head = newHead;
		}
		if(head == states.length-2){
			return true;
		} 
		else{
		return false;
	    }
	}

	
	public static void main (String args[]){
		System.out.print("Welcome to the Turing Machine Simulator!");
		System.out.println(" ");

		//We defined the usertape here as originally I had a user input planned
		int[] userTape={2,0,0,1,2};

		System.out.println("Beginning to analyze information..");
		System.out.println(" ");
		
		//We define the lr, symbols, and states 
		char[][] lr={
			{'H','H','R'},
			{'L','R','L'},
			{'L','H','H'},
			{'H','H','H'},
			{'H','H','H'},
			{'H','H','H'}
		};	
		//We define the lr, symbols, and states 
		int[][] symbols={
			{0,1,2},
			{1,0,1},
			{0,1,2},
			{0,1,2},
			{0,1,2},
			{0,1,2}
		};
		//We define the lr, symbols, and states 
		int[][] states ={
		   {5,5,1},
		   {2,1,2},
		   {2,5,3},
		   {5,5,4},
		   {4,4,4},
		   {5,5,5}
		};
		//Prints out the input contained in the user tape
		System.out.print("User tape input: ");
		for(int i=userTape.length-1;i>=0;i--){
			if(userTape[i]!=2){
				System.out.print(userTape[i]);
			}
		}
		System.out.println(" ");
		//Prints out the user tape length 
		System.out.println("The output of the user tape length is: ");
		System.out.println(userTape.length);
		System.out.println(" ");
		//Will begin to start the simulation, defines a boolean to print at the end.
		boolean valid = check(userTape, lr, symbols, states);
		System.out.println(" ");
		//Will print out the current user tape
		System.out.println("Current user tape: ");
		for(int i=userTape.length-1 ; i>=0; i--){
			if(userTape[i]!=2){
				System.out.print(userTape[i]);
			}
		}
		System.out.println(" ");
		//Prints out the boolean to see if the user tape was valid in the first place
		System.out.println("Is the userTape valid?: " + valid);
		System.out.println(" ");
		//Greets you farewell!
		System.out.println("Simulation terminated. Goodbye!");
    }
}
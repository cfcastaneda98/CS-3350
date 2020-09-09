/** 
Author: Carlos Fernando Castaneda
Class : CS 3350
Date Modified: September 8, 2020
Instructor: Vladik Kreinovich 
Assingment: Homework 2
TA: Ricardo R. Sillas
Purpose: to implement an alogrithm to simulate the function of
a given automaton.
*/

//Imports utilities needed to run the code
import java.util. * ;

//Main Class of the program. There is only one needed for this program.
public class Main {
  //Method that returns the 2d array for describing what state the finite automaton moves
  public static int[][] stateDirection(int n, int m) {
    //Brings in a scanner to read the input of the user.
    Scanner sc = new Scanner(System. in );
    int[][] autoStates = new int[n][m];

    // This helps us undestand which states are linking to one another from each step of the automaton.
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        System.out.println("You are in state " + i + ". In what state do you want to move to if you read the symbol " + j + " ? ");
        autoStates[i][j] = sc.nextInt();
      }
    }
    //Returns the 2D array
    return autoStates;
  }

  //Method that creates a boolean array to be used for describing for each state, whether it is final or not
  public static boolean[] finalStates(int n) {
    //Brings in a scanner to read the input of the user.
    Scanner sc = new Scanner(System. in );
    //Creates the boolean array
    boolean[] finalStates = new boolean[n];
    // This helps us undestand which states are final in the automaton.
    for (int i = 0; i < n; i++) {
      System.out.println("You are in state " + i + ". Is this a final state? (Hint: Enter either 'True' or 'False')");
      finalStates[i] = sc.nextBoolean();
    }
    //Returns the boolean array
    return finalStates;
  }

  //Method that processes each word from an array to validate them on the automaton
  public static int[] validateWord(int m) {
    //Brings in a scanner to read the input of the user.
    Scanner sc = new Scanner(System. in );
    int[] word = new int[m];

    //Asks the user for a symbol to use to test the automaton.
    for (int i = 0; i < m; i++) {
      System.out.println("Please enter (in int format) Symbol #" + i);
      word[i] = sc.nextInt();
    }
    //Returns the array back
    return word;
  }

  //Method that displays the result of the test for the automaton
  public static void testResults(int[] words, int[][] states, boolean[] finalStates) {
    //Starts at state 0, as asked in the homework.
    int currState = 0;

    //Browses through the words from state 0 onwards checking all of the words to test
    for (int i = 0; i < words.length; i++) {
      currState = states[currState][words[i]];
    }
    //If the current state is in the fianl state, then the word is accepted, otherwise it is rejected.
    if (finalStates[currState]) {
      System.out.println("The word is accepted.");
    }
    else {
      System.out.println("The word is rejected.");
    }
  }

  //Main method for the program 
  public static void main(String[] args) {
    //Brings in a scanner to read the input of the user.
    System.out.println("Hello. Welcome to the Finite Automaton Emulator!");
    Scanner sc = new Scanner(System. in );

    //Asks for the number of states in the automaton
    System.out.println("How many states are there in the automaton? (Hint: 'N')");
    int n = sc.nextInt();

    //Asks for the number of symbols in the automaton
    System.out.println("How many symbols are there in the automaton? (Hint: 'M')");
    int m = sc.nextInt();

    //Creates a 2D integer array to be used for describing what state the finite automaton moves
    int[][] autoStates = stateDirection(n, m);

    //Creates a boolean array to be used for describing for each state, whether it is final or not
    boolean[] finalS = finalStates(n);

    //Creates a boolean that marks if the process
    boolean completion = false;
    while (!completion) {

      int[] wordChecked = validateWord(m);
      testResults(wordChecked, autoStates, finalS);

      //Asks the User if they want to continue, if they do
      System.out.println("Do you wish to continue? Y/N");
      char cont = sc.next().charAt(0);

      //Will end the program if answer no
      if (cont == 'N') {
        System.out.println("Thank you for using this program. Goodbye!");
        completion = true;

      }
    }
  }
}
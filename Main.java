/** 
Author: Carlos Fernando Castaneda
Class : CS 3350
Date Modified: October 19, 2020
Instructor: Vladik Kreinovich 
Assingment: Homework 7
TA: Ricardo R. Sillas
Purpose: to implement an alogrithm that creates a mathematical expression into
a Postfix expression and solves that expression as well.
*/

//Imports utilities needed to run the code
import java.util.Stack;
import java.util.Scanner;

//Main Class of the program. There is only one needed for this program.
public class Main {
  // Method that converts a given infix expression to a postfix expression. 
  static String postfixConverter(String expression) {
    System.out.
    println("This is the expression in which we are trying to solve: ");
    System.out.println(expression);

    String postfix = new String(""); // Creates a new string to build the new postfix expression 

    Stack < Character > stack = new Stack < >(); // Creates an empty stack to place characters 
    for (int i = 0; i < expression.length(); ++i) { // Scans all of the characters one by one 
      char current = expression.charAt(i);

      if (Character.isLetterOrDigit(current)) { // If the current character is a number, add it to the string. 
        postfix += current;
      }
      else if (current == '(') {
        stack.push(current);
      }
      else if (current == ')') {
        while (!stack.isEmpty() && stack.peek() != '(') { //Will do these special steps to ensure that all of the items in the parentheses are done properly
          postfix += stack.pop();
        }
        if (!stack.isEmpty() && stack.peek() != '(') {
          return "ERROR: This is an invalid Expression!"; // invalid expression    
        }
        else {
          stack.pop();
        }
      }
      else { // Has found an opperator and will determine its hierarchy of importance
        while (!stack.isEmpty() && operatorValue(current) <= operatorValue(stack.peek())) {
          if (stack.peek() == '(') return "ERROR: This is an invalid Expression!";
          postfix += stack.pop();
        }
        stack.push(current); //Pushes the current character after everything
      }

    }

    while (!stack.isEmpty()) { // Proceeds to pop all of the items in the stack and adds them to the postfix expression
      if (stack.peek() == '(') {
        return "ERROR: Invalid Expression";
      }
      postfix += stack.pop();
    }
    return postfix; //Returns the final expression
  }

  // Method that solves a given postfix expression. 
  static int postfixSolver(String expression) {

    Stack < Integer > stack = new Stack < >(); //Creates a new stack to evaluate  
    for (int i = 0; i < expression.length(); i++) { // Scans all of the characters one by one 
      char current = expression.charAt(i);

      if (Character.isDigit(current)) { //Will push the character if it is a number on the stack
        stack.push(current - '0');
      }

      else { //Will proceed to apply an operation of two numbers based on the operand available in the stack at the moment
        int integer1 = stack.pop();
        int integer2 = stack.pop();

        switch (current) {
        case '+':
          stack.push(integer2 + integer1);
          break;

        case '-':
          stack.push(integer2 - integer1);
          break;

        case '/':
          stack.push(integer2 / integer1);
          break;

        case '*':
          stack.push(integer2 * integer1);
          break;
        }
      }
    }
    return stack.pop(); //Will return the final solution
  }

  // Method that returns the value of an operator based on its importance in the context of the expression
  static int operatorValue(char operator) {
    switch (operator) { // Will use the swicth cases depending on the different operators available
    case '+':
    case '-':
      return 1;
    case '*':
    case '/':
      return 2;
    }
    return - 1; //Returns this value if it detects another operator that is not appropriate
  }

  // Driver method 
  public static void main(String[] args) {
    System.out.
    println("Welcome to the Arithmetic converter! Please choose from below: ");
    System.out.println(" ");

    int state = 1;

    while (state == 1) { //Uses a while loop to go back to the menu after resolving the expression
      System.out.println("A: Calculate Postfix and Result of Expression");
      System.out.println("B: Exit the program");
      Scanner sc = new Scanner(System. in ); // Create a Scanner object to use for user selected expression
      char selection = sc.next().charAt(0);
      if (selection == 'a' || selection == 'A') {
        System.out.
        println("Please enter your one-digit expression without any spaces please!");
        Scanner expression = new Scanner(System. in );
        String exp = expression.nextLine();
        String exp2 = postfixConverter(exp); //New String that we will use for postfix solution
        System.out.println("The resulting postfix is:  \t" + exp2);
        System.out.println("The solution to the postfix is:  \t" + postfixSolver(exp2));
        System.out.println(" ");
        System.out.println("Tasks completed! Please choose from below:");
        System.out.println(" ");
      }
      else if (selection == 'b' || selection == 'B') { //Option for when the User wants to terminate the program
        System.out.println("Thank you for using this program. Goodbye!");
        state = 0;
      }
      else { //Bounce statement in case user inputs wrong letters
        System.out.println("ERROR: Selection not valid!");
        continue;
      }
    }
  }
}
/*
 * Author: Mauricio I. Reyes Villanueva
 * Class: Data Structures 201
 * Period: 4A
*/

// Imports
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringJoiner;

public class Welcome {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // Variable Declarations
        int intInput;
        boolean validInput = false;

        Scanner scanner;
        String nameInput;
        StringBuilder stringObject;
        StringBuilder reversedString;

        System.out.println("\nWelcome to CS 201: Data Structures!\n");
        System.out.print("What is your name? ");

        // Create a new scanner object for inputs
        scanner = new Scanner(System.in);
        nameInput = scanner.nextLine();

        // Loop until all user inputs are valid
        while (validInput == false) {
            try {
                // Ask for an integer to make a triangle out of
                System.out.print("Please enter an integer: ");
                intInput = scanner.nextInt();

                // Don't display welcome message if input is 0 because program will loop
                if (intInput != 0) {
                    // Print out a welcome message using the nameInput and display it in reverse
                    System.out.println("\nWelcome " + nameInput + "!");

                    stringObject = new StringBuilder(nameInput);
                    reversedString = stringObject.reverse();

                    System.out.println("Your name backwards is: " + reversedString);
                }

                // Handle the integer input and make a triangle output from it
                if (intInput > 0) {
                    // Create a triangle based on a positive integer input
                    for(int i = 1; i <= intInput; i++) {
                        StringJoiner treeNumbers = new StringJoiner(", ");
                        for(int j = 1; j <= i; j++) treeNumbers.add(Integer.toString(i));
                        System.out.println(treeNumbers);
                        validInput = true;
                    }
                } else if (intInput < 0){
                    // Create a triangle based on a negative integer input
                    for(int i = -1; i >= intInput; i--) {
                        StringJoiner treeNumbers = new StringJoiner(", ");
                        for(int j = -1; j >= i; j--) treeNumbers.add(Integer.toString(i));
                        System.out.println(treeNumbers);
                        validInput = true;
                    }
                } else {
                    // Handle an input cavity where a tree cannot be printed if 0 is entered, and make the user try again.
                    scanner.nextLine(); // This is done in order to have a clear scanner buffer
                    System.out.println("\nCannot print a triangle of height 0. Please try again.");
                }
            // Handle an inputMisMatch error which would occur if an non integer value is entered when an integer is requested
            } catch (InputMismatchException err ) {
                scanner.nextLine(); // This is done in order to have a clear scanner buffer
                System.out.println("\nThat is not a valid input. Please try again.");
            }
        }

        // End the program with an empty line, by closing the scanner to prevent a resource leak, and by returning.
        System.out.println();
        scanner.close();
        return;
    } // end of the main method
} // end of the welcome class

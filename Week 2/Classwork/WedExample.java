/**
 * ListExample.java
 * A class to help provide some examples of local variables
 * ... and the scope in which variables can be used
 *
 * @author Matt Lepinski
 */
 
 // This imports all of the utility classes (and interfaces)
 // Things like List are part of the standard java utilities package
 import java.util.*;

 public class WedExample {

    //This variable belongs to the class
    //It can be used in any function (method) within the class
    public static String message;

    public static void main(String[] args){
        int number = 0;
        // The variable number belongs to the main function
        // It can be used anywhere in the main function
        // It cannot be used in other functions.

        Scanner sc = new Scanner(System.in);



        while ( number < 20){
            int temp_num = 0;
            // The variable temp_num belongs to the while loop 
            // It can't be used outside the while loop

            System.out.print("Enter a number: ");
            temp_num = sc.nextInt();
            number = number + temp_num;
        }

        System.out.println("The total number is:" + number);

        System.out.println("The temp number is" + temp_num);

    }
 }

    
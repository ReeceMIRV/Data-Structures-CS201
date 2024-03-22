import java.util.Scanner;

/**
 * Conditionals.java
 * Jeff Ondich, Carleton College, 2014-01-01
 * Modified: Anna Rafferty, Carleton College, 2016-08-24
 * Modified: Sneha Narayan, Eric Alexander, 2019-04-02
 *
 * A tiny illustration of if-statements and console input.
 *
 * This is the Java half of a pair of parallel examples in Python and Java.
 * See conditionals.py.
 *
 * 1. What is the purpose of the Scanner class in this program?
 *      The purpose of the Scanner class in this object is to provide a class that we can use to create an instance of an object that "scans" for input.
 *
 * 2. What do you think the "new" keyword does in the line Scanner scanner = new Scanner(System.in)?
 *      The "new" keyword creates an instance of the Scanner class.
 */
public class Conditionals {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Number, please: ");
        int number = scanner.nextInt();
        if (number > 100) {
            System.out.format("Gosh, %d is a big number\n", number);
        } else if (number == 47) {
            System.out.format("47 is my lucky number\n");
        } else if (number >= 0) {
            System.out.format("%d is pretty little\n", number);
        } else {
            System.out.format("%d is negative\n", number);
        }

        scanner.close();
    }

}

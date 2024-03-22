import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * LineReader.java
 * Jeff Ondich, 2014-01-01
 * Modified: Anna Rafferty, 2016-08-24
 * Modified: Layla Oesper, 2017-08-30
 * Modified: Sneha Narayan, Eric Alexander, 2019-04-02
 *
 * A very brief introduction to File and Scanner, excessively commented
 * for tutorial purposes.
 *
 * This is the Java half of a pair of parallel examples in Python and Java.
 * See linereader.py.
 *
 * Try a few things:
 *
 * 1. Compile and run LineReader, ensuring that science.txt is in the same directory.
 *    ("javac LineReader.java" followed by "java LineReader")
 *
 * 2. What happens when you change the inputFilePath variable to "nonexistent.txt"? In less
 *    than two sentences, describe what happens, and why.
 *    The Java program compiles fine because there's no compile time errors, but the interpretation fails because it can't find the file.
 *
 * 3. What happens when you remove the try/catch blocks and just do
 *    "Scanner scanner = new Scanner(inputFile)"? In less than 2 sentences
 *    describe the purpose of the try/catch block as written here.
 *    The purpose of the try/catch block is to make sure that the program can continue to run even if there is a FileNotFoundException. 
 * 
 * 4. Visit the java String documentation. Using the String methods available there, amend
 *    this program to do the following things:
 *      a. Just print out lines that start with a quotation mark (")
 *      b. Print the lines in the quotation marks in lowercase, rather than upper case
 *      c. After printing out the total number of lines in the file, print out just the
 *         number of lines that begin with quotation marks
 *
 * 5. Uncomment the block (marked UNCOMMENT THIS...).
 *    Re-compile your program and run it with the following command:
 *      java LineReader science.txt
 *    In less than two sentences each, answer:
 *      a. What did this edit change about the program?
 *      This made it so that arguments are accepted and required when running the program.
 *      b. What does args[0] refer to?
 *      Args[0] refers to the number of arguments that can be initiated when first executing the program.
 *      c. Why might a programmer decide to design their program in this way?
 *      A programmer may decide to design their program this way for reasons just like this one, which is to eliminate the need to hardcode a path into your program and instead allow flexibility in the program.
 */
public class LineReader {
    public static void main(String[] args) {
        // Create a File object connected to the file you want to read.

        // COMMENT THIS LINE OF CODE FOR QUESTION 5
        //String inputFilePath = "science.txt";

        // UNCOMMENT THIS CODE BLOCK FOR QUESTION 5

        if (args.length == 0) {
            System.err.println("Usage: java CommandLine inputFilePath");
            System.exit(1);
        }

        String inputFilePath = args[0];

        // UNCOMMENT ABOVE BLOCK FOR QUESTION 5

        File inputFile = new File(inputFilePath);

        // Create a Scanner object connected to your file. This is where
        // the JVM tries to actually open the file, and thus this is
        // where an exception can occur. That's why there's a try/catch
        // block to catch the exception. It allows the programmer to
		    // build in a way for the program to recover from the error (in
	        // this case, by just printing the error for the user).
        Scanner scanner = null;
        
        try {
            scanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(1);
        }

        //// Get one line at a time from the file, and print each line in upper
        // Get one line at a time from the file, and print each line in lowercase
        // case to standard output.
        int numberOfLines = 0;
        int numberOfQuoteLines = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            // print of the lines that start with a quotation mark
            if (line.startsWith("\"")) {
                System.out.println(line.toLowerCase());
                numberOfQuoteLines++;
            }

            numberOfLines++;
        }

        System.out.println("\nNumber of lines in file: " + numberOfLines);
        System.out.println("Number of quoted lines in file: " + numberOfQuoteLines + "\n");
        scanner.close();

    }
}

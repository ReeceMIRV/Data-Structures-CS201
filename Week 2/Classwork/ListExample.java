/**
 * ListExample.java
 * A class to help with learning about Lists in Java
 *
 * @author Matt Lepinski
 */
 
 // This imports all of the utility classes (and interfaces)
 // Things like List are part of the standard java utilities package
 import java.util.*;

 // We need these classes to read from files
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.Scanner;
 
public class ListExample {

    /**
     * Print a given list to System.out
     */
    public static void printList( List<Integer> theList) {
        for( Integer num : theList){
            System.out.println( num );
        }
    }


    /**
     * Read integers from a file and store them in a List
     */
    public static List<Integer> fileToList(String filename){
        //We start with an empty ArrayList
        ArrayList<Integer> tempList = new ArrayList<Integer>();

        File inputFile = null;
        Scanner fileReader = null;

        //Here we open the input file
        try {
            inputFile = new File( filename );
            fileReader = new Scanner(inputFile);
        
        } catch (FileNotFoundException e){
            System.out.println("Error Trying to Open the File " + filename);

        }

        //Now let's loop through the file and put the items into the list
        while (fileReader.hasNextInt() ){
            Integer nextNum = fileReader.nextInt();
            tempList.add(nextNum);
        }

        return tempList;

    }

    public static void main(String[] args) {
        System.out.println("First we read some numbers");

        List<Integer> numList = fileToList("random_numbers.txt");


        System.out.println("Then we print the items in the list:");
        printList( numList );
        
    }
}
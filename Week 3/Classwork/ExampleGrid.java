/*
This example demonstrates two-dimensional lists
That is, using a List that holds other lists to make a grid

@author Matt Lepinski
*/

// Because I am using Lists, I need to import the java utility package
// ... which includes useful things like Lists and Scanners
import java.util.*;
import java.io.File;

public class ExampleGrid{

    // Here is a 2-dimensional List that forms our grid
    private ArrayList< ArrayList<Letter> > grid;

    //This creates a size-by-size grid of Letter objects
    //It does NOT put any data into the letter objects
    public void makeEmptyGrid(int size){
        grid = new ArrayList< ArrayList<Letter> >();

        //Do this size times (once for each row)
        int r = 0;
        while(r<size){
            r += 1;
            
            //We make a row and then add it to our grid
            ArrayList<Letter> row = new ArrayList<Letter>();
            for(int c= 0; c<size; c += 1){
                //We are adding size number of Letters to the row
                row.add( new Letter() );
            }

            grid.add(row);
        }
    }

    // This returns the character in position row, col of our grid
    public String getData(int row, int col){
        Letter temp = grid.get(row).get(col);
        return temp.getData();
    }

    // This changes the character in position row, col of our grid
    public void setData(int row, int col, String s){
        Letter temp = grid.get(row).get(col);
        temp.setData(s);
    }

    // This prints out each row of the grid
    public void print(){
        int size = grid.size();

        for(int row = 0; row < size; row += 1){
            for(int col =0; col < size; col +=1){
                // Note: The "this" here is optional
                System.out.print( getData(row, col) );
            }
            // Println takes us to a new line at the end of the row
            System.out.println();
        }
    }

    // This reads in grid from a file
    // The first line of the file should contain the size of the grid
    // Each of the following lines should contain one row of the grid
    // Each row should be size characters separated by space
    public void load(String fileName){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }

        // First we read in the size and create an empty grid
        String line = scanner.nextLine();
        int size = Integer.parseInt( line );
        makeEmptyGrid(size);

        for(int row = 0; row < size; row += 1){
            line = scanner.nextLine();
            // We split(" ") because the letters are separated by spaces
            // ... in the input file
            String[] letters = line.split(" ");

            for(int col=0; col < size; col += 1){
                setData(row, col, letters[col]);
            }
        }

    }

    public static void main(String[] args){
        ExampleGrid my_grid = new ExampleGrid();
        my_grid.load("grid.txt");

        my_grid.print();
    }

}
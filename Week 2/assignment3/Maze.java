/**
 * Maze.java
 * A class for loading and printing mazes from files.
 *
 * @author Leon Liang & Mauricio I. Reyes Villanueva
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Maze {
    private ArrayList< ArrayList<MazeSquare> > listOfSquareRows = new ArrayList< ArrayList<MazeSquare> >();

    private int mazeWidth;
    private int mazeHeight;

    private Position startPosition;
    private Position endPosition;

    int startPosX;
    int startPosY;

    int endPosX;
    int endPosY;
     
    /**
     * Constructor for the Maze class
     */
    public Maze() {}

    /**
     * Load in a Maze from a given file
     *
     * @param fileName the name of the file containing the maze
     */
    public void load(String fileName) {
        // Create a scanner for the given file
        Scanner scanner = null;

        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(1);
        }

        //First line of file is "width & height" of maze
        String[] mazeDimensions = scanner.nextLine().split(" ");
        mazeWidth = Integer.parseInt(mazeDimensions[0]);
        mazeHeight = Integer.parseInt(mazeDimensions[1]);

        //Second line of file is "start position"
        String[] startPositionXY = scanner.nextLine().split(" ");
        startPosX = Integer.parseInt(startPositionXY[0]);
        startPosY = Integer.parseInt(startPositionXY[1]);
        startPosition = new Position(startPosX, startPosY);

        //Third line of file is "end position"
        String[] endPositionXY = scanner.nextLine().split(" ");
        endPosX = Integer.parseInt(endPositionXY[0]);
        endPosY = Integer.parseInt(endPositionXY[1]);
        endPosition = new Position(endPosX, endPosY);

        //Rest of Lines till EOF, loops through until it reaches the last row
        while (scanner.hasNext()) {
            //MazeSquare mySquare = new MazeSquare();
            String currentLine = scanner.nextLine();
            String[] currentSplitRow = currentLine.split("");

            ArrayList<MazeSquare> currentSquareRow = new ArrayList<MazeSquare>();
            
            //Nested for loop that loops through each char in the row
            for (int i = 0; i < mazeWidth; i++) {
                char squareChar = currentSplitRow[i].charAt(0);
                MazeSquare square = new MazeSquare();

                square.setSquare(squareChar);
                currentSquareRow.add(square);
            }

            listOfSquareRows.add(currentSquareRow);
        }

    }

    /**
     * Print the Maze to System.out
     */
    public void print() {
        // Print The initial Header Line
        for (int i = 1; i <= mazeWidth; i++) {
            System.out.print("+-----");
        }
        System.out.print("+" + "\n"); 

        //Loop through the first arrayList (the y axis)
        for (int gridPosY = 0; gridPosY < listOfSquareRows.size(); gridPosY++){
            int padding = 3;
            int rowPosY = 1;
            
            // Define where the center of the square (in the y axis) will be based on the print padding
            startPosition.setSquareCenterY(padding);
            endPosition.setSquareCenterY(padding);
            
            //Loop through the second arrayList (the x axis)
            while (rowPosY <= padding){
                for (int rowPosX = 0; rowPosX < mazeWidth; rowPosX++) {
                    MazeSquare currentSquare = listOfSquareRows.get(gridPosY).get(rowPosX);

                    //If has left wall then draw left wall
                    if (currentSquare.hasLeftWall() == true){
                        System.out.print("|");
                        
                        // else just draw an empty spot
                    } else {
                        System.out.print(" ");
                    }

                    //Always draw an empty horizontal spacer which is half the square distance (left side)
                    System.out.print("  ");

                    // Check if the current position is a start position or not & print "S" if it is
                    if (startPosition.isCurrentPosition(rowPosX, gridPosY, rowPosY)) {
                        System.out.print("S");

                    // If it's not a start position, then check if the current position is an end position & print "F" if it is
                    } else if (endPosition.isCurrentPosition(rowPosX, gridPosY, rowPosY)){
                        System.out.print("F");

                    // Else, if it was neither a start nor end position, then print a placeholder space
                    } else {
                        System.out.print(" ");
                    }

                    //Always draw an empty horizontal spacer which is half the square distance (right side)
                    System.out.print("  ");
                    
                }
                
                rowPosY++;
                System.out.println("|");  
            }
            
            for (int rowPosX = 0; rowPosX < mazeWidth; rowPosX++) {
                MazeSquare currentSquare = listOfSquareRows.get(gridPosY).get(rowPosX);

                //If has left wall then draw left wall
                if (currentSquare.hasBottomWall() == true){
                    System.out.print("+-----");
                } else {
                    System.out.print("+     ");
                }
            }
            
            System.out.print("+" + "\n");
        }

    }
    // MORE METHODS AS YOU NEED THEM
    public int calc_size(){
        return mazeWidth * mazeHeight;
    }

    // This main program acts as a simple unit test for the
    // load-from-file and print-to-System.out Maze capabilities.
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Maze mazeFile");
            System.exit(1);
        }

        Maze maze = new Maze();
        maze.load(args[0]);
        maze.print();
    }
}
import java.io.*;
import java.util.*;

/**
 * Maze.java
 * Solution to the first Maze Assignment (HW3).
 * CS 201: Data Structures - Winter 2018
 *
 * @author Eric Alexander
 * 
 * @Editors Mauricio I. Reyes Villanueva & Robel
 */
public class Maze {
    private ArrayList<ArrayList<MazeSquare>> rowList;
    private int w, h;
    private int startRow, startCol, endRow, endCol;

    // I am including MazeSquare as an inner class
    // to simplify the file structure a little bit.
    private class MazeSquare {
        private int r, c;
        private boolean top, bottom, left, right,
                start, end;

        private MazeSquare(int r, int c,
        boolean top, boolean bottom, boolean left, boolean right,
        boolean start, boolean end) {
            
            this.r = r;
            this.c = c;
            this.top = top;
            this.bottom = bottom;
            this.left = left;
            this.right = right;
            this.start = start;
            this.end = end;
        }

        // By default no squares are visited
        private boolean visited = false;

        boolean hasTopWall() {
            return top;
        }
        boolean hasBottomWall() {
            return bottom;
        }
        boolean hasLeftWall() {
            return left;
        }
        boolean hasRightWall() {
            return right;
        }
        boolean isStart() {
            return start;
        }
        boolean isEnd() {
            return end;
        }
        int getRow() {
            return r;
        }
        int getCol() {
            return c;
        }

        // Visited Methods
        // Returns whether or not we've visited this mazeSquare
        public boolean isVisited() {
            return visited;
        }
        
        public void visited() {
            visited = true;
        }

        public void notVisited() {
            visited = false;
        }

        public String toString(){
            return "(" + r + ", " + c + ")";
        }
    }

    public MazeSquare getNeighbor(MazeSquare currSquare, String direction) {
        // Get the row & column of the current square
        int currRow = currSquare.getRow();
        int currCol = currSquare.getCol();

        // Switch Statement for handling our direction
        switch (direction) {
            case "left":
                // Make sure there's a MazeSquare to the left
                if (currCol >= 0) { // Only shift left if currColumn >= startOfMazeWidth
                    currCol--;
                }
                break;
            case "top":
                // Make sure there's a MazeSquare in the upwards direction
                if (currRow >= 0) { // Only shift up if currRow >= startOfMazeHeight
                    currRow--;
                }
                break;
            case "right":
                // Make sure there's a MazeSquare to the right
                if (currCol <= w) { // Only Shift Right if currCol <= widthOfMaze
                    currCol++;
                }
                break;
            case "bottom":
                // Make sure there's a MazeSquare in the downwards direction
                if (currRow <= h) { // Only shift down if currRow <= heightOfMaze
                    currRow++;
                }
                break;
        }

        // Get the neighboring mazeSquare
        MazeSquare neighboringSquare = rowList.get(currRow).get(currCol);

        // Return the Neighbor 
        return neighboringSquare;
    }

    /**
     * Construct a new Maze
     */
    public Maze() {
        rowList = new ArrayList<ArrayList<MazeSquare>>();
    }

    /**
     * Load Maze in from given file
     *
     * @param fileName the name of the file containing the Maze structure
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

        // First line of file is "w h"
        String[] lineParams = scanner.nextLine().split(" ");
        w = Integer.parseInt(lineParams[0]);
        h = Integer.parseInt(lineParams[1]);

        // Second line of file is "startCol startRow"
        lineParams = scanner.nextLine().split(" ");
        startCol = Integer.parseInt(lineParams[0]);
        startRow = Integer.parseInt(lineParams[1]);

        // Third line of file is "endCol endRow"
        lineParams = scanner.nextLine().split(" ");
        endCol = Integer.parseInt(lineParams[0]);
        endRow = Integer.parseInt(lineParams[1]);

        // Read the rest of the lines (L or | or _ or -)
        String line;
        int rowNum = 0;
        boolean top, bottom, left, right;
        boolean start, end;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            rowList.add(new ArrayList<MazeSquare>());

            // Loop through each cell, creating MazeSquares
            for (int i = 0; i < line.length(); i++) {
                // For top, check row above, if there is one
                if (rowNum > 0) {
                    top = rowList.get(rowNum-1).get(i).hasBottomWall();
                } else {
                    top = true;
                }

                // For right, check cell to the right, if there is one
                if (i < line.length() - 1 ) {
                    char nextCell = line.charAt(i+1);
                    if (nextCell == 'L' || nextCell == '|') {
                        right = true;
                    } else {
                        right = false;
                    }
                } else {
                    right = true;
                }

                // For left and bottom, switch on the current character
                switch (line.charAt(i)) {
                    case 'L':
                        left = true;
                        bottom = true;
                        break;
                    case '_':
                        left = false;
                        bottom = true;
                        break;
                    case '|':
                        left = true;
                        bottom = false;
                        break;
                    case '-':
                        left = false;
                        bottom = false;
                        break;
                    default:
                        left = false;
                        bottom = false;
                }

                // Check to see if this is the start or end spot
                start = startCol == i && startRow == rowNum;
                end = endCol == i && endRow == rowNum;

                // Add a new MazeSquare
                rowList.get(rowNum).add(new MazeSquare(rowNum, i, top, bottom, left, right, start, end));

            }
            
            rowNum++;
        }
    }

    /**
     * Solve Maze and Return to Callee
     */
    public LLStack<MazeSquare> getSolution() {
        // Create a Stack
        LLStack<MazeSquare> stack = new LLStack<MazeSquare>();

        // Get the starting square and set it to the currSquare
        // Get the final square to compare it to the currSquare
        MazeSquare currSquare = rowList.get(startRow).get(startCol);
        MazeSquare endSquare = rowList.get(endRow).get(endCol);

        // Push the start square into the stack
        stack.push(currSquare);

        // Loop until the stack is Empty
        while (!stack.isEmpty()) {
            List<MazeSquare> potentialRoutes = new ArrayList<MazeSquare>();
            int potentialRoute = 0;

            currSquare = stack.peek(); // Move into the next Square
            currSquare.visited(); // Mark it as visited

            // If the currSquare == endSquare then we solved the maze, so return the stack
            if (currSquare == endSquare) return stack;

            // If there is no left wall & if the left neighbor has not been visited, then push leftNeighbor into the stack
            if (!currSquare.hasLeftWall()) {
                MazeSquare leftNeighbor = getNeighbor(currSquare, "left");
                if (!leftNeighbor.isVisited()) { 
                    // Since the neighbor is a potential route, add it to the list
                    potentialRoutes.add(leftNeighbor);
                    potentialRoute++;
                }
            }
            
            // If there is no top wall & if the top neighbor has not been visited, then push topNeighbor into the stack
            if (!currSquare.hasTopWall()) {
                MazeSquare topNeighbor = getNeighbor(currSquare, "top");
                if (!topNeighbor.isVisited()) {
                    // Since the neighbor is a potential route, add it to the list
                    potentialRoutes.add(topNeighbor);
                    potentialRoute++;
                }
            }
            
            // If there is no right wall & if the right neighbor has not been visited, then push rightNeighbor into the stack
            if (!currSquare.hasRightWall()) {
                MazeSquare rightNeighbor = getNeighbor(currSquare, "right");
                if (!rightNeighbor.isVisited()) {
                    // Since the neighbor is a potential route, add it to the list
                    potentialRoutes.add(rightNeighbor);
                    rightNeighbor.notVisited();
                    potentialRoute++;
                }
            }
            
            // If there is no bottom wall & if the bottom neighbor has not been visited, then push bottomNeighbor into the stack
            if (!currSquare.hasBottomWall()) {
                MazeSquare bottomNeighbor = getNeighbor(currSquare, "bottom");
                if (!bottomNeighbor.isVisited()) {
                    // Since the neighbor is a potential route, add it to the list
                    potentialRoutes.add(bottomNeighbor);
                    potentialRoute++;
                }
            }

            if (potentialRoute == 0) stack.pop();
            else stack.push(potentialRoutes.get(0));

        } // End While Loop
        return stack;
    }

    /**
     * Print the Maze to the Console
     */
    public void print() {
        // YOUR CODE WILL GO HERE:
        // GET SOLUTION
        LLStack<MazeSquare> solutionStack = getSolution();

        // Before printing, use your getSolution() method
        // to get the solution to the Maze.
        ArrayList<MazeSquare> currRow;
        MazeSquare currSquare;
        
        // Print each row of text based on top and left
        for (int r = 0; r < rowList.size(); r++) {
            currRow = rowList.get(r);

            // First line of text: top wall
            for (int c = 0; c < currRow.size(); c++) {
                System.out.print("+");
                if (currRow.get(c).hasTopWall()) {
                    System.out.print("-----");
                } else {
                    System.out.print("     ");
                }
            }
            System.out.println("+");

            // Second line of text: left wall then space
            for (int c = 0; c < currRow.size(); c++) {
                if (currRow.get(c).hasLeftWall()) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
                System.out.print("     ");
            }
            System.out.println("|");

            // Third line of text: left wall, then space, then start/end/sol, then space
            for (int c = 0; c < currRow.size(); c++) {
                currSquare = currRow.get(c);

                if (currSquare.hasLeftWall()) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }

                System.out.print("  ");

		        // YOU WILL ADD CODE HERE
		        // If currSquare is part of the solution, mark it with *
                if (currSquare.isStart() && currSquare.isEnd()) {
                    System.out.print("SE ");
                } else if (currSquare.isStart() && !currSquare.isEnd()) {
                    System.out.print("S  ");
                } else if (!currSquare.isStart() && currSquare.isEnd()) {
                    System.out.print("E  ");
                } else if (solutionStack.contains(currSquare)) {
                    // Print out a start if the solution stack contains the currSquare
                    System.out.print("*  ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println("|");

            // Fourth line of text: same as second
            for (int c = 0; c < currRow.size(); c++) {
                if (currRow.get(c).hasLeftWall()) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
                System.out.print("     ");
            }
            System.out.println("|");
        }

        // Print last row of text as straight wall
        for (int c = 0; c < rowList.get(0).size(); c++) {
            System.out.print("+-----");
        }
        System.out.println("+");

        // Print No Solution if Stack is Empty
        if (solutionStack.isEmpty()) System.out.println("\nNo Solution: Unsolvable Maze\n");
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

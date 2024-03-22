# Assignment 3

**Due Date:** Tuesday, September 26 by 10:00 PM

## Goals

This assignment will give you some practice designing and writing Java classes and methods and reading in data from files. It will also prepare you for a future assignment in which we will use a stack data structure to solve a maze. (In this assignment, we will just be focusing on printing the maze to the screen.)

This will be a partner assignment. You should work with the partners that I assigned to you in class.

## Reading a maze

You'll write a class called Maze which will represent a single maze. Maze.java will also include a main() method that tests the loading and printing of mazes. When you run java Maze maze.txt, your program should load the maze from maze.txt and print the human-readable maze to System.out.

For example, if maze.txt looks like:

    6 5
    0 0
    5 4
    L-_|_-
    |L--|_
    |-_-|_
    |L|L||
    L__L__

then the output should be:

    +-----+-----+-----+-----+-----+-----+
    |                 |                 |
    |  S              |                 |
    |                 |                 |
    +-----+     +-----+     +-----+     +
    |     |                 |           |
    |     |                 |           |
    |     |                 |           |
    +     +-----+     +     +     +-----+
    |                       |           |
    |                       |           |
    |                       |           |
    +     +     +-----+     +     +-----+
    |     |     |     |     |     |     |
    |     |     |     |     |     |     |
    |     |     |     |     |     |     |
    +     +-----+     +-----+     +     +
    |                 |                 |
    |                 |              F  |
    |                 |                 |
    +-----+-----+-----+-----+-----+-----+

## The maze file format

Mazes for this assignment will all be rectangular with walls along the entire outside of the maze, with no gaps along the outer walls. Rather than an entrance and an exit, there will be a "start square" and a "finish square"--the goal of the maze solver will be to get from S to F.

The maze files will have the following structure:

+ [Number of columns] [Number of rows]
+ [0-based column number of the start square] [0-based row number of the start square]
+ [0-based column number of the finish square] [0-based row number of the finish square]
+ [Row 0 description]
+ [Row 1 description]
+ ...

Each row description includes a single character for each square in that row, and each character describes the left and bottom walls for its square. (Note: Think about this for a second. Why do we only need to specify the left and bottom?) Specifically:

+ L (uppercase "L") means that the square has both a left and a bottom wall
+ | (vertical bar) means that the square has a left wall, but no bottom wall
+ _ (underscore) means that the square has a bottom wall, but no left wall
+ - (hyphen) means that the square has neither a left nor a bottom wall.

For example, consider the following (with comments in brackets added that would not appear in the file itself)

    3 2    [The maze has 3 columns and 2 rows]
    0 0    [The start square is at the upper left]
    2 0    [The finish square is at the upper right]
    L-|    [(0,0) has left and bottom walls; (1,0) has neither left nor bottom; (2,0) has just left]
    L__    [(0,1) has left and bottom walls; (1,1) has just bottom; (2,1) has just bottom]

This would yield this maze:

    +-----+-----+-----+
    |           |     |
    |  S        |  F  |
    |           |     |
    +-----+     +     +
    |                 |
    |                 |
    |                 |
    +-----+-----+-----+

##Code structure

You will be submitting two files: Maze.java and MazeSquare.java. Skeleton code for these two files is attached below. The MazeSquare class will contain data about each individual square in the maze, including which walls it has, whether or not it is a start or end point, etc. The Maze class will contain an 2D ArrayList of all the MazeSquare objects, and will contain the functionality for loading and printing the maze.
ArrayLists

The Maze has an instance variable of a type that you may or may not have seen in class yet: an ArrayList. ArrayLists in Java are similar to the list type in Python. Rather than having a pre-defined size, like arrays, ArrayLists can have objects added to them and removed from them dynamically. For example:

    ArrayList<String> names = new ArrayList<String>();
    names.add("Sally");
    names.add("George");
    names.add(1, "Sue");

    for (int i = 0; i < names.size(); i++) {
      System.out.println(names.get(i));
    }

will print out:

    Sally
    Sue
    George

Some things to notice about this code:

+ We had to specify the type of object that the ArrayList contains (in this case, String). This is done between < and >.
+ The add() method can be provided one parameter (in which case it adds the given object to the end of the ArrayList) or two parameters (in which case it adds the given object to the given index of the ArrayList).
+ We cannot use standard indexing notation (e.g., names[i]) to retrieve objects from an ArrayList, and instead have to use the get() method.
+ Other methods may be slightly different, as well. (For example, we used .size() to get the number of elements in the list, rather than .length.)
+ We will talk about how ArrayLists are implemented in great depth. For now, however, you should be able to use them without knowing their implementation. (This is the power of ADTs!) It will likely be helpful to refer to the Javadoc for ArrayLists.

## Getting input from the command line

The Maze class also contains code in its main method for retrieving input from the command-line. You saw this in CommandLine.java in HW1, which may be worth referring back to. Arguments to the command-line are stored in the args array as Strings. So, for example:

    public class Test {
      public static void main(String[] args) {
        System.out.println(String.format("You said %s", args[0]));
      }
    }

would create the following output if run like so:

    $ javac Test.java
    $ java Test potato
    You said potato

If you have questions about the command line (or anything else with the assignment!), feel free to ask me or your peers!

## Reading Files

As part of the load() method of the Maze class, you will need to read in the contents of a text file line by line. You can do this using a Scanner class as you have seen before--the main difference is that we will be passing in a File object instead of System.in. The skeleton code provided should be enough to get you started (though as always, it may be helpful to refer to the Javadocs--or the beginning of Section A.10 of the textbook). Particularly relevant methods to look at are the nextLine() and hasNextLine() methods from the Scanner class, as well as the split() method from the String class.

## How to Submit

To submit your assignment, one of you and your partner should create a directory named assignment3 in your Hand-in directory and place your Maze.java and MazeSquare.java files within it. The directory structure relative to your course directory on the lab Macs should be Hand-in/[your_username]/assignment3/Maze.java.

You and your partner should only submit to one of your Hand-in directories. However, be sure to include both of your names in all files.

## Grading

This assignment is worth 20 points. Some of the things we will be grading you on include:

+ Does the program compile? It is essential that you check whether your submitted work compiles. If you submit a program that doesn't compile, you will automatically receive at least a 25% deduction for the assignment, even if the problem was relatively minor.
+ Does the program correctly take maze files as input through the command-line?
+ Does the program print mazes correctly? There are two maze files included here, which should be good test cases, but you should create your own test cases, as well! Think about how different kinds of mazes might throw off your printing functionality.
+ Are both of your files well documented, including Javadoc-style comments for each class and method, as well as in-line comments to describe the logic behind individual tasks you perform within methods?
+ Do your variable and method names make sense?
+ Do you make good decisions as to what is public vs. private, and whether variables are instance variables or local to the methods in which they are used?

As always, start early, and have fun! Your instructor, the lab assistants, and the prefect are all here to help you succeed. Don't hesitate to ask for help if you're struggling!

## Acknowledgments

This assignment was originally written by Jeff Ondich, with modifications by Eric Alexander and Titus Klinge.

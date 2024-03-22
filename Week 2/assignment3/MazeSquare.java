/**
 * MazeSquare.java
 * A helper class for maze solving assignment.
 * Represents a single square within a rectangular maze.
 *
 * @author Mauricio I. Reyes Villanueva & Leon Liang
 */

public class MazeSquare {
    // Instance variables of your choosing
    char squareChar;

    // A constructor, taking whatever parameters you decide:
    public MazeSquare(char squareChar) {
        this.squareChar = squareChar;
    }

    // Whatever methods you want, such as:
    ////public Array<String> mySquare = new Array<String>("Ho");

    public boolean hasLeftWall() {
        if (squareChar == 'L' || squareChar == '|') {
            return true;
        }
        return false;
    }

    public boolean hasBottomWall() {
        if (squareChar == 'L' || squareChar == '_') {
            return true;
        }
        return false;
    }

    public void setSquare(char x){
            squareChar = x;
    }

    public char getSquare(){
        return squareChar;
    }
    

    public MazeSquare(){
        squareChar = 0;
    }
}
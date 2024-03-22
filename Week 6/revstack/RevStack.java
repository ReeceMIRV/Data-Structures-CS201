/**
 * Maze.java
 * Solution to the first Maze Assignment (HW3).
 * CS 201: Data Structures - Winter 2018
 *
 * @author Eric Alexander
 * 
 * @Editors Mauricio I. Reyes Villanueva & Robel
 */
public class RevStack {
    LLStack<Character> charStack = new LLStack<Character>();

    public String reverseString(String string) {
        char[] charArray = string.toCharArray();

        for (int i=0; i < charArray.length; i++) {
            char currChar = string.charAt(i);
            charStack.push(currChar);
        }

        for (int i=0; i < charArray.length; i++) {
            char myChar = charStack.pop();
            charArray[i] = myChar;
        }

        return String.valueOf(charArray);
    }

    // This main program acts as a simple unit test for the
    // load-from-file and print-to-System.out Maze capabilities.
    public static void main(String[] args) {
        RevStack maze = new RevStack();

        String reversedString = maze.reverseString("Racecar 0");
        System.out.println(reversedString);
    }
}

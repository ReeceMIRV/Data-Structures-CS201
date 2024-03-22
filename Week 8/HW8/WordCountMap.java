/**
 * The class that creates our tree.
 */

import java.util.*;

public class WordCountMap {
  private Node root;
  private int numWords;

  // Constructor class
  public WordCountMap() {
    this.root = null;
    this.numWords = 0;
  }

  private class Node {
    private String word;
    private int count;

    private Node left;
    private Node right;

    // Constructor class
    public Node(String word) {
      // Create a temp node with values
      this.word = word;
      this.count = 1;
    }

    public String toString() {
      return("  {\n  Word: " + word + "\n  Word Count: " + count + "\n  Left Child: " + left + "\n  Right Child: " + right + "\n  }\n");
    }
  }

  /**
  * @return If the specified word is already in this WordCountMap, then its
  * count is increased by one. 
  * Otherwise, the word is added to this map with a count of 1.
  */
  public void incrementCount(String word) { insertInTree(word); }

  /**
  * @approach
  * Adds nodes to the binary tree in alphabetical order
  */
  private void insertInTree(String word) {
    // If the tree is empty then we create a new node and make it the root
    if (root == null) {
      // Store the new word in a new node as the root and increment size count of tree
      root = new Node(word);
      numWords++;

    } else {
      insertInTreeRecursively(root, word);
    }
  }
  private void insertInTreeRecursively(Node current, String word) {
    // If our current nodes string matches one in the tree, then we increment it and return
    if (current.word.equals(word)) {
      current.count += 1;
      return;
    }

    // If the word we're looking for has a value less than the current node's word value, then we move to the left child
    if (word.compareTo(current.word) < 0) {
      // If the left child node is null then add the word to the left child
      if (current.left == null) {
        // Add our node to the end of the tree, increment count, & return
        Node node = new Node(word);
        current.left = node;
        numWords++;
        return;

      } else {
        // If there is a left child then we want to keep traversing down the tree
        insertInTreeRecursively(current.left, word);
      }
    }

    // If the word we're looking for has a value greater than the current node's word value, then we move to the right child
    else if (word.compareTo(current.word) > 0) {
      // If the left child node is null then add the word to the right child
      if (current.right == null) {
        // Add our node to the end of the tree, increment count, & return
        Node node = new Node(word);
        current.right = node;
        numWords++;
        return;

      } else {
        // If there is a right child then we want to keep traversing down the tree
        insertInTreeRecursively(current.right, word);
      }
    }
  }

   /**
   * @return Returns a list of WordCount objects, one per word stored in this
   * WordCountMap, sorted alphabetically by word.
   */
  public ArrayList<WordCount> getWordCountsByWord() {
    ArrayList<WordCount> list = new ArrayList<WordCount>();
    ArrayList<WordCount> orderedList = getWordCountsByWordRecursively(root, list);

    return orderedList;
  }

  private ArrayList<WordCount> getWordCountsByWordRecursively(Node current, ArrayList<WordCount> list) {
    // Base Case: If the current node is null, return.
    if (current == null) return list;

    // Recursively traverse the left subtree
    getWordCountsByWordRecursively(current.left, list);

    // Convert the currentNode object into a wordCount object
    // And then add it to the list. Note: Every node/wordCount is a root at some point in time
    WordCount wordCount = new WordCount(current.word, current.count);
    list.add(wordCount);

    // Recursively traverse the right subtree
    getWordCountsByWordRecursively(current.right, list);

    return list;
  }

  /**
   * @output 
   * Traverses the binary tree and prints in alphabetical order
   */
  public void printBinaryTree() {
    printBinaryTreeRecursively(root);
  }
  private void printBinaryTreeRecursively(Node current) {
    // Base Case: If the current node is null, return.
    if (current == null) return;

    // Recursive call to print the left subtree.
    printBinaryTreeRecursively(current.left);

    // Print the current node. Every node is a root at some point in time
    System.out.println(current.word + ": " + current.count);

    // Recursive call to print the right subtree.
    printBinaryTreeRecursively(current.right);
  }

   /**
   * @return Returns an array list of WordCount objects, one per word stored in this
   * WordCountMap, sorted in decreasing order by count.
   */
  public ArrayList<WordCount> getWordCountsByCount() {
    ArrayList<WordCount> list = new ArrayList<WordCount>();
    list = getWordCountsByCountHelper(root, list);

    Collections.sort(list);
    return list;
  }

  private ArrayList<WordCount> getWordCountsByCountHelper(Node current, ArrayList<WordCount> list) {
    // Base Case: If the current node is null, return.
    if (current == null) return list;

    // Recursively traverse the left subtree
    getWordCountsByCountHelper(current.left, list);

    // Convert the currentNode object into a wordCount object
    // And then add it to the correct spot in the list. Note: Every node/wordCount is a root at some point in time
    WordCount wordCount = new WordCount(current.word, current.count);
    list.add(wordCount);

    // Recursively traverse the right subtree
    getWordCountsByCountHelper(current.right, list);

    return list;
  }

  /**
   * @output 
   * Traverses the binary tree, sorts it, and prints it in descending order
   */
  public void printWordsByCount() {
    ArrayList<WordCount> list = new ArrayList<WordCount>();
    list = getWordCountsByCountHelper(root, list);

    Collections.sort(list);
    for (WordCount wordCount: list) {
      System.out.println(wordCount.word + ": " + wordCount.count);
    }
  }

  public String toString() {
    return ("  {\n  Root Word: " + root.word + "\n  Number of Unique Words: " + numWords + " \n  }\n");
  }
}
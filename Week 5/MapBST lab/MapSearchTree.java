/**
 *  Class MapSearchTree
 * 
 * This class uses a Binary Search Tree data structure to implement 
 * the Map abstract data type (ADT)
 * 
 * For simplicity this class only works for Maps where the keys are Strings
 * and the values associated with the keys are integers
 * 
 * @author Matt Lepinski
 */


public class MapSearchTree{

    private MapNode root;

    // Put a key-value pair into the Mapping
    public void put(String key, int value){
        if (root == null){
            root = new MapNode(key, value);
        }
        else{
            root.insert(key, value);
        }
    }

    
    // Returns the Value associated with a given key
    public int get(String key){
        return root.getValue(key);
    }

    // Checks if a given key is present in the Mapping
    public boolean containsKey(String key){
        if (root == null){
            return false;
        }
        else{
            return root.contains(key);
        }
    }

    // Constructor creates an empty Search Tree
    public MapSearchTree(){
        root = null;
    }

    public static void main(String[] args){
        MapSearchTree test_tree = new MapSearchTree();



        // Put some code here to test inserting key/value pairs
        // ... and also getting values from the tree

    }

}
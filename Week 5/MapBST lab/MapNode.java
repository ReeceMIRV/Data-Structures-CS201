/**
 *  Class MapNode
 * 
 * This class implements a single node of a Binary Search Tree
 * 
 * This node is used to implement a Map where the keys are Strings
 * and the values associated with the keys are integers
 * 
 * @author Matt Lepinski
 */

public class MapNode{
    private String key;
    private int value;
    private MapNode left;
    private MapNode right;


    // Check to see if the node or any of its children contain a given key
    public boolean contains(String k){
        if( key.equals(k)){
            return true;
        }
        else{
            if (k.compareTo(key) < 0){
                if( left != null){
                    return left.contains(k);
                }
                else{
                    return false;
                }
            }
            else{
                if( right != null){
                    return right.contains(k);
                }
                else{
                    return false;
                }
            }

        }
    }

    // Find the value associated with a given key
    public int getValue(String k){
        return 0;
    }

    // Insert the given key/value pair into the appropriate location below this node
    public void insert(String k, int v){
        
    }


    // Constructor creates a MapNode storing a given key/value pair
    public MapNode(String k, int v){
        key = k;
        value = v;
        left = null;
        right = null;
    }

}
/**
 * An implementation of the List ADT using
 * a linked list.  Specifically, this implementation
 * only allows a List to contain Comparable items.
 *
 * @author Layla Oesper 
 * @author Eric Alexander
 * @author Mauricio I. Reyes Villanueva
*/

/* Note <E extends Comparable<E> means this container
 * can only old objects of type E that are Comparable.
 */
public class RecursiveLinkedList<E extends Comparable<E>>{ 
    
    /* Internal Node class used for creating linked objects.
    */
    private class Node<E> {
        private E data;
        private Node<E> next;
    
        private Node(E dataItem) {
            data = dataItem;
            next = null;
        }
        
        private Node(E dataItem, Node<E> nextNode) {
            data = dataItem;
            next = nextNode;
        }
         
    } // End Node class
    
    //Instance variables for RecursiveLinkedList
    private Node<E> head;
    private int numItems;
    
    /**
     * Creates an empty RecursiveLinkedList
     */
    public RecursiveLinkedList() {
        head = null;
        numItems = 0;
    }
    
    /**
     * Returns the data stored at positon index.
     * @param index
     * @return The data stored at position index. 
     */
    public E get(int index) {
        if (index < 0 || index >= numItems) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        } 
        Node<E> node = getNode(index);
        return node.data;
    }
    
    /*
     * Helper method that retrieves the Node<E> stored at 
     * the specified index.
     */
    private Node<E> getNode(int index) {
        Node<E> node = head;
        for (int i = 0; i < index && node != null; i++) {
            node = node.next;
        }
        return node;
    }
    
    /**
     * Removes and returns the data stored at the specified index.
     * @param index The position of the data to remove.
     * @return The data previously stored at index position.
     */
    public E remove(int index) {
        if (index < 0 || index >= numItems) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        
        if (index == 0){
            return removeFirst();
        } else {
            Node<E> before = getNode(index - 1);
            return removeAfter(before);
        }
    }
    
    /*
     * Helper method that removes the Node<E> after the
     * specified Node<E>. Returns the data that was
     * stored in the removed node.
     */
    private E removeAfter(Node<E> node) {
        Node<E> temp = node.next;
        if (temp != null) {
            node.next = temp.next;
            numItems--;
            return temp.data;
        } else {
            return null;
        }
    }
    
    /*
     * Helper method that removes the first Node<E> in
     * the Linked List.  Returns the data that was
     * stored in the removed node.
     */
    private E removeFirst() {
        Node<E> temp = head;
        if (head != null) {
            head = head.next;
        }
        
        if (temp != null) {
            numItems--;
            return temp.data;
        } else {
            return null;
        }
    }
    
    /**
     * Adds the data to the list at the specified index.
     * @param index The position to add the data.
     * @param anEntry The particular data to add to the list.
     */
    public void add(int index, E anEntry) {
        if (index < 0 || index > numItems) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        if (index == 0) {
            addFirst(anEntry);
        } else {
            Node<E> node = getNode(index - 1);
            addAfter(node, anEntry);
        }
    }
    
    /*
     * Helper method that adds anEntry to the first
     * position in the list.
     */
    private void addFirst(E anEntry) {
        head = new Node<E>(anEntry, head);
        numItems++;
    }
    
    /*
     * Helper method that adds anEntry after the
     * specified Node<E> in the linked list.
     */
    private void addAfter(Node<E> before, E anEntry) {
        before.next = new Node<E>(anEntry, before.next);
        numItems++;
    }
    
    /**
     * Add the specified data to the end of the list.
     * @param anEntry The data to add to this list.
     */
    public boolean add(E anEntry) {
        add(numItems, anEntry);
        return true;
    }
    
    /**
     * Returns the size of the list in terms of items stored.
     * @returns the number of items in the list.
     */
    public int size() {
        return numItems;
    }
    
    /**
     * Modifies the list so the specified index now 
     * contains newValue (overwriting the old data).
     * @param index The position int he list to add data.
     * @param newValue The data to place in the list.
     * @return The previous data value stored at index.
     */
    public E set(int index, E newValue) {
        if (index < 0 || index >= numItems) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> node = getNode(index);
        E result = node.data;
        node.data = newValue;
        return result;
    }
    
    /**
     * A string representation of the List.
     * @returns A string representation of the list.
     */
    public String toString() {
        String s = "[";
        Node<E> temp = head;
        for (int i = 0; i < numItems; i++) {
            s = s + temp.data.toString();
            if (i < numItems - 1) {
                s = s + ", ";
            }
            temp = temp.next;
        }
        s = s + "]";
        return s;
    }
    
    /**
     * Return the maximum element in the list using
     * compareTo() method of Comparable.
     *
     * @return maximum element of the list
     **/
    public E max(){
        // YOUR CODE WILL GO HERE
        // You will likely want to use a helper method

        // Assume first index is max
        E max = get(0);
        int i = 0;

        // If the linkedList is empty then return null, else run helper method to get max
        if (size() == 0) return max;
        else max = maxRecursively(i, max);

        return max;
    }

    private E maxRecursively(int i, E max) {
        // base case: If at end of list, then return max
        if (i >= size()) return max;
        // if (+) then it's greater, if (0) then it's equal to, if (-) then it's less
        else if (get(i).compareTo(max) > 0) max = get(i);

        return maxRecursively(i+1, max);
    }

    /**
     * Remove all elements that match element using the 
     * equals() operator to determine a match. 
     * (Don't use ==).
     *
     * @param element The element that should be removed
     **/
    public void removeAll(E element){
        // YOUR CODE WILL GO HERE
        // You will likely want to use a helper method
        int i = 0;

        // If the linkedList is empty then return, else run helper method to remove matches
        if (size() == 0) return;
        else removeAllRecursively(i, element);
    }

    private void removeAllRecursively(int i, E element) {
        // base case: if at end of list then return
        if (i >= size()) return;
        // else if elements match then remove it
        else if (get(i).equals(element)) {
            remove(i);
            i--; // item was removed so we should check this position again for the new item there
        }

        removeAllRecursively(i+1, element);
    }

    /*
      i=0 [a b c d]
      i=2 [a a b c d]
      i=4 [a a b b c c d]
      i=6 [a a b b c c d d]
    */

    /**
     * Duplicate each element of the list
     *
     * For example, the list [ 0 1 2 ] duplicated becomes 
     * [ 0 0 1 1 2 2 ]
     **/
    public void duplicate(){
        // YOUR CODE WILL GO HERE
        // You will likely want to use a helper method!
        int i = 0;

        // If the linkedList is empty then return, else run helper method to duplicate
        if (size() == 0) return;
        else duplicateRecursively(i);
    }

    private void duplicateRecursively(int i) {
        // base case: if at end of list then return
        if (i + 1 >= size() + 1) return;
        // else duplicate items
        else add(i + 1, get(i));

        duplicateRecursively(i+2);
    }
 
    /**
     * Here are a couple short tests. You should 
     * should make sure to thoroughly test your code.
     */
    public static void main(String[] args) {
        RecursiveLinkedList<String> linkedList = new RecursiveLinkedList<String>();
        linkedList.add("c");
        linkedList.add("b");
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("b");
        linkedList.add("g");
        linkedList.add("b");
        linkedList.add("b");
        linkedList.add("b");
        linkedList.add("d");
        linkedList.add("e");
        linkedList.add("b");

        System.out.println("\nOriginal List: " + linkedList);

        String maxStr = linkedList.max();
        System.out.println("Max: " + maxStr);

        linkedList.removeAll("b");
        System.out.println("Removed b: " + linkedList);

        linkedList.duplicate();
        System.out.println("Duplicated: " + linkedList + "\n");
    }
    
}

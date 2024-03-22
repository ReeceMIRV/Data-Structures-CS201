/**
 * This class creates a Tour of Points using a 
 * Linked List implementation.  The points can
 * be inserted into the list using two heuristics.
 *
 * @author 
 * @author Eric Alexander, modified code 01-12-2018
 * @author Layla Oesper, modified code 09-22-2017
 */

public class Tour {
    double currentSmallestDistance = 0;

    /**
     * Constructor for the Tour class.  By default sets head to null.
     */
    public Tour() {
        head = null;
        size = 0;
    }
    
    /** 
     * A helper class that defines a single node for use in a tour.
     * A node consists of a Point, representing the location of that
     * city in the tour, and a pointer to the next Node in the tour.
     */
    private class Node {
        private Point p;
        private Node next;
	
        /** 
         * Constructor creates a new Node at the given Point newP
         * with an initial next value of null.
         * 
         * @param newP the point to associate with the Node.
         */
        public Node(Point newP) {
            p = newP;
            next = null;
        }

        /** 
         * Constructor creates a new Node at the given Point newP
         * with the specified next node.
         *
         * @param newP the point to associate with the Node.
         * @param nextNode the nextNode this node should point to.
         */
        public Node(Point newP, Node nextNode) {
            p = newP;
            next = nextNode;
        }
          
    } // End Node class

    
    // Tour class Instance variables
    private Node head;
    private int size = 0; //number of nodes
    //Add other instance variables you think might be useful.
    
    
    public void insertAtEnd(Point point) {
            Node node = new Node(point);
            node.next = null;

            if (head == null) {
                head = node;

            } else {
                Node tempNode = head;

                while (tempNode.next != null) {
                    tempNode = tempNode.next;
                }
                
                tempNode.next = node;
                tempNode = tempNode.next;
            }
        }

        // public void insertAfter(Point point) {
        //     Node node = new Node(point);
        //     Node tempNode = head;
        //     Node nextNode = tempNode.next;

        //     while (tempNode.next != null && nextNode.p != point) {
        //         tempNode = tempNode.next;
        //         System.out.println(tempNode.p);
        //         System.out.println(nextNode.p);
        //     }

        //     System.out.println("END");
            
        //     // This stores the next node in the nodes next value
        //     tempNode.next = node;

        //     // This stores the next node in tempNode
        //     tempNode = tempNode.next;
        // }

        public Point getFirstPoint() {
            if (head == null) return null;
            return head.p;
        }

        public Point getXPoint(int positionX) {
            int counter = 0;
            Node tempNode = head;

            while (counter <= positionX) {
                tempNode = tempNode.next;
                counter++;
            }
            
            return tempNode.p;
        }

        public void setXPoint(int positionX, Point point) {
        }

        public Point getLastPoint() {
            Node tempNode = head;

            if (head == null) return null;

            while (tempNode.next != null) {
                tempNode = tempNode.next;
            }

            return tempNode.p;
        }
    
    // ADD YOUR METHODS BELOW HERE
    
    public void draw(){

    }

    public int size(){
        return size;
    }
    
    public double distance(){
        return 0.0;
    }

    public void insertNearest(Point point){

        // Check to see if we have a first point & get it if so
        Point firstPoint = getFirstPoint();
        int index = 0;

        // If there is no first point then create a head which will be that first point
        if (firstPoint == null) {
            head = new Node(point, null);
            insertAtEnd(point);
            size++;
        } else {
            while (index < size) {
                Point currentPoint = point;
                Point comparisonPoint = getXPoint(index);
                double currentDistance = currentPoint.distanceTo(comparisonPoint);

                if (currentDistance > currentSmallestDistance) {
                    //currentSmallestDistance = currentDistance;
                    setXPoint(index, currentPoint);
                    size++;
                }

                index++;
            }
        }
    }
    /*public void insertNearest(Point point){

        // Check to see if we have a first point & get it if so
        Point firstPoint = getFirstPoint();
        int index = 0;

        // If there is no first point then create a head which will be that first point
        if (firstPoint == null) {
            head = new Node(point, null);
            insertAtEnd(point);
            size++;
        } else {
            while (index < size) {
                Point currentPoint = point;
                Point comparisonPoint = getXPoint(index);
                double currentDistance = currentPoint.distanceTo(comparisonPoint);

                if (currentDistance < currentSmallestDistance) {
                    //currentSmallestDistance = currentDistance;
                    Point insert = comparisonPoint
                    size++;
                }

                index++;
            }
            insertAfterPoint(insert);
        }

        System.out.println(getLastPoint());
    }
    */

    public void insertSmallest(Point p){
        
    }
    // ADD YOUR METHODS ABOVE HERE
   
    public static void main(String[] args) {
        /* Use your main() function to test your code as you write it. 
         * This main() will not actually be run once you have the entire
         * Tour class complete, instead you will run the NearestInsertion
         * and SmallestInsertion programs which call the functions in this 
         * class. 
         */
        
        
        //One example test could be the follow (uncomment to run):
        Tour tour = new Tour();
        Point p = new Point(0,0);
        tour.insertNearest(p);
        p = new Point(0,100);
        tour.insertNearest(p);
        p = new Point(100, 100);
        tour.insertNearest(p);
        p = new Point(50, 50);
        tour.insertNearest(p);

        //System.out.println("Tour distance =  "+tour.distance());
        //System.out.println("Number of points = "+tour.size());
        //System.out.println(tour);
         

        // the tour size should be 3 and the distance 341.42 (don't forget to include the trip back
        // to the original point)
    
        // uncomment the following section to draw the tour, setting w and h to the max x and y 
        // values that occur in your tour points
	
        /*
        int w = 100 ; //Set this value to the max that x can take on
        int h = 100 ; //Set this value to the max that y can take on
        StdDraw.setCanvasSize(w, h);
        StdDraw.setXscale(0, w);
        StdDraw.setYscale(0, h);
        StdDraw.setPenRadius(.005);
        tour.draw(); 
        */
    }
   
}
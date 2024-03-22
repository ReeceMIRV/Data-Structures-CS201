import java.util.Arrays;

public class SelectionSorter {
        // Constructor
        public SelectionSorter() {
    
        }
    
        public int[] sort(int[] numbers) {
            int iterationPos = 0;
    
            // If there are no items in the array then return
            if (numbers.length == 0) return numbers;
            return sortRecursively(numbers, iterationPos); // otherwise sort
        }
    
        public int[] sortRecursively(int[] numbers, int iterationPos) {
            // Start checking the list from the current iteration position, which is also the currMinPos since everything prior is already sorted
            int currMinPos = iterationPos;
    
            // Base Case: Return if the end of the list has been iterated through
            if (iterationPos >= numbers.length - 1) return numbers;
    
            // Iterate through the list (starting at the current iterationPos) and find the position of the smallest number
            for (int currPos=iterationPos; currPos < numbers.length; currPos++) {
                // if the currentMinimum is greater than the currPosition's value then set it as the new currMinPos
                if (numbers[currMinPos] > numbers[currPos]) currMinPos = currPos;
            }
    
            // If the currMin (found at currMinPos) is not at our iterationPos, then swap (move currMin into currIterationPos)
            if (iterationPos != currMinPos) {
                int temp = numbers[iterationPos];
                numbers[iterationPos] = numbers[currMinPos];
                numbers[currMinPos] = temp;
            }
    
            return sortRecursively(numbers, iterationPos+1);
        }
    
        public static void main(String[] args) {
            SelectionSorter sorter = new SelectionSorter();
            int[] numbers = {-7, 0, 4, 17, 2, 1, 7, 2, 3, 0, 180, -4, 13};

            int[] sortedNumbers = sorter.sort(numbers);
            System.out.println("\nSorted Array: " + Arrays.toString(sortedNumbers) + "\n");
        }
    }
    
/*

Selection Sort Algortithm:
--------------------------
[3, 5, 9, 2, 0, 4, 7, 8]

For selection sort we first want to determine our iteration position, so in this case pos[0]
    iterationPos = 0;

We also want to make our first item in the array our current minimum so...
    currPos = 0;
    currMinPos = currPos;

Then we move on to the next item in the array, and we check to see if that item is less than the position of our current minimum
If it is, then we mark that position as our new minimum position
    currPos = 1;
    if (arr[currMinPos] > arr[currPos]) currMinPos = currPos;

Then we rinse and repeat until we reach the end of our array
    while(currPos < arr.length)

Once we reach the end of our array, we want to swap our currMinPos with our current iteration position
To swap items we also always want a have a temp variable that will hold the item being swapped out
    temp = arr[iterationPos]
    arr[iterationPos] = arr[currMinPos]

We want to iterate through our array starting from each position
For example in this case we started in pos[0], we now want to iterate through pos[1], pos[2], pos[3], and so on and so forth so we do
    iterationPos++

*/

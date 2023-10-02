package sortArlgorithms;

public class SelectionSort implements SortFunction {

    /**
     * The algorithm repeatedly selects the smallest (or largest)
     * element from the unsorted portion of the list and swaps it
     * with the first element of the unsorted part.
     * This process is repeated for the remaining unsorted portion
     * until the entire list is sorted.
     * <p>
     * Best Time Complexity: O(n^2)
     * Average Time Complexity: O(n^2)
     * Worst Time Complexity: O(n^2)
     */

    @Override
    public int[] sort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            int pos = i;
            for (int j = i + 1; j < arr.length; j++) {
                //find the index of the minimum element
                if (arr[j] < arr[pos]) {
                    pos = j;
                }
            }

            //swap the current element with the minimum element
            int temp = arr[pos];
            arr[pos] = arr[i];
            arr[i] = temp;
        }

        return arr;
    }
}

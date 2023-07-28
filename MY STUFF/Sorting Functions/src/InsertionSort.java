public class InsertionSort implements SortFunction{

    /**
     * The algorithm repeatedly selects the smallest (or largest)
     * element from the unsorted portion of the list and swaps it
     * with the first element of the unsorted part.
     * This process is repeated for the remaining unsorted portion
     * until the entire list is sorted.
     * <p>
     * Best Time Complexity: O(n)
     * Average Time Complexity: O(n^2)
     * Worst Time Complexity: O(n^2)
     */
    @Override
    public int[] sort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            // Move elements of arr[0..i-1], that are
            // greater than key, to one position ahead
            // of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }

        return arr;
    }
}

package sortArlgorithms;

public class BubbleSort implements SortFunction {

    /**
     * Keep swapping elements that are not
     * in their right location till the array is sorted.
     * <p>
     * Best Time Complexity: O(n)
     * Average Time Complexity: O(n^2)
     * Worst Time Complexity: O(n^2)
     */

    @Override
    public int[] sort(int[] arr) {
        int length = arr.length;
        int temp = 0;

        for (int i = 0; i < length; i++) {
            for (int j = 1; j < length; j++) {
                if (arr[j - 1] > arr[j]) {
                    //swap elements
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }


        return arr;
    }
}

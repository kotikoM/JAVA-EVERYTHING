package sortArlgorithms;

public class MergeSort implements SortFunction {

    /**
     * Divide an array into smaller sub-arrays,
     * sorting each sub-array, and then merging
     * the sorted sub-arrays back together
     * to form the final sorted array.
     * <p>
     * Best Time Complexity: O(n*log n)
     * Average Time Complexity: O(n*log n)
     * Worst Time Complexity: O(n*log n)
     */

    @Override
    public int[] sort(int[] arr) {
        mergeSort(arr);
        return arr;
    }

    public void mergeSort(int[] arr) {
        int length = arr.length;
        if (length <= 1) {
            return;
        }
        int middle = length / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[length - middle];

        int index = 0;
        int rightIndex = 0;

        for (; index < length; index++) {
            if (index < middle) {
                leftArray[index] = arr[index];
            } else {
                rightArray[rightIndex++] = arr[index];
            }
        }
        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(leftArray, rightArray, arr);
    }

    public void merge(int[] leftArray, int[] rightArray, int[] array) {
        int leftSize = leftArray.length;
        int rightSize = rightArray.length;
        int index = 0;
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < leftSize && rightIndex < rightSize) {
            if (leftArray[leftIndex] < rightArray[rightIndex]) {
                array[index++] = leftArray[leftIndex++];
            } else {
                array[index++] = rightArray[rightIndex++];
            }
        }

        //leftover
        while (leftIndex < leftSize) {
            array[index++] = leftArray[leftIndex++];
        }

        while (rightIndex < rightSize) {
            array[index++] = rightArray[rightIndex++];
        }
    }
}

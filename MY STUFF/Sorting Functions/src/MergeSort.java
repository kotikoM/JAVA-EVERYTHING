import java.util.Arrays;

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
        return mergeSort(arr, 0, arr.length - 1);
    }

    public static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;
        int k = l;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public int[] mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;

            // Recursive calls to sort the two halves
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }

        return arr;
    }
}

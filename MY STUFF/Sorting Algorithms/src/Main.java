import sortArlgorithms.*;
import java.util.Arrays;

public class Main {

    public static void display(SortFunction sortFunction, int[] array) {
        String result = Arrays.toString(sortFunction.sort(array));
        String sortType = String.valueOf(sortFunction.getClass()).split(" ")[1];

        System.out.println(sortType + " result:\n" + result);
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 60, 35, 2, 45, 320, 5};

        display(new MergeSort(), arr1);
    }
}

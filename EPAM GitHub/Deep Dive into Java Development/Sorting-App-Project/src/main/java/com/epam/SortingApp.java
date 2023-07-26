package com.epam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class represents the Sorting App that takes up to ten command-line arguments as integer values,
 * sorts them in ascending order, and then prints them to the standard output.
 */
public class SortingApp {
    private static final Logger logger = LogManager.getLogger(SortingApp.class);

    /**
     * Indicates that the sorting process has finished successfully.
     */
    public static void finish() {
        logger.info("Your array has been successfully sorted.");
    }

    /**
     * Indicates a failure in the sorting process.
     */
    public static void fail() {
        logger.error("NULL POINTER EXCEPTION");
    }

    /**
     * Sorts the given array of integers in ascending order.
     *
     * @param arr The array of integers to be sorted.
     * @throws IllegalArgumentException If the input array is null.
     */
    public static void main(String[] arr) {

        // Check for NullCase
        if (arr == null) {
            fail();
            throw new IllegalArgumentException();
        }

        // Check for an empty array
        if (arr.length == 0) {
            System.out.println("No arguments provided.");
            logger.info("Could not sort: No arguments were provided");
            return;
        }

        // Check for too big of an array
        if (arr.length > 10) {
            System.out.println("Too many arguments. Maximum is 10.");
            logger.info("Could not sort: Too many arguments were provided");
            return;
        }

        // Parse string array into int array
        int[] numbers = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            try {
                numbers[i] = Integer.parseInt(arr[i]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid argument: " + arr[i]);
                return;
            }
        }

        // Sort the array using bubble sort
        int n = numbers.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    // Swap numbers[j] and numbers[j + 1]
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }

        // Output the sorted array
        System.out.print("Sorted numbers: {");
        for (int i = 0; i < numbers.length - 1; i++) {
            System.out.print(numbers[i] + ", ");
        }
        System.out.println(numbers[numbers.length - 1] + "}");

        finish();

    }
}
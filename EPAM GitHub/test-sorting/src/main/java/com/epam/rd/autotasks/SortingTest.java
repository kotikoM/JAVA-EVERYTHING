package com.epam.rd.autotasks;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class SortingTest {

    Sorting sorting = new Sorting();

    @Test(expected = IllegalArgumentException.class)
    public void testNullCase(){
        int[] input = null;
        sorting.sort(input);

    }

    @Test
    public void testEmptyCase(){
        int[] input = {};
        int[] expected = {};

        sorting.sort(input);

        assertArrayEquals(input, expected);
    }

    @Test
    public void testSingleElementArrayCase() {
        int[] input = {1};
        int[] expected = {1};

        sorting.sort(input);

        assertArrayEquals(input, expected);
    }

    @Test
    public void testSortedArraysCase() {
        int[] input = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};

        sorting.sort(input);

        assertArrayEquals(input, expected);

    }

    @Test
    public void testOtherCases() {
        int[] input = {5, 3, 5, 1, 2, 4, 1};
        int[] expected = {1, 1, 2, 3, 4, 5, 5};

        sorting.sort(input);

        assertArrayEquals(input, expected);
    }

}
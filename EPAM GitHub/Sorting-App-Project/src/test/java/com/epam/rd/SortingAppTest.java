package com.epam.rd;


import com.epam.SortingApp;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * This tester class tests the Sorting App.
 * One can find tests for array being Null, too Big, Empty, etc.
 */
public class SortingAppTest {

    @Test (expected = IllegalArgumentException.class)
    public void testForNull(){
        SortingApp.main(null);
    }

    @Test
    public void testForEmptyArg(){
        // Redirect the standard output to capture the printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Test for empty array
        SortingApp.main(new String[] {});
        assertEquals("No arguments provided.", outputStream.toString().split("\n")[0].trim());
    }

    @Test
    public void testForSingleElement(){
        // Redirect the standard output to capture the printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Test for empty array
        SortingApp.main(new String[] {"1"});
        assertEquals("Sorted numbers: {1}", outputStream.toString().split("\n")[0].trim());
    }

    @Test
    public void testForTooBigArg(){
        // Redirect the standard output to capture the printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Test for too big of an array
        SortingApp.main(new String[] {"10", "5", "7", "3", "1", "-1", "30", "5645", "-123", "0", "2", "9"});
        assertEquals("Too many arguments. Maximum is 10.", outputStream.toString().split("\n")[0].trim());
    }

    @Test
    public void testForInvalidArgument(){
        // Redirect the standard output to capture the printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Test for too big of an array
        SortingApp.main(new String[] {"12", "ab", "86", "5", "1", "-7"});
        assertEquals("Invalid argument: ab", outputStream.toString().split("\n")[0].trim());
    }

}

package com.epam.rd;

import com.epam.SortingApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

/**
 * This class tests the Sorting App with proper inputs.
 */
@RunWith(Parameterized.class)
public class ProperSortingTest {

    private String[] input;

    private String output;


    public ProperSortingTest(String[] input, String output){
        this.input = input;
        this.output = output;
    }

    @Parameterized.Parameters
    public static Collection<Object []> tests(){
        return Arrays.asList(new Object[][]{

                //mixed size arrays
                {new String[]{"5", "4", "2", "7", "6", "9", "10", "8"},
                        ("Sorted numbers: {2, 4, 5, 6, 7, 8, 9, 10}")},

                {new String[]{"2", "3", "8", "7", "10", "6"},
                        ("Sorted numbers: {2, 3, 6, 7, 8, 10}")},

                {new String[]{"-623", "8674", "-2091", "5023", "-7549", "912", "6542", "1337", "-418", "7995"},
                        ("Sorted numbers: {-7549, -2091, -623, -418, 912, 1337, 5023, 6542, 7995, 8674}")},

                {new String[] {"3245", "-3801", "-9099", "7236", "-680", "2179", "-2854", "-6127"},
                        ("Sorted numbers: {-9099, -6127, -3801, -2854, -680, 2179, 3245, 7236}")},

                {new String[]{"-7364", "5708", "-2313", "9865", "1346", "-8222", "4839", "281", "4590", "-9171"},
                        ("Sorted numbers: {-9171, -8222, -7364, -2313, 281, 1346, 4590, 4839, 5708, 9865}")},

                {new String[] {"549", "-5008", "3716", "-1296", "-753", "6703", "9032", "-4149", "6145", "8159"},
                        ("Sorted numbers: {-5008, -4149, -1296, -753, 549, 3716, 6145, 6703, 8159, 9032}")},

                {new String[] {"2274", "666", "-9362", "-2071", "3948", "5669", "-7261", "8910", "-6715", "-217"},
                        ("Sorted numbers: {-9362, -7261, -6715, -2071, -217, 666, 2274, 3948, 5669, 8910}")},

                //duplicates
                {new String[]{"-7364", "5708", "9865", "9865", "1346", "-8222", "4839", "281", "4590", "-9171"},
                        ("Sorted numbers: {-9171, -8222, -7364, 281, 1346, 4590, 4839, 5708, 9865, 9865}")},

                {new String[]{"8183", "9876", "9876", "6547", "-8491", "2229", "1305", "1305"},
                        ("Sorted numbers: {-8491, 1305, 1305, 2229, 6547, 8183, 9876, 9876}")},

                {new String[]{"1", "1", "1", "2", "1", "1", "1", "1"},
                        ("Sorted numbers: {1, 1, 1, 1, 1, 1, 1, 2}")},

                {new String[]{"1", "1", "1", "2", "2", "2", "2", "1"},
                        ("Sorted numbers: {1, 1, 1, 1, 2, 2, 2, 2}")}

        });
    }


    @Test
    public void testSortingApp(){
        // Redirect the standard output to capture the printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Test for too big of an array
        SortingApp.main(input);
        assertTrue(outputStream.toString().trim().contains(output));
    }


}

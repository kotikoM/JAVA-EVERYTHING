package Medium;

import java.util.Arrays;

public class ZigZag_Conversation {
    /*The string "PAYPALISHIRING" is written in a zigzag pattern
    on a given number of rows like this:
    P   A   H   N
    A P L S I I G
    Y   I   R
    */
    public static String prettyZigZagOutput(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        String[] elements = s.split("");
        String[] res = new String[numRows];
        int index = 0;
        int elemLength = elements.length;

        while (index < elemLength) {
            //write down
            for (int i = 0; i < numRows && index < elemLength; i++) {
                if (res[i] == null) {
                    res[i] = elements[index++];
                } else {
                    res[i] += elements[index++];
                }
            }

            //write diagonally
            for (int i = numRows - 1; i > -1 && index < elemLength; i--) {
                if (i == 0 || i == numRows - 1) {
                    //only spaces
                    res[i] = res[i] + " ".repeat(numRows);
                } else {
                    //spaces and value
                    res[i] += " ".repeat(numRows - i - 1)
                            + elements[index++] +
                            " ".repeat(i);
                }
            }
        }


        return String.join("\n", res);
    }

    public static String convert(String s, int numRows) {
        if (numRows == 1 || s.length() < numRows) {
            return s;
        }

        String[] elements = s.split("");
        String[] res = new String[numRows];
        int index = 0;
        int elemLength = elements.length;

        while (index < elemLength) {
            //write down
            for (int i = 0; i < numRows && index < elemLength; i++) {
                if (res[i] == null) {
                    res[i] = elements[index++];
                } else {
                    res[i] += elements[index++];
                }
            }

            //write diagonally
            for (int i = numRows - 2; i > 0 && index < elemLength; i--) {
                //spaces and value
                res[i] += elements[index++];
            }
        }


        return String.join("", res);
    }
}

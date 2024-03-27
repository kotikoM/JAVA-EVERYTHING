import java.util.Arrays;

public class PatternMatcher {
    public static int findPattern(String string, String pattern) {
        int[] lps = computeLPSArray(pattern);
        int i = 0;
        int j = 0;


        while (i < string.length()) {
            if (j == pattern.length()) {
                return i - j;
            }

            //match
            if (string.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
            //does not match
            else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return -1;
    }

    public static int[] computeLPSArray(String pattern) {
        int length = pattern.length();
        int[] lps = new int[length];
        int i = 1;
        int lenOfLongestPrefix = 0;

        while (i < length) {
            if (pattern.charAt(i) == pattern.charAt(lenOfLongestPrefix)) {
                lenOfLongestPrefix++;
                lps[i] = lenOfLongestPrefix;
                i++;
            } else {
                if (lenOfLongestPrefix != 0) {
                    lenOfLongestPrefix = lps[lenOfLongestPrefix - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    public static void main(String[] args) {
        String string = "110101";
        String pattern = "01011";
        System.out.println(Arrays.toString(computeLPSArray(pattern)));
    }
}

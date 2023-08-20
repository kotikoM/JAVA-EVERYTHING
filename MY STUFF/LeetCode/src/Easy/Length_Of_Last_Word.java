package Easy;

public class Length_Of_Last_Word {

    /* Given a string s consisting of words and spaces,
    return the length of the last word in the string.
    A word is a maximal substring consisting of non-space characters only.*/
    public static int lengthOfLastWord(String s) {
        s = s.trim();
        int start = s.length();
        int end = 0;

        for (int i = start - 1; i > -1 ; i--) {
            if (s.charAt(i) == ' ') {
                end = i + 1;
                break;
            }
        }

        return start - end;
    }
}

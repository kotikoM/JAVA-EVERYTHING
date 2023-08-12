package Easy;

public class Find_the_Index_of_the_First_Occurrence_in_a_String {
    /*Given two strings needle and haystack,
    return the index of the first occurrence of needle in haystack,
    or -1 if needle is not part of haystack.*/

    public int strStr(String haystack, String needle) {
        //haystack = "sadbutsad", needle = "sad"
        for (int i = 0; i <= (haystack.length() - needle.length()); i++) {
            if (haystack.startsWith(needle, i)) {
                return i;
            }
        }
        return -1;
    }
}

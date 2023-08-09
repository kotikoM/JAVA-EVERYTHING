package Easy;

public class Palindrome_Number {
    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        int n = s.length();

        for (int i=0; i<n/2; i++) {
            //Check whether the elements at the same distance from
            //beginning and from ending are same, if not return false
            if (s.charAt(i) != s.charAt(n-i-1)) return false;
        }
        return true;
    }
}

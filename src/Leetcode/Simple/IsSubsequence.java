package Leetcode.Simple;

public class IsSubsequence {
    public static void main(String[] args) {
        String s = "axc", t = "ahbgdc";
        boolean res = isSubsequence(s, t);
    }

    public static boolean isSubsequence(String s, String t) {
        int lenS = s.length(), lenT = t.length(), i = 0, j = 0;
        while (i < lenS && j < lenT) {
            if (s.charAt(i) == t.charAt(j))
                i += 1;
            j += 1;
        }
        return i == lenS;
    }
}

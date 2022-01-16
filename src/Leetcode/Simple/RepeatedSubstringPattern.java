package Leetcode.Simple;

/*
* https://leetcode.com/problems/repeated-substring-pattern/submissions/
* */
public class RepeatedSubstringPattern {
    public static boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        if (len == 1) return false;
        int[] pi = piFunc(s);
        int lastP = pi[len - 1], pos = len - lastP;
        return lastP != 0 && len % pos == 0;
    }

    private static int[] piFunc(String str) {
        int len = str.length();
        int[] pi = new int[len];
        for (int i = 1; i < len; i += 1) {
            int j = pi[i - 1];
            while(j > 0 && str.charAt(i) != str.charAt(j)) j = pi[j - 1];
            if (str.charAt(i) == str.charAt(j)) pi[i] = j + 1;
        }
        return pi;
    }

    public static void main(String[] args) {
        String s = "abac";
        boolean res = repeatedSubstringPattern(s);
    }
}

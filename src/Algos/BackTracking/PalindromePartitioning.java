package Algos.BackTracking;

import java.util.*;

/*
 * https://leetcode.com/problems/palindrome-partitioning/submissions/
 * */
public class PalindromePartitioning {
    public static List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        backTrack(0, s, new ArrayList<>(), ans);
        return ans;
    }

    private static void backTrack(int start, String s, List<String> palindrome, List<List<String>> ans) {
        for (int end = start; end < s.length(); end += 1) {
            if (isPalindrome(start, end, s)) {
                palindrome.add(s.substring(start, end + 1));
                if (end + 1 == s.length())
                    ans.add(new ArrayList<>(palindrome));
                else
                    backTrack(end + 1, s, palindrome, ans);
                palindrome.remove(palindrome.size() - 1);
            }
        }
    }


    private static boolean isPalindrome(int start, int end, String s) {
        while (start < end)
            if (s.charAt(start++) != s.charAt(end--)) return false;
        return true;
    }

    public static void main(String[] args) {
        String s = "bb";
        List<List<String>> ans = partition(s);
    }
}

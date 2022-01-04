package Leetcode.Simple;

/*
* https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/
* */
public class LongestSubstringWithoutRepeatingCharacters {
    public static int lengthOfLongestSubstring(String s) {
        int len = s.length(), maxSeq = 0;
        if (len == 0) return 0;
        int[] dp = new int[len];
        dp[0] = 1;
        for (int i = 1; i < len; i += 1) {
            int repeat = dp[i - 1];
            int uniq = 1;
            for (int j = i - 1; j >= i - repeat; j -= 1) {
                if (s.charAt(j) != s.charAt(i)) {
                    uniq += 1;
                } else {
                    break;
                }
            }
            dp[i] = uniq;
            maxSeq = Math.max(uniq, maxSeq);
        }
        return maxSeq;
    }

    public static void main(String[] args) {
//        String s = "bbbbb";
//        String s = "abcabcbb";
        String s = "";
        int res = lengthOfLongestSubstring(s);
    }
}

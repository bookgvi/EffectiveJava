package Leetcode.Simple;

/*
* https://leetcode.com/problems/implement-strstr/submissions/
* */
public class ImplementStrStr {
    public static int strStr(String haystack, String needle) {
        int lenSS = needle.length(), ans = -1;
        if (lenSS < 1) return 0;
        int[] z = zFunc(needle + "#" + haystack);
        for (int i = 0; i < z.length; i += 1) {
            if (z[i] == lenSS) {
                ans = i;
                break;
            }
        }
        if (ans != -1) ans = ans - lenSS - 1;
        return ans;
    }

    private static int[] zFunc(String str) {
        int len = str.length();
        int[] z = new int[len];
        for (int i = 1, l = 0, r = 0; i < len; i += 1) {
            if (i <= r) z[i] = Math.min(z[i - l], r - i + 1);
            while (i + z[i] < len && str.charAt(i + z[i]) == str.charAt(z[i])) z[i] += 1;
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        return z;
    }

    public static void main(String[] args) {
        String s = "hello";
        String ss = "ll";
        int res = strStr(s, ss);
    }
}

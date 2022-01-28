package Algos.String;

import java.util.*;

/*
 * https://leetcode.com/problems/palindrome-partitioning/
 * */
public class PalindromePartitioning {
    private static final int k = (int) 1e5 + 1;
    private static final int mod = (int) 1e9 + 7;
    private static final int powsCount = 17;
    private static final long[] pows = pows();

    private static long[] pows() {
        long[] pows = new long[powsCount];
        pows[0] = 1;
        for (int i = 1; i < powsCount; i += 1)
            pows[i] = pows[i - 1] * k % mod;
        return pows;
    }

    private static long getHash(String str) {
        int len = str.length();
        long hash = 0;
        for (int i = 0; i < len; i += 1)
            hash = (hash + (str.charAt(i) - 'a' + 1) * pows[i]) % mod;
        return hash;
    }

    private static long getReverseHash(String str) {
        int len = str.length();
        long hash = 0;
        for (int i = 0; i < len; i += 1)
            hash = (hash + (str.charAt(i) - 'a' + 1) * pows[len - 1 - i]) % mod;
        return hash;
    }

    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        for (int pos = 0, len = s.length(); pos < len; pos += 1) {
            List<String> pals = new ArrayList<>();
            for (int off = 0; pos + off < len; off += 1) {
                String ss = s.substring(off, off + pos + 1);
                long h1 = getHash(ss);
                long h2 = getReverseHash(ss);
                if (h1 == h2) pals.add(ss);
            }
            if (pals.size() > 0) res.add(pals);
        }
        return res;
    }

    public static void main(String[] args) {
        String str = "abcb";
        partition(str);
    }
}

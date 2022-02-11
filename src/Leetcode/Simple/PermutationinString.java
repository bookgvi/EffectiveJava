package Leetcode.Simple;

/*
 * https://leetcode.com/problems/permutation-in-string/submissions/
 * */
public class PermutationinString {
    private static final int k = (int) 1e5 + 5;
    private static final int mod = (int) 1e9 + 7;
    private static final long[] pows = pows();
    private static final char firstChar = 'a';

    private static long getHash(String str) {
        int len = str.length();
        str = sort(str);
        long hash = 0;
        for (int i = 0; i < len; i += 1)
            hash += (str.charAt(i) - firstChar + 1) * pows[i] % mod;
        return hash;
    }

    private static long[] pows() {
        int max = k;
        long[] pows = new long[max];
        pows[0] = 1;
        for (int i = 1; i < max; i += 1)
            pows[i] = pows[i - 1] * k % mod;
        return pows;
    }

    private static String sort(String str) {
        byte[] strBytes = str.getBytes();
        int len = str.length(), b = 5;
        int[] tmp = new int[len], count = new int[1 << b];
        for (int i = 0; i < len; i += 1)
            count[strBytes[i] - firstChar] += 1;
        for (int i = 1; i < 1 << b; i += 1)
            count[i] += count[i - 1];
        for (int i = len - 1; i >= 0; i -= 1)
            tmp[--count[strBytes[i] - firstChar]] += strBytes[i];
        StringBuilder sortedStr = new StringBuilder();
        for (int i = 0; i < len; i += 1)
            sortedStr.append(Character.toString(tmp[i]));
        return sortedStr.toString();
    }

    public static boolean checkInclusion(String s1, String s2) {
        boolean res = false;
        int lenS1 = s1.length(), lenS2 = s2.length();
        if (lenS1 <= lenS2) {
            long s1Hash = getHash(s1);
            for (int i = 0; i + lenS1 - 1 < lenS2; i += 1) {
                String subStr = s2.substring(i, i + lenS1);
                long calcHash = getHash(subStr);
                if (calcHash == s1Hash) {
                    res = true;
                    break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s1 = "ab", s2 = "eidbaooo";
        boolean res = checkInclusion(s1, s2);
    }
}

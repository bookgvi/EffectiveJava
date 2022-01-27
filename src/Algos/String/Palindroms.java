package Algos.String;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * Алгоритм Манакера по нахождению подпалиндромов
 * */
public class Palindroms {
    private static final long k = (int) 1e5 + 1;
    private static final long mod = (int) 1e9 + 7;
    private static final long[] pows = pows();

    public static void main(String[] args) {
        String str = "aabrrrbaa";
        System.out.println(Arrays.toString(manakerOdds(str)));
        long h1 = getHash(str);
        long h2 = getReverseHash(str);
        String isPalindrome = h1 == h2 ? "is palindrome" : "isn't palindrome";
        System.out.printf("String \"%s\" %s", str, isPalindrome);
    }

    private static int[] manakerOdds(String str) {
        int len = str.length();
        int[] m = new int[len];
        for (int i = 1, l = 0, r = 0; i < len; i += 1) {
            if (i < r) m[i] = Math.min(m[r + l - i], r - i + 1);
            while (i - m[i] >= 0 && i + m[i] < len && str.charAt(i - m[i]) == str.charAt(i + m[i]))
                m[i] += 1;
            if (i + m[i] - 1 > r) {
                l = i - m[i] + 1;
                r = i + m[i] - 1;
            }
        }
        return m;
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

    private static long[] pows() {
        int max = (int) 1e5;
        long[] pows = new long[max];
        pows[0] = 1;
        for (int i = 1; i < max; i += 1)
            pows[i] = pows[i - 1] * k % mod;
        return pows;
    }
}

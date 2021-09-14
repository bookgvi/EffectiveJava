package Leetcode.Simple;

import java.util.Arrays;

public class Palindrome {
    public static void main(String[] args) {
        int x = -123321;
        System.out.println(isPalindrome(x));
    }

    private static boolean isPalindrome(int x) {
        StringBuilder sb = new StringBuilder(String.valueOf(x));
        String delimiter = "#";
        int len = sb.length();
        int restStart = sb.length() >> 1;
        int halfLen = (len & 1) == 1 ? restStart + 1 : restStart;
        String prefix = sb.substring(0, halfLen);
        String str = prefix + new StringBuilder(sb.substring(restStart, len)).reverse();
        String strForZ = prefix + delimiter + str;
        int[] z = zFunction(strForZ);
        long res = Arrays.stream(z).filter(elt -> elt == halfLen).count();
        return res > 1;
    }

    private static int[] zFunction(String str) {
        int len = str.length();
        int[] z = new int[len];
        for (int i = 1, l = 0, r = 0; i < len; i += 1) {
            if (i <= r) z[i] = Math.min(z[i - l], r - i + 1);
            while (z[i] + i < len && str.charAt(z[i]) == str.charAt(z[i] + i)) z[i] += 1;
            if (i > r) {
                l = i;
                r = z[i] + i - 1;
            }
        }
        return z;
    }
}

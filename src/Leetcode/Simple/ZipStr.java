package Leetcode.Simple;

import java.util.Arrays;
import java.util.stream.Stream;

public class ZipStr {
    public static void main(String[] args) {
        String str = "babcbb";
        int[] pi = piFunc(str);
        System.out.println(str);
        System.out.println(Arrays.toString(pi));
        int pos = zipStr(pi);
        if (pos != -1) System.out.println(str.substring(0, pos));
        else System.out.println("No fate...");
    }

    private static int[] piFunc1(String str) {
        int len = str.length();
        int[] pi = new int[len];
        for (int i = 1; i < len; i += 1) {
            int j = pi[i - 1];
            while (j > 0 && str.charAt(i) != str.charAt(j)) j = pi[j - 1];
            if (str.charAt(i) == str.charAt(j)) pi[i] = j + 1;
        }
        return pi;
    }

    private static int zipStr1(int[] pi) {
        int len = pi.length;
        int res = len - pi[len - 1];
        if (len % res == 0) return res;
        return -1;
    }

    private static int[] piFunc(String str) {
        int len = str.length();
        int[] pi = new int[len];
        for (int i = 1; i < len; i += 1) {
            int j = pi[i - 1];
            while (j > 0 && str.charAt(i) != str.charAt(j)) j = pi[j - 1];
            if (str.charAt(i) == str.charAt(j)) pi[i] = j + 1;
        }
        return pi;
    }

    private static int zipStr(int[] pi) {
        int len = pi.length, lastPref = pi[len - 1];
        int pos = len - lastPref;
        if (len % pos == 0) return pos;
        return -1;
    }

}

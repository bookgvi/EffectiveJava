package Leetcode.Simple;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Palindrome {
    public static void main(String[] args) {
        int x = 11;
        System.out.println(isPalindrome(x));
    }

    private static boolean isPalindrome(int x) {
        String strToAnalize = getStrToAnalize(x);
        int[] pal = zFunction(strToAnalize);
        return pal[pal.length / 2] > strToAnalize.length() / 2;
    }

    private static int[] zFunction(String str) {
        int len = str.length();
        int[] z = new int[len];
        int i = len / 2;
        while (i - z[i] >= 0 && z[i] + i < len && str.charAt(i - z[i]) == str.charAt(z[i] + i)) z[i] += 1;
        return z;
    }

    private static String getStrToAnalize(int x) {
        String tmpStr = String.valueOf(x);
        return (tmpStr.length() & 1) == 1 ? tmpStr : Stream.of(tmpStr.split("")).map(ch -> ch + "#").collect(Collectors.joining()).substring(0, tmpStr.length() * 2 - 1);
    }
}

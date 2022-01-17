package Leetcode.Simple;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReverseString {
    public static void main(String[] args) {
        String str = "Мама мыла   раму";
        String strReversed = new StringBuilder(str).reverse().toString();
        System.out.printf("%s\n", reverse(strReversed));

        char[] s = new char[]{'h', 'e', 'l', 'l', 'o'};
        System.out.println(Arrays.toString(s));
        reverseString(s);
        System.out.println(Arrays.toString(s));

        str = "abcdef";
        String res = reverseStr(str, 3);
    }

    /*
    * https://leetcode.com/problems/reverse-words-in-a-string-iii/submissions/
    * */
    private static String reverse(String str) {
        return Stream.of(str.split(" ")).map(subStr -> new StringBuilder(subStr).reverse().append(" "))
                .collect(Collectors.joining())
                .substring(0, str.length());
    }

    /*
     * https://leetcode.com/problems/reverse-string/submissions/
     * */
    public static void reverseString(char[] s) {
        int end = s.length - 1, start = 0;
        for (int i = start; i <= (end + start) >> 1; i += 1) {
            int tmp = end + start - i;
            swap(i, tmp, s);
        }
    }

    /*
    * https://leetcode.com/problems/reverse-string-ii/submissions/
    * */
    public static String reverseStr(String str, int k) {
        char[] s = str.toCharArray();
        for (int i = 0, len = s.length; i < len; i += 2 * k) {
            int end = Math.min(i + k - 1, len - 1);
            for (int j = i; j <= (i + end) >> 1; j += 1) {
                int tmp = i + end - j;
                if (tmp >= s.length) break;
                swap(j, tmp, s);
            }
        }
        StringBuilder strRes = new StringBuilder();
        for (char ch : s) strRes.append(ch);
        return strRes.toString();
    }

    private static void swap(int i, int j, char[] s) {
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }

}

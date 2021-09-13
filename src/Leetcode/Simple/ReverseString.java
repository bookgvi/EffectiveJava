package Leetcode.Simple;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReverseString {
    public static void main(String[] args) {
        String str = "Мама мыла   раму";
        String strReversed = new StringBuilder(str).reverse().toString();
        System.out.printf("%s, %d\n", str, str.length());
        System.out.printf("%s, %d\n", strReversed, strReversed.length());
        System.out.printf("%s, %d\n", reverse(strReversed), reverse(strReversed).length());
    }
    private static String reverse(String str) {
        return Stream.of(str.split(" ")).map(subStr -> new StringBuilder(subStr).reverse().append(" "))
                .collect(Collectors.joining())
                .substring(0, str.length());
    }
}

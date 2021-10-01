package Leetcode.Simple;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReverseString {
    public static void main(String[] args) {
        String str = "Мама мыла   раму";
        String strReversed = new StringBuilder(str).reverse().toString();
        System.out.printf("%s\n", reverse(strReversed));
    }
    private static String reverse(String str) {
        return Stream.of(str.split(" ")).map(subStr -> new StringBuilder(subStr).reverse().append(" "))
                .collect(Collectors.joining())
                .substring(0, str.length());
    }
}

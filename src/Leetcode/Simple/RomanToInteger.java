package Leetcode.Simple;

import java.util.*;

/*
* https://leetcode.com/problems/roman-to-integer/submissions/
* */
public class RomanToInteger {
    public static int romanToInt(String s) {
        int res = 0, len = s.length();
        char next, cur;
        Map<Character, Integer> romanToInt = new HashMap<>();
        romanToInt.put('I', 1);
        romanToInt.put('V', 5);
        romanToInt.put('X', 10);
        romanToInt.put('L', 50);
        romanToInt.put('C', 100);
        romanToInt.put('D', 500);
        romanToInt.put('M', 1000);

        for (int i = 0; i < len; i += 1) {
            next = s.charAt(Math.min(len - 1, i + 1));
            cur = s.charAt(i);
            int digit = romanToInt.getOrDefault(cur, 0),
                nextDigit = romanToInt.getOrDefault(next, 0);
            if (digit * 10 == nextDigit || digit * 5 == nextDigit) digit = -digit;
            res += digit;
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "MCMXCIV";
        int res = romanToInt(s);
    }
}

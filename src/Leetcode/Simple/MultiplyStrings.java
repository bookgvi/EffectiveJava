package Leetcode.Simple;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 *        Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class MultiplyStrings {
    private static int base = (int) 1e1;
    public static String multiply(String num1, String num2) {
        List<Integer> a = strToNum(num1);
        List<Integer> b = strToNum(num2);
        List<Integer> c = IntStream.range(0, a.size() + b.size()).mapToObj(i -> 0).collect(Collectors.toList());
        for (int i = 0, aLen = a.size(); i < aLen; i += 1) {
            for (int j = 0, bLen = b.size(), carry = 0; j < bLen || carry != 0; j += 1) {
                int bVal = j < bLen ? b.get(j) : 0;
                int aVal = a.get(i);
                int tmp = c.get(i + j) + aVal * bVal + carry;
                c.set(i + j, tmp % base);
                carry = tmp / base;
            }
        }
        while(c.size() > 1 && c.get(c.size() - 1) == 0) c.remove(c.size() - 1);
        return numToStr(c);
    }

    private static String sum(String num1, String num2)  {
        List<Integer> a = strToNum(num1), b = strToNum(num2);
        int lenA = a.size(), lenB = b.size(), len = Math.max(lenA, lenB);
        for (int i = 0, carry = 0; i < len || carry != 0; i += 1) {
            if (i == lenA) a.add(0);
            int valA = a.get(i);
            int valB = lenB == i ? 0 : b.get(i);
            a.set(i, valA + valB + carry);
            carry = a.get(i) > base ? 1 : 0;
            if (carry == 1) a.set(i, a.get(i) - base);
        }
        return numToStr(a);
    }

    public static List<Integer> strToNum(String num) {
        List<Integer> res = new ArrayList<>();
        for (int i = num.length() - 1; i >= 0 ; i -= 1) {
            res.add(num.charAt(i) - 48);
        }
        return res;
    }

    public static String numToStr(List<Integer> num) {
        StringBuilder str = new StringBuilder();
        for (int i = num.size() - 1; i >= 0 ; i -= 1) str.append(num.get(i));
        return str.toString();
    }

    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "956";
        String res = multiply(num1, num2);
        String res2 = sum(num1, num2);
    }
}

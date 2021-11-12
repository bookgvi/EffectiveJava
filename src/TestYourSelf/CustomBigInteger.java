package TestYourSelf;

import java.util.*;
import java.util.stream.*;

public class CustomBigInteger {
    private static final int base = (int) 1e4;
    private static final int radix = 10;
    private static final int digits = 4;

    private static String listToString(List<Integer> nums) {
        StringBuilder numStr = new StringBuilder();
        int len = nums.size();
        for (int i = len - 1; i >= 0; i -= 1) {
            String tmp = String.valueOf(nums.get(i));
            if (tmp.length() < digits) {
                StringBuilder zeros = new StringBuilder();
                for (int j = 0, delta = digits - tmp.length(); j < delta; j += 1)
                    zeros.append("0");
                numStr.append(zeros);
            }
            numStr.append(tmp);
        }
        while (numStr.charAt(0) == '0') numStr.delete(0, 1);
        if (numStr.length() == 0) return "0";
        return numStr.toString();
    }

    private static List<Integer> strToList(String str) {
        List<Integer> nums = new ArrayList<>();
        for (int i = str.length(); i > 0; i -= digits) {
            if (i < digits) nums.add(Integer.parseInt(str.substring(0, i), radix));
            else nums.add(Integer.parseInt(str.substring(i - digits, i), radix));
        }
        return nums;
    }

    private static List<Integer> sum(String num1, String num2) {
        List<Integer> a = strToList(num1), b = strToList(num2);
        int len = Math.max(a.size(), b.size());
        for (int i = 0, carry = 0; i < len || carry != 0; i += 1) {
            if (i == a.size()) a.add(0);
            int valB = i == b.size() ? 0 : b.get(i);
            int valA = a.get(i);
            a.set(i, valA + valB + carry);
            carry = a.get(i) > base ? 1 : 0;
            if (carry == 1) a.set(i, a.get(i) - base);
        }
        return a;
    }

    private static List<Integer> multiply(String num1, String num2) {
        List<Integer> a= strToList(num1), b = strToList(num2),
                c = IntStream.range(0, a.size() + b.size()).mapToObj(i -> 0).collect(Collectors.toList());
        for (int i = 0, lenA = a.size(); i < lenA; i += 1) {
            for (int j = 0, carry = 0, lenB = b.size(); j < lenB || carry != 0; j += 1) {
                int valB = j == lenB ? 0 : b.get(j);
                int valA = a.get(i);
                int tmp = c.get(i + j) + valB * valA + carry;
                c.set(i + j, tmp % base);
                carry = tmp / base;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        String num1 = "2009";
        String num2 = "00090003";
        List<Integer> n1 = strToList(num1);
        List<Integer> n2 = strToList(num2);
        num1 = listToString(n1);
        num2 = listToString(n2);
        List<Integer> c = multiply(num1, num2);
        List<Integer> sum = sum(num1, num2);
        String resSum = listToString(sum);
        String resC = listToString(c);
    }
}
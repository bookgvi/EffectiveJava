package TestYourSelf;

import java.util.*;
import java.util.stream.*;

public class CustomBigInteger {
    private static final int base = (int) 1e4;
    private static final int radix = 10;
    private static final int digits = 4;

    private static String listToString(List<Integer> nums) {
        StringBuilder res = new StringBuilder();
        for (int i = nums.size() - 1; i >= 0; i -= 1) {
            String tmp = String.valueOf(nums.get(i));
            if (tmp.length() < digits) {
                StringBuilder zeros = new StringBuilder();
                IntStream.range(0, tmp.length() - digits).forEach(j -> zeros.append("0"));
                res.append(zeros);
            }
            res.append(tmp);
        }
        while (res.charAt(0) == '0') res.delete(0, 1);
        if (res.length() < 1) return "0";
        return res.toString();
    }

    private static List<Integer> strToList(String num) {
        List<Integer> res = new ArrayList<>();
        for (int i = num.length(); i > 0; i -= digits) {
            if (i < digits) res.add(Integer.parseInt(num.substring(0, i), radix));
            else res.add(Integer.parseInt(num.substring(i - digits, i), radix));
        }
        return res;
    }

    private static List<Integer> sum(String num1, String num2) {
        List<Integer> a = strToList(num1), b = strToList(num2);
        int len = Math.min(a.size(), b.size());
        for (int i = 0, carry = 0; i < len || carry != 0; i += 1) {
            if (i == a.size()) a.add(0);
            int valB = i < b.size() ? b.get(i) : 0;
            int valA = a.get(i);
            a.set(i, valA + valB + carry);
            carry = a.get(i) > base ? 1 : 0;
            if (carry != 0) a.set(i, a.get(i) - base);
        }
        return a;
    }

    private static List<Integer> multiply(String num1, String num2) {
        List<Integer> a = strToList(num1), b = strToList(num2),
                c = IntStream.range(0, a.size() + b.size()).mapToObj(i -> 0).collect(Collectors.toList());
        for (int i = 0; i < a.size(); i += 1) {
            for (int j = 0, carry = 0; j < b.size() || carry != 0; j += 1) {
                int valA = a.get(i);
                int valB = j < b.size() ? b.get(j) : 0;
                int tmp = c.get(i + j) + valA * valB + carry;
                c.set(i + j, tmp % base);
                carry = tmp / base;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        String num1 = "2009";
        String num2 = "2029";
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
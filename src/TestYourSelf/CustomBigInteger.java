package TestYourSelf;

import java.util.*;
import java.util.stream.*;

public class CustomBigInteger {
    private static final int base = (int) 1e4;
    private static final int radix = 10;
    private static final int digits = 4;

    private static String numToStr(List<Integer> bi) {
        StringBuilder num = new StringBuilder();
        for (int i = bi.size() - 1; i >= 0; i -= 1) {
            String tmpStr = String.valueOf(bi.get(i));
            int zerosCount = digits - tmpStr.length();
            StringBuilder zeros = new StringBuilder();
            for (int k = 0; k < zerosCount; k += 1)
                zeros.append("0");
            num.append(zeros).append(tmpStr);
        }
        while (num.charAt(0) == '0') num.delete(0, 1);
        return num.toString();
    }

    private static List<Integer> strToNum(String nums) {
        int len = nums.length();
        List<Integer> bi = new ArrayList<>();
        for (int i = len; i > 0; i -= digits) {
            if (i < digits) bi.add(Integer.valueOf(nums.substring(0, i), radix));
            else bi.add(Integer.valueOf(nums.substring(i - digits, i), radix));
        }
        while (bi.size() > 1 && bi.get(bi.size() - 1) == 0) bi.remove(bi.size() - 1);
        return bi;
    }

    public static String multiply(String num1, String num2) {
        List<Integer> a= strToNum(num1), b = strToNum(num2);
        List<Integer> res = IntStream.range(0, a.size() + b.size()).mapToObj(i -> 0).collect(Collectors.toList());
        for (int i = 0, aLen = a.size(); i < aLen; i += 1) {
            for (int j = 0, bLen = b.size(), carry = 0; j < bLen || carry != 0; j += 1) {
                int bVal = j < b.size() ? b.get(j) : 0;
                int aVal = a.get(i);
                int tmp = res.get(i + j) + aVal * bVal + carry;
                res.set(i + j, tmp % base);
                carry = tmp / base;
            }
        }
        return numToStr(res);
    }

    private static List<Integer> sum(List<Integer> a, List<Integer> b) {
        int len = Math.max(a.size(), b.size());
        for (int i = 0, carry = 0; i < len || carry == 1; i += 1) {
            if (i == a.size()) a.add(0);
            int bVal = i < b.size() ? b.get(i) : 0;
            int aVal = a.get(i);
            a.set(i, carry + aVal + bVal);
            carry = a.get(i) >= base ? 1 : 0;
            if (carry == 1) a.set(i, a.get(i) - base);
        }
        return a;
    }

    public static void main(String[] args) {
        String num1 = "2";
        String num2 = "3";
        String c = multiply(num1, num2);
        System.out.println(c);
    }
}

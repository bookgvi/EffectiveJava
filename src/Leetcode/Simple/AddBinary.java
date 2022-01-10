package Leetcode.Simple;

import java.util.*;

/*
* https://leetcode.com/problems/add-binary/submissions/
* */
public class AddBinary {
    private static final int base = 2;
    private static final int digits = 1;
    private static List<Integer> strToList(String num) {
        List<Integer> res = new ArrayList<>();
        for (int i = num.length(); i > 0; i -= digits) {
            if (i < digits) res.add(Integer.parseInt(num.substring(0, i)));
            else res.add(Integer.parseInt(num.substring(i - digits, i)));
        }
        return res;
    }

    private static String listToStr(List<Integer> num) {
        StringBuilder res = new StringBuilder();
        for (int len = num.size(), i = len - 1; i >= 0; i -= 1) {
            String tmp = String.valueOf(num.get(i));
            if (tmp.length() - digits < 0) {
                res.append("0".repeat(digits - tmp.length()));
            }
            res.append(tmp);
        }
        while(res.length() > 0 && res.charAt(0) == '0') res.delete(0, 1);
        if (res.length() < 1) return "0";
        return res.toString();
    }

    public static String addBinary(String a, String b) {
        List<Integer> num1 = strToList(a), num2 = strToList(b);
        int len = Math.max(num1.size(), num2.size());
        for (int i = 0, carry = 0; i < len || carry != 0; i += 1) {
            if (i == num1.size()) num1.add(0);
            int valB = i < num2.size() ? num2.get(i) : 0;
            int valA = num1.get(i);
            num1.set(i, valA + valB + carry);
            carry = num1.get(i) >= base ? 1 : 0;
            if (carry == 1) num1.set(i, num1.get(i) - base);
        }
        return listToStr(num1);
    }

    public static void main(String[] args) {
        String a = "0", b = "0";
        String res = addBinary(a, b);
    }
}

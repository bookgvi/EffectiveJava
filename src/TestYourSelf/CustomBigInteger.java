package TestYourSelf;

import java.util.*;
import java.util.stream.*;

public class CustomBigInteger {
    private static final int base = (int) 1e4;
    private static final int radix = 10;
    private static final int digits = 4;

    private static List<Integer> fromStrToList(String str) {
        List<Integer> num = new ArrayList<>();
        for (int i = str.length(); i > 0; i -= digits) {
            if (i < digits) num.add(Integer.parseInt(str.substring(0, i), radix));
            else num.add(Integer.parseInt(str.substring(i - digits, i), radix));
        }
        while (num.size() > 0 && num.get(num.size() - 1) == 0) num.remove(num.size() - 1);
        return num;
    }

    private static String fromListToString(List<Integer> num) {
        StringBuilder str = new StringBuilder();
        for (int i = num.size() - 1; i >= 0; i -= 1) {
            String tmpStr = String.valueOf(num.get(i));
            if (tmpStr.length() < digits) {
                int delta = digits - tmpStr.length();
                StringBuilder zeros = new StringBuilder();
                for (int k = 0; k < delta; k += 1) {
                    zeros.append("0");
                }
                str.append(zeros).append(tmpStr);
            } else {
                str.append(tmpStr);
            }
        }
        while (str.length() > 1 && str.charAt(0) == '0') str.delete(0, 1);
        if (str.length() == 0) return "0";
        return str.toString();
    }

    private static List<Integer> sum(String num1, String num2) {
        List<Integer> a = fromStrToList(num1);
        List<Integer> b = fromStrToList(num2);
        int len = Math.max(a.size(), b.size());
        for (int i = 0, carry = 0; i < len || carry == 1; i += 1) {
            if (i == a.size()) a.add(0);
            int aVal = a.get(i);
            int bVal = i < b.size() ? b.get(i) : 0;
            a.set(i, aVal + bVal + carry);
            carry = a.get(i) > base ? 1 : 0;
            if (carry == 1) a.set(i, a.get(i) - base);
        }
        return a;
    }

    private static List<Integer> multiply(String num1, String num2) {
        List<Integer> a = fromStrToList(num1);
        List<Integer> b = fromStrToList(num2);
        List<Integer> c = IntStream.range(0, a.size() + b.size()).mapToObj(i -> 0).collect(Collectors.toList());
        for (int i = 0, aLen = a.size(); i < aLen; i += 1) {
            for (int j = 0, bLen = b.size(), carry = 0; j < bLen || carry != 0; j += 1) {
                int bVal = j < bLen ? b.get(j) : 0;
                int aVal = a.get(i);
                int tmpRes = c.get(i + j) + aVal * bVal + carry;
                c.set(i + j, tmpRes % base);
                carry = tmpRes / base;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        String num1 = "200092000920009200092000920009200092000920009200092000920009";
        String num2 = "90003";
        List<Integer> c = multiply(num1, num2);
        List<Integer> sum = sum(num1, num2);
        List<Integer> n1 = fromStrToList(num1);
        String resN1 = fromListToString(n1);
        String resSum = fromListToString(sum);
        String resC = fromListToString(c);
    }
}

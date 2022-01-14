package Leetcode.Simple;

/*
*
* https://leetcode.com/problems/string-to-integer-atoi/submissions/
* */
public class StringToInteger {
    public int myAtoi(String s) {
        int index = 0, n = s.length(), sign = 1;
        long res = 0;
        if (n < 1) return 0;
        while(index < n && s.charAt(index) == ' ') index += 1;
        if (index < n && s.charAt(index) == '-') {
            sign = -1;
            index += 1;
        } else if (index < n && s.charAt(index) == '+') {
            index += 1;
        }
        while(index < n && s.charAt(index) >= '0' && s.charAt(index) <= '9') {
            int digit = s.charAt(index) - '0';
            res = res * 10 + digit;
            if (res > Integer.MAX_VALUE && sign == 1) {
                res = Integer.MAX_VALUE;
                break;
            } else if (res > Integer.MAX_VALUE) {
                res = Integer.MIN_VALUE;
                break;
            }
            index += 1;
        }
        return (int) res * sign;
    }
}

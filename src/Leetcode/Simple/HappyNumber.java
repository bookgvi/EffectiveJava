package Leetcode.Simple;

/*
 * https://leetcode.com/problems/happy-number/submissions/
 * */
public class HappyNumber {
    private static int mul(int n, int pow) {
        int res = 0;
        while (pow > 0) {
            if ((pow & 1) == 1) res += n;
            n += n;
            pow >>= 1;
        }
        return res;
    }

    private static int proceedDigits(int n) {
        int digit = 0, base = 10;
        while (n > 0) {
            int tmpDigit = n % base;
            digit += mul(tmpDigit, tmpDigit); // если заменить на умножение (tmpDigit * tmpDigit) то результат на литкоде будет 100%
            n /= base;
        }
        return digit;
    }

    public static boolean isHappy(int n) {
        int slow = n;
        int fast = proceedDigits(slow);
        while (fast != 1 && fast != slow) {
            slow = proceedDigits(slow);
            fast = proceedDigits(proceedDigits(fast));
        }
        return fast == 1;
    }

    public static void main(String[] args) {
        int n = 13;
        boolean isHappy = isHappy(n);
    }
}

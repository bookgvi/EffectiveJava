package Leetcode.Simple;

/*
 * https://leetcode.com/problems/add-digits/submissions/
 * */
public class AddDigits {
    public static void main(String[] args) {
        int num = 38;
        int res1 = Solution1.addDigits(num);
        int res2 = Solution2.addDigits(num);
    }

    // first solution
    private static class Solution1 {
        public static int addDigits(int num) {
            int base = 10, digit = 0;
            while (num != 0) {
                digit = digit + num % base;
                num /= base;
                if (num == 0 && digit >= base) {
                    num = digit;
                    digit ^= digit;
                }
            }
            return digit;
        }
    }

    /*
     *The original number is divisible by 9 if and only if the sum of its digits is divisible by 9
     *
     * second solution
     * */
    private static class Solution2 {
        public static int addDigits(int num) {
            if (num == 0) return 0;
            if (num % 9 == 0) return 9;
            return num % 9;
        }
    }

}

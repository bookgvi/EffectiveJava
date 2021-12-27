package Leetcode.Simple;

/*
* https://leetcode.com/problems/number-complement/submissions/
* */
public class NumberComplement {
    public static int findComplement(int num) {
        int firstBit = 0;
        for (int i = 31; i >= 0; i -= 1) {
            if ((num & (1 << i)) != 0) {
                firstBit = i;
                break;
            }
        }
        for (int i = firstBit; i >= 0; i -= 1) {
            num ^= (1 << i);
        }
        return num;
    }
    public static void main(String[] args) {
        int res = findComplement(5);
    }
}

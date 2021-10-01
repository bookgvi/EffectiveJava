package Leetcode.Simple;

public class HanoyTower {
    public static void main(String[] args) {
        int n = 3;
        hanoy(n, 1, 2, 3);
    }

    private static int greyCode(int n) {
        return n ^ (n >> 1);
    }

    private static void hanoy(int n, int from, int middle, int target) {
        if (n != 0) {
            hanoy(n - 1, from, target, middle);
            System.out.printf("%d диск на %d штырь\n", from, target);
            hanoy(n - 1, middle, from, target);
        }
    }
}

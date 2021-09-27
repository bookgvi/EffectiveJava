package Leetcode.Simple;

public class Division {
    public static void main(String[] args) {
        int n = 32;
        getIt(n);
    }

    private static void getIt(int n) {
        for (int abcde = 1234; abcde < 98765 / n; abcde += 1) {
            int fghij = abcde * n;
            int use = abcde < 10000 ? 1 : 0;
            int tmp = abcde;
            while (tmp != 0) {
                use |= 1 << (tmp % 10);
                tmp /= 10;
            }
            tmp = fghij;
            while (tmp != 0) {
                use |= 1 << (tmp % 10);
                tmp /= 10;
            }
            if (use == (1 << 10) - 1) {
                System.out.printf("%d / %d = %d\n", fghij, abcde, n);
            }
        }
    }
}

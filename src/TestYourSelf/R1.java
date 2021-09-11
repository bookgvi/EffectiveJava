package TestYourSelf;

import java.util.ArrayList;
import java.util.List;

public class R1 {
    public static void main(String[] args) {
        int a = 15;
        int b = 39;
        int c = 12;
        System.out.printf("%dx + %dy = %d\n", a, b, c);
        int[] extGCD = extGCD(a, b);
        diofant(extGCD, c);
    }
    private static int[] extGCD(int a, int b) {
        int[] res = new int[3];
        if (a == 0) {
            res[0] = b;
            res[2] = 1;
            return res;
        }
        res = extGCD(b % a, a);
        int swap = res[1];
        res[1] = res[2] - (b / a) * res[1];
        res[2] = swap;
        return res;
    }

    private static void diofant(int[] extGCD, int c) {
        int gcd = extGCD[0];
        if (c % gcd != 0) {
            System.out.println("fail... Решений нет");
        }
        int x = extGCD[1] * c / gcd;
        int y = extGCD[2] * c / gcd;
        System.out.printf("x = %d, y = %d\n", x, y);
    }
}
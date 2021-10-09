package Algos;

import java.util.Arrays;

/*
* Решение уравнений вида ax+by=с
* x2 = y1 - (b / a) * x1
* y2 = x1
* k = c / gcd(a, b)
* x = k * x2
* y = k * y2
* */
public class Diofant {
    public static void main(String[] args) {
        int a = 6;
        int b = 17;
        int c = 1;
        int[] ev = evklidExt(a, b);
        if (c % ev[0] != 0) {
            System.out.println("Решений нет...");
            return;
        }
        int x = ev[1] * (c / ev[0]);
        int y = ev[2] * (c / ev[0]);
        System.out.printf("x = %d, y = %d\n", x, y);
    }

    private static int[] evklidExt(int a, int b) {
        int[] res = new int[3];
        if (a == 0) {
            res[0] = b;
            res[2] = 1;
            return res;
        }
        res = evklidExt(b % a, a);
        int tmp = res[1];
        res[1] = res[2] - (b / a) * res[1];
        res[2] = tmp;
        return res;
    }
}
package Algos;

import java.util.Arrays;

public class GCD {
    public static void main(String[] args) {
        int a = 210, b = 588;
        System.out.println(gcd(a, b));
        System.out.println(gcdNotRecursion(a, b));
        System.out.println(Arrays.toString(diafant(a, b)));
    }

    /*
     * Алоритм Евклида для нахождения НОД
     * */
    static public int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static int gcdNotRecursion(int a, int b) {
        int x = 0;
        do {
            x = b;
            b = a % b;
            a = x;
        } while (b != 0);
        return a;
    }

    /*
     * расширеный алгоритм Евклида:
     * a*x+b*y=gcd(a,b)
     * диафантово уравнение
     * */
    public static int[] diafant(int a, int b) {
        int[] res = new int[3];
        if (a == 0) {
            res[0] = b;
            res[2] = 1;
            return res;
        }
        res = diafant(b % a, a);
        int tmp = res[1];
        res[1] = res[2] - (b / a) * res[1];
        res[2] = tmp;
        return res;
    }
}

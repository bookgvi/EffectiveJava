package TestYourSelf;

import java.math.BigInteger;
import java.util.*;

public class Repeat {
    public static void main(String[] args) {
        int a = 9;
        int b = 72;
        int n = 15;
        int pow = 6;
        System.out.println(phi(n));
        System.out.println(pow(2, pow));
        System.out.println(powRecursive(2, pow));
        System.out.println(evklid(a, b));
        System.out.println(erat(n));
        System.out.println(Arrays.toString(evklidExt(a, b)));
    }

    private static int phi(int n) {
        int result = n;
        for (int i = 2; i * i <= n; i += 1) {
            if (n % i == 0) {
                while (n % i == 0) {
                    result -= result / i;
                    n /= i;
                }
            }
        }
        if (n > 1) result -= result / n;
        return result;
    }

    private static int pow(int n, int pow) {
        int res = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) res *= n;
            pow >>= 1;
            n *= n;
        }
        return res;
    }

    private static int powRecursive(int n, int pow) {
        if (pow == 0) return 1;
        if ((pow & 1) == 1) return powRecursive(n, --pow) * n;
        return powRecursive(n, pow >> 1) * powRecursive(n, pow >> 1);
    }

    private static int evklid(int a, int b) {
        if (a == 0) return b;
        return evklid(b % a, a);
    }

    private static Map<Integer, Boolean> erat(int n) {
        Map<Integer, Boolean> erat = new HashMap<>();
        for (int i = 2; i <= n; i += 1) {
            erat.putIfAbsent(i, true);
            if (!erat.get(i)) continue;
            for (int j = 2 * i; j <= n; j += i)
                erat.putIfAbsent(j, false);
        }
        return erat;
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

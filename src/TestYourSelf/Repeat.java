package TestYourSelf;

import java.math.BigInteger;
import java.util.*;

public class Repeat {
    public static void main(String[] args) {
        int mod = (int) 1e3 + 7;
        int a = (int) 11;
        int invA = powMod(a, phi(mod) - 1, mod);
        System.out.printf("diofant = %s\n", Arrays.toString(evklidExt(a, mod)));
        System.out.printf("invA = %d\n", invA);
        System.out.println(evklidExt(a, mod)[1] % mod * a % mod);
        System.out.println(invA * a % mod);
    }

    private static int powMod(int n, int pow, int mod) {
        int res = 1;
        while (pow != 0) {
            if ((pow & 1) == 1) res = (res * n) % mod;
            n = (n * n) % mod;
            pow >>= 1;
        }
        return res;
    }

    private static int phi(int n) {
        int res = n;
        for (int i = 2; i * i <= n; i += 1) {
            if (n % i == 0) {
                while (n % i == 0) n /= i;
                res -= res / i;
            }
        }
        if (n > 1) res -= res / n;
        return res;
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

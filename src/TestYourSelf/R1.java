package TestYourSelf;

import java.util.*;

public class R1 {
    public static void main(String[] args) {
        int n = 6;
        int mod = 17;
        System.out.println(Arrays.toString(evklidExt(n, mod)));
        System.out.println(evklidExt(n, mod)[1]);
        int invN = modPow(n, phi(mod) - 1, mod);
        System.out.println(invN);
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

    private static int modPow(int n, int pow, int mod) {
        int res = 1;
        while (pow > 0) {
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
}
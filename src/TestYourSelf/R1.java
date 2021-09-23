package TestYourSelf;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class R1 {
    public static final int maxValue = (int) 1e3 + 5;

    public static void main(String[] args) {
        int n = 6;
        int pow = 17;
        System.out.println(phi(n));
        System.out.println(pow(n, pow));
        System.out.println(gcd(n, pow));
        System.out.println(Arrays.toString(evklidExt(n, pow)));
        System.out.println(erat(pow));
        System.out.println(modPow(n, phi(pow) - 1, pow));
    }

    private static int modPow(int n, int pow, int mod) {
        int res = 1;
        while (pow != 0) {
            if ((pow & 1) == 1) res = (res * n) % mod;
            n = (n * n) % mod;
            pow >>= 1;
        }
        return res;
    }

    private static List<Integer> erat(int n) {
        List<Integer> primes = new ArrayList<>();
        Map<Integer, Boolean> erat = new HashMap<>();
        for (int i = 2; i <= n; i += 1) {
            erat.putIfAbsent(i, true);
            if (!erat.get(i)) continue;
            primes.add(i);
            for (int j = 2 * i; j <= n; j += i)
                erat.putIfAbsent(j, false);
        }
        return primes;
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

    private static int pow(int n, int pow) {
        int res = 1;
        while (pow != 0) {
            if ((pow & 1) == 1) res *= n;
            n *= n;
            pow >>= 1;
        }
        return res;
    }

    private static int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b % a, a);
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
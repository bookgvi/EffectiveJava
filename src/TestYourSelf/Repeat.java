package TestYourSelf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repeat {
    private static final Map<Integer, Integer> values = new HashMap<>();
    private static final Map<Integer, Integer> first = new HashMap<>();
    private static final List<Integer> Coins = List.of(1, 2, 5);
    private static final Integer INF = Integer.MAX_VALUE / 4 * 3;

    public static void main(String[] args) {
        int m = 17;
        int x = 6;
        int phi = getEulerFunc(m);
        int invX = powMod(x, phi - 1, m);
        int invXprime = powMod(x, 17 - 2, m);
        System.out.println(phi);
        System.out.println(invX);
        System.out.println(invXprime);
    }

    private static int getEulerFunc(int n) {
        int result = n;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                while (n % i == 0) {
                    n /= i;
                }
                result -= result / i;
            }
        }
        if (n > 1) result -= result / n;
        return result;
    }

    private static int powMod(int n, int pow, int mod) {
        int res = 1;
        while(pow > 0) {
            if ((pow & 1) == 1) res = res * n % mod;
            n = n * n % mod;
            pow >>= 1;
        }
        return res;
    }

    private static int getEulerFuncForFactorization(int n, List<Integer> primes) {
        for (int prime : primes) {
            n -= n / prime;
        }
        return n;
    }

    private static List<Integer> getPrimes(int n) {
        Map<Integer, Boolean> primesMap = new HashMap<>();
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            primesMap.putIfAbsent(i, true);
            if (!primesMap.get(i)) continue;
            primes.add(i);
            for (int j = 2 * i; j <= n; j += i) {
                primesMap.putIfAbsent(j, false);
            }
        }
        return primes;
    }

}

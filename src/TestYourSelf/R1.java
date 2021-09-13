package TestYourSelf;

import java.util.*;

public class R1 {
    public static void main(String[] args) {
        int n = 17;
        int a = 6;
        int b = 49;
        int mod = 17;
        System.out.println(erat(n));
        System.out.println(gcd(a, b));
        System.out.println(Arrays.toString(evclidExt(a, mod, 1)));
        System.out.println(factorization(b));
        System.out.println(powMod(2, 5, 1));
        System.out.println(phi(a));
        int invA = powMod(a, phi(mod) - 1, mod);
        System.out.println(invA);
    }

    private static Map<Integer, Boolean> erat(int n) {
        Map<Integer, Boolean> erat = new HashMap<>();
        for (int i = 2; i <= n; i += 1) {
            erat.putIfAbsent(i, true);
            if (!erat.get(i)) continue;
            for (int j = 2 * i; j <= n; j += i) erat.putIfAbsent(j, false);
        }
        return erat;
    }

    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    private static int[] evclidExt(int a, int b, int mod) {
        int[] result = new int[3];
        if (a == 0) {
            result[0] = b;
            result[2] = 1;
            return result;
        }
        result = evclidExt(b % a, a, mod);
        int tmp = result[1];
        result[1] = result[2] - (b / a) * result[1];
        result[2] = tmp;
        return result;
    }

    private static List<Integer> factorization(int n) {
        List<Integer> factors = new ArrayList<>();
        for (int i = 2; i * i <= n; i += 1) {
            if (n % i == 0) {
                while (n % i == 0) {
                    factors.add(i);
                    n /= i;
                }
            }
        }
        if (n > 1) factors.add(n);
        return factors;
    }

    private static int powMod(int n, int pow, int mod) {
        int result = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) {
                result = (result * n) % mod;
            }
            n = (n * n) % mod;
            pow >>= 1;
        }
        return result;
    }

    private static int phi(int n) {
        int result = n;
        for (int i = 2; i * i <= n; i += 1) {
            if (n % i == 0) {
                while (n % i == 0) n /= i;
                result -= result / i;
            }
        }
        if (n > 1) result -= result / n;
        return result;
    }
}
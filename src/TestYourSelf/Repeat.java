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
        int n = 12, b = 30;
        System.out.println(getPrimes(n));
        System.out.println(gcdRecursion(n, b));
        System.out.println(gcdNonRecursion(n, b));
    }

    public static List<Integer> getPrimes(int x) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i * i < x; i++) {
            while (x % i == 0) {
                primes.add(i);
                x /= i;
            }
        }
        if (x > 1) primes.add(x);
        return primes;
    }

    public static int gcdRecursion(int a, int b) {
        if (a == 0) return b;
        return gcdRecursion(b % a, a);
    }

    public static int gcdNonRecursion(int a, int b) {
        int x = b;
        while (b != 0) {
            x = b;
            b = a % b;
            a = x;
        }
        return a;
    }

}

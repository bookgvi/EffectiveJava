package TestYourSelf;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class R3 {
    static int n = 15, k = n;
    static List<Integer> firstT = new ArrayList<>();
    static List<Integer> valuesT = new ArrayList<>();
    static boolean[] readyT = new boolean[k + 1];
    static int[] coins = new int[]{1, 3, 7};

    public static void main(String[] args) {
        firstT.add(0);
        valuesT.add(0);
        int[] coins = new int[]{1, 3, 7};
        List<Integer> first = solve(n, coins);

        solveR(k);
        System.out.println(valuesT.get(k));
        while (n > 0) {
            System.out.printf("%d ", first.get(n));
            n -= first.get(n);
        }
        System.out.println();
        System.out.println(erat(k));
    }

    private static List<Integer> erat(int n) {
        Map<Integer, Boolean> erat = new HashMap<>();
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i += 1) {
            erat.putIfAbsent(i, true);
            if (!erat.get(i)) continue;
            primes.add(i);
            for (int j = 2 * i; j <= n; j += i)
                erat.putIfAbsent(j, false);
        }
        return primes;
    }

    private static int solveR(int n) {
        if (n < 0) return Integer.MAX_VALUE / 4 * 3;
        if (n == 0) return 0;
        if (readyT[n]) return valuesT.get(n);
        int best = Integer.MAX_VALUE / 4 * 3;
        for (int coin : coins) {
            best = Math.min(best, solveR(n - coin) + 1);
        }
        valuesT.add(n, best);
        readyT[n] = true;
        return best;
    }



    private static List<Integer> solve(int n, int[] coins) {
        List<Integer> first = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        first.add(0);
        values.add(0);
        for (int i = 1; i <= n; i += 1) {
            first.add(i);
            values.add(Integer.MAX_VALUE / 4 * 3);
            for (int coin : coins) {
                if (i - coin >= 0 && (values.get(i - coin) + 1) - values.get(i) < 0) {
                    values.set(i, values.get(i - coin) + 1);
                    first.set(i, coin);
                }
            }
        }
        return first;
    }
}

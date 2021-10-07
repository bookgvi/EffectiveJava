package TestYourSelf;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class R3 {
    public static void main(String[] args) {
        int[] coins = new int[]{1, 3, 5, 10, 25, 50};
        int n = 43, k = 17, a = 5;
        List<Integer> first = solve(n, coins);
        while (n > 0) {
            System.out.printf("%d ", first.get(n));
            n -= first.get(n);
        }
    }

    private static List<Integer> solve(int n, int[] coins) {
        List<Integer> first = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        first.add(0);
        values.add(0);
        for (int i = 1; i <= n; i += 1) {
            first.add(0);
            values.add(Integer.MAX_VALUE / 10 * 9);
            for (int coin : coins) {
                if (i - coin >= 0 && values.get(i - coin) + 1 < values.get(i)) {
                    values.set(i, values.get(i - coin) + 1);
                    first.set(i, coin);
                }
            }
        }
        return first;
    }

    private static List<Integer> solve1(int n, int[] coins) {
        List<Integer> first = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        first.add(0);
        values.add(0);
        for (int i = 1; i <= n; i += 1) {
            first.add(0);
            values.add(Integer.MAX_VALUE / 4 * 3);
            for (int coin : coins) {
                if (i - coin >= 0 && values.get(i - coin) + 1 < values.get(i)) {
                    values.set(i, values.get(i - coin) + 1);
                    first.set(i, coin);
                }
            }
        }
        return first;
    }
}

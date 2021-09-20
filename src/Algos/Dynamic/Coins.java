package Algos.Dynamic;

import java.util.*;

public class Coins {
    public static void main(String[] args) {
        final int n = 13;
        List<Integer> coins = new ArrayList<>();
        coins.add(1);
        coins.add(3);
        coins.add(5);

        List<Integer> first = solve(n, coins);
        int k = n;
        while (k > 0) {
            System.out.printf("%d ", first.get(k));
            k -= first.get(k);
        }
        System.out.println();
    }

    private static List<Integer> solve(int n, List<Integer> coins) {
        Map<Integer, Integer> solve = new HashMap<>();
        List<Integer> first = new ArrayList<>();
        solve.put(0, 0);
        first.add(0);
        for (int i = 1; i <= n; i += 1) {
            solve.put(i, Integer.MAX_VALUE / 2);
            first.add(0);
            for (int coin : coins) {
                if ((i - coin) >= 0 && solve.get(i - coin) + 1 < solve.get(i)) {
                    solve.replace(i, solve.get(i - coin) + 1);
                    first.set(i, coin);
                }
            }
        }
        return first;
    }
}

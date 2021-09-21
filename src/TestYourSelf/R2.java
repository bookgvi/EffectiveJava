package TestYourSelf;

import java.util.*;

public class R2 {
    public static void main(String[] args) {
        int n = 19;
        int[] coins = new int[]{1, 3, 5, 10};
        System.out.println(erat(n));

        List<Integer> first = solve(n, coins);
        while (n > 0) {
            System.out.printf("%d ", first.get(n));
            n -= first.get(n);
        }
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

    private static List<Integer> solve(int n, int[] coins) {
        List<Integer> first = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        first.add(0);
        values.add(0);
        for (int i = 1; i <= n; i += 1) {
            first.add(0);
            values.add(Integer.MAX_VALUE / 4 * 3);
            for (int coin : coins) {
                if ((i - coin) >= 0 && values.get(i - coin) + 1 < values.get(i)) {
                    values.set(i, values.get(i - coin) + 1);
                    first.set(i, coin);
                }
            }
        }
        return first;
    }
}

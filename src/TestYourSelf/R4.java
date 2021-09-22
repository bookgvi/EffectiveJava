package TestYourSelf;

import java.util.ArrayList;
import java.util.List;

public class R4 {

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 3, 5};
        int n = 7;
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
        for (int i = 0; i <= n; i += 1) {
            first.add(i);
            values.add(Integer.MAX_VALUE >> 2 * 3);
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

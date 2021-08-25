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
        int n = 7;
        solve(n);
        System.out.println(values);
        System.out.println(first);
        int k = n;
        StringBuilder solve = new StringBuilder();
        while (k > 0) {
            solve.append(first.get(k)).append(" + ");
            k -= first.get(k);
        }
        String res = solve.delete(solve.length() - 2, solve.length()).toString();
        System.out.println(res);
    }

    private static void solve(int inCoin) {
        values.put(0, 0);
        for (int x = 1; x <= inCoin; x++) {
            values.put(x, INF);
            for (int coin : Coins) {
                if (coin <= x && values.get(x - coin) + 1 < values.get(x)) {
                    values.put(x, values.get(x - coin) + 1);
                    first.put(x, coin);
                }
            }
        }
    }
}

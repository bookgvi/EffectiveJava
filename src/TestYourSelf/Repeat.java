package TestYourSelf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repeat {
    public static void main(String[] args) {
        int n = 11;
        solve(n);
        System.out.println(values);

        System.out.printf("Разменять %d\n", n);
        StringBuilder coinsToSolve = new StringBuilder();
        String delimiter = " + ";
        int k = n;
        while (k > 0) {
            coinsToSolve.append(first.get(k)).append(delimiter);
            k -= first.get(k);
        }
        if (coinsToSolve.length() > 0) coinsToSolve.delete(coinsToSolve.length() - delimiter.length(), coinsToSolve.length());
        System.out.printf("%d = %s", n, coinsToSolve);
    }
    private static final List<Integer> coins = new ArrayList<>(List.of(1, 2, 5));
    private static final Map<Integer, Integer> values = new HashMap<>();
    private static final Map<Integer, Integer> first = new HashMap<>();

    private static void solve(int inCoin) {
        values.put(0, 0);
        for(int i = 1; i <= inCoin; i++) {
            values.put(i, Integer.MAX_VALUE / 2);
            for(int coin : coins) {
                if (values.containsKey(i - coin) && values.containsKey(i)) {
                    if (i - coin >= 0
                            && values.get(i - coin) + 1 < values.get(i)) {
                        values.put(i, values.get(i - coin) + 1);
                        first.put(i, coin);
                    }
                }
            }
        }
    }
}

package TestYourSelf.DP;

import java.util.ArrayList;
import java.util.List;

public class Coins {
    private static List<Integer> solve(int k, int[] coins) {
        List<Integer> values = new ArrayList<>(), steps = new ArrayList<>();
        values.add(0); steps.add(0);
        for (int i = 1; i <= k; i += 1) {
            values.add(Integer.MAX_VALUE / 2);
            steps.add(0);
            for (int coin : coins) {
                if (i - coin >= 0 && values.get(i - coin) + 1 < values.get(i)) {
                    values.set(i, values.get(i - coin) + 1);
                    steps.set(i, coin);
                }
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 3, 10};
        int k = 27;

        List<Integer> res = solve(k, coins);
        int i = k;
        while(i > 0) {
            System.out.printf("%d ", res.get(i));
            i -= res.get(i);
        }
        System.out.println();
    }
}

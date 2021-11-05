package TestYourSelf.DP;

import java.util.*;
import java.util.stream.*;

public class Coins {
    private static List<Integer> solve(int k, int[] coins) {
        List<Integer> d = new ArrayList<>(), steps = new ArrayList<>();
        d.add(0); steps.add(0);
        for (int i = 1; i <= k; i += 1) {
            steps.add(0);
            d.add(Integer.MAX_VALUE / 2);
            for(int coin : coins) {
                if (i - coin >= 0 && d.get(i - coin) + 1 < d.get(i)) {
                    d.set(i, d.get(i - coin) + coin);
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

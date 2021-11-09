package TestYourSelf.DP;

import java.util.*;
import java.util.stream.*;

public class Coins {
    private static List<Integer> solve(int k, int[] coins) {
        List<Integer> first = new ArrayList<>(), dp = new ArrayList<>();
        dp.add(0); first.add(0);
        for (int i = 1; i <= k; i += 1) {
            first.add(0);
            dp.add(Integer.MAX_VALUE / 2);
            for (int coin : coins) {
                if (i - coin >= 0 && dp.get(i - coin) + 1 < dp.get(i)) {
                    dp.set(i, dp.get(i - 1) + 1);
                    first.set(i, coin);
                }
            }
        }
        return first;
    }

    public static void main(String[] args) {
        int[] coins = {1, 5, 10, 25};
        int k = 43, t = k;
        List<Integer> res = solve(k, coins);
        while(t > 0) {
            System.out.println(res.get(t));
            t -= res.get(t);
        }
    }
}

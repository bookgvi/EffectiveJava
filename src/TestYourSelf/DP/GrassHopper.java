package TestYourSelf.DP;

import java.util.ArrayList;
import java.util.List;

public class GrassHopper {
    private static int[] solve1(int k, int[] coins) {
        int len = coins.length;
        int[] values = new int[len + 1], steps = new int[len + 1];
        for (int i = 1; i <= len; i += 1) {
            int max = i - 1;
            for (int j = Math.max(i - k, 0); j < i; j += 1) {
                if (values[j] > values[max]) max = j;
            }
            values[i] = values[max] + coins[Math.min(i, len - 1)];
            steps[i] = max;
        }
        return steps;
    }

    private static int[] solve2(int k, int[] coins) {
        int len = coins.length;
        int[] values = new int[len], steps = new int[len];
        for (int i = 0; i < len; i += 1) {
            values[i] = 1;
            for (int j = 1; j <= k; j += 1) {
                if (i - j >= 0 && values[i - j] + coins[i] > values[i]) {
                    values[i] = values[i - j] + coins[i];
                    steps[i] = j;
                }
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        int[] coins = new int[]{0, 0, 2, -3, 5, -2, 3};
        int[] res = solve1(3, coins);
        int i = res.length - 1;
        List<Integer> ans = new ArrayList<>();
        do {
            i = res[i];
            ans.add(i);
        } while (i > 0);

        System.out.println(ans);
//        while (i > 1) {
//            System.out.println(res[i]);
//            i -= res[i];
//        }
    }
}

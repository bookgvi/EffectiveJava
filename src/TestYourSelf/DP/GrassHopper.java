package TestYourSelf.DP;

import java.util.ArrayList;
import java.util.List;

public class GrassHopper {
    private static int[] solve1(int k, int[] coins) {
        int len = coins.length;
        int[] dp = new int[len + 1], steps = new int[len + 1];
        for (int i = 1; i <= len; i += 1) {
            int max = i - 1;
            for (int j = Math.max(1, i - k); j < i; j += 1) {
                if (dp[j] > dp[max]) max = j;
            }
            if (i < len) dp[i] = dp[max] + coins[i];
            steps[i] = max;
        }
        return steps;
    }

    private static void displayResults(int[] steps) {
        int len = steps.length;
        do {
            len = steps[len - 1];
            System.out.println(len);
        } while (len > 0);
    }

    public static void main(String[] args) {
        int[] coins = {0, 1, 2, -3, 5};
        int k = 3;
        int[] res = solve1(k, coins);
        displayResults(res);
    }
}

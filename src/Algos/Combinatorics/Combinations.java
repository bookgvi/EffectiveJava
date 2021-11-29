package Algos.Combinatorics;

public class Combinations {
    private static int combinations(int n, int k) {
        int n1 = Math.max(n, k);
        k = Math.min(n, k);
        n = n1;
        int[][] dp = new int[n + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i += 1) {
            dp[i][0] = 1; dp[i][i] = 1;
            for (int j = 1; j < i; j += 1)
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
        }
        return dp[n][k];
    }

    public static void main(String[] args) {
        int res = combinations(7, 14);
        System.out.println(res);
    }
}

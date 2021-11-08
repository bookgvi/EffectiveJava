package Leetcode.Simple;

import java.math.BigInteger;

public class UniqueBinarySearchTrees_Catalan {
    private static final int max = 19;
    private static int[] catalan = catalan();

    public static int numTrees(int n) {
        return catalan[n];
    }

    private static int[] catalan() {
        int[] dp = new int[max + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= max; i += 1)
            for (int j = 0; j < i; j += 1)
                dp[i] += dp[j] * dp[i - j - 1];
        return dp;
    }

    public static void main(String[] args) {
        int res = numTrees(19);
    }
}

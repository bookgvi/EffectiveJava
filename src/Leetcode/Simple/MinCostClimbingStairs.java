package Leetcode.Simple;

public class MinCostClimbingStairs {
    public static int minCostClimbingStairs(int[] cost) {
        int len = cost.length, k = 2;
        int[] dp = new int[len + 1];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i <= len; i += 1) {
            int minI = i - 1;
            for (int j = Math.max(0, i - k); j < i; j += 1)
                if (dp[j] < dp[minI]) minI = j;
            int val = i < len ? cost[i] : 0;
            dp[i] = dp[minI] + val;
        }
        return dp[len];
    }

    public static void main(String[] args) {
//        int[] cost = new int[]{10, 15, 20};
        int[] cost = new int[]{1,100,1,1,1,100,1,1,100,1};
        int res = minCostClimbingStairs(cost);
    }
}

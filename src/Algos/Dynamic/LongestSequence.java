package Algos.Dynamic;

import java.util.*;

public class LongestSequence {
    /*
    * nums = {2, 1, 3, 4, 1, 1}
    * values = {0, 0, 0, 0, 0, 0}
    * counter = 0, i = 1, j = 0
    * nums[i] = 1, nums[j] = 2
    *
    * O(n^2)
    * */
    private static List<Integer> solve(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len], p = new int[len];
        for (int i = 0; i < len; i += 1) {
            dp[i] = 1; p[i] = -1;
            for (int j = 0; j < i; j += 1) {
                if (nums[i] > nums[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    p[i] = j;
                }
            }
        }
        int elt = 0, ans = dp[0];
        for (int i = 0; i < len; i += 1) {
            if(ans < dp[i]) {
                ans = dp[i];
                elt = Math.max(elt, i);
            }
        }
        List<Integer> restored = new ArrayList<>();
        while (elt != -1) {
            restored.add(elt);
            elt = p[elt];
        }
        return restored;
    }

    /*
    * O(n*log(n))
    * */
    private static int[] solveExt(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len + 1], p = new int[len], np = new int[len];
        dp[0] = Integer.MIN_VALUE;
        for (int i = 1; i <= len; i += 1)
            dp[i] = Integer.MAX_VALUE;
        for (int i = 0; i < len; i += 1) {
            for (int j = 1; j <= len; j += 1) {
                if (dp[j - 1] < nums[i] && nums[i] < dp[j]) {
                    dp[j] = nums[i];
                    p[i] = j;
                    np[j] = i;
                }
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 4, 2, 5, 6, 3};
//        int[] nums = {2, 1, 3, 4, 1, 1};
        List<Integer> restored = solve(nums);
        int[] r = solveExt(nums);
    }
}

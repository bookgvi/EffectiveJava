package TestYourSelf.DP;

import java.util.ArrayList;
import java.util.List;

/*
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * You can assume that you can always reach the last index.
 *
 * https://leetcode.com/problems/jump-game-ii/
 * */
//TODO WA -> NM
public class JumpGameII {
    public static int jump(int[] nums) {
        int[] dp = new int[nums.length];

        dp[nums.length - 1] = 0;

        for (int i = nums.length - 2; i >= 0; i--) {
            int steps = nums[i];
            if (steps > 0) {

                int min = Integer.MAX_VALUE;
                for (int j = 1; j <= steps && i + j < nums.length; j++) {
                    if (dp[i + j] < min) {
                        if (dp[i + j] == 0 && i + j == nums.length - 1)
                            min = dp[i + j];
                        else if (dp[i + j] != 0) {
                            min = dp[i + j];
                        }

                    }

                    if (min != Integer.MAX_VALUE)
                        dp[i] = min + 1;

                }
            } else {
                dp[i] = 0;
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {
//        int[] nums = {2, 3, 0, 1, 4};
//        int[] nums = {2,3,1,1,4};
//        int[] nums = {2,1};
//        int[] nums = {1, 3, 2};
//        int[] nums = {4, 1, 3, 2,1};
//        int[] nums = {1,1,1,1};
        int[] nums = {1, 2, 3};
        int res = jump(nums);
    }
}

package TestYourSelf.DP;

import java.util.Arrays;

/*
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed. All houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected,
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *
 * https://leetcode.com/problems/house-robber-ii/
 * */
public class HouseRobberII {
    public static int rob(int[] nums) {
        int len = nums.length;
        if (len == 1) return nums[0];
        else if (len == 0) return 0;
        else if (len == 2) return Math.max(nums[0], nums[1]);
        int[] minusFirst = new int[len - 1], minusLast = new int[len - 1];
        System.arraycopy(nums, 1, minusFirst, 0, len - 1);
        System.arraycopy(nums, 0, minusLast, 0, len - 1);
        int[] resMinusFirst = helper(minusFirst);
        int[] resMinusLast = helper(minusLast);
        return Math.max(resMinusFirst[len - 1], resMinusLast[len - 1]);
    }

    private static int[] helper(int[] nums) {
        int len = nums.length;
        if (len == 1) return nums;
        else if (len == 0) return new int[]{};
        int[] dp = new int[len + 1];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i <= len; i += 1) {
            int val = i < len ? nums[i] : 0;
            dp[i] = Math.max(val + dp[i - 2], dp[i - 1]);
        }
        return dp;
    }

    public static void main(String[] args) {
//        int[] nums = {2, 3, 2};
//        int[] nums = {1, 2, 1, 1};
//        int[] nums = {1, 2, 3, 1};
//        int[] nums = {1};
        int[] nums = {0, 0};
        int res = rob(nums);
    }
}

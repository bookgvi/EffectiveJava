package TestYourSelf.DP;

/*
 * You are given an integer array nums. You are initially positioned at the array's first index,
 * and each element in the array represents your maximum jump length at that position.
 * Return true if you can reach the last index, or false otherwise.
 *
 * https://leetcode.com/problems/jump-game/
 * */
public class JumpGame {
    public static boolean canJump(int[] nums) {
        int len = nums.length;
        if (len < 1) return false;
        if (len == 1) return true;
        boolean[] dp = new boolean[len];
        dp[0] = nums[0] != 0;
        for (int i = 1; i < len; i += 1) {
            for (int j = 0; j < i; j += 1) {
                dp[i] |= i - (nums[j] + j) <= 0 && dp[j];
                if (dp[i]) break;
            }
        }
        return dp[len - 1];
    }

    public static void main(String[] args) {
//        int[] nums = {2, 0, 1, 1, 6};
//        int[] nums = {2, 3, 1, 1, 6};
//        int[] nums = {3,2,1,0,4};
//        int[] nums = {};
//        int[] nums = {-10};
//        int[] nums = {2, 1, 0, 1, 3};
        int[] nums = {0, 2, 3};
        boolean res = canJump(nums);
    }
}

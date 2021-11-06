package Leetcode.Simple;

public class MaximumSubarray {
    public static int maxSubArray(int[] nums) {
        int res = (int)-1e4, sum = (int)-1e4;
        for (int i = 0, len = nums.length; i < len; i += 1) {
            res = nums[i];
            sum = Math.max(nums[i] + sum, nums[i]);
            res = Math.max(res, sum);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int res = maxSubArray(nums);
    }
}

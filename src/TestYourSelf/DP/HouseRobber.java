package TestYourSelf.DP;

public class HouseRobber {
    public static int rob(int[] nums) {
        int len = nums.length;
        if (len == 1) return nums[0];
        int[] dp = new int[len + 1];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i <= len; i += 1) {
            int val = i < len ? nums[i] : 0;
            dp[i] = Math.max(val + dp[i - 2], dp[i - 1]);
        }
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 1, 2};
        int res = rob(nums);
    }
}

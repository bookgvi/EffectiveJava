package TestYourSelf.DP;

public class HouseRobber {
    public static int rob(int[] nums) {
        int len = nums.length;
        if (len == 1) return 0;
        int dp[] = new int[len + 1];
        dp[0] = nums[0];
        dp[1] = nums[1];
        for (int i = 2; i <= len; i += 1) {
            int numVal = i < len ? nums[i] : 0;
            int max = i - 2;
            for (int j = 0; j <= i - 2; j += 1)
                if (dp[max] < dp[j]) max = j;
            dp[i] = Math.max(numVal + dp[max], dp[i - 1]);
        }
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 1, 2};
        int res = rob(nums);
    }
}

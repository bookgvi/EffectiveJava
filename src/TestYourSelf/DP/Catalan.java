package TestYourSelf.DP;

public class Catalan {
    private static int[] catalan(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i += 1) {
            for (int j = 0; j < i; j += 1) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        int n = 10;
        int[] cn = catalan(n);
    }
}

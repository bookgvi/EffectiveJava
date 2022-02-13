package Algos.Dynamic;

public class Turtle {
    private static int[][] solve(int[][] matrix) {
        int lenI = matrix.length, lenJ = matrix[0].length;
        int[][] dp = new int[lenI][lenJ];
        dp[lenI - 1][0] = matrix[lenI - 1][0];
        for (int i = lenI - 2; i >= 0; i -= 1) dp[i][0] = dp[i + 1][0] + matrix[i][0];
        for (int j = 1; j < lenJ; j += 1) dp[lenI - 1][j] = dp[lenI - 1][j - 1] + matrix[lenI - 1][j];
        for (int i = lenI - 2; i >= 0; i -= 1)
            for (int j = 1; j < lenJ; j += 1)
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]) + matrix[i][j];
        return dp;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {9, 8, 6, 2},
                {10, 11, 13, 11},
                {3, 7, 12, 8},
                {5, 9, 13, 9}
        };
        int[][] res = solve(matrix);
    }
}

package Algos.Combinatorics;

public class Combinations {
    private static int combinations(int n, int k) {
        int[][] triangle = new int[n + 1][n + 1];
        triangle[0][0] = 1;
        for (int i = 0; i <= n; i += 1) {
            triangle[i][0] = 1;
            triangle[i][i] = 1;
            for (int j = 1; j < i; j += 1)
                triangle[i][j] = triangle[i - 1][j - 1] + triangle[i - 1][j];
        }
        return triangle[n][k];
    }

    public static void main(String[] args) {
        int res = combinations(14, 7);
        System.out.println(res);
    }
}

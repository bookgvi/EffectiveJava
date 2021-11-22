package Algos.Combinatorics;

public class Combinations {
    private static int combinations(int n, int k) {
        if (n < k) {
            int tmp = n;
            n = k;
            k = tmp;
        }
        int[][] comb = new int[n + 1][n + 1];
        comb[0][0] = 1;
        for (int i = 1; i <= n; i += 1) {
            comb[i][0] = 1;
            comb[i][i] = 1;
            for (int j = 1; j < i; j += 1)
                comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];
        }
        return comb[n][k];
    }

    public static void main(String[] args) {
        int res = combinations(7, 14);
        System.out.println(res);
    }
}

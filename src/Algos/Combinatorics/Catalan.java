package Algos.Combinatorics;

import java.util.stream.*;

public class Catalan {
    private static final int maxF = (int) 1e5;
    private static long[] fact = fact();
    private static long[] catalanArr = catalanDP();

    public static void main(String[] args) {
        String startArr = "(((())))";
        long Cn = catalanArr[startArr.length() >> 1]; //catalan(startArr.length() / 2);
        System.out.printf("Cn = %d\n", Cn);
        for (int i = 1; i <= Cn; i += 1) {
            System.out.printf("%d)\t%s\n", i, startArr);
            startArr = permutation(startArr);
        }
    }

    private static String permutation(String seq) {
        StringBuilder res = new StringBuilder();
        int len = seq.length(), depth = 0;
        for (int i = len - 1; i >= 0; i -= 1) {
            if (seq.charAt(i) == '(') depth -= 1;
            else depth += 1;
            if (depth > 0 && seq.charAt(i) == '(') {
                depth -= 1;
                int open = (len - i - 1 - depth) >> 1;
                int close = len - i - 1 - open;
                res.append(seq, 0, i).append(")").append(repeat("(", open)).append(repeat(")", close));
                break;
            }
        }
        return res.toString();
    }

    private static String repeat(String str, int n) {
        return str.repeat(n);
    }


    private static long catalan(int n) {
        return fact[2 * n] / (fact[n] * fact[n + 1]);
    }

    private static long[] catalanDP() {
        long[] dp = new long[maxF];
        dp[0] = 1; dp[1] = 1;
        for (int i = 2; i < maxF; i += 1)
            for(int j = 0; j < i; j += 1)
                dp[i] += dp[i - j - 1] * dp[j];
        return dp;
    }

    private static long[] fact() {
        long[] fact = new long[maxF];
        fact[0] = 1;
        for (int i = 1; i < maxF; i += 1)
            fact[i] = fact[i - 1] * i;
        return fact;
    }
}

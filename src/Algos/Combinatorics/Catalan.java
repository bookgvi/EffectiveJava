package Algos.Combinatorics;

import java.util.stream.*;

public class Catalan {
    private static final int maxF = (int) 1e5;
    private static long[] fact = fact();

    public static void main(String[] args) {
        String startArr = "(((())))";
        long Cn = catalan(startArr.length() / 2);
        System.out.printf("Cn = %d\n", Cn);
        for (int i = 1; i <= Cn; i += 1) {
            System.out.printf("%d)\t%s\n", i, startArr);
            startArr = permutation(startArr);
        }
    }

    private static String permutation(String str) {
        int len = str.length(), depth = 0;
        StringBuilder seq = new StringBuilder();
        for (int i = len - 1; i >= 0; i -= 1) {
            if (str.charAt(i) == '(') depth -= 1;
            else depth += 1;
            if (depth > 0 && str.charAt(i) == '(') {
                depth -= 1;
                int open = (len - i - 1 - depth) >> 1;
                int close = len - i - 1- open;
                seq.append(str, 0, i).append(")").append(repeat("(", open)).append(repeat(")", close));
                break;
            }
        }
        return seq.toString();
    }

    private static String repeat(String ch, int count) {
        StringBuilder res = new StringBuilder();
        IntStream.range(0, count).forEach(i -> res.append(ch));
        return res.toString();
    }

    private static long catalan(int n) {
        return fact[2 * n] / (fact[n] * fact[n + 1]);
    }

    private static long[] fact() {
        long[] fact = new long[maxF];
        fact[0] = 1;
        for (int i = 1; i < maxF; i += 1)
            fact[i] = fact[i - 1] * i;
        return fact;
    }
}

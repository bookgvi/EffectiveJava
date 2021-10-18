package Algos;

import java.util.stream.*;

public class Catalan {
    public static void main(String[] args) {
        String startArr = "((((()))))";
        long Cn = catalan(startArr.length() / 2);
        System.out.printf("Cn = %d\n", Cn);
        for (int i = 1; i <= Cn; i += 1) {
            System.out.printf("%d)\t%s\n", i, startArr);
            startArr = permutation(startArr);
        }
    }

    private static String permutation(String str) {
        int n = str.length(), depth = 0;
        StringBuilder res = new StringBuilder();
        for (int i = n - 1; i >= 0; i -= 1) {
            if (str.charAt(i) == '(') depth--;
            else depth++;
            if (depth > 0 && str.charAt(i) == '(') {
                depth -= 1;
                int open = (n - 1 - i - depth) >> 1;
                int close = n - 1 - i - open;
                String opens = repeatedStr('(', open);
                String closes = repeatedStr(')', close);
                res.append(str, 0, i).append(")").append(opens).append(closes);
                break;
            }
        }
        return res.toString();
    }

    private static String repeatedStr(char ch, int count) {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, count).forEach(i -> sb.append(ch));
        return sb.toString();
    }

    private static long catalan(int n) {
        return fact(2 * n) / (fact(n) * fact(n + 1));
    }

    private static long fact(int n) {
        return getFact(2, n);
    }

    private static long getFact(int l, int r) {
        if (l == r) return l;
        if (r - l == 1) return (long) r * l;
        int mid = (r + l) >> 1;
        return getFact(l, mid) * getFact(mid + 1, r);
    }
}

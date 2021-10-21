package Algos;

import java.util.stream.*;

public class Catalan {
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
        StringBuilder resStr = new StringBuilder();
        for (int i = len - 1; i >= 0; i -= 1) {
            if (str.charAt(i) == '(') depth -= 1;
            else depth += 1;
            if (depth > 0 && str.charAt(i) == '(') {
                depth -= 1;
                int open = (len - 1 - i - depth) >> 1;
                int close = len - 1 - i - open;
                String openChar = repeated("(", open);
                String closeChar = repeated(")", close);
                resStr.append(str, 0, i).append(")").append(openChar).append(closeChar);
                break;
            }
        }
        return resStr.toString();
    }

    private static String repeated(String ch, int count) {
        StringBuilder res = new StringBuilder();
        IntStream.range(0, count).forEach(i -> res.append(ch));
        return res.toString();
    }

    private static long catalan(int n) {
        return fact(2 * n) / (fact(n) * fact(n + 1));
    }

    private static long fact(int n) {
        return getFact(2, n);
    }

    private static long getFact(int l, int r) {
        if (l == r) return l;
        if (r - l == 1) return (long) l * r;
        int mid = (r + l) >> 1;
        return getFact(l, mid) * getFact(mid + 1, r);
    }
}

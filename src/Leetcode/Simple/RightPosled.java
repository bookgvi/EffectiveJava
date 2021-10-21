package Leetcode.Simple;

import java.io.*;
import java.math.BigInteger;
import java.util.stream.IntStream;

public class RightPosled {
    public static void main(String[] args) {

        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        try {
//            int count = Integer.parseInt(r.readLine());
            for (int count = 10; count < 12; count += 1) {
//                if (count > 11 || count < 0) return;
                String str = generateStart(count);
                BigInteger catalan = catalan(count);
                for (int i = 0; !BigInteger.valueOf(i).equals(catalan); i += 1) {
                    System.out.println(str);
                    str = nextPermutation(str);
                }
                r.readLine();
            }
        } catch (Exception ignored) {
        }
    }

    private static BigInteger catalan(int n) {
        return fact(2 * n).divide(fact(n).multiply(fact(n + 1)));
    }

    private static String generateStart(int n) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i += 1)
            res.append("(");
        for (int i = 0; i < n; i += 1)
            res.append(")");
        return res.toString();
    }

    private static String nextPermutation(String str) {
        int len = str.length(), depth = 0;
        StringBuilder res = new StringBuilder();
        for (int i = len - 1; i >= 0; i -= 1) {
            if (str.charAt(i) == '(') depth -= 1;
            else depth += 1;
            if (depth > 0 && str.charAt(i) == '(') {
                depth -= 1;
                int open = (len - 1 - i - depth) >> 1;
                int close = len - 1 - i - open;
                String openStr = repeated("(", open);
                String closeStr = repeated(")", close);
                res.append(str, 0, i).append(")").append(openStr).append(closeStr);
                break;
            }
        }
        return res.toString();
    }

    private static String repeated(String ch, int count) {
        StringBuilder res = new StringBuilder();
        IntStream.range(0, count).forEach(i -> res.append(ch));
        return res.toString();
    }

    private static BigInteger binTreeFactorial(int l, int r) {
        if (l == r) return BigInteger.valueOf(l);
        if (l - r > 1) return BigInteger.ONE;
        if (l - r == 1) return BigInteger.valueOf(l).multiply(BigInteger.valueOf(r));
        int mid = (l + r) / 2;
        return binTreeFactorial(l, mid).multiply(binTreeFactorial(mid + 1, r));
    }

    private static BigInteger fact(int n) {
        return binTreeFactorial(2, n);
    }
}

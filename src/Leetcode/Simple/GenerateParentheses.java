package Leetcode.Simple;

import java.util.stream.IntStream;
import java.util.*;

public class GenerateParentheses {
    private static final int k = 16;
    private static final long[] fact = fact();

    public static void main(String[] args) {
        int n = 8;
        System.out.println(generateParenthesis(n));
    }

    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        String seq = getFirstSeq(n);
        res.add(seq);
        for (int i = 1; i < catalan(n); i += 1) {
            seq = nextSequence(seq);
            res.add(seq);
        }
        return res;
    }

    private static String nextSequence(String seq) {
        int len = seq.length(), depth = 0;
        StringBuilder res = new StringBuilder();

        for (int i = len - 1; i >= 0; i -= 1) {
            if (seq.charAt(i) == '(') depth -= 1;
            else depth += 1;
            if (depth > 0 && seq.charAt(i) == '(') {
                depth -= 1;
                int open = (len - 1 - i - depth) >> 1;
                int close = len - 1 - i - open;
                String openChar = repeated("(", open);
                String closeChar = repeated(")", close);
                res.append(seq, 0, i).append(")").append(openChar).append(closeChar);
                break;
            }
        }
        return res.toString();
    }

    private static String getFirstSeq(int n) {
        return repeated("(", n) + repeated(")", n);
    }

    private static String repeated(String ch, int count) {
        StringBuilder res = new StringBuilder();
        IntStream.range(0, count).forEach(i -> res.append(ch));
        return res.toString();
    }

    private static long catalan(int n) {
        return fact[2 * n] / (fact[n] * fact[n + 1]);
    }

    private static long[] fact() {
        long[] fact = new long[k + 1];
        fact[0] = 1;
        for (int i = 1; i <= k; i+= 1)
            fact[i] = fact[i - 1] * i;
        return fact;
    }
}

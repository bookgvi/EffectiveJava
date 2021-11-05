package TestYourSelf;


import java.math.BigInteger;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class BracketSequence {
    private static final int maxF = (int) 1e5;
    private static final long[] fact = fact();

    public static void main(String[] args) {
        final int n = 6;
        String startSeq = getFirstSequence(n);
        long catalan = catalan(n);
        System.out.printf("count = %d\n", catalan);
        System.out.printf("%d)\t%s\n", 1, startSeq);
        for (int i = 1; i < catalan; i += 1) {
            startSeq = nextSequence(startSeq);
//            System.out.printf("\"%s\", ", startSeq);
            System.out.printf("%d)\t%s\n", i + 1, startSeq);
        }
    }

    private static String nextSequence(String seq) {
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

    private static long catalan(int n) {
        return fact[2 * n] / (fact[n] * fact[n + 1]);
    }

    private static String getFirstSequence(int n) {
        return repeat("(", n) + repeat(")", n);
    }

    private static String repeat(String ch, int count) {
        StringBuilder res = new StringBuilder();
        IntStream.range(0, count).forEach(i -> res.append(ch));
        return res.toString();
    }

    private static long[] fact() {
        long[] fact = new long[maxF];
        fact[0] = 1;
        for (int i = 1; i < maxF; i += 1)
            fact[i] = fact[i - 1] * i;
        return fact;
    }
}

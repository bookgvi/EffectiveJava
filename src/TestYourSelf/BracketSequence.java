package TestYourSelf;


import java.math.BigInteger;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class BracketSequence {
    private static final int maxF = (int) 1e2;
    private static final long[] fact = fact();

    public static void main(String[] args) {
        final int n = 4;
        String startSeq = getFirstSequence(n);
        long catalan = catalan(n);
        System.out.printf("count = %d\n", catalan);
        System.out.printf("%d)\t%s\n", 1, startSeq);
        for (int i = 1; i < catalan; i += 1) {
            startSeq = nextSequence(startSeq);
            System.out.printf("%d)\t%s\n", i + 1, startSeq);
        }
    }

    private static String nextSequence(String str) {
        StringBuilder seq = new StringBuilder();
        int depth = 0;
        for (int len = str.length(), i = len - 1; i >= 0; i -= 1) {
            if (str.charAt(i) == '(') depth -= 1;
            else depth += 1;
            if (depth > 0 && str.charAt(i) == '(') {
                depth -= 1;
                int open = (len - 1 - i - depth) >> 1;
                int close = len - 1 - i - open;
                seq.append(str, 0, i).append(")").append(repeated("(", open)).append(repeated(")", close));
                break;
            }
        }
        return seq.toString();
    }

    private static long catalan(int n) {
        return fact[2 * n] / (fact[n] * fact[n + 1]);
    }

    private static String getFirstSequence(int n) {
        return repeated("(", n) + repeated(")", n);
    }

    private static String repeated(String ch, int count) {
        StringBuilder seq = new StringBuilder();
        IntStream.range(0, count).forEach(i -> seq.append(ch));
        return seq.toString();
    }

    private static long[] fact() {
        long[] fact = new long[maxF];
        fact[0] = 1;
        for (int i = 1; i < maxF; i += 1)
            fact[i] = fact[i - 1] * i;
        return fact;
    }
}

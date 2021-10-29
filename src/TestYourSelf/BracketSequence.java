package TestYourSelf;


import java.math.BigInteger;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class BracketSequence {
    private static final int maxF = (int) 1e5;
    private static final long[] fact = fact();

    public static void main(String[] args) {
        final int n = 15000; // 30000 / 2 = 15000
        String startSeq = getFirstSequence(n);
        BigInteger catalan = catalan(n);
        System.out.printf("count = %d\n", catalan);
        System.out.printf("%d)\t%s\n", 1, startSeq);
        for (int i = 1; !BigInteger.valueOf(i).equals(catalan); i += 1) {
            startSeq = nextSequence(startSeq);
            System.out.printf("\"%s\", ", startSeq);
//            System.out.printf("%d)\t%s\n", i + 1, startSeq);
        }
    }

    private static String nextSequence(String str) {
        StringBuilder seq = new StringBuilder();
        int len = str.length(), depth = 0;
        for (int i = len - 1; i >= 0; i -= 1) {
            if (str.charAt(i) == '(') depth -= 1;
            else depth += 1;
            if (depth > 0 && str.charAt(i) == '(') {
                depth -= 1;
                int open = (len - 1 - i - depth) >>> 1;
                int close = len - 1 - i - open;
                seq.append(str, 0, i).append(")").append(repeat("(", open)).append(repeat(")", close));
                break;
            }
        }
        return seq.toString();
    }

    private static String getFirstSequence(int n) {
        return repeat("(", n) + repeat(")", n);
    }

    private static String repeat(String ch, int count) {
        StringBuilder res = new StringBuilder();
        IntStream.range(0, count).forEach(i -> res.append(ch));
        return res.toString();
    }

    private static BigInteger catalan(int n) {
        return BigInteger.valueOf(fact[2 * n]).divide(BigInteger.valueOf(fact[n])).divide(BigInteger.valueOf(fact[n + 1]));
    }

    private static long[] fact() {
        int max = (int) 1e6;
        long[] fact = new long[max];
        fact[0] = 1;
        for (int i = 1; i < max; i += 1)
            fact[i] = fact[i - 1] * i;
        return fact;
    }
}

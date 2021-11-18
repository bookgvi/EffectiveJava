package TestYourSelf;

import java.util.stream.IntStream;

public class BracketSequence {
    private static final long[] catalan = catalan();

    public static void main(String[] args) {
        final int n = 4;
        String startSeq = getFirstSequence(n);
        System.out.printf("count = %d\n", catalan[n]);
        System.out.printf("%d)\t%s\n", 1, startSeq);
        for (int i = 1; i < catalan[n]; i += 1) {
            startSeq = nextSequence(startSeq);
            System.out.printf("%d)\t%s\n", i + 1, startSeq);
        }
    }

    private static String nextSequence(String seq) {
        int len = seq.length(), depth = 0;
        StringBuilder res = new StringBuilder();
        for (int i = len - 1; i >= 0; i -= 1) {
            if (seq.charAt(i) == '(') depth -= 1;
            else depth += 1;
            if (depth > 0 && seq.charAt(i) == '(') {
                depth -= 1;
                int open = (len - i - 1 - depth) >>> 1;
                int close = len - i - 1 - open;
                res.append(seq, 0, i).append(")").append(repeat("(", open)).append(repeat(")", close));
                break;
            }
        }
        return res.toString();
    }

    private static String getFirstSequence(int n) {
        return repeat("(", n) + repeat(")", n);
    }

    private static String repeat(String ch, int count) {
        StringBuilder res = new StringBuilder();
        IntStream.range(0, count).forEach(i -> res.append(ch));
        return res.toString();
    }

    private static long[] catalan() {
        int max = (int) 1e3;
        long[] dp = new long[max];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < max; i += 1) {
            for (int j = 0; j < i; j += 1) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp;
    }
}

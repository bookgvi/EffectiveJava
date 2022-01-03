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

    private static String getFirstSequence(int n) {
        return repeat("(", n) + repeat(")", n);
    }

    private static String repeat(String ch, int n) {
        StringBuilder res = new StringBuilder();
        IntStream.range(0, n).forEach(i -> res.append(ch));
        return res.toString();
    }

    private static long[] catalan() {
        int max = 20;
        long[] catalan = new long[max];
        catalan[0] = 1;
        catalan[1] = 1;
        for (int i = 2; i < max; i += 1)
            for (int j = 0; j < i; j += 1)
                catalan[i] += catalan[j] * catalan[i - j - 1];
        return catalan;
    }
}

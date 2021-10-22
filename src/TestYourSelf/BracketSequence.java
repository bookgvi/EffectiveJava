package TestYourSelf;


import java.math.BigInteger;
import java.util.stream.IntStream;

public class BracketSequence {
    private static final int maxF = (int) 1e2;
    private static final BigInteger[] fact = fact();

    public static void main(String[] args) {
        final int n = 4;
        String startSeq = getFirstSequence(n);
        BigInteger catalan = catalan(n);
        System.out.printf("count = %d\n", catalan);
        System.out.printf("%d)\t%s\n", 1, startSeq);
        for (int i = 1; !catalan.equals(BigInteger.valueOf(i)); i += 1) {
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
                int open = (len - 1 - i - depth) >> 1;
                int close = len - 1 - i - open;
                res.append(seq, 0, i)
                        .append(")")
                        .append(repeated("(", open))
                        .append(repeated(")", close));
                break;
            }
        }
        return res.toString();
    }

    private static String getFirstSequence(int n) {
        return String.valueOf(repeated("(", n)) + repeated(")", n);
    }

    private static StringBuilder repeated(String ch, int count) {
        StringBuilder res = new StringBuilder();
        IntStream.range(0, count).forEach(i -> res.append(ch));
        return res;
    }

    private static BigInteger catalan(int n) {
        return fact[2 * n].divide((fact[n].multiply(fact[n + 1])));
    }

    private static BigInteger[] fact() {
        BigInteger[] fact = new BigInteger[maxF];
        fact[0] = BigInteger.ONE;
        for (int i = 1; i < maxF; i += 1)
            fact[i] = fact[i - 1].multiply(BigInteger.valueOf(i));
        return fact;
    }
}

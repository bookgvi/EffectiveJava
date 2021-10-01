package TestYourSelf;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class R2 {
    public static void main(String[] args) {
        System.out.println(fact(7));
    }

    private static BigInteger fact(int n) {
        return binMultiply(2, n);
    }

    private static BigInteger binMultiply(int a, int b) {
        if (b - a < 0) return BigInteger.ONE;
        else if (a - b == 0) return BigInteger.valueOf(a);
        else if (a - b == 1) return BigInteger.valueOf(a).multiply(BigInteger.valueOf(b));
        int mid = (a + b) / 2;
        return binMultiply(a, mid).multiply(binMultiply(mid + 1, b));
    }
}

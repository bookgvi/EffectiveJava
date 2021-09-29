package Algos;

import java.math.BigInteger;

public class Combinatorics {
    public static void main(String[] args) {
        int n = 23;
        int k = 2;
        BigInteger c = combination(k, n);
        BigInteger p = c.multiply(BigInteger.valueOf(100)).divide(BigInteger.valueOf(365));
        System.out.println(c);
        System.out.println(p);
    }

    private static BigInteger binTreeFactorial(int l, int r) {
        if (l > r) return BigInteger.ONE;
        if (l == r) return BigInteger.valueOf(l);
        else if (r - l == 1) return BigInteger.valueOf(l).multiply(BigInteger.valueOf(r));
        int mid = (r + l) / 2;
        return binTreeFactorial(l, mid).multiply(binTreeFactorial(mid + 1, r));
    }

    private static BigInteger factorial(int n) {
        return binTreeFactorial(2, n);
    }

    private static BigInteger combination(int k, int n) {
        BigInteger kFact = factorial(k);
        BigInteger nFact = factorial(n);
        BigInteger nkFact = factorial(n - k);
        BigInteger z = kFact.multiply(nkFact);
        return nFact.divide(z);
    }
}

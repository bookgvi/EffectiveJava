package TestYourSelf;

import java.util.*;

public class R1 {
    public static void main(String[] args) {
        int a = 21009;
        int b = 58879;
        System.out.println(gcdEvklid(a, b));

        List<Integer> primesA = factorization(a);
        List<Integer> primesB = factorization(b);
        int inter = intersect(primesA, primesB);
        List<Integer> interElems = getElemsOfSet(inter);
        int gcd2 = getGcdFromSet(interElems);
        System.out.println(gcd2);
    }

    private static int gcdEvklid(int a, int b) {
        if (a == 0) return b;
        return gcdEvklid(b % a, a);
    }

    private static int set(List<Integer> elems) {
        int set = 0;
        for (int elem : elems) {
            set |= (1 << elem);
        }
        return set;
    }

    private static List<Integer> getElemsOfSet(int set) {
        List<Integer> elems = new ArrayList<>();
        for (int i = 0; i < Integer.BYTES * 8; i++) {
            if ((set & (1 << i)) != 0) elems.add(i);
        }
        return elems;
    }

    private static int intersect(int set1, int set2) {
        return set1 & set2;
    }

    private static int intersect(List<Integer> set1values, List<Integer> set2values) {
        int set1 = set(set1values);
        int set2 = set(set2values);
        return set1 & set2;
    }

    private static List<Integer> factorization(int n) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                while (n % i == 0) {
                    primes.add(i);
                    n /= i;
                }
            }
        }
        if (n > 1) primes.add(n);
        return primes;
    }

    private static int getGcdFromSet(List<Integer> elemsInSet) {
       return elemsInSet.stream().reduce(1, (cur, prev) -> cur * prev);
    }
}
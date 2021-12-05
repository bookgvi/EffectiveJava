package TestYourSelf;

import java.util.*;

public class NumberTheory {
    public static void main(String[] args) {
        int a = 3, b = 21;
        System.out.println(gcd(a, b));

        int evklid = extEvklid(a, b)[0];
        System.out.println(evklid);

        List<Integer> primes = erat(20);
        System.out.println(primes);
    }

    private static int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }

    private static int[] extEvklid(int a, int b) {
        int[] res = new int[3];
        if (a == 0) {
            res[0] = b;
            res[2] = 1;
            return res;
        }
        res = extEvklid(b % a, a);
        int tmp = res[1];
        res[1] = res[2] - (b / a) * res[1];
        res[2] = tmp;
        return res;
    }

    private static List<Integer> erat(int n) {
        List<Integer> primes = new ArrayList<>();
        int[] erath = new int[n + 1];
        for (int i = 2; i <= n; i += 1) {
            if (erath[i] == 0)
                primes.add(i);
            for (int j = i; j <= n; j += i)
                erath[j] = 1;
        }
        return primes;
    }
}

package TestYourSelf;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class R1 {
    public static void main(String[] args) {
        int n = 20;
        int x = 3;
        int pow = 5;
//        System.out.println(getPrimes(n));
//        System.out.println(erathosfen(n));
        System.out.println(binaryPow(x, pow));
    }

    public static List<Integer> getPrimes(int n) {
        List<Integer> primes = new ArrayList<>();
        for(int i = 2; i * i <= n; i++) {
            while(n % i == 0) {
                primes.add(i);
                n /= i;
            }
        }
        if (n > 1) primes.add(n);
        return primes;
    }

    private static int binaryPow(int n, int pow) {
        int res = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) {
                res *= n;
            }
            n *= n;
            pow >>= 1;
        }
        return res;
    }
}
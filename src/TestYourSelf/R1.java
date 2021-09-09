package TestYourSelf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class R1 {
    public static void main(String[] args) {
        int n = 20;
        System.out.println(getPrimes(n));
//        System.out.println(erathosfen(n));
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
}
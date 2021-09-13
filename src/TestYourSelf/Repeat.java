package TestYourSelf;

import java.math.BigInteger;
import java.util.*;

public class Repeat {
    public static void main(String[] args) {
        int a = 12;
        int b = 2;
        int mod = 561;
        System.out.println(Arrays.toString(extEvklid(b, mod)));
        System.out.printf("inv(b) = %d\n", extEvklid(b, mod)[1]);

        int phi = phi(mod);
        int invB = powMod(b, phi - 1, mod);
        System.out.printf("inv(b) = %d\n", invB);

        BigInteger prime = BigInteger.TWO.pow(mod - 1);
        String ch = prime.equals(BigInteger.ONE) ? "==" : "!=";
        System.out.printf("%d простое - %s, phi(%d) = %d %s 1\n", mod, prime.equals(BigInteger.ONE), mod, prime, ch);
    }

    private static int[] extEvklid(int a, int b) {
        int[] result = new int[3];
        if (a == 0) {
            result[0] = b;
            result[2] = 1;
            return result;
        }
        result = extEvklid(b % a, a);
        int tmp = result[1];
        result[1] = result[2] - (b / a) * result[1];
        result[2] = tmp;
        return result;
    }

    private static int phi(int n) {
        int result = n;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                while (n % i == 0) n /= i;
                result -= result / i;
            }
        }
        if (n > 1) result -= result / n;
        return result;
    }

    private static int powMod(int n, int pow, int mod) {
        int result = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) result = (n * result) % mod;
            n = (n * n) % mod;
            pow >>= 1;
        }
        return result;
    }
}

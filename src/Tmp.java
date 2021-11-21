import java.util.*;

public class Tmp {
    private static int phi(int n) {
        int res = n;
        for (int i = 2; i <= n; i += 1) {
            if (n % i == 0) {
                while (n % i == 0) n /= i;
                res -= res / i;
            }
        }
        if (n > 1) res -= res / n;
        return res;
    }

    private static int pow(int n, int pow) {
        int res = 1;
        while(pow > 0) {
            if ((pow & 1) == 1) res *= n;
            n *= n;
            pow >>= 1;
        }
        return res;
    }

    private static int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }

    private static List<Integer> erat(int n) {
        boolean[] erat = new boolean[n + 1];
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i += 1) {
            if (erat[i]) continue;
            primes.add(i);
            for (int j = i; j <= n; j += i)
                erat[j] = true;
        }
        return primes;
    }

    private static int[] evklidExt(int a, int b) {
        int[] res = new int[3];
        if (a == 0) {
            res[0] = b;
            res[2] = 1;
            return res;
        }
        res = evklidExt(b % a, a);
        int tmp = res[1];
        res[1] = res[2] - (b / a) * res[1];
        res[2] = tmp;
        return res;
    }

    public static void main(String[] args) {
        int a = 41, b = 6;
        int[] res = evklidExt((int) 1e9 + 7, (int) 1e5);
    }
}

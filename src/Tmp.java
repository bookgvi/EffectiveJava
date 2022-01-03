import java.util.*;

public class Tmp {
    private static final int k = (int) 1e5 + 1;
    private static final int mod = (int) 1e9 + 7;
    private static final long[] pows = pows();

    private static long getHash(String str) {
        int len = str.length();
        long hash = 0;
        for (int i = 0; i < len; i += 1)
            hash = (hash + (str.charAt(i) - 'a' + 1) * pows[i]) % mod;
        return hash;
    }

    private static long[] pows() {
        int max = (int) 1e4 + 3;
        long[] pows = new long[max];
        pows[0] = 1;
        for (int i = 1; i < max; i += 1)
            pows[i] = pows[i - 1] * k % mod;
        return pows;
    }

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

    private static int modPow(int n, int pow, int mod) {
        int res = 1;
        while(pow > 0) {
            if ((pow & 1) == 1) res = res * n % mod;
            n = n * n % mod;
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
        String str1 = "melioration";
        String str2 = "demystify";
        long h1 = getHash(str1);
        long h2 = getHash(str2);
    }
}

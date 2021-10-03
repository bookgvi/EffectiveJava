package TestYourSelf;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class R4 {
    private static final int k = (int) 1e5 + 5;
    private static final int mod = (int) 1e9 + 7;
    private static final String firstChar = "a";
    private static final byte firstCharByte = firstChar.getBytes()[0];
    private static final String str = "abcocbab";
    private static final String ss = "b";
    private static final long[] pows = pows();
    private static final long[] prefixHashes = prefixHashes(str);

    public static void main(String[] args) {
        System.out.println(erat(21));
        System.out.println(str);
        System.out.println(rabbinKarp(ss));
    }

    private static List<Integer> rabbinKarp(String subStr) {
        int ssLen = subStr.length();
        List<Integer> indexes = new ArrayList<>();
        long ssHash = getHash(subStr);
        long prefixHash = 0;
        for (int i = 0; i + ssLen - 1 < prefixHashes.length; i += 1) {
            long hash = prefixHashes[i + ssLen - 1];
            if (i > 0) prefixHash = prefixHashes[i - 1];
            long invP = modPow(pows[i], phi(mod) - 1, mod);
//            long invP = evklidExt(pows[i], mod)[1];
            long calcHash = hash - prefixHash < 0
                    ? hash * invP % mod - prefixHash * invP % mod
                    : (hash - prefixHash) * invP % mod;
            if (ssHash == calcHash) indexes.add(i);
        }
        return indexes;
    }

    private static long[] pows() {
        int size = (int) 1e5;
        long[] pows = new long[size];
        pows[0] = 1;
        for (int i = 1; i < size; i += 1) {
            pows[i] = pows[i - 1] * k % mod;
        }
        return pows;
    }

    private static long[] prefixHashes(String str) {
        int len = str.length();
        long[] hashes = new long[len];
        byte[] strBytes = str.getBytes();
        hashes[0] = (strBytes[0] - firstCharByte + 1) * pows[0] % mod;
        for (int i = 1; i < len; i += 1) {
            hashes[i] = (hashes[i - 1] + (strBytes[i] - firstCharByte + 1) * pows[i]) % mod;
        }
        return hashes;
    }

    private static long getHash(String str) {
        int len = str.length();
        long hash = 0;
        byte[] strBytes = str.getBytes();
        for (int i = 0; i < len; i += 1) {
            hash = (hash + (strBytes[i] - firstCharByte + 1) * pows[i]) % mod;
        }
        return hash;
    }

    private static long modPow(long n, long pow, int mod) {
        long res = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) res = (res * n) % mod;
            n = (n * n) % mod;
            pow >>= 1;
        }
        return res;
    }

    private static long phi(long n) {
        long res = n;
        for (int i = 2; (long) i * i <= n; i += 1) {
            if (n % i == 0) {
                while (n % i == 0) n /= i;
                res -= res / i;
            }
        }
        if (n > 1) res -= res / n;
        return res;
    }

    private static long[] evklidExt(long a, long b) {
        long[] res = new long[3];
        if (a == 0) {
            res[0] = b;
            res[2] = 1;
            return res;
        }
        res = evklidExt(b % a, a);
        long tmp = res[1];
        res[1] = res[2] - (b / a) * res[1];
        res[2] = tmp;
        return res;
    }

    private static List<Integer> erat(int n) {
        List<Integer> primes = new ArrayList<>();
        boolean[] erat = new boolean[n + 1];
        for (int i = 2; i <= n; i += 1) {
            if (erat[i]) continue;
            primes.add(i);
            for (int j = 2 * i; j <= n; j += i) {
                erat[j] = true;
            }
        }
        return primes;
    }
}

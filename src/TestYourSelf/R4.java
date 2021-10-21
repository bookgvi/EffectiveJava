package TestYourSelf;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.*;

public class R4 {
    private static final int k = (int) 1e5 + 5;
    private static final int mod = (int) 1e9 + 7;
    private static final String firstChar = "a";
    private static final byte firstCharByte = firstChar.getBytes()[0];
    private static final String str = "abcocbabcdd";
    private static final String ss = "c";
    private static final long[] pows = pows();
    private static final long[] invP = invP();
    private static final long[] prefixHashes = prefixHashes(str);

    public static void main(String[] args) {
        System.out.println(erat(21));
        System.out.printf("%s <-- %s\n", str, ss);
        System.out.println(rabbinKarp(ss));
    }

    private static List<Integer> rabbinKarp(String subStr) {
        int lenSS = subStr.length();
        List<Integer> indexes = new ArrayList<>();
        long strHash = getHash(subStr);
        for (int i = 0; i + lenSS - 1 < prefixHashes.length; i += 1) {
            long calcHash = hash(prefixHashes, i, lenSS - 1);
            if (calcHash == strHash) indexes.add(i);
        }
        return indexes;
    }

    private static long getHash(String str) {
        int len = str.length();
        long hash = 0;
        byte[] strBytes = str.getBytes();
        for (int i = 0; i < len; i += 1)
            hash += (strBytes[i] - firstCharByte + 1) * pows[i] % mod;
        return hash;
    }

    private static long hash(long[] phs, int pos, int offset) {
        long strH = phs[pos + offset];
        long prefH = pos > 0 ? phs[pos - 1] : 0;
        strH = strH - prefH < 0 ? strH + mod : strH;
        return (strH - prefH) * invP[pos] % mod;
    }

    private static long[] prefixHashes(String str) {
        int len = str.length();
        long[] hashes = new long[len];
        byte[] strBytes = str.getBytes();
        hashes[0] = (strBytes[0] - firstCharByte + 1) * pows[0] % mod;
        for (int i = 1; i < len; i += 1)
            hashes[i] = hashes[i - 1] + (strBytes[i] - firstCharByte + 1) * pows[i] % mod;
        return hashes;
    }

    private static long[] pows() {
        int max = (int) 1e5 + 5;
        long[] pows = new long[max];
        pows[0] = 1;
        for (int i = 1; i < max; i += 1)
            pows[i] = pows[i - 1] * k % mod;
        return pows;
    }

    private static long[] invP() {
        int max = pows.length;
        long[] invP = new long[max];
        long phi = phi(mod) - 1;
        for (int i = 0; i < max; i += 1)
            invP[i] = modPow(pows[i], phi, mod);
        return invP;
    }

    private static long modPow(long n, long pow, int mod) {
        long res = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) res = res * n % mod;
            n = n * n % mod;
            pow >>= 1;
        }
        return res;
    }

    private static long phi(long n) {
        long res = n;
        for (int i = 2; (long) i * i <= n; i += 1) {
            if (n % i == 0) {
                while(n % i == 0) n /= i;
                res -= res / i;
            }
        }
        if (n > 1) res -= res / n;
        return res;
    }

    private static List<Integer> erat(int n) {
        List<Integer> primes = new ArrayList<>();
        boolean[] erat = new boolean[n + 1];
        for (int i = 2; i < n; i += 1) {
            if (erat[i]) continue;
            primes.add(i);
            for (int j = 2 * i; j < n; j += i)
                erat[j] = true;
        }
        return primes;
    }
}

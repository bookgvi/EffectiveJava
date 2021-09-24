package TestYourSelf;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class R1 {
    public static final int k = (int) 1e3 + 5;
    public static final int mod = (int) 1e9 + 7;
    public static final String str = "1012101";
    public static final String firstCh = "0";
    public static final byte firstChByte = firstCh.getBytes()[0];
    public static final long[] pows = pows();
    public static final long[] prefixHashes = prefixHashes(str);

    public static void main(String[] args) {
        String ss = "0";
        System.out.println(str);
        System.out.println(ss);
        System.out.println(rabbinKarp(ss));
    }

    private static List<Integer> rabbinKarp(String subStr) {
        int len = subStr.length();
        List<Integer> res = new ArrayList<>();
        List<Integer> res2 = new ArrayList<>();
        long ssHash = getHash(subStr);
        for (int i = 0; i + len - 1 < str.length(); i += 1) {
            long prefixHash = 0;
            long hash = prefixHashes[i + len - 1];
            if (i > 0) prefixHash = prefixHashes[i - 1];
            long calcSsHash = hash - prefixHash;
            if (calcSsHash < 0) calcSsHash *= -1;
            if (calcSsHash == ssHash * pows[i] % mod) res.add(i);
            long invP = modPow(pows[i], phi(mod) - 1, mod);
            long calcSsHash2 = hash - calcSsHash < 0
                    ? hash * invP % mod - prefixHash * invP % mod
                    : (hash - prefixHash) * invP % mod;
            if (calcSsHash2 == ssHash) res2.add(i);
        }
        return res;
    }

    private static long getHash(String str) {
        int len = str.length();
        long hash = 0;
        byte[] strBytes = str.getBytes();
        for (int i = 0; i < len; i += 1) {
            hash = (hash + (strBytes[i] - firstChByte + 1) * pows[i]) % mod;
        }
        return hash;
    }

    private static long[] prefixHashes(String str) {
        int len = str.length();
        long[] hashes = new long[len];
        byte[] strBytes = str.getBytes();
        hashes[0] = (strBytes[0] - firstChByte + 1) * pows[0] % mod;
        for (int i = 1; i < len; i += 1) {
            hashes[i] = (hashes[i - 1] + (strBytes[i] - firstChByte + 1) * pows[i]) % mod;
        }
        return hashes;
    }

    private static long[] pows() {
        int powsCount = (int) 1e5 + 5;
        long[] pows = new long[powsCount];
        pows[0] = 1;
        for (int i = 1; i < powsCount; i += 1) {
            pows[i] = (pows[i - 1] * k) % mod;
        }
        return pows;
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

    private static long phi(int n) {
        int res = n;
        for (int i = 2; i * i <= n; i += 1) {
            if (n % i == 0) {
                while (n % i == 0) n /= i;
                res -= res / i;
            }
        }
        if (n > 1) res -= res / n;
        return res;
    }

}
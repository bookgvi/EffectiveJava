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
        String ss = "2";
        System.out.println(ss);
        System.out.println(str);
        System.out.println(rabbinKarp(ss));
    }

    private static List<Integer> rabbinKarp(String subStr) {
        int len = subStr.length();
        List<Integer> indexes = new ArrayList<>();
        long hashSubStr = getHash(subStr);
        long prefixHash = 0;
        for (int i = 0; i + len - 1 < prefixHashes.length; i += 1) {
            long hash = prefixHashes[i + len - 1];
            if (i > 0) prefixHash = prefixHashes[i - 1];
            long invP = modPow(pows[i], phi(mod) - 1, mod);
            long calcHash = hash - prefixHash < 0
                    ? hash * invP % mod - prefixHash * invP % mod
                    : (hash - prefixHash) * invP % mod;
            if (hashSubStr == calcHash) indexes.add(i);
        }
        return indexes;
    }

    private static long getHash(String str) {
        int len = str.length();
        byte[] strByte = str.getBytes();
        long hash = 0;
        for (int i = 0; i < len; i += 1) {
            hash = (hash + (strByte[i] - firstChByte + 1) * pows[i]) % mod;
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
        int size = (int) 1e5 + 5;
        long[] pows = new long[size];
        pows[0] = 1;
        for (int i = 1; i < size; i += 1) {
            pows[i] = pows[i - 1] * k % mod;
        }
        return pows;
    }

    private static long modPow(long n, long pow, int mod) {
        long res = 1;
        while (pow != 0) {
            if ((pow & 1) == 1) res = (res * n) % mod;
            n = (n * n) % mod;
            pow >>= 1;
        }
        return res;
    }

    private static long phi(long n) {
        long res = n;
        for (long i = 2; i * i <= n; i += 1) {
            if (n % i == 0) {
                while (n % i == 0) n /= i;
                res -= res / i;
            }
        }
        if (n > 1) res -= res / n;
        return res;
    }
}
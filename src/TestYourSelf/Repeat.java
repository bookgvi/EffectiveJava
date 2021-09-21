package TestYourSelf;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Repeat {
    private static final int k = (int) 1e5 + 5;
    private static final int mod = (int) 1e9 + 7;
    private static final String firstChar = "a";
    private static final byte firstCharByte = firstChar.getBytes()[0];
    private static final long[] pows = pows();
    private static final String str = "if you want to fuck for funny - fuck yourself and keep the money";
    private static final long[] prefixHashes = prefixHashes(str);

    public static void main(String[] args) {
        String ss = "f";
        System.out.println(rabbinKarp(ss));
    }

    private static List<Integer> rabbinKarp(String subStr) {
        List<Integer> res = new ArrayList<>();
        int ssLen = subStr.length();
        long ssHash = getHash(subStr);
        for (int i = 0; i + ssLen - 1 < prefixHashes.length; i += 1) {
            long prefHash = 0;
            long hash = prefixHashes[i + ssLen - 1];
            if (i > 0) prefHash = prefixHashes[i - 1];
            long invPowsI = modPow(pows[i], phi(mod) - 1, mod);
            long calcHash = hash - prefHash < 0
                    ? hash * invPowsI % mod - prefHash * invPowsI % mod
                    : (hash - prefHash) * invPowsI % mod;
            if (calcHash == ssHash) res.add(i);
        }
        return res;
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

    private static long getHash(String str) {
        int len = str.length();
        byte[] strBytes = str.getBytes();
        long hash = 0;
        for (int i = 0; i < len; i += 1) {
            hash = (hash + (strBytes[i] - firstCharByte + 1) * pows[i]) % mod;
        }
        return hash;
    }

    private static long[] prefixHashes(String str) {
        int len = str.length();
        byte[] strBytes = str.getBytes();
        long[] hashes = new long[len];
        hashes[0] = (strBytes[0] - firstCharByte + 1) * pows[0] % mod;
        for (int i = 1; i < len; i += 1) {
            hashes[i] = ((strBytes[i] - firstCharByte + 1) * pows[i] + hashes[i - 1]) % mod;
        }
        return hashes;
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
}

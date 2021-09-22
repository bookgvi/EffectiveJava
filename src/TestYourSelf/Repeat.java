package TestYourSelf;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Repeat {
    private static final int k = (int) 1e5 + 5;
    private static final int mod = (int) 1e9 + 7;
    private static final String firstChar = "0";
    private static final byte firstCharByte = firstChar.getBytes()[0];
    private static final long[] pows = pows();
    private static final String str = "01234560136123";
    private static final long[] prefixHashes = prefixHashes(str);

    public static void main(String[] args) {
        String ss = "8";
        System.out.printf("\"%s\", substring \"%s\" is in %s indexes\n", str, ss, rabinKarp(ss));
    }

    private static List<Integer> rabinKarp(String subStr) {
        int ssLen = subStr.length();
        List<Integer> indexes = new ArrayList<>();
        long ssHash = getHash(subStr);
        for (int i = 0; i + ssLen - 1 < prefixHashes.length; i += 1) {
            long prefixHash = 0;
            long hash = prefixHashes[i + ssLen - 1];
            if (i > 0) prefixHash = prefixHashes[i - 1];
            long invPH = modPow(pows[i], phi(mod) - 1, mod);
            long calcHash = hash - prefixHash > 0
                    ? (hash - prefixHash) * invPH % mod
                    : hash * invPH % mod - prefixHash * invPH % mod;
            if (ssHash == calcHash) indexes.add(i);
        }
        return indexes;
    }

    private static long[] pows() {
        long[] pows = new long[k];
        pows[0] = 1;
        for(int i = 1; i < k; i += 1) {
            pows[i] = pows[i - 1] * k % mod;
        }
        return pows;
    }

    private static long[] prefixHashes(String str) {
        int len = str.length();
        long[] hashes = new long[len];
        byte[] strBytes = str.getBytes();
        hashes[0] = ((strBytes[0] - firstCharByte + 1) * pows[0]) % mod;
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

    private static long phi(long n) {
        long res = n;
        for (long i = 2; i * i < n; i += 1) {
            if (n % i == 0) {
                while (n % i == 0) n /= i;
                res -= res / i;
            }
        }
        if (n > 0) res -= res / n;
        return res;
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

    private static long[] evklidExt(int a, int b) {
        long[] res = new long[3];
        if (a == 0) {
            res[0] = b;
            res[2] = 1;
            return res;
        }
        res = evklidExt(b % a, a);
        long tmp = res[1];
        res[1] = res[2] - (b / a) * res[1];
        res[2] = res[1];
        return res;
    }
}

package Algos.String;

import java.util.*;

public class DeleteSomeSubString {
    private static final int maxP = (int) 1e5 + 5;
    private static final int k = (int) 1e5 + 5;
    private static final int mod = (int) 1e9 + 7;
    private static final int firstCharByte = "a".getBytes()[0];
    private static final long[] pows = pows();
    private static final long[] invP = invP();

    private static String processString(String str, String ss) {
        List<Integer> indexes = new ArrayList<>();
        int len = str.length(), lenSS = ss.length();
        long strHash = getHash(ss);
        long[] phs = phs(str);
        for (int i = 0; i + lenSS - 1 < len; i += 1) {
            long calcHash =  hash(phs, i, lenSS - 1);
            if (calcHash == strHash) indexes.add(i);
        }
        StringBuilder res = new StringBuilder(str);
        for (int index : indexes) {
            int dl = len - res.length();
            res.delete(index - dl, lenSS + index - dl);
            res.insert(index - dl, "...");
        }
        return res.toString();
    }

    private static long getHash(String str) {
        int len = str.length();
        long hash = 0;
        for (int i = 0; i < len; i += 1)
            hash = (hash + (str.charAt(i) - firstCharByte + 1) * pows[i]) % mod;
        return hash;
    }

    private static long hash(long[] phs, int pos, int off) {
        long strH = phs[pos + off];
        long prefH = pos > 0 ? phs[pos - 1] : 0;
        strH = strH - prefH < 0 ? strH + mod : strH;
        return (strH - prefH) * invP[pos] % mod;
    }

    private static long[] phs(String str) {
        int len = str.length();
        long[] hashes = new long[len];
        byte[] strBytes = str.getBytes();
        hashes[0] = (strBytes[0] - firstCharByte + 1) * pows[0] % mod;
        for (int i = 1; i < len; i += 1)
            hashes[i] = hashes[i - 1] + (strBytes[i] - firstCharByte + 1) * pows[i] % mod;
        return hashes;
    }

    private static long[] pows() {
        long[] pows = new long[maxP];
        pows[0] = 1;
        for (int i = 1; i < maxP; i += 1)
            pows[i] = pows[i - 1] * k % mod;
        return pows;
    }

    private static long[] invP() {
        long[] invP = new long[maxP];
        long phi = phi(mod) - 1;
        for (int i = 0; i < maxP; i += 1)
            invP[i] = modPow(pows[i], phi, mod);
        return invP;
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

    private static long modPow(long n, long pow, int mod) {
        long res = 1;
        while(pow > 0) {
            if ((pow & 1) == 1) res = res * n % mod;
            n = n * n % mod;
            pow >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        String str = "aabcdfgdfgabcasd";
        String ss = "abc";
        String res = processString(str, ss);
        System.out.printf("%s -> %s", str, res);
    }
}

package TestYourSelf.Hashes;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class RabinKarp {
    private static int k = (int) 1e5 + 5;
    private static int mod = (int) 1e9 + 7;
    private static long[] pows = pows();
    private static long[] invP = invP();

    private static String firstChar = "a";
    private static int firstCharByte = firstChar.getBytes(StandardCharsets.UTF_8)[0];

    public static void main(String[] args) {
        String str = "absabbssba";
        String ss = "absabbssba";
        List<Integer> indexes = rabinKarp(str, ss);
        System.out.printf("%s\t<-\t%s\n", str, ss);
        System.out.println(indexes);
    }

    private static List<Integer> rabinKarp(String str, String ss) {
        List<Integer> indexes = new ArrayList<>();
        int lenStr = str.length(), ssLen = ss.length();
        long strH = getHash(ss);
        long[] phs = prefixHash(str);
        for (int i = 0; i + ssLen - 1 < lenStr; i += 1) {
            long calcHash = hash(phs, i, ssLen - 1);
            if (strH == calcHash) indexes.add(i);
        }
        return indexes;
    }

    private static long getHash(String str) {
        int len = str.length();
        byte[] strBytes = str.getBytes();
        long hash = 0;
        for (int i = 0; i < len; i += 1)
            hash = (hash + (strBytes[i] - firstCharByte + 1) * pows[i]) % mod;
        return hash;
    }

    private static long hash(long[] phs, int pos, int off) {
        long strH = phs[pos + off];
        long prefH = pos > 0 ? phs[pos - 1] : 0;
        strH = strH - prefH < 0 ? strH + mod : strH;
        return (strH - prefH) * invP[pos] % mod;
    }

    private static long[] prefixHash(String str) {
        int len = str.length();
        long[] hashes = new long[len];
        byte[] strBytes = str.getBytes();
        hashes[0] = (strBytes[0] - firstCharByte + 1) * pows[0] % mod;
        for (int i = 1; i < len; i += 1)
            hashes[i] = hashes[i - 1] + (strBytes[i] - firstCharByte + 1) * pows[i] % mod;
        return hashes;
    }

    private static long[] pows() {
        int max = (int) 1e5;
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

    private static long modPow(long n, long pow, int mod) {
        long res = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) res = res * n % mod;
            n = n * n % mod;
            pow >>= 1;
        }
        return res;
    }
}

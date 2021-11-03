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
        int lenSS = ss.length(), len = str.length();
        long[] phs = prefixHashes(str);
        long hash = getHash(ss);
        for (int i = 0; i + lenSS - 1< len; i += 1) {
            long calcHash = hash(phs, i, lenSS - 1);
            if (calcHash == hash) indexes.add(i);
        }
        return indexes;
    }

    private static long getHash(String str) {
        byte[] strBytes = str.getBytes();
        long hash = 0;
        for (int i = 0, len = str.length(); i < len; i += 1)
            hash = (hash + (strBytes[i] - firstCharByte + 1) * pows[i]) % mod;
        return hash;
    }

    private static long hash(long[] phs, int pos, int len) {
        long strH = phs[pos + len];
        long prefH = pos > 0 ? phs[pos - 1] : 0;
        strH = strH < prefH ? strH + mod : strH;
        return (strH - prefH) * invP[pos];
    }

    private static long[] prefixHashes(String str) {
        int len = str.length();
        long[] hashes = new long[len];
        byte[] strBytes = str.getBytes();
        hashes[0] = (strBytes[0] - firstCharByte + 1) * pows[0];
        for (int i = 1; i < len; i += 1)
            hashes[i] = (hashes[i - 1] + (strBytes[i] - firstCharByte + 1) * pows[i]) % mod;
        return hashes;
    }

    private static long[] invP() {
        int max = pows.length;
        long[] invP = new long[max];
        long phi = phi(mod) - 1;
        for (int i = 0; i < max; i += 1)
            invP[i] = modPow(pows[i], phi, mod);
        return invP;
    }

    private static long[] pows() {
        int max = (int) 1e5;
        long[] pows = new long[max];
        pows[0] = 1;
        for (int i = 1; i < max; i += 1)
            pows[i] = pows[i - 1] * k % mod;
        return pows;
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
}

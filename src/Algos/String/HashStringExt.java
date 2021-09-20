package Algos.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HashStringExt {
    private static final int k = (int) 31;
    private static final long mod = (long) 1e5 + 7;
    private static final long[] powsK = pows(k);
    private static final String first = "0";
    private static final byte firstInABC = first.getBytes()[0];

    public static void main(String[] args) {
        String str = "0123451230";
        String ss1 = "3";
        long[] preHashes = preHashes(str);
        System.out.println(Arrays.toString(preHashes));
        System.out.println(getHash(ss1));
        System.out.printf("Подcтрока %s в строке %s находится в позициях %s", ss1, str, rabbinKarp(ss1, preHashes));
    }


    private static List<Integer> rabbinKarp(String str, long[] preHashes) {
        List<Integer> res = new ArrayList<>();
        int strL = str.length();
        long prefHash = 0;
        long strH = getHash(str);
        for (int i = 0; i < preHashes.length - strL + 1; i += 1) {
            long strHash = preHashes[i + strL - 1];
            if (i > 0) prefHash = preHashes[i - 1];
            if (strHash == prefHash + powsK[i] * strH) {
                res.add(i);
            }
        }
        return res;
    }

    private static long subHash(long prefH, long strH, int prefL) {
        long[] evk = evklidExt(powsK[prefL], mod);
        long invP = evk[1];
        return ((strH - prefH) * invP) % mod;
    }

    private static long getHash(String str) {
        int len = str.length();
        byte[] strBytes = str.getBytes();
        long hash = 0;
        for (int i = 0; i < len; i += 1) {
            hash = ((strBytes[i] - firstInABC + 1) * powsK[i] + hash); // % mod;
        }
        return hash;
    }

    private static long[] preHashes(String str) {
        int len = str.length();
        long[] preHashes = new long[len];
        byte[] strBytes = str.getBytes();
        preHashes[0] = ((strBytes[0] - firstInABC + 1) * powsK[0]); // % mod;
        for (int i = 1; i < len; i += 1) {
            preHashes[i] = ((strBytes[i] - firstInABC + 1) * powsK[i] + preHashes[i - 1]); // % mod;
        }
        return preHashes;
    }

    private static long[] pows(int k) {
        int count = (int) 1e5 + 5;
        long[] pows = new long[count];
        pows[0] = 1;
        for (int i = 1; i < count; i += 1) {
            pows[i] = (pows[i - 1] * k); // % mod;
        }
        return pows;
    }

    private static long modPow(long n, long pow, long mod) {
        long res = 1;
        while (pow != 0) {
            if ((pow & 1) == 1) res = (res * n) % mod;
            n = (n * n) % mod;
            pow >>= 1;
        }
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

    private static long phi(long n) {
        long res = n;
        for (long i = 2; i * i <= n; i += 1) {
            if (n % i == 0) {
                while (n % i == 0) n /= i;
                res -= res / i;
            }
        }
        if (res > 1) res -= res / n;
        return res;
    }
}
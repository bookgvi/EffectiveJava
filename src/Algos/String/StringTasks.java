package Algos.String;

import java.util.ArrayList;
import java.util.List;

public class StringTasks {
    private static final int k = (int) 1e5 + 5;
    private static final int mod = (int) 1e9 + 7;
    private static final String str = "abcttabcdd";
    private static final String subStr = "d";
    private static final String firstChar = "a";
    private static final byte firstCharByte = firstChar.getBytes()[0];
    private static final long[] pows = pows();
    private static final long[] invP = invP();
    private static final long[] prefixHashes = prefixHashes(str);

    public static void main(String[] args) {
        System.out.println(str);
        System.out.println(subStr);
        List<Integer> indexes = rabbinKarp(subStr);
        System.out.println(indexes);
        int compare = compareStrings(0, 5, subStr.length());
        System.out.printf("%d, %d, %b\n", compare, subStr.length(), compare == subStr.length());
    }

    private static int compareStrings(int startIndex1, int startIndex2, int len) {
        int k = 0;
        for(int i = len / 2; i > 0; i /= 2) {
            while(k + i < len && hash(startIndex1, k + i) == hash(startIndex2, k + i)) k += i;
        }
        return k + 1;
    }

    private static long hash(int startIndex, int mid) {
        long hash = prefixHashes[startIndex + mid];
        long prefix = startIndex > 0 ? prefixHashes[startIndex - 1] : 0;
        if (hash - prefix < 0) hash += mod;
        return (hash - prefix) * invP[startIndex] % mod;
    }

    private static List<Integer> rabbinKarp(String subStr) {
        int ssLen = subStr.length();
        List<Integer> indexes = new ArrayList<>();
        long ssHash = getHash(subStr), prefix = 0;
        for(int i = 0; i + ssLen - 1 < prefixHashes.length; i += 1) {
            long hash = prefixHashes[i + ssLen - 1];
            if (i > 0) prefix = prefixHashes[i - 1];
            if(hash - prefix < 0) hash += mod;
            long calcHash = (hash - prefix) * invP[i] % mod;
            if (ssHash == calcHash) indexes.add(i);
        }
        return indexes;
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
        long hash = 0;
        int len = str.length();
        byte[] strBytes = str.getBytes();
        for (int i = 0; i < len; i += 1) {
            hash = (hash + (strBytes[i] - firstCharByte + 1) * pows[i]) % mod;
        }
        return hash;
    }

    private static long[] invP() {
        int len = pows.length;
        long[] invP = new long[len];
        long phi = phi(mod) - 1;
        for (int i = 0; i < len; i += 1) {
            invP[i] = modPow(pows[i], phi, mod);
        }
        return invP;
    }

    private static long[] pows() {
        final int MAX_VALUE = (int) 1e5 + 5;
        long[] pows = new long[MAX_VALUE];
        pows[0] = 1;
        for (int i = 1; i < MAX_VALUE; i += 1) {
            pows[i] = pows[i - 1] * k % mod;
        }
        return pows;
    }

    private static long modPow(long n, long pow, int mod) {
        long res = 1;
        while(pow > 0) {
            if ((pow & 1) == 1) res = n * res % mod;
            n = n * n % mod;
            pow >>= 1;
        }
        return res;
    }

    private static long phi(long n) {
        long res = n;
        for(int i = 2; (long) i * i <= n; i += 1) {
            if (n % i == 0) {
                while (n % i == 0) n /= i;
                res -= res / i;
            }
        }
        if (n > 1) res -= res / n;
        return res;
    }
}

package Algos.String;

public class Anagrams {
    private static final int k = (int) 1e5 + 5;
    private static final int mod = (int) 1e7 + 9;
    private static final String firstChar = "a";
    private static final byte firstCharCode = firstChar.getBytes()[0];
    private static final String str = "abcdefabcdabef";
    private static final long[] pows = pows();
    private static final long[] prefixHashes = prefixHashes(str);

    public static void main(String[] args) {
        String s1 = "abcabcabcabcabc";
        String s2 = "bacbacbacbacbac";
        String s3 = "cabcabcabacbacb";
        String s4 = new StringBuilder(s1).reverse().toString();

        long h1 = getXorHash(s1);
        long h2 = getXorHash(s2);
        long h3 = getXorHash(s3);
        long h4 = getXorHash(s4);
        System.out.printf("%d, %d, %d, %d\n", h1, h2, h3, h4);
    }

    private static long getXorHash(String str) {
        int len = str.length();
        byte[] strBytes = str.getBytes();
        long pow = pows[(int) 1e2];
        long hash = (strBytes[0] - firstCharCode + 1) * pow % mod;
        for (int i = 1; i < len; i += 1) {
            hash = (hash ^ (strBytes[i] - firstCharCode + 1) * pow) % mod;
        }
        return hash;
    }

    private static long getHash(String str) {
        int len = str.length();
        byte[] strBytes = str.getBytes();
        long hash = 0;
        for (int i = 0; i < len; i += 1) {
            hash = (hash + (strBytes[i] - firstCharCode + 1) * pows[i]) % mod;
        }
        return hash;
    }

    private static long[] prefixHashes(String str) {
        int len = str.length();
        long[] hashes = new long[len];
        byte[] strBytes = str.getBytes();
        hashes[0] = (strBytes[0] - firstCharCode + 1) * pows[0] % mod;
        for (int i = 1; i < len; i += 1) {
            hashes[i] = (hashes[i - 1] + (strBytes[i] - firstCharCode + 1) * pows[i]) % mod;
        }
        return hashes;
    }

    private static long[] pows() {
        int max = (int) 1e5 + 5;
        long[] pows = new long[max];
        pows[0] = 1;
        for (int i = 1; i < max; i += 1) {
            pows[i] = pows[i - 1] * k % mod;
        }
        return pows;
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

    private static long modPow(long n, long pow, int mod) {
        long res = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) res = (res * n) % mod;
            n = (n * n) % mod;
            pow >>= 1;
        }
        return res;
    }
}

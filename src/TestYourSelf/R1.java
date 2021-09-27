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
        String ss = "1";
        System.out.println(str);
        System.out.println(rabbinKarp(ss));

        System.out.println(Arrays.toString(zFunc(str)));
        System.out.println(Arrays.toString(piFunc(str)));
        System.out.println(Arrays.toString(piFuncExt(str)));
        System.out.println(Arrays.toString(manacher(str)));
    }

    private static List<Integer> rabbinKarp(String subStr) {
        List<Integer> indexes = new ArrayList<>();
        int lenSubStr = subStr.length();
        long hashSubStr = getHash(subStr);
        long prefixHash = 0;
        for (int i = 0; i + lenSubStr - 1 < prefixHashes.length; i += 1) {
            long hash = prefixHashes[i + lenSubStr - 1];
            if (i > 0) prefixHash = prefixHashes[i - 1];
            long invP = modPow(pows[i], phi(mod) - 1, mod);
            long calcHash = (hash - prefixHash) < 0 ? hash * invP % mod - prefixHash * invP % mod : (hash - prefixHash) * invP % mod;
            if (hashSubStr == calcHash) indexes.add(i);
        }
        return indexes;
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

    private static long getHash(String str) {
        int len = str.length();
        long hash = 0;
        byte[] strBytes = str.getBytes();
        for (int i = 0; i < len; i += 1) {
            hash = (hash + (strBytes[i] - firstChByte + 1) * pows[i]) % mod;
        }
        return hash;
    }

    private static long[] pows() {
        int maxSize = (int) 1e5 + 5;
        long[] pows = new long[maxSize];
        pows[0] = 1;
        for (int i = 1; i < maxSize; i += 1) {
            pows[i] = pows[i - 1] * k % mod;
        }
        return pows;
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
        if (n > 1) res -= res / n;
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

    private static int[] zFunc(String str) {
        int len = str.length();
        int[] z = new int[len];
        for (int i = 1, l = 0, r = 0; i < len; i += 1) {
            if (i <= r) z[i] = Math.min(z[i - l], r - i + 1);
            while (i + z[i] < len && str.charAt(i + z[i]) == str.charAt(z[i])) z[i] += 1;
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        return z;
    }

    private static int[] piFunc(String str) {
        int len = str.length();
        int[] pi = new int[len];
        for (int i = 0; i < len; i += 1) {
            for (int j = 0; j < i; j += 1) {
                String ss1 = str.substring(0, j + 1).intern();
                String ss2 = str.substring(i - j, i + 1).intern();
                if (ss1 == ss2) pi[i] = j + 1;
            }
        }
        return pi;
    }

    private static int[] piFuncExt(String str) {
        int len = str.length();
        int[] pi = new int[len];
        for (int i = 1; i < len; i += 1) {
            int j = pi[i - 1];
            while (j > 0 && str.charAt(i) != str.charAt(j)) j = pi[j - 1];
            if (str.charAt(i) == str.charAt(j)) pi[i] = j + 1;
        }
        return pi;
    }

    private static int[] manacher(String str) {
        int len = str.length();
        int[] m = new int[len];
        for (int i = 1, l = 0, r = 0; i < len; i += 1) {
            if (i < r) m[i] = Math.min(m[l + r - i], r - i + 1);
            while (i - m[i] >= 0 && i + m[i] < len && str.charAt(i - m[i]) == str.charAt(i + m[i])) m[i] += 1;
            if (i + m[i] - 1 > r) {
                l = i - m[i] + 1;
                r = i + m[i] - 1;
            }
        }
        return m;
    }
}
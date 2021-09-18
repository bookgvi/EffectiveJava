package Algos.String;

import java.util.Arrays;

public class HashString {
    private static final int powsCount = (int) 1e5 + 5;
    private static final int k = 31;
    private static final int mod = (int) 1e3 + 7;
    private static final int[] pows = pows();
    private static final String first = "0";
    private static final byte a = first.getBytes()[0];

    public static void main(String[] args) {
        String str = "01234567123998761230";
        int[] prefixHashes = prefixHashes(str);
        String s1 = "0";
        String s2 = "123";

        System.out.printf("%d, %d, %d, %d, %d\n",
                hash("0"),
                hash("01"),
                hash("012"),
                hash("0123"),
                hash("01234")
        );
        System.out.println(Arrays.toString(prefixHashes));
        int hashS1AndS2 = concat(s1, s2);
        int hash0123 = hash(s1 + s2);
        int hash0123fromPrefixHashes = prefixHashes[(s1 + s2).length()];
        System.out.printf("%d, %d, %d\n", hashS1AndS2, hash0123, hash0123fromPrefixHashes);
        int subStrHash = subStr(s1, s1 + s2);
        System.out.printf("%d, %d\n", hash(s2), subStrHash);
        System.out.printf("%d, %d\n", hash(s1 + s2), subStrInStr(prefixHashes, 0, 3));
        System.out.println("Finish");
    }

    private static int subStrInStr(int[] prefixHashes, int l, int r) {
        if (l == 0) {
            return prefixHashes[r];
        }
        int invL = modPow(pows[l], phi(mod) - 1, mod);
        return (prefixHashes[r] - prefixHashes[l - 1]) * invL % mod;
    }

    private static int subStr(String prefix, String str) {
        int hPrefix = hash(prefix);
        int hStr = hash(str);
        int[] diofant = evklidExt(pows[prefix.length()], mod);
        int invP1 = diofant[1];
        int invP2 = modPow(pows[prefix.length()], phi(mod) - 1, mod);
        return (hStr - hPrefix) * invP2 % mod;
    }

    private static int concat(String s1, String s2) {
        int h1 = hash(s1);
        int h2 = hash(s2);
        return h1 + pows[s1.length()] * h2 % mod;
    }

    private static int hash(String str) {
        int len = str.length();
        int hash = 0;
        byte[] strBytes = str.getBytes();
        for (int i = 0; i < len; i += 1) {
            int ch = strBytes[i] - a + 1;
            hash = (ch * pows[i] + hash) % mod;
        }
        return hash;
    }

    private static int[] prefixHashes(String str) {
        int len = str.length();
        int[] hashes = new int[len];
        byte[] strBytes = str.getBytes();
        hashes[0] = (strBytes[0] - a + 1) * pows[0] % mod;
        for (int i = 1; i < len; i += 1) {
            hashes[i] = ((strBytes[i] - a + 1) * pows[i] + hashes[i - 1]) % mod;
        }
        return hashes;
    }

    private static int[] pows() {
        int[] pows = new int[powsCount];
        pows[0] = 1;
        for (int i = 1; i < powsCount; i += 1) {
            pows[i] = (pows[i - 1] * k) % mod;
        }
        return pows;
    }

    private static int[] evklidExt(int a, int b) {
        int[] res = new int[3];
        if (a == 0) {
            res[0] = b;
            res[2] = 1;
            return res;
        }
        res = evklidExt(b % a, a);
        int tmp = res[1];
        res[1] = res[2] - (b / a) * res[1];
        res[2] = tmp;
        return res;
    }

    private static int modPow(int n, int pow, int mod) {
        int res = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) res = (res * n) % mod;
            pow >>= 1;
            n = (n * n) % mod;
        }
        return res;
    }

    private static int phi(int n) {
        int res = n;
        for (int i = 2; i * i <= n; i += 1) {
            if (n % i == 0) {
                while (n % i == 0) n /= i;
                res -= res / i;
            }
        }
        if (n > 1) res -= res / n;
        return res;
    }
}

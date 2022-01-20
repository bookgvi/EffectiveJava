package TestYourSelf.Tasks.FindSubString;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class RabinKarp {
    private static final int k = (int) 1e5 + 5;
    private static final int mod = (int) 1e9 + 7;
    private static final long[] pows = pows();
    private static final long[] invP = invP();

    public static void main(String[] args) {
        String str = "abracadabra";
        String ss = "racadabr";

        List<Integer> index = searchSubStr(str, ss);
        System.out.println(str);
        System.out.println(ss);
        System.out.println(index);
    }

    private static List<Integer> searchSubStr(String str, String ss) {
        List<Integer> indexes = new ArrayList<>();
        long[] phs = prefixHashes(str);
        long strH = getHash(ss);
        int lenSS = ss.length(), len = str.length();
        if (lenSS > 0) {
            for (int i = 0; i + lenSS - 1 < len; i += 1) {
                long calcHash = hash(phs, i, lenSS - 1);
                if (calcHash == strH) indexes.add(i);
            }
        }
        return indexes;
    }

    private static long getHash(String str) {
        int len = str.length();
        long hash = 0;
        for (int i = 0; i < len; i += 1)
            hash = (hash + (str.charAt(i) - 'a' + 1) * pows[i]) % mod;
        return hash;
    }

    private static long hash(long[] phs, int pos, int off) {
        long strH = phs[pos + off];
        long prefH = pos > 0 ? phs[pos - 1] : 0;
        strH = strH - prefH < 0 ? strH + mod : strH;
        return (strH - prefH) * invP[pos] % mod;
    }

    private static long[] prefixHashes(String str) {
        int len = str.length();
        long[] hashes = new long[len];
        hashes[0] = (str.charAt(0) - 'a' + 1) * pows[0] % mod;
        for (int i = 1; i < len; i += 1)
            hashes[i] = hashes[i - 1] + (str.charAt(i) - 'a' + 1) * pows[i] % mod;
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

    private static long modPow(long n, long pow, int mod) {
        long res = 1;
        while(pow > 0) {
            if ((pow & 1) == 1) res = res * n % mod;
            n = n * n % mod;
            pow >>= 1;
        }
        return res;
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
}

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
        String ss = "abracadabra";

        List<Integer> index = searchSubStr(str, ss);
        System.out.println(str);
        System.out.println(ss);
        System.out.println(index);
    }

    private static List<Integer> searchSubStr(String str, String subStr) {
        int lenStr = str.length(), lenSS = subStr.length();
        List<Integer> index = new ArrayList<>();
        long ssHash = getHash(subStr);
        long[] prefH = prefixHashes(str);
        for (int i = 0; i + lenSS - 1 < lenStr; i += 1) {
            long calcHash = hash(prefH, i, lenSS - 1);
            if (ssHash == calcHash) index.add(i);
        }
        return index;
    }

    private static long getHash(String str) {
        int len = str.length();
        byte[] strBytes = str.getBytes();
        long hash = 0;
        for (int i = 0; i < len; i += 1)
            hash += strBytes[i] * pows[i] % mod;
        return hash;
    }

    private static long hash(long[] phs, int pos, int offset) {
        long strH = phs[pos + offset];
        long prefH = pos > 0 ? phs[pos - 1] : 0;
        strH = strH - prefH < 0 ? strH + mod : strH;
        return (strH - prefH) * invP[pos];
    }

    private static long[] prefixHashes(String str) {
        int len = str.length();
        long[] hashes = new long[len];
        byte[] strBytes = str.getBytes();
        hashes[0] = strBytes[0] * pows[0] % mod;
        for (int i = 1; i < len; i += 1)
            hashes[i] = hashes[i - 1] + strBytes[i] * pows[i] % mod;
        return hashes;
    }

    private static long[] pows() {
        long[] pows = new long[k];
        pows[0] = 1;
        for (int i = 1; i < k; i += 1)
            pows[i] = pows[i - 1] * k % mod;
        return pows;
    }

    private static long[] invP() {
        long[] invP = new long[k];
        long phi = phi(mod) - 1;
        for (int i = 0; i < k; i+= 1)
            invP[i] = modPow(pows[i], phi, mod);
        return invP;
    }

    private static long modPow(long n, long pow, long mod) {
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
                while(n % i == 0) n /= i;
                res -= res / i;
            }
        }
        if (n > 1) res -= res / n;
        return res;
    }
}

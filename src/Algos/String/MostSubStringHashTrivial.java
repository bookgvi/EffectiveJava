package Algos.String;

import java.util.*;
import java.util.stream.*;

public class MostSubStringHashTrivial {
    private static final int k = (int) 1e5 + 5;
    private static final int mod = (int) 1e9 + 7;
    private static final String firstChar = "A";
    private static final byte firstCharByte = firstChar.getBytes()[0];

    private static final String str1 = "VOTEFORTHEGREATALBANIAFORYOUVOTEFORTHEGREATALBANIAFORYOUVOTEFORTHEGREATALBANIAFORYOUVOTEFORTHEGREATALBANIAFORYOUVOTEFORTHEGREATALBANIAFORYOUVOTEFORTHEGREATALBANIAFORYOUVOTEFORTHEGREATALBANIAFORYOUVOTEFORTHEGREATALBANIAFORYOUVOTEFORTHEGREATALBANIAFORYOUVOTEFORTHEGREATALBANIAFORYOU";
    private static final String str2 = "CHOOSETHEGREATALBANIANFUTURECHOOSETHEGREATALBANIANFUTURECHOOSETHEGREATALBANIANFUTURECHOOSETHEGREATALBANIANFUTURECHOOSETHEGREATALBANIANFUTURECHOOSETHEGREATALBANIANFUTURECHOOSETHEGREATALBANIANFUTURECHOOSETHEGREATALBANIANFUTURECHOOSETHEGREATALBANIANFUTURECHOOSETHEGREATALBANIANFUTURE";

    private static final long[] pows = pows();
    private static final long[] invP = invP();

    public static void main(String[] args) {
        long[] phs1 = prefixHashes(str1);
        long[] phs2 = prefixHashes(str2);
        int len = str1.length();
        long startTime = System.nanoTime();
        String res = hashSearch(phs1, phs2, len);
        long endTime = System.nanoTime();
        System.out.printf("%.8f\n", (endTime - startTime) / 1e9);
        System.out.println(res);
    }

    private static String hashSearch(long[] phs1, long[] phs2, int len) {
        int l = 0, r = len, pos = -1;
        while (r - l > 1) {
            int mid = (r + l) / 2;
            int p = -1;
            for (int i = 0; i + mid < len; i += 1) {
                for (int j = 0; j + mid < len; j += 1) {
                    if (binSearch(phs1, i, phs2, j, mid)) {
                        p = i;
                        break;
                    }
                }
            }
            if (p >= 0) {
                l = mid;
                pos = p;
            } else {
                r = mid;
            }
        }
        return str1.substring(pos, pos + l);
    }

    private static boolean binSearch(long[] phs1, int pos1, long[] phs2, int pos2, int len) {
        int k = 0;
        for (int i = len / 2; i > 0; i /= 2) {
            while (i + k < len && hash(phs1, pos1, i + k) == hash(phs2, pos2, i + k)) k += i;
        }
        return k + 1 == len;
    }

    private static long hash(long[] hash, int pos, int offset) {
        long strH = hash[pos + offset];
        long prefH = pos > 0 ? hash[pos - 1] : 0;
        strH = strH < prefH ? strH + mod : strH;
        return (strH - prefH) * invP[pos] % mod;
    }

    private static List<Long> sort(List<Long> unsortedArr) {
        List<Long> arr = List.copyOf(unsortedArr);
        int max = 1 << (Integer.BYTES * 8 >> 1);
        List<List<Long>> digits = LongStream.range(0, max).mapToObj(i -> new ArrayList<Long>()).collect(Collectors.toList());
        List<List<Long>> digits2 = LongStream.range(0, max).mapToObj(i -> new ArrayList<Long>()).collect(Collectors.toList());
        for (long elt : arr) {
            digits.get((int) (elt % max)).add(elt);
        }
        for (List<Long> eltList : digits) {
            for (Long elt : eltList) {
                digits2.get((int) (elt / max)).add(elt);
            }
        }
        List<Long> resList = digits2.stream().flatMap(Collection::stream).collect(Collectors.toList());
        return resList;
    }

    private static long[] prefixHashes(String str) {
        int len = str.length();
        long[] hashes = new long[len];
        byte[] strBytes = str.getBytes();
        hashes[0] = (strBytes[0] - firstCharByte + 1) * pows[0] % mod;
        for (int i = 1; i < len; i += 1) {
            hashes[i] = hashes[i - 1] + (strBytes[i] - firstCharByte + 1) * pows[i] % mod;
        }
        return hashes;
    }

    private static long[] pows() {
        final int max = (int) 1e5 + 5;
        long[] pows = new long[max];
        pows[0] = 1;
        for (int i = 1; i < max; i += 1) {
            pows[i] = pows[i - 1] * k % mod;
        }
        return pows;
    }

    private static long[] invP() {
        final int max = pows.length;
        long[] invP = new long[max];
        long phi = phi(mod) - 1;
        for (int i = 0; i < max; i += 1) {
            invP[i] = modPow(pows[i], phi, mod);
        }
        return invP;
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

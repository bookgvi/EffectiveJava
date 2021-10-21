package Algos.String;

import java.util.*;
import java.util.stream.*;

public class MostSubStringHash {
    private static final int k = (int) 1e5 + 5;
    private static final int mod = (int) 1e9 + 7;
    private static final String firstChar = "A";
    private static final byte firstCharByte = firstChar.getBytes()[0];

    private static final String str1 = "VOTEFORTHEGREATALBANIAFORYOU";
    private static final String str2 = "CHOOSETHEGREATALBANIANFUTURE";
    private static final long[] pows = pows();
    private static final long[] invP = invP();

    public static void main(String[] args) {
        long[] phs1 = prefixHashes(str1);
        long[] phs2 = prefixHashes(str2);
        int len = Math.min(str1.length(), str2.length());
        String res = searchSubStr(phs1, phs2, len);
        System.out.println(res);
    }

    private static String searchSubStr(long[] phs1, long[] phs2, int len) {
        int l = 0, r = len, pos = -1;
        while (r - l > 1) {
            int mid = (r + l) / 2;
            List<Long> str1Hashes = new ArrayList<>();
            for (int i = 0; i + mid < len; i += 1) {
                 str1Hashes.add(hash(phs1, i, mid));
            }
            str1Hashes = sort(str1Hashes);
            int p = -1;
            for (int i = 0; i + mid < len; i += 1) {
                long hstr2 = hash(phs2, i, mid);
                int isFound = binSearch(hstr2, str1Hashes);
                if (isFound != -1) {
                    p = i;
                    break;
                }
            }
            if (p >= 0) {
                l = mid;
                pos = p;
            } else {
                r = mid;
            }
        }
        return str2.substring(pos, pos + l + 1);
    }

    private static long hash(long[] phs, int pos, int offset) {
        long hash = phs[pos + offset];
        long prefix = pos > 0 ? phs[pos - 1] : 0;
        hash = hash - prefix < 0 ? hash + mod : hash;
        return (hash - prefix) * invP[pos] % mod;
    }

    private static int binSearch(long n, List<Long> arr) {
        int len = arr.size();
        int k = len - 1;
        for (int i = len / 2; i > 0; i /= 2) {
            while (k - i >= 0 && arr.get(k - i) >= n) k -= i;
        }
        if (arr.get(k) == n) return k;
        return -1;
    }

    private static List<Long> sort(List<Long> unsortedArr) {
        int size = 1 << (Integer.BYTES << 2) ;
        List<Long> arr = List.copyOf(unsortedArr);
        List<List<Long>> digits = LongStream.range(0, size).mapToObj(i -> new ArrayList<Long>()).collect(Collectors.toList());
        List<List<Long>> digits2 = LongStream.range(0, size).mapToObj(i -> new ArrayList<Long>()).collect(Collectors.toList());

        for (Long elt : arr) {
            digits.get((int) (elt % size)).add(elt);
        }

        for (List<Long> eltList : digits) {
            for (Long elt : eltList) {
                digits2.get((int)(elt / size)).add(elt);
            }
        }
        return digits2.stream().flatMap(Collection::stream).collect(Collectors.toList());
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
        int max = (int) 1e5 + 5;
        long[] pows = new long[max];
        pows[0] = 1;
        for (int i = 1; i < max; i += 1) {
            pows[i] = pows[i - 1] * k % mod;
        }
        return pows;
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

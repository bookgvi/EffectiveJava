package Structures.Suffix;

import java.util.*;
import java.util.stream.*;

public class SuffixArrayTrivial {
    private static final String str1 = "if you want to fuck for funny fuck yourself and keep the money";
    private static final String str2 = "if you want to have a sex fuck my dog his name is rex";
    private static final String firstChar = "A";
    private static final byte firstCharByte = firstChar.getBytes()[0];

    private static final int k = (int) 1e5 + 5;
    private static final int mod = (int) 1e9 + 7;
    private static final long[] pows = pows();
    private static final long[] invP = invP();

    public static void main(String[] args) {
        long[] phs1 = prefixHashes(str1);
        long[] phs2 = prefixHashes(str2);
        int len = Math.min(str1.length(), str2.length());
        String res = searchSubString(phs1, phs2, len);
        System.out.println(res);
    }

    private static String searchSubString(long[] phs1, long[] phs2, int len) {
        int pos = -1, l = 0, r = len - 1;
        while(r - l > 1) {
            int mid = (r + l) / 2;
            List<Long> hash1Str = new ArrayList<>();
            for (int i = 0; i + mid < len; i += 1) {
                hash1Str.add(hash(phs1, i, mid));
            }
            hash1Str = sort(hash1Str);
            int p = -1;
            for(int i = 0; i + mid < len; i += 1) {
                long res = binSearch(hash(phs2, i, mid), hash1Str);
                if (res != -1) {
                    p = i;
                    break;
                }
            }
            if (p != -1) {
                pos = p;
                l = mid;
            } else {
                r = mid;
            }
        }
        return str2.substring(pos, pos + l + 1);
    }

    private static boolean compareStr(String str1, int pos1, String str2, int pos2, int same) {
        byte[] strBytes1 = str1.getBytes();
        byte[] strBytes2 = str2.getBytes();
        return (strBytes1[pos1 + same] - firstCharByte + 1) <= (strBytes2[pos2 + same] - firstCharByte + 1);
    }

    private static int lcp(long[] phs1, int pos1, long[] phs2, int pos2, int len) {
        int l = 0, r = len - 1;
        while (r - l > 1) {
            int mid = (r + l) / 2;
            if (hash(phs1, pos1, mid) == hash(phs2, pos2, mid)) l = mid;
            else r = mid;
        }
        return l;
    }

    private static long binSearch(long n, List<Long> arr) {
        int len = arr.size(), k = 0;
        for (int i = len / 2; i > 0; i /= 2) {
            while (i + k < len && arr.get(i + k) <= n) k += i;
        }
        if (arr.get(k) == n) return k;
        return -1;
    }

    private static List<Long> sort(List<Long> arr) {
        int max = 1 << Integer.BYTES * 8 / 2;
        List<List<Long>> digits = LongStream.range(0, max).mapToObj(i -> new ArrayList<Long>()).collect(Collectors.toList());
        List<List<Long>> digits2 = LongStream.range(0, max).mapToObj(i -> new ArrayList<Long>()).collect(Collectors.toList());
        for (Long elt : arr) {
            digits.get((int) (elt % max)).add(elt);
        }
        for (List<Long> eltList : digits) {
            for (Long elt : eltList) {
                digits2.get((int) (elt / max)).add(elt);
            }
        }
        return digits2.stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    private static long hash(long[] phs, int pos, int offset) {
        long strH = phs[pos + offset];
        long pref = pos > 0 ? phs[pos - 1] : 0;
        strH = strH < pref ? strH + mod : strH;
        return (strH - pref) * invP[pos] % mod;
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
        int count = (int) 1e5 + 5;
        long[] pows = new long[count];
        pows[0] = 1;
        for (int i = 1; i < count; i += 1) {
            pows[i] = pows[i - 1] * k % mod;
        }
        return pows;
    }

    private static long[] invP() {
        int count = pows.length;
        long[] invP = new long[count];
        long phi = phi(mod) - 1;
        for (int i = 0; i < count; i += 1) {
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

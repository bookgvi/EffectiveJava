package TestYourSelf.Hashes;

import java.util.*;
import java.util.stream.Collectors;

public class ReplaceSubStrWithDash {
    private static final int k = (int) 1e5 + 5;
    private static final int mod = (int) 1e9 + 7;
    private static final long[] pows = pows();
    private static final long[] invP = invP();

    private static final String firstChar = "0";
    private static final int firstCharByte = firstChar.getBytes()[0];
    private static final String abc = getAbc();

    public static void main(String[] args) {
        String str = "aaaabcdfdffdefgrttt";
        long[] phStr = prefixHashes(str);
        long[] phAbc = prefixHashes(abc);

        String res = searchStr(phAbc, phStr, str);
        System.out.println(res);
    }

    private static String searchStr(long[] phs1, long[] phs2, String str) {
        int len = str.length(), pos = 2;
        Map<Integer, Integer> indexes = new HashMap<>();
        while (pos < len) {
            long[] str1hashes = new long[phs1.length - pos];
            for (int i = 0; i + pos < phs1.length; i += 1)
                str1hashes[i] = hash(phs1, i, pos);
            sort (str1hashes);
            for (int i = 0; i + pos < phs2.length; i += 1) {
                if (binSearch(hash(phs2, i, pos), str1hashes) != -1) {
                    indexes.put(i, i + pos);
                    for (int j = i + 1; j < i + pos + 1; j += 1)
                        if (indexes.get(j) != null) indexes.remove(j);
                }
            }
            pos += 1;
        }
        int cur = 0;
        StringBuilder res= new StringBuilder();
        List<Integer> keys = indexes.keySet().stream().sorted().collect(Collectors.toList());

        for (int index : keys) {
            int l = indexes.get(index);
            res.append(str, cur, index);
            res.append(convert(str, index, l));
            cur = l + 1;
        }
        if (cur < len) res.append(str, cur, len);
        return res.toString();
    }

    private static String convert(String str, int pos, int len) {
        return "[" + str.charAt(pos) + "-" + str.charAt(len) + "]";
    }

    private static void sort(long[] arr) {
        int len = arr.length, b = 8, dw = 4;
        long[] t = new long[len];
        for (int p = 0; p < dw; p += 1) {
            int[] count = new int[1 << b];
            for (long elt : arr)
                count[(int) ((elt ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)] += 1;
            for (int i = 1; i < 1 << b; i += 1)
                count[i] += count[i - 1];
            for (int i = len - 1; i >= 0; i -= 1)
                t[--count[(int) ((arr[i] ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)]] = arr[i];
            System.arraycopy(t, 0, arr, 0, len);
        }
    }

    private static int binSearch(long n, long[] arr) {
        int len = arr.length, l = 0, r = len - 1;
        while (r - l >= 0) {
            int mid = (r + l) >> 1;
            if (arr[mid] == n) return mid;
            else if (arr[mid] < n) l = mid + 1;
            else r = mid - 1;
        }
        return -1;
    }

    private static long hash(long[] phs, int pos, int offset) {
        long strH = phs[pos + offset];
        long prefH = pos > 0 ? phs[pos - 1] : 0;
        strH = strH - prefH < 0 ? strH + mod : strH;
        return (strH - prefH) * invP[pos] % mod;
    }

    private static long[] prefixHashes(String str) {
        int len = str.length();
        long[] hashes = new long[len];
        byte[] strBytes = str.getBytes();
        hashes[0] = (strBytes[0] - firstCharByte + 1) * pows[0] % mod;
        for (int i = 1; i < len; i += 1)
            hashes[i] = hashes[i - 1] + (strBytes[i] - firstCharByte + 1) * pows[i] % mod;
        return hashes;
    }

    private static String getAbc() {
        StringBuilder abc = new StringBuilder();
        for (int i = 31; i < 1 << 8; i += 1)
            abc.append(Character.toString(i));
        return abc.toString();
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

    private static long modPow(long n, long pow, int mod) {
        long res = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) res = res * n % mod;
            n = n * n % mod;
            pow >>= 1;
        }
        return res;
    }
}

package TestYourSelf.Hashes;

import java.util.*;
import java.util.stream.*;

public class ReplaceSubStrWithDash {
    private static final int k = (int) 1e5 + 5;
    private static final int mod = (int) 1e9 + 7;
    private static final long[] pows = pows();
    private static final long[] invP = invP();

    private static final String firstChar = "0";
    private static final int firstCharByte = firstChar.getBytes()[0];
    private static final String abc = getAbc();

    public static void main(String[] args) {
        String str = "abcdeaaauvwx1234";
        long[] phStr = prefixHashes(str);
        long[] phAbc = prefixHashes(abc);

        String res = searchStr(phAbc, phStr, str);
        System.out.printf("%s -> %s\n", str, res);
    }

    private static String searchStr(long[] phAbc, long[] phStr, String str) {
        StringBuilder res = new StringBuilder();
        Map<Integer, Integer> indexes = new HashMap<>();
        int abcLen = phAbc.length, strLen = phStr.length, off = 2;
        while (off < abcLen) {
            off += 1;
            long[] abcHashes = new long[abcLen - off + 1];
            for (int i = 0; i + off - 1 < abcLen; i += 1)
                abcHashes[i] = hash(phAbc, i, off - 1);
            sort(abcHashes);
            for (int i = 0; i + off - 1 < strLen; i += 1) {
                if (binSearch(hash(phStr, i, off - 1), abcHashes) != -1) {
                    indexes.put(i, off);
                    for (int j = i + 1; j < i + off; j += 1)
                        if (indexes.get(j) != null) indexes.remove(j);
                }
            }
        }
        List<Integer> positions = new ArrayList<>(indexes.keySet()).stream().sorted().collect(Collectors.toList());
        int curPos = 0;
        for (int startToConvert : positions) {
            int endToConvert = startToConvert + indexes.get(startToConvert) - 1;
            res.append(str, curPos, startToConvert).append(convert(startToConvert, endToConvert, str));
            curPos = endToConvert + 1;
        }
        if (curPos < strLen) res.append(str, curPos, strLen);
        return res.toString();
    }

    private static String convert(int start, int end, String str) {
        return "[" + str.charAt(start) + "-" + str.charAt(end) + "]";
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

    private static long hash(long[] phs, int pos, int off) {
        long strH = phs[pos + off];
        long prefH = pos > 0 ? phs[pos - 1] : 0;
        strH = strH < prefH ? strH + mod : strH;
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
        for (int i = 31; i < 255; i += 1)
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

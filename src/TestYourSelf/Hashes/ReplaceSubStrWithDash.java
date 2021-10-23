package TestYourSelf.Hashes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReplaceSubStrWithDash {
    private static final int k = (int) 1e5 + 5;
    private static final int mod = (int) 1e9 + 7;
    private static final long[] pows = pows();
    private static final long[] invP = invP();

    private static final String firstChar = "0";
    private static final int firstCharByte = firstChar.getBytes()[0];
    private static final String abc = getAbc();

    public static void main(String[] args) {
        String str = "33333333345111";
        long[] phStr = prefixHashes(str);
        long[] phAbc = prefixHashes(abc);

        String res = searchStr(phStr, phAbc, str);
        System.out.println(res);
    }

    private static String searchStr(long[] phStr, long[] phAbc, String str) {
        int strLen = str.length(), abcLen = phAbc.length, mid = 1;
        Map<Integer, Integer> positions = new HashMap<>();
        StringBuilder res = new StringBuilder();
        while (mid < strLen) {
            mid += 1;
            long[] abcHashes = new long[abcLen - mid];
            for (int i = 0; i + mid < abcLen; i += 1)
                abcHashes[i] = hash(phAbc, i, mid);
            sort(abcHashes);
            for (int i = 0; i + mid < strLen; i += 1) {
                if (binSearch(hash(phStr, i, mid), abcHashes) != -1) {
                    positions.put(i, mid + 1);
                    for (int j = 1; j <= mid; j += 1)
                        if (positions.get(i + j) != null) positions.remove(i + j);
                }
            }
        }
        int curPos = 0;
        for (int position : positions.keySet()) {
            String convert = convertStr(str.substring(position, position + positions.get(position)));
            res.append(str, curPos, position).append(convert);
            curPos = position + positions.get(position);
        }
        if (curPos < strLen)
            res.append(str, curPos, strLen);
        return res.toString();
    }

    private static String convertStr(String str) {
        int len = str.length(), firstChar = str.charAt(0), lasChar = str.charAt(len - 1);
        if (len < 3) return str;
        String firstStr = Character.toString(firstChar);
        String lastStr = Character.toString(lasChar);
        return "[" + firstStr + "-" + lastStr + "]";
    }

    private static String getAbc() {
        StringBuilder abc = new StringBuilder();
        for (int i = 1; i < (1 << 8); i += 1)
            abc.append(Character.toString(i));
        return abc.toString();
    }

    private static void sort(long[] arr) {
        int b = 8, dw = Integer.BYTES, len = arr.length;
        long[] t = new long[len];
        for (int p = 0; p < dw; p += 1) {
            int[] c = new int[1 << b];
            for (long elt : arr)
                c[(int) ((elt ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)] += 1;
            for (int i = 1; i < 1 << b; i += 1)
                c[i] += c[i - 1];
            for (int i = len - 1; i >= 0; i -= 1)
                t[--c[(int) ((arr[i] ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)]] = arr[i];
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

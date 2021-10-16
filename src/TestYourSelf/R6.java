package TestYourSelf;

import java.util.*;
import java.util.stream.*;

public class R6 {
    private static final int k = (int) 1e5 + 5;
    private static final int mod = (int) 1e9 + 7;
    private static final long[] pows = pows();
    private static final long[] invP = invP();
    private static final String firstChar = "a";
    private static final byte firstCharByte = firstChar.getBytes()[0];
    private static final String str1 = "VOTEFORTHEGREATALBANIAFORYOU";
    private static final String str2 = "CHOOSETHEGREATALBANIANFUTURE";

    public static void main(String[] args) {
        final long[] prefHash1 = prefixHashes(str1);
        final long[] prefHash2 = prefixHashes(str2);
        int len = Math.min(str1.length(), str2.length());
        long startTime = System.nanoTime();
        String str = searchString(prefHash1, prefHash2, len);
        long endTime = System.nanoTime();
        System.out.println(str);
        System.out.printf("%.8f\n", (endTime - startTime) / 1e9);

        boolean cmp = strComparator(prefHash1, 7, prefHash2, 6, 14);
        System.out.println(cmp);
    }

    private static String searchString(long[] phs1, long[] phs2, int len) {
        int pos = -1, l = 0, r = len - 1;
        while (r - l >= 0) {
            int mid = (r + l) >> 1;
            long[] str1hash = new long[len - mid];
            for (int i = 0; i + mid < len; i += 1) {
                str1hash[i] = hash(phs1, i, mid);
            }
            sort(str1hash);
            int p = -1;
            for (int i = 0; i + mid < len; i += 1) {
                if (binSearch(hash(phs2, i, mid), str1hash) != -1) {
                    p = i;
                    break;
                }
            }
            if (p != -1) {
                pos = p;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return str2.substring(pos, pos + l);
    }

    private static boolean strComparator(long[] phs1, int pos1, long[] phs2, int pos2, int len) {
        int l = 0, r = len - 1;
        while (r - l >= 0) {
            int mid = (r + l) >> 1;
            long h1 = hash(phs1, pos1, mid);
            long h2 = hash(phs2, pos2, mid);
            if (h1 == h2) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return len == l && str1.charAt(pos1 + l) == str2.charAt(pos2 + l);
    }

    private static long binSearch(long n, long[] arr) {
        int k = 0, len = arr.length;
        for (int i = len >> 1; i > 0; i >>= 1) {
            while (k + i < len && arr[i + k] <= n) k += i;
        }
        if (arr[k] == n) return k;
        return -1;
    }

    private static void sort(long[] a) {
        final int d = 8;
        final int w = 32;
        long[] t = new long[a.length];
        for (int p = 0; p < w / d; p++) {
            int[] cnt = new int[1 << d];
            for (int i = 0; i < a.length; i++)
                ++cnt[(int) (((a[i] ^ Integer.MIN_VALUE) >>> (d * p)) & ((1 << d) - 1))];
            for (int i = 1; i < cnt.length; i++)
                cnt[i] += cnt[i - 1];
            for (int i = a.length - 1; i >= 0; i--)
                t[--cnt[(int) (((a[i] ^ Integer.MIN_VALUE) >>> (d * p)) & ((1 << d) - 1))]] = a[i];
            System.arraycopy(t, 0, a, 0, a.length);
        }
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
        byte[] strByte = str.getBytes();
        hashes[0] = (strByte[0] - firstCharByte + 1) * pows[0] % mod;
        for (int i = 1; i < len; i += 1) {
            hashes[i] = hashes[i - 1] + (strByte[i] - firstCharByte + 1) * pows[i] % mod;
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
        int max = pows.length;
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
}

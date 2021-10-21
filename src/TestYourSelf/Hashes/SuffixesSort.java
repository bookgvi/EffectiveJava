package TestYourSelf.Hashes;

import java.util.Arrays;
import java.util.function.Supplier;

public class SuffixesSort {
    private static final int k = (int) 1e5 + 5;
    private static final int mod = (int) 1e9 + 7;
    private static final long[] pows = pows();
    private static final long[] invP = invP();

    private static final String str1 = "abracadabra";
    private static final String str2 = "this is not a test";
    private static final String first = "a";
    private static final int firstCharByte = first.getBytes()[0];

    public static void main(String[] args) {
        int len1 = str1.length();
        int[] arr = new int[len1];

//        int res = comparator(5, 7);
//        System.out.printf("comparator: %d\n", res);

        for (int i = 1; i < len1; i += 1) arr[i - 1] = i;

        System.out.println(Arrays.toString(arr));
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));

        for (int i : arr) {
            String suffix = str1.substring(i);
            System.out.println(suffix);
        }
    }

    private static void mergeSort(int[] arr) {
        for (int i = 1, len = arr.length; i < len; i <<= 1) {
            for (int j = 0; j < len - i; j += i << 1) {
                merge(j, Math.min(j + i, len), Math.min(j + (i << 1), len), arr);
            }
        }
    }

    private static void merge(int l, int mid, int r, int[] arr) {
        int[] merge = new int[r - l];
        int it1 = 0, it2 = 0;
        while (l + it1 < mid && mid + it2 < r) {
            if (comparator(l + it1, mid + it2) > 0) {
                merge[it1 + it2] = arr[l + it1];
                it1 += 1;
            } else {
                merge[it1 + it2] = arr[mid + it2];
                it2 += 1;
            }
        }
        while (l + it1 < mid) {
            merge[it1 + it2] = arr[l + it1];
            it1 += 1;
        }
        while (mid + it2 < r) {
            merge[it1 + it2] = arr[mid + it2];
            it2 += 1;
        }
        for (int i = 0; i < it1 + it2; i += 1)
            arr[l + i] = merge[i];
    }

    /**
    * @return
     *      "< 0" - first > second
     *      "0"   - first == second
     *      "> 0" - first < second
    * */
    private static int comparator(int pos1, int pos2) {
        long[] phs1 = prefixHashes(SuffixesSort.str1), phs2 = prefixHashes(SuffixesSort.str1);
        int len = Math.min(str1.length() - pos1, str1.length() - pos2), l = 0, r = len - 1;
        while (r - l >= 0) {
            int mid = (r + l) >> 1;
            long h1 = hash(phs1, pos1, mid);
            long h2 = hash(phs2, pos2, mid);
            if (h1 == h2) l = mid + 1;
            else r = mid - 1;
        }
        if (l - len < 0)
            return SuffixesSort.str1.charAt(pos2 + l) - SuffixesSort.str1.charAt(pos1 + l);
        return l - len;
    }

    private static long hash(long[] phs, int pos, int offset) {
        long strH = phs[pos + offset];
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

    private static long[] pows() {
        int max = (int) 1e5 +5;
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
        while(pow > 0) {
            if ((pow & 1) == 1) res = res * n % mod;
            n = n * n % mod;
            pow >>= 1;
        }
        return res;
    }
}

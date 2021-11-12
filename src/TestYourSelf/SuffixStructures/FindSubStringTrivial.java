package TestYourSelf.SuffixStructures;

import java.util.Arrays;

public class FindSubStringTrivial {
    public static void main(String[] args) {
        String findMe = "bracadabra";
        String str = "abracadabra";
        System.out.printf("%s <- %s\n positions ", str, findMe);
        int[] p = suffixArray(str);
        for (int i = 0, ssLen = findMe.length(); i < ssLen; i += 1) {
            int l = binarySearchL(findMe.charAt(i), p, i, str);
            if (l == -1) {
                System.out.println("not found...");
                return;
            }
            int r = binarySearchR(findMe.charAt(i), p, i, str);
            int[] tmp = Arrays.copyOf(p, p.length);
            p = new int[r - l + 1];
            System.arraycopy(tmp, l, p, 0, r - l + 1);
        }
        System.out.println(Arrays.toString(p));
    }

    private static int binarySearchL(int chCode, int[] arr, int off, String str) {
        int len = arr.length, k = len - 1;
        for (int i = len >> 1; i > 0; i >>= 1)
            while (k - i >= 0 && str.substring(arr[k - i]).charAt(Math.min(str.substring(arr[k - i]).length() - 1, off)) >= chCode)
                k -= i;
        if (str.substring(arr[k]).charAt(Math.min(str.substring(arr[k]).length() - 1, off)) == chCode) return k;
        return -1;
    }

    private static int binarySearchR(int chCode, int[] arr, int off, String str) {
        int len = arr.length, k = 0;
        for (int i = len >> 1; i > 0; i >>= 1)
            while(k + i < len && str.substring(arr[k + i]).charAt(off) <= chCode) k += i;
        if (str.substring(arr[k]).charAt(off) == chCode) return k;
        return -1;
    }

    private static int[] suffixArray(String str) {
        int len = str.length(), b = 8;
        int[] p = new int[len], c = new int[len], count = new int[1 << b];
        for (int i = 0; i < len; i += 1)
            count[str.charAt(i)] += 1;
        for (int i = 1; i < 1 << b; i += 1)
            count[i] += count[i - 1];
        for (int i = len - 1; i >= 0; i -= 1)
            p[--count[str.charAt(i)]] = i;
        c[p[0]] = 0;
        int classes = 1;
        for (int i = 1; i < len; i += 1) {
            if (str.charAt(p[i]) != str.charAt(p[i - 1])) classes += 1;
            c[p[i]] = classes - 1;
        }
        int[] cn = new int[len], pn = new int[len];
        for (int h = 0; (1 << h) < len; h += 1) {
            for (int i = 0; i < len; i += 1) {
                pn[i] = p[i] - (1 << h);
                if (pn[i] < 0) pn[i] += len;
            }
            count = new int[1 << b];
            for (int i = 0; i < len; i += 1)
                count[c[pn[i]]] += 1;
            for (int i = 1; i < 1 << b; i += 1)
                count[i] += count[i - 1];
            for (int i = len - 1; i >= 0; i -= 1)
                p[--count[c[pn[i]]]] = pn[i];
            cn[p[0]] = 0;
            classes = 1;
            for (int i = 1; i < len; i += 1) {
                int mid1 = (p[i] + (1 << h)) % len, mid2 = (p[i - 1] + (1 << h)) % len;
                if (c[p[i]] != c[p[i - 1]] || c[mid1] != c[mid2]) classes += 1;
                cn[p[i]] = classes - 1;
            }
            System.arraycopy(cn, 0, c, 0, len);
        }
        return p;
    }
}

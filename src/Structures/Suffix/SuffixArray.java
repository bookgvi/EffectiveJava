package Structures.Suffix;

import java.util.*;

public class SuffixArray {
    private static final String str = "abracadabra";
    //    private static final String str = "abaab";
    private static final String firstChar = "a";
    private static final byte firstCharByte = firstChar.getBytes()[0];

    public static void main(String[] args) {
        System.out.println(Arrays.toString(lexicoGraphikSuffixSort(str)));
        for (int pos : lexicoGraphikSuffixSort(str)) {
            System.out.println(str.substring(pos));
        }
    }

    private static int[] lexicoGraphikSuffixSort(String str) {
        int len = str.length();
        int[] count = new int[1 << 8], p = new int[len], c = new int[len];
        byte[] strBytes = str.getBytes();
        int firstCharByte = "a".getBytes()[0];
        for (int i =0; i < len; i += 1)
            count[strBytes[i] - firstCharByte + 1] += 1;
        for (int i = 1; i < 1 << 8; i += 1)
            count[i] += count[i - 1];
        for (int i = len - 1; i >=0; i -= 1)
            p[--count[strBytes[i] - firstCharByte + 1]] = i;

        int classes = 1;
        for (int i = 1; i < len; i += 1) {
            if (str.charAt(p[i - 1]) != str.charAt(p[i])) classes += 1;
            c[p[i]] = classes - 1;
        }

        int[] pn = new int[len], cn = new int[len];
        for (int h = 0; (1 << h) < len; h += 1) {
            count = new int[1 << 8];
            for (int i = 0; i < len; i += 1) {
                pn[i] = p[i] - (1 << h);
                if (pn[i] < 0) pn[i] += len;
            }
            for (int i = 0; i < len; i += 1)
                count[c[pn[i]]] += 1;
            for (int i = 1; i < 1 << 8; i += 1)
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

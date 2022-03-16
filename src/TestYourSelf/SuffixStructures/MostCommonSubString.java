package TestYourSelf.SuffixStructures;

import java.util.*;

public class MostCommonSubString {
    private static List<int[]> classesList = new ArrayList<>();

    private static int[] suffixArray(String str) {
        int len = str.length(), b = 8;
        int[] p = new int[len], c = new int[len], count = new int[1 << b];
        for (int i = 0; i < len; i += 1)
            count[str.charAt(i) - 'a'] += 1;
        for (int i = 1; i < 1 << b; i += 1)
            count[i] += count[i - 1];
        for (int i = len - 1; i >= 0; i -= 1)
            p[--count[str.charAt(i) - 'a']] = i;
        c[p[0]] = 0;
        int classes = 1;
        for (int i = 1; i < len; i += 1) {
            if (str.charAt(p[i]) != str.charAt(p[i - 1])) classes += 1;
            c[p[i]] = classes - 1;
        }
        classesList.add(Arrays.copyOf(c, c.length));
        int[] pn = new int[len], cn = new int[len];
        for (int h = 0; 1 << h < len; h += 1) {
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
            classesList.add(Arrays.copyOf(c, c.length));
        }
        return p;
    }

    private static int commonSubStringLength(String str) {
        int[] p = suffixArray(str);
        int res = 0, l = 0, r = 2;
        for (int i = classesList.size() - 1; i >= 0; i -= 1) {
            int[] c = classesList.get(i);
            int len = c.length;
            if(l < len && r < len && c[l] == c[r]) {
                l += 1 << i;
                r += 1 << i;
                res += 1 << i;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String str = "abracadabra";
        int res = commonSubStringLength(str);
    }
}

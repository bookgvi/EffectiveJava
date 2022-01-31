package Structures.Suffix;

import java.util.*;

public class CompareSubStrings {
    private static final List<int[]> cs = new ArrayList<>();

    private static int[] suffixArray(String str) {
        int len = str.length(), b = 5;
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
        cs.add(Arrays.copyOf(c, len));
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
            cs.add(Arrays.copyOf(c, len));
        }
        return p;
    }

    /**
     * Вычисление максимальной длинны 2-х подстрок в строке с помощью суффиксного массива
     * @param str - строка;
     * @param index1 - первый индекс; начало первой подстроки
     * @param index2 - второй индекс; начало второй подстроки
     * @return длинна общей подстроки для данных индексов
     * */
    private static int compareSubStrings(String str, int index1, int index2) {
        int res = 0;
        int[] p = suffixArray(str);
        for (int i = cs.size() - 1; i >= 0; i -= 1) {
            int[] c = cs.get(i);
            int len = c.length;
            if (index1 < len && index2 < len && c[index1] == c[index2]) {
                index1 += 1 << i;
                index2 += 1 << i;
                res += 1 << i;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String str = "abrakadabra";
        int index1 = 1, index2 = 8;
        int res = compareSubStrings(str, index1, index2);
    }
}

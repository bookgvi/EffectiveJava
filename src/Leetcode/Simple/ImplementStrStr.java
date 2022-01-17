package Leetcode.Simple;

import java.util.*;

/*
 * https://leetcode.com/problems/implement-strstr/submissions/
 * */
public class ImplementStrStr {
    public static int strStr(String haystack, String needle) {
        int lenSS = needle.length(), ans = -1;
        if (lenSS < 1) return 0;
        int[] z = zFunc(needle + "#" + haystack);
        for (int i = 0; i < z.length; i += 1) {
            if (z[i] == lenSS) {
                ans = i;
                break;
            }
        }
        if (ans != -1) ans = ans - lenSS - 1;
        return ans;
    }

    private static int[] zFunc(String str) {
        int len = str.length();
        int[] z = new int[len];
        for (int i = 1, l = 0, r = 0; i < len; i += 1) {
            if (i <= r) z[i] = Math.min(z[i - l], r - i + 1);
            while (i + z[i] < len && str.charAt(i + z[i]) == str.charAt(z[i])) z[i] += 1;
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        return z;
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
        int[] pn = new int[len], cn = new int[len];
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

    private static int binSearchL(int chCode, int[] arr, int off, String str) {
        int len = arr.length, k = len - 1;
        for (int i = len >> 1; i > 0; i >>= 1)
            while(k - i >= 0
            && off < str.substring(arr[k - i]).length()
            && str.substring(arr[k - i]).charAt(off) >= chCode)
                k -= i;
        if (k > -1 && off < str.substring(arr[k]).length() && str.substring(arr[k]).charAt(off) == chCode) return k;
        return -1;
    }

    private static int binSearchR(int chCode ,int[] arr, int off, String str) {
        int len = arr.length, k = 0;
        for (int i = len >> 1; i > 0; i >>= 1)
            while(k + i < len
            && off < str.substring(arr[k + i]).length()
            && str.substring(arr[k + i]).charAt(off) <= chCode)
                k += i;
        if (off < str.substring(arr[k]).length() && str.substring(arr[k]).charAt(off) == chCode) return k;
        return -1;
    }

    private static int findSubstring(String str, String ss) {
        int[] p = suffixArray(str);
        for (int i = 0, len = ss.length(); i < len; i += 1) {
            int l = binSearchL(ss.charAt(i), p, i, str);
            if (l == -1) {
                System.out.println("... not found");
                return -1;
            }
            int r = binSearchR(ss.charAt(i), p, i, str);
            int[] t = Arrays.copyOf(p, p.length);
            p = new int[r - l + 1];
            System.arraycopy(t, l, p, 0, r - l + 1);
        }
        int ans = -1;
        for (int pos : p)
            ans = Math.min(ans, pos);
        return ans;
    }

    public static void main(String[] args) {
        String s = "babbbbbabb";
        String s2 = "hello#";
        String ss = "ll";
        int res = strStr(s, ss);


        int[] p = suffixArray(s);
        int[] p2 = suffixArray(s2);

        System.out.println(Arrays.toString(p));
        for (int pos : p) {
            System.out.println(s.substring(pos));
        }
        System.out.println(Arrays.toString(p2));
        for (int pos : p2) {
            System.out.println(s2.substring(pos));
        }

        System.out.println(findSubstring(s, ss));
        System.out.println(findSubstring(s2, ss));
    }
}

package TestYourSelf;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class R2 {
    public static void main(String[] args) {
        String str = "aabcadabcaab";
        System.out.println(Arrays.toString(zFunc(str)));
        System.out.println(Arrays.toString(pFunc(str)));
        System.out.println(Arrays.toString(pFuncExt(str)));
        System.out.println(Arrays.toString(manacher(str)));
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

    private static int[] pFunc(String str) {
        int len = str.length();
        int[] pi = new int[len];
        for (int i = 0; i < len; i += 1) {
            for (int j = 0; j < i; j += 1) {
                String ss1 = str.substring(0, j + 1).intern();
                String ss2 = str.substring(i - j, i + 1).intern();
                if (ss1 == ss2) pi[i] = j + 1;
            }
        }
        return pi;
    }

    private static int[] pFuncExt(String str) {
        int len = str.length();
        int[] pi = new int[len];
        for (int i = 1; i < len; i += 1) {
            int j = pi[i - 1];
            while (j > 0 && str.charAt(i) != str.charAt(j)) j = pi[j - 1];
            if (str.charAt(i) == str.charAt(j)) pi[i] = j + 1;
        }
        return pi;
    }

    private static int[] manacher(String str) {
        int len = str.length();
        int[] m = new int[len];
        for (int i = 1, l = 0, r = 0; i < len; i += 1) {
            if (i < r) m[i] = Math.min(m[l + (r - i)], r - i + 1);
            while (i - m[i] >= 0 && i + m[i] < len && str.charAt(i - m[i]) == str.charAt(i + m[i])) m[i] += 1;
            if (i + m[i] - 1 > r) {
                l = i - m[i] + 1;
                r = i + m[i] - 1;
            }
        }
        return m;
    }
}

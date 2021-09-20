package TestYourSelf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class R2 {
    public static void main(String[] args) {
        int n = 6;
        String str = "abcdabca";
        System.out.println(Arrays.toString(zFuncExt(str)));
        System.out.println(Arrays.toString(piFunc(str)));
        System.out.println(Arrays.toString(piFuncExt(str)));
    }

    private static int[] zFuncExt(String str) {
        int len = str.length();
        int[] z = new int[len];
        for (int i = 1, l = 0, r = 0; i < len; i += 1) {
            if (i <= r) z[i] = Math.min(z[i - l], r - i + 1);
            while (i + z[i] < len && str.charAt(z[i]) == str.charAt(i + z[i])) z[i] += 1;
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        return z;
    }

    private static int[] piFunc(String str) {
        int len = str.length();
        int[] pi = new int[len];
        for (int i = 1; i < len; i += 1) {
            for (int j = 0; j < i; j += 1) {
                String ss1 = str.substring(0, j + 1).intern();
                String ss2 = str.substring(i - j, i + 1).intern();
                if (ss1 == ss2) pi[i] = j + 1;
            }
        }
        return pi;
    }

    private static int[] piFuncExt(String str) {
        int len = str.length();
        int[] pi = new int[len];
        for (int i = 1; i < len; i += 1) {
            int j = pi[i - 1];
            while(j > 0 && str.charAt(i) != str.charAt(j)) j = pi[j - 1];
            if (str.charAt(i) == str.charAt(j)) pi[i] = j + 1;
        }
        return pi;
    }
}

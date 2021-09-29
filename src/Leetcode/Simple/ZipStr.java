package Leetcode.Simple;

import java.util.Arrays;
import java.util.stream.Stream;

public class ZipStr {
    public static void main(String[] args) {
        String str = "aabaataabaab";
        String tmpStr = zipStr(str);
        System.out.println(str);
        System.out.println(tmpStr);
        System.out.println(Arrays.toString(piFunc(str)));
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

    private static int[] piFunc(String str) {
        int len = str.length();
        int[] pi = new int[len];
        for (int i = 1; i < len; i += 1) {
            int j = pi[i - 1];
            while (j > 0 && str.charAt(i) != str.charAt(j)) j = pi[j - 1];
            if (str.charAt(i) == str.charAt(j)) pi[i] = j + 1;
        }
        return pi;
    }

    private static String zipStr(String str) {
        int[] z = zFunc(str);
        int len = z.length;
        StringBuilder sb = new StringBuilder();
        int i = 1;
        while (i < len) {
            sb.append(str.charAt(i));
            if (i == 1 && z[i] > 0) sb.append(z[i] + 1);
            else if (z[i] > 1) sb.append(z[i]);
            i = z[i] > 0 ? i + z[i] : i + 1;
        }
        return sb.toString();
    }
}

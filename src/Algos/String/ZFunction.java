package Algos.String;

import java.util.Arrays;

public class ZFunction {
    public static void main(String[] args) {
        String str = "abacaba";
        String str1 = "aaabaab";
        String str2 = "aaaaba";
        System.out.println(Arrays.toString(zFunc(str)));
        System.out.println(Arrays.toString(zFuncExt(str)));
        System.out.println(Arrays.toString(zFunc(str1)));
        System.out.println(Arrays.toString(zFuncExt(str1)));
        System.out.println(Arrays.toString(zFunc(str2)));
        System.out.println(Arrays.toString(zFuncExt(str2)));
    }

    private static int[] zFunc(String str) {
        int strLen = str.length();
        int[] zArray = new int[strLen];
        for (int i = 1; i < strLen; i++) {
            while (i + zArray[i] < strLen && str.charAt(zArray[i]) == str.charAt(i + zArray[i])) {
                zArray[i]++;
            }
        }
        return zArray;
    }

    private static int[] zFuncExt(String str) {
        int len = str.length();
        int[] z = new int[len];
        for (int i = 1, l = 0, r = 0; i < len; i++) {
            if (i <= r) z[i] = Math.min(z[i - l], r - i + 1);
            while (z[i] + i < len && str.charAt(z[i]) == str.charAt(z[i] + i)) z[i] += 1;
            if (i + z[i] - 1 > r) {
                l = i;
                r = z[i] + i - 1;
            }
        }
        return z;
    }
}

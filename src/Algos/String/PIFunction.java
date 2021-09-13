package Algos.String;

import java.util.Arrays;

/*
 * Префиксная функция
 * */
public class PIFunction {
    public static void main(String[] args) {
        String str = "abcabcd";
        System.out.println(Arrays.toString(piFunction(str)));
    }

    private static int[] piFunction(String str) {
        int len = str.length();
        int[] pi = new int[len];
        for (int i = 0; i < len; i += 1) {
            for (int j = 0; j < i; j += 1) {
                String ss = str.substring(0, j + 1).intern();
                String ss1 = str.substring(i - j, i + 1).intern();
                if (ss == ss1) {
                    pi[i] = j + 1;
                }
            }
        }
        return pi;
    }
}

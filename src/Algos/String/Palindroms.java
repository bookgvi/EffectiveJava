package Algos.String;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * Алгоритм Манакера по нахождению подпалиндромов
 * */
public class Palindroms {
    public static void main(String[] args) {
        String str = "12343211232";
        System.out.println(Arrays.toString(manakerOdds(str)));
    }

    private static int[] manakerOdds(String str) {
        int len = str.length();
        int[] m = new int[len];
        for (int i = 1, l = 0, r = 0; i < len; i += 1) {
            if (i < r) m[i] = Math.min(m[i - l], r - i + 1);
            while (i - m[i] >= 0 && i + m[i] < len && str.charAt(i - m[i]) == str.charAt(i + m[i]))
                m[i] += 1;
            if (i + m[i] - 1 > r) {
                l = i - m[i] + 1;
                r = i + m[i] - 1;
            }
        }
        return m;
    }

}

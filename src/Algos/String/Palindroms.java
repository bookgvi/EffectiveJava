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
        int[] manacher = manacherOdds(str);
        System.out.println(Arrays.toString(manacher));
    }

    private static int[] manacherOdds(String str) {
        int len = str.length();
        int[] z = new int[len];
        for (int i = 1, l = 0, r = 0; i < len; i += 1) {
            if (i < r) z[i] = Math.min(z[l + r - i], r - i + 1);
            while (i + z[i] < len && i - z[i] >= 0 && str.charAt(i + z[i]) == str.charAt(i - z[i])) z[i] += 1;
            if (z[i] + i - 1 > r) {
                l = i - z[i] + 1;
                r = i + z[i] - 1;
            }
        }
        return z;
    }

}

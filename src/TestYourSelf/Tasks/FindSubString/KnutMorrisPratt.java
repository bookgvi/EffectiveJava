package TestYourSelf.Tasks.FindSubString;

import java.util.ArrayList;
import java.util.List;

public class KnutMorrisPratt {
    public static void main(String[] args) {
        String str = "abracadabra";
        String ss = "a";

        List<Integer> index = searchSubStrPi(str, ss);
        System.out.println(str);
        System.out.println(ss);
        System.out.println(index);
        index = searchSubStrZ(str, ss);
        System.out.println(index);
    }

    private static List<Integer> searchSubStrPi(String str, String ss) {
        List<Integer> res = new ArrayList<>();
        int lenSS = ss.length();
        String strToAnalize = ss + "#" + str;
        int[] pi = piFunc(strToAnalize);
        for (int i = lenSS; i < strToAnalize.length(); i += 1) {
            if (pi[i] == lenSS) res.add(i - 2 * lenSS);
        }
        return res;
    }

    private static List<Integer> searchSubStrZ(String str, String ss) {
        List<Integer> res = new ArrayList<>();
        int lenSS = ss.length();
        String strToAnalize= ss + "#" + str;
        int[] z = zFunc(strToAnalize);
        for (int i = lenSS; i < strToAnalize.length(); i += 1) {
            if (z[i] == lenSS) res.add(i - lenSS - 1);
        }
        return res;
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
}

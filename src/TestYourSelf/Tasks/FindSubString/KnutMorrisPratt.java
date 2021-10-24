package TestYourSelf.Tasks.FindSubString;

import java.util.ArrayList;
import java.util.List;

public class KnutMorrisPratt {
    public static void main(String[] args) {
        String str = "abracadabra";
        String ss = "bra";

        List<Integer> index = searchSubStrPi(str, ss);
        System.out.println(str);
        System.out.println(ss);
        System.out.println(index);
        index = searchSubStrZ(str, ss);
        System.out.println(index);
    }

    private static List<Integer> searchSubStrZ(String str, String ss) {
        List<Integer> indexes = new ArrayList<>();
        String tpmStr = ss + "$" + str;
        int ssLen = ss.length(), tmpLen = tpmStr.length();
        int[] z = zFunc(tpmStr);
        for (int index = 0; index < tmpLen; index += 1) {
            if (z[index] == ssLen) indexes.add(index - ssLen - 1);
        }
        return indexes;
    }

    private static List<Integer> searchSubStrPi(String str, String ss) {
        List<Integer> indexes = new ArrayList<>();
        String tmpStr = ss + "$" + str;
        int ssLen = ss.length(), lenTmp = tmpStr.length();
        int[] pi = piFunc(tmpStr);
        for (int i = 0; i < lenTmp; i += 1)
            if (pi[i] == ssLen) indexes.add(i - 2 * ssLen);
        return indexes;
    }

    private static int[] zFunc(String str) {
        int len = str.length();
        int[] z = new int[len];
        for (int i = 1, l = 0, r = 0; i < len; i += 1) {
            if (i <= r) z[i] = Math.min(z[i - l], r - i + 1);
            while(i + z[i] < len && str.charAt(i + z[i]) == str.charAt(z[i])) z[i] += 1;
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
}

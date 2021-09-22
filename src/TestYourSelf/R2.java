package TestYourSelf;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class R2 {
    public static void main(String[] args) {
        String str = "aabcaabaab";
        System.out.println(Arrays.toString(zFuncExt(str)));
        System.out.println(Arrays.toString(piFunc(str)));
        System.out.println(Arrays.toString(piFuncExt(str)));
        System.out.println(manakerOdds(str));
    }

    private static int[] zFuncExt(String str) {
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
        for (int i = 0; i < len; i += 1) {
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
            while (j > 0 && str.charAt(i) != str.charAt(j)) j = pi[j - 1];
            if (str.charAt(i) == str.charAt(j)) pi[i] = j + 1;
        }
        return pi;
    }

    private static List<Integer> manakerOdds(String str) {
        int len = str.length();
        if ((len & 1) != 1) {
            str = Stream.of(str.split("")).map(ch -> ch + "#").collect(Collectors.joining());
            len = str.length() - 1;
            str = str.substring(0, len);
        }
        List<Integer> m = new ArrayList<>();
        IntStream.range(0, len).forEach(i -> m.add(0));
        for (int i = 1, l = 0, r = 0; i < len; i += 1) {
            if (i < r) m.set(i, Math.min(r - i + 1, m.get(l + r - i)));
            while (i - m.get(i) >= 0 && i + m.get(i) < len && str.charAt(i - m.get(i)) == str.charAt(i + m.get(i))) m.set(i, m.get(i) + 1);
            if (i + m.get(i) - 1 > r) {
                l = i - m.get(i) + 1;
                r = i + m.get(i) - 1;
            }
        }
        List<Integer> res = IntStream.range(0, m.size())
                .filter(i -> (i & 1) != 1).mapToObj(i -> m.get(i) / 2).collect(Collectors.toList());
        return res;
    }
}

package TestYourSelf.Bor;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        AhoKorasik bor = new AhoKorasik();
        String[] keyWords1 = {"i", "in", "tin", "sting"};
        String[] keyWords2 = {"he", "s", "she", "his", "hers", "him"};
        String[] keyWords3 = {"a", "aa", "aaa", "aaaa", "hers", "s"};
        String[] keyWords4 = {"a", "bc", "abc", "dab", "dabc"};


        String textForAnal = "   stinghersstinger dabc";
        List.of(keyWords1, keyWords2, keyWords3, keyWords4).forEach(bor::addKeyWord);
        bor.initBor();
        System.out.println(textForAnal);
        System.out.println(bor.analizeText(textForAnal));
        bor.dfs();

        bor = new AhoKorasik();
        String str = "abracadabra";
        String[] suffixes = getSuffixes(str);
        Arrays.stream(suffixes).forEach(bor::addKeyWord);
        bor.initBor();
        System.out.println(bor.analizeText(str));
        long startTime = System.nanoTime();
        bor.dfs();
        long endTime = System.nanoTime();
        System.out.printf("%f8\n", (endTime - startTime) / 1e9);
    }

    private static String[] getSuffixes(String str) {
        int len = str.length();
        String[] res = new String[len - 1];
        for (int i = 1; i < len; i += 1) {
            res[i - 1] = str.substring(len - i, len);
        }
        return res;
    }
}

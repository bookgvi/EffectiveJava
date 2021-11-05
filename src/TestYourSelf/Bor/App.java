package TestYourSelf.Bor;

import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        AhoKorasik bor = new AhoKorasik();
        String[] keyWords1 = {"he", "she", "his", "hers"};
        String[] keyWords2 = {"a", "abba", "aca"};

        String textForAnal = "abacabba";
        List.of(keyWords1, keyWords2).forEach(bor::addKeyWord);
        bor.initBor();
        System.out.println(textForAnal);
        Map<String, List<Integer>> res = bor.analizeText(textForAnal);
        List<String> resValues = res.keySet().stream().sorted().collect(Collectors.toList());
        System.out.println(resValues.get(resValues.size() - 1));
//        bor.dfs();
        System.out.println(res);
        System.out.println();

        String str = "abracadabra";
        String[] suffixes = getSuffixes(str);
        SuffixArrayViaKarasik bor1 = new SuffixArrayViaKarasik();
        Arrays.stream(suffixes).forEach(bor1::addKeyWord);
        bor1.initBor();
//        System.out.println(bor1.analizeText(str));
        long startTime = System.nanoTime();
        bor1.dfs();
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

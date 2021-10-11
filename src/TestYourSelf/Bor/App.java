package TestYourSelf.Bor;

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
    }
}

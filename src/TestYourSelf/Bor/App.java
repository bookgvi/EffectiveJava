package TestYourSelf.Bor;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        AhoKorasik bor = new AhoKorasik();
//        String[] keyWords = {"i", "in", "tin", "sting"};
//        String textForAnal = "sting";
//        String[] keyWords = {"he",  "s", "she", "his", "hers", "him"};
//        String textForAnal = "shsshers";
        String[] keyWords = {"a",  "aa", "aaa", "aaaa", "hers", "s"};
        String textForAnal = "shsshersaaaaaaaaa";


        bor.addKeyWord(keyWords);

        System.out.println(Arrays.toString(keyWords));
        System.out.println(textForAnal);
        System.out.println(bor.searchForStrings(textForAnal));
    }
}

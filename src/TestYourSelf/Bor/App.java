package TestYourSelf.Bor;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        AhoKorasik bor = new AhoKorasik();
        String[] strArr = {"i", "in", "tin", "sting"};
        String[] keyWords = {"he",  "s", "she", "his", "hers", "him"};
        String textForAnal = "sting";
        String text = "shsshers";


        bor.addKeyWord(strArr);

        System.out.println(Arrays.toString(strArr));
        System.out.println(textForAnal);
        System.out.println(bor.searchForStrings(textForAnal));
    }
}

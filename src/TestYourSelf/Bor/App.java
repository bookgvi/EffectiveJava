package TestYourSelf.Bor;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        Bor bor = new Bor();
        String[] strArr = {"ab", "about", "at", "ate", "be", "bed", "edge", "get"};
        String[] keyWords = {"he",  "s", "she", "his", "hers", "him"};
        String strToFind = "abccab";
        String text = "shsshers";


        bor.addKeyWords(keyWords);

        System.out.println(Arrays.toString(keyWords));
        System.out.println(text);
        System.out.println(bor.analizeText(text));
    }
}

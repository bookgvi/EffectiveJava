package TestYourSelf.Bor;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        AhoKorasik bor = new AhoKorasik();
        String[] strArr = {"a", "ab", "bab", "bc", "bca", "c", "caa"};
        String strToFind = "abccab";
        bor.addKeyWord(strArr);

        System.out.println(Arrays.toString(strArr));
        System.out.println(strToFind);
        System.out.println(bor.analizeText(strToFind));
    }
}

package Algos.Bor;

public class App {
    public static void main(String[] args) {
        AhoKorasik bor = new AhoKorasik();
        String[] keyWords = {"a", "abba", "acb", "ac"};
        String text = "abacabba";

        for (String keyWord : keyWords) bor.addKeyWords(keyWord);
        System.out.println(text);
        System.out.println(bor.textAnalyze(text));
    }
}

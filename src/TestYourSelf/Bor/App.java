package TestYourSelf.Bor;

public class App {
    public static void main(String[] args) {
        AhoKorasik bor = new AhoKorasik();
        String[] strArr = {"he", "she", "his", "hers", " t"};
        for (String str : strArr) {
            bor.addString(str);
        }
//        bor.dfs("root");
        bor.bfs("root");
        System.out.println();
        System.out.println(bor.findStr("is she sister"));
        bor.bfs("h", true);
    }
}

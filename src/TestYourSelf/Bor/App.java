package TestYourSelf.Bor;

public class App {
    public static void main(String[] args) {
        AhoKorasik bor = new AhoKorasik();
        String[] strArr = {"fuck", "cunt", "ass"};
        String strToFind = "Motherfucker fuck for funny. Kiss my ass. Dirty cunt";
        for (String str : strArr) {
            bor.addString(str);
        }
//        bor.dfs("root");
        bor.bfs("root");
        System.out.println(strToFind);
        System.out.println(bor.findKeyWords(strToFind));
//        bor.bfs("h", true);
    }
}

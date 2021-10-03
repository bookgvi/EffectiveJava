package TestYourSelf.Bor;

public class App {
    public static void main(String[] args) {
        Bor bor = new Bor();
        String str1 = "Hello!";
        String str2 = "Helping.";
        bor.addString(str1);
        bor.addString(str2);
        bor.dfs("e");
        bor.dfs("H");
//        bor.dfs("H");
//        System.out.println(bor.findString("Hel"));
    }
}

package TestYourSelf.Bor;

public class App {
    public static void main(String[] args) {
        Bor bor = new Bor();
        String str1 = "Hello!";
        String str2 = "Helsing.";
        bor.addString(str1);
        bor.addString(str2);
        bor.dfs("H");
        bor.dfs("H");
    }
}

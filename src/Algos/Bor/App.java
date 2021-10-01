package Algos.Bor;

public class App {
    public static void main(String[] args) {
        Bor bor = new Bor();
        String str1 = "Hello!";
        String str2 = "Helsing.";
        bor.addString(str1);
        bor.addString(str2);
        bor.dfs("H");
        bor.dfs("H");
        System.out.println(bor.findString(str1));
        System.out.println(bor.findString("Q" + str1));
        System.out.println(bor.findString("lo"));
        System.out.println(bor.findString("Hell"));
        System.out.println(bor.findString("Helsing."));
    }
}

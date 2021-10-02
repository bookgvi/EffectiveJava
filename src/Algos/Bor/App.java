package Algos.Bor;

public class App {
    public static void main(String[] args) {
        Bor bor = new Bor();
        String str1 = "Hello!";
        String str2 = "Helsing.";
        String str3 = "Peace.";
        String str4 = "Peaceful";
        bor.addString(str1);
        bor.addString(str2);
        bor.addString(str3);
        bor.addString(str4);
        bor.dfs("H");
        bor.bfs("H");
        bor.dfs("P");
        bor.bfs("P");
        System.out.println(bor.findString(str1));
        System.out.println(bor.findString("Q" + str1));
        System.out.println(bor.findString("Peace"));
        System.out.println(bor.findString("Hell"));
        System.out.println(bor.findString("Helsing."));
    }
}

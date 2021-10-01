package Algos.Bor;

import java.util.Arrays;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        Bor bor = new Bor();
        String str = "Hello wow!";
        String str1 = "Helsing";
        bor.addString(str);
        bor.addString(str1);
        bor.dfs("H");
        bor.dfs("H");

        String str2 = "ing";
        System.out.println(bor.findString("Hello wow!"));
    }
}

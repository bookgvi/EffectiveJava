package Leetcode.Simple.CombinIter;

public class App {
    public static void main(String[] args) {
        String str = "bdareg";
        int len = 4;
        CombinationIterator ci = new CombinationIterator(str, len);
        while (ci.hasNext()) {
            System.out.println(ci.next());
        }
    }
}

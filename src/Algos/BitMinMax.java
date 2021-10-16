package Algos;

public class BitMinMax {

    public static void main(String[] args) {
        final int x = -10, y = 21;
        System.out.printf("%d < %d\n", min(x, y), max(x, y));
    }

    private static int min(int a, int b) {
        return (b + ((a - b) & (a - b >> 31)));
    }

    private static int max(int a, int b) {
        return (a - ((a - b) & ((a - b) >> 31)));
    }
}

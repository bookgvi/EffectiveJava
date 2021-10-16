package Algos;

public class BitMinMax {

    public static void main(String[] args) {
        final int x = -210, y = 21;
        System.out.printf("Min: %d, max: %d\n", min(x, y), max(x, y));
    }
    private static int min(int x, int y) {
        return y + ((x - y) & ((x - y) >> 31));
    }

    private static int max(int x, int y) {
        return x - ((x - y) & ((x - y) >> 31));
    }
}

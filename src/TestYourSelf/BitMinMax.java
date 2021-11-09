package TestYourSelf;

public class BitMinMax {

    public static void main(String[] args) {
        final int x = -21, y = -100;
        System.out.printf("%d < %d\n", min(x, y), max(x, y));
    }

    private static int min(int x, int y) {
        return y - ((y - x) & ((x - y) >> 31));
    }

    private static int max(int x, int y) {
        return x + ((y - x) & ((x - y) >> 31));
    }
}

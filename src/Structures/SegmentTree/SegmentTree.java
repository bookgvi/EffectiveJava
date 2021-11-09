package Structures.SegmentTree;

public class SegmentTree {
    private static final int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
    private static final int n = arr.length;
    private static final int N = (int) 1e5;
    private static final int[] st = new int[2 * n];

    private static void build() {
        System.arraycopy(arr, 0, st, n, n);
        for (int i = n - 1; i > 0; i -= 1)
            st[i] = st[i << 1] + st[i << 1 | 1];
    }

    private static void modify(int p, int value) {

    }

    public static void main(String[] args) {
        build();
//        int res = query(1, 7);
//        modify(0, 0);
//        res = query(0, 3);
//        modify(0, -1);
//        res = query(0, n - 1);
    }
}

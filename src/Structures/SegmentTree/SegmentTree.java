package Structures.SegmentTree;

public class SegmentTree {
    private static final int[] arr = {7,1,5,3,6,4};
    private static final int n = arr.length;
    private static final int N = (int) 1e5;
    private static final int[] st = new int[2 * n];

    private static void build() {
        System.arraycopy(arr, 0, st, n, n);
        for (int i = n - 1; i > 0; i -= 1)
            st[i] = st[i << 1 | 1] + st[i << 1];
    }

    private static void modify(int p, int value) {
        for (st[p += n] = value; p > 1; p >>= 1)
            st[p >> 1] = st[p] - st[p ^ 1];
    }

    private static int query(int l, int r) {
        int res = 0;
        r += 1;
        for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
            if ((l & 1) == 1) res += st[l++];
            if ((r & 1) == 1) res += st[--r];
        }
        return res;
    }

    public static void main(String[] args) {
        build();
        int res = query(1, 7);
//        modify(0, 0);
//        res = query(0, 3);
//        modify(0, -1);
        int res2 = query(1, 2);
        int res3 = query(3, 4);
    }
}

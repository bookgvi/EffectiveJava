package Structures.SegmentTree;

public class RMQ {
    private static int[] buildST(int[] arr) {
        int len = arr.length;
        int[] st = new int[len << 1];
        for (int i = 0; i < len; i += 1)
            st[i + len] = arr[i];
        for (int i = len - 1; i > 0; i -= 1)
            st[i] = Math.min(st[i << 1], st[i << 1 | 1]);
        return st;
    }

    private static int query(int l, int r, int[] st) {
        int res = Integer.MAX_VALUE, n = st.length >> 1;
        r += 1;
        for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
            if ((l & 1) == 1) res = Math.min(res, st[l++]);
            if ((r & 1) == 1) res = Math.min(res, st[--r]);
        }
        return res;
    }

    private static void modify(int v, int val, int[] st) {
        int n = st.length >> 1;
        for (st[v += n] = val; v > 1; v >>= 1)
            st[v >> 1] = Math.min(st[v], st[v ^ 1]);
    }

    public static void main(String[] args) {
        int[] arr = {13, 3, 2, 5, 6, 12, 11, 2, 3, 4};
        int min;
        int[] st = buildST(arr);
        min = query(3, 6, st);
        modify(5, 1, st);
        min = query(3, 6, st);
    }
}

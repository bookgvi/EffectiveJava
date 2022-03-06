package Structures.SegmentTree;

import java.util.Arrays;

public class SegmentTreeStructure {
    private static int[] build(int[] arr) {
        int len = arr.length;
        int[] st = new int[len << 1];
        System.arraycopy(arr, 0, st, len, len);
        for (int v = len - 1; v > 0; v -= 1)
            st[v] = st[v << 1] + st[v << 1 | 1];
        return st;
    }

    private static int[] modify(int v, int value, int[] origSt) {
        int[] st = Arrays.copyOf(origSt, origSt.length);
        int n = st.length >> 1;
        for (st[v += n] = value; v > 1; v >>= 1)
            st[v >> 1] = st[v] + st[v ^ 1];
        return st;
    }

    private static int query(int l, int r, int[] st) {
        int res = 0, n = st.length >> 1;
        r += 1;
        r = Math.min(r, n);
        for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
            if ((l & 1) == 1) res += st[l++];
            if ((r & 1) == 1) res += st[--r];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3};
        int[] st = build(arr);
        int[] modSt = modify(arr.length - 1, 7, st);
        int sumRange = query(0, 1, modSt);
    }
}

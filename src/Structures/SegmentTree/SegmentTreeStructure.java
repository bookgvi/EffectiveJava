package Structures.SegmentTree;

import java.util.Arrays;

public class SegmentTreeStructure {
    private static int[] build(int[] arr) {
        int len = arr.length, n = len << 1;
        int[] st = new int[n];
        System.arraycopy(arr, 0, st, len, len);
        for (int i = len - 1; i > 0; i -= 1)
            st[i] = st[i << 1] + st[i << 1 | 1];
        return st;
    }

    private static int[] modify(int v, int val, int[] stOrig) {
        int len = stOrig.length, n = len >> 1;
        int[] st = Arrays.copyOf(stOrig, len);
        for(st[v += n] = val; v > 1; v >>= 1)
            st[v >> 1] = st[v] + st[v ^ 1];
        return st;
    }

    private static int query(int l, int r, int[] st) {
        int res = 0, n = st.length >> 1;
        r += 1;
        for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
            if ((l & 1) == 1) res += st[l++];
            if ((r & 1) == 1) res += st[--r];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {5,8,6,3,2,7,2,6};
        int[] st = build(arr);
        int[] modSt = modify(arr.length - 1, 7, st);
        int sumRange = query(0, 6, modSt);
    }
}

package Structures.Set;

import java.util.*;

public class UniqElts {
    private static final Map<Integer, List<Integer>> g = new HashMap<>();
    private static final Set<Integer> used = new HashSet<>();
    private static final List<Integer> ans = new ArrayList<>();
    private static final List<Integer> t = new ArrayList<>();

    private static List<Integer> sort(List<Integer> arr) {
        for (int i = 0, len = arr.size(); i < len; i += 1)
            for (int j = i; j > 0 && arr.get(j - 1) - arr.get(j) > 0; j -= 1)
                swap(j - 1, j, arr);
        return arr;
    }

    private static void swap(int i, int j, List<Integer> arr) {
        int tmp = arr.get(i);
        arr.set(i, j);
        arr.set(j, tmp);
    }

    private static void init(int[][] quasiSet) {
        for (int[] l : quasiSet) {
            int l0 = l[0];
            for (int i = 1; i <= l.length; i += 1) {
                t.add(l[i - 1]);
                g.put(l0, t);
                t.add(l0);
                g.put(l[i - 1], t);
            }
        }
    }

    private static void dfs(int v) {
        used.add(v);
        ans.add(v);
        for (Integer next : g.get(v)) {
            if (!used.contains(next)) {
                dfs(next);
            }
        }
    }

    private static List<Integer> buildSet(int[][] quasiSet) {
        init(quasiSet);
        for (int[] l : quasiSet) {
            if(!used.contains(l[0])) dfs(l[0]);
        }
        return sort(ans);
    }

    public static void main(String[] args) {
        int[][] quasiSet = new int[][]{{6, 1, 2, 3, 4}, {6, 1, 3, 5, 7, 8}};
        List<Integer> res = buildSet(quasiSet);
    }
}

package Structures.Graph.AnotheRealization;

import java.util.*;
import java.util.stream.*;

/*
*       0
*      / \
*     1   4
*    / \   \
*   2   3 — 5
*
*
* */
public class SomeGraphII {
    private static final int VERTEX_COUNT = 6;
    private static final List<List<Integer>> g = IntStream.rangeClosed(0, VERTEX_COUNT).mapToObj(i -> new ArrayList<Integer>()).collect(Collectors.toList());
    private static List<Integer> used, p, time_in, time_out, cycle, mst;
    private static int isCycle = 0, time = 0, cycle_start = 0, cycle_end = 0;

    private static void init() {
        used = IntStream.rangeClosed(0, VERTEX_COUNT).mapToObj(i -> 0).collect(Collectors.toList());
        p = IntStream.rangeClosed(0, VERTEX_COUNT).mapToObj(i -> 0).collect(Collectors.toList());
        time_in = IntStream.rangeClosed(0, VERTEX_COUNT).mapToObj(i -> 0).collect(Collectors.toList());
        time_out = IntStream.rangeClosed(0, VERTEX_COUNT).mapToObj(i -> 0).collect(Collectors.toList());
        cycle = new ArrayList<>();
        mst = new ArrayList<>();
        isCycle = 0;
        time = 0;
        cycle_start = 0;
        cycle_end = 0;
    }

    private static void addEdge(int start, int end) {
        g.get(start).add(end);
        g.get(end).add(start);
    }

    private static void build() {
        init();
        addEdge(0, 1);
        addEdge(0, 4);
        addEdge(4, 5);
        addEdge(1, 2);
        addEdge(1, 3);
//        addEdge(3, 5);
    }

    private static void dfs(int v) {
        used.set(v, 1);
        time_in.set(v, time++);
        for (int next : g.get(v)) {
            if (used.get(next) == 0) {
                p.set(next, v);
                dfs(next);
            } else if (used.get(next) == 1 && next != p.get(v)) {
                isCycle = 1;
                cycle_start = next;
                cycle_end = v;
            }
        }
        used.set(v, 2);
        time_out.set(v, time++);
        mst.add(v);
    }

    private static void getCycle() {
        while (cycle_end != cycle_start) {
            cycle.add(cycle_end);
            cycle_end = p.get(cycle_end);
        }
        cycle.add(cycle_start);
    }

    private static void swap(int i, int j, List<Integer> arr) {
        int tmp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, tmp);
    }

    private static List<Integer> reverse(List<Integer> arr) {
        for (int i = 0, len = arr.size(); i <= (len - 1) >> 1; i += 1) {
            int tmp = (len - 1) - i;
            swap(tmp, i, arr);
        }
        return arr;
    }

    public static void main(String[] args) {
        build();
        dfs(0);
        reverse(mst);
        System.out.println("MST:");
        for (int i : mst)
            System.out.printf("%d (%d, %d)\n", i, time_in.get(i), time_out.get(i));
        if (isCycle == 1) {
            getCycle();
            reverse(cycle);
            System.out.println("\nCycle:");
            for (int i = 0, len = cycle.size(); i < len; i += 1)
                System.out.printf("%d (%d, %d)\n", cycle.get(i), time_in.get(i), time_out.get(i));
        }

    }
}

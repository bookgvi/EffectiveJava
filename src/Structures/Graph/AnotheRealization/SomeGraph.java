package Structures.Graph.AnotheRealization;

import java.util.*;

public class SomeGraph {
    private static final Map<Integer, Map<Integer, Integer>> adj = new HashMap<>();
    private static final int[] visited = new int[10], mst = new int[10], timeIn = new int[10], timeOut = new int[10];
    private static int time = 0;

    private static void addEdge(int start, int end) {
        Map<Integer, Integer> cols = adj.getOrDefault(start, new HashMap<>());
        cols.putIfAbsent(end, 1);
        adj.putIfAbsent(start, cols);
    }

    private static void build() {
        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(0, 3);
        addEdge(1, 4);
        addEdge(1, 8);
        addEdge(2, 5);
        addEdge(3, 6);
        addEdge(3, 7);
        addEdge(7, 9);
    }

    private static void dfs(int v) {
        visited[v] = 1;
        timeIn[v] = time++;
        mst[v] = v;
        Map<Integer, Integer> vertexes = adj.getOrDefault(v, new HashMap<>());
        for (int nextV : vertexes.keySet()) {
            if (visited[nextV] != 1) dfs(nextV);
        }
        timeOut[v] = time++;
    }

    public static void main(String[] args) {
        build();
        dfs(0);
        for (int i : mst) {
            System.out.printf("%d(%d, %d)\n", i, timeIn[i], timeOut[i]);
        }
    }
}

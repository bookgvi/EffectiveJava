package Structures.Graph.DSU;

public class DSU {
    private static final int n = 10;
    private static int[] parent = new int[n];
    private static int[] size = new int[n];

    private static void createSet(int v) {
        parent[v] = v;
    }

    private static int findSet(int v) {
        if (parent[v] == v) return v;
        return parent[v] = findSet(parent[v]);
    }

    private static void unionSet(int a, int b) {
        a = findSet(a);
        b = findSet(b);
        if (a != b)
            if (size[a] < size[b]) {
                int t = a;
                a = b;
                b = t;
            }
        parent[b] = a;
        size[a] += size[b];
    }

}

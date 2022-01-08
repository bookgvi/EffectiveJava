package Structures.Graph.DSU;

public class DSU {
    private static final int n = 10;
    private static final int[] parent = new int[n];
    private static final int[] size = new int[n];

    private static int createSet(int v) {
        return parent[v] = v;
    }

    private static int findSet(int v) {
        if (parent[v] == v) return v;
        return parent[v] = findSet(parent[v]);
    }

    private static int unionSets(int a, int b) {
        a = findSet(a);
        b = findSet(b);
        if (a != b)
            if (size[a] < size[b])
                swap(a, b, size);
        parent[b] = a;
        size[a] += size[b];
        return a;
    }

    private static void swap(int a, int b, int[] arr) {
        int t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }
}

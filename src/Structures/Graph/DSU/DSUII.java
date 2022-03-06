package Structures.Graph.DSU;

public class DSUII {
    private static final int n = 10;
    private static int[] size = new int[n];
    private static int[] parent = new int[n];

    private static int find_set(int v) {
        if (v == parent[v]) return v;
        return parent[v] = find_set(parent[v]);
    }

    private static void create_set(int v) {
        parent[v] = v;
    }

    private static void merge_set(int a, int b) {
        a = find_set(a);
        b = find_set(b);
        if (a != b) {
            if (size[a] < size[b])
                swap(a, b, size);
            parent[b] = a;
            size[a] += size[b];
        }
    }

    private static void swap(int i, int j, int[] arr) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

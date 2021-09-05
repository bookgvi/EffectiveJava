package Algos.Graphs;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        Graph graph = Graph.INSTANCE;

        graph.addVertex("A"); // 0
        graph.addVertex("B"); // 1
        graph.addVertex("C"); // 2
        graph.addVertex("D"); // 3
        graph.addVertex("E"); // 4
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");

        graph.addEdge(0, 1); // AB
        graph.addEdge(1, 2); // BC
        graph.addEdge(0, 4); // AD
        graph.addEdge(3, 4); // DE

        System.out.println(Arrays.deepToString(graph.get_matrix()));
        graph.dfs(0);
    }
}
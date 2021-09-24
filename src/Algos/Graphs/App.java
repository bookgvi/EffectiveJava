package Algos.Graphs;

public class App {
    public static void main(String[] args) {
        IGraph graph = new Graph(10);
        GraphUtils graphUtils = GraphUtils.getInstance(graph);

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
        graph.addEdge(0, 3); // AD
        graph.addEdge(3, 4); // DE

//        System.out.println(Arrays.deepToString(graph.get_matrix()));
        graphUtils.dfs(0);
        System.out.println();
        graphUtils.bfs(0);
    }
}
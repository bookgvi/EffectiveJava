package Structures.Graph;

public class App {
    public static void main(String[] args) {
//        GraphR5 theGraph = new GraphR5(10);
        GraphR theGraph = new GraphR(10);
        theGraph.addVertex("A");
        theGraph.addVertex("B");
        theGraph.addVertex("C");
        theGraph.addVertex("D");
        theGraph.addVertex("E");
        theGraph.addEdge("A", "C");
        theGraph.addEdge("A", "D");
        theGraph.addEdge("B", "D");
        theGraph.addEdge("B", "E");
        theGraph.addEdge("C", "E");

        theGraph.dfs("A");
        System.out.println();
        theGraph.dfs("C");
        System.out.println();
        theGraph.bfs("A");
        System.out.println();
//        theGraph.mstD("A");
//        theGraph.mstB("A");
//        theGraph.mstB("C");
    }
}

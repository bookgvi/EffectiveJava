package Algos.Graph;

public class App {
    public static void main(String[] args) {
//        GraphR5 theGraph = new GraphR5(10);
        GraphR theGraph = new GraphR(10);
        theGraph.addVertex("A");
        theGraph.addVertex("B");
        theGraph.addVertex("C");
        theGraph.addVertex("D");
        theGraph.addVertex("E");
        theGraph.addEdge(0, 1);
        theGraph.addEdge(1, 2);
        theGraph.addEdge(0, 3);
        theGraph.addEdge(3, 4);

//        theGraph.dfs(0);
//        System.out.println();
//        theGraph.dfs(2);
//        System.out.println();
//        theGraph.bfs(0);
//        System.out.println();
        theGraph.mstD(0);
//        theGraph.mstB(0);
//        theGraph.mstB(3);
    }
}

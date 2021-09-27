package TestYourSelf.Graph;


public class App {
    public static void main(String[] args) {
        GraphR5 theGraph = new GraphR5(10);
        theGraph.addVertex("A");
        theGraph.addVertex("B");
        theGraph.addVertex("C");
        theGraph.addVertex("D");
        theGraph.addVertex("E");
        theGraph.addEdge(0, 1);
        theGraph.addEdge(1, 2);
        theGraph.addEdge(0, 3);
        theGraph.addEdge(3, 4);

        theGraph.mstB(0);
        System.out.println();
        theGraph.dfs(0);
        System.out.println();
    }

}

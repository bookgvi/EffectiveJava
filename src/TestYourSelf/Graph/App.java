package TestYourSelf.Graph;


public class App {
    public static void main(String[] args) {
        GraphR5 theGraph = new GraphR5(10);
        theGraph.addVertex("A");
        theGraph.addVertex("B");
        theGraph.addVertex("C");
        theGraph.addVertex("D");
        theGraph.addVertex("E");
        theGraph.addVertex("F");
        theGraph.addVertex("G");
        theGraph.addVertex("H");
        theGraph.addVertex("I");
        theGraph.addEdge(1, 2);
        theGraph.addEdge(1, 3);
        theGraph.addEdge(1, 4);
        theGraph.addEdge(3, 4);
        theGraph.addEdge(3, 6);
        theGraph.addEdge(2, 4);
        theGraph.addEdge(4, 6);
        theGraph.addEdge(4, 8);

        theGraph.displayAdjMatrix();

        theGraph.mstB(6);
        System.out.println();

        theGraph.dfs(6);
        System.out.println();
        theGraph.dfs(7);
        System.out.println();
    }

}

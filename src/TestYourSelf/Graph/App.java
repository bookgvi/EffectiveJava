package TestYourSelf.Graph;


public class App {
    public static void main(String[] args) {
        GraphR5 theGraph = new GraphR5();
        theGraph.addVertex("A");
        theGraph.addVertex("B");
        theGraph.addVertex("C");
        theGraph.addVertex("D");
        theGraph.addVertex("E");
        theGraph.addVertex("F");
        theGraph.addVertex("G");
        theGraph.addVertex("H");
        theGraph.addVertex("I");

        theGraph.addEdge("A", "C");
        theGraph.addEdge("A", "D");
        theGraph.addEdge("B", "D");
        theGraph.addEdge("B", "E");
        theGraph.addEdge("C", "A");
        theGraph.addEdge("C", "E");
        theGraph.addEdge("D", "B");
        theGraph.addEdge("D", "A");
        theGraph.addEdge("E", "B");
        theGraph.addEdge("E", "C");


        theGraph.dfs("A");
        theGraph.dfs("B");
//        theGraph.bfs("B");
//        theGraph.mstB("B");
    }

}

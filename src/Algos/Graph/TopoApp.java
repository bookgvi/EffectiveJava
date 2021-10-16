package Algos.Graph;

public class TopoApp {
    public static void main(String[] args) {
        Topo theGraph = new Topo();

        theGraph.addVertex("A");
        theGraph.addVertex("B");
        theGraph.addVertex("C");
        theGraph.addVertex("D");
        theGraph.addVertex("E");
        theGraph.addVertex("F");
        theGraph.addVertex("G");
        theGraph.addVertex("H");
        theGraph.addEdge("A", "B");
        theGraph.addEdge("A", "C");

        theGraph.dfs("C", true);
        theGraph.dfs("B", true);
        theGraph.dfs("C", true);
        theGraph.dfs("D", true);
        theGraph.displayTopo(); // BAEDGCFH (?)
    }
}

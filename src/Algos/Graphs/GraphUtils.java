package Algos.Graphs;

public class GraphUtils {
    private static IGraph _graph;
    private GraphUtils() {}

    public static GraphUtils getInstance(IGraph graph) {
        _graph = graph;
        return new GraphUtils();
    }

    public void hasVisited(int startVertex) {
    }
}

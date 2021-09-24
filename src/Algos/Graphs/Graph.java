package Algos.Graphs;

public class Graph extends AbstractGraph {
    public Graph(int max_vertex) {
        super(max_vertex);
    }

    @Override
    public void displayVertex(int vertexNum) {
        System.out.println(getVertexes()[vertexNum].getLabel());
    }

    @Override
    public void addEdge(int start, int end) {
        int[][] adjMatrix = getAdjMatrix();
        adjMatrix[start][end] = 1;
        adjMatrix[end][start] = 1;
        setAdjMatrix(adjMatrix);
    }

}

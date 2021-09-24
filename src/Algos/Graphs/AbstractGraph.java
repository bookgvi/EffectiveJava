package Algos.Graphs;

import java.util.Arrays;

public abstract class AbstractGraph implements IGraph {
    private IVertex[] vertexes;
    private int vertexQuantity = 0;
    private int[][] adjMatrix;

    public AbstractGraph(int max_vertex) {
        vertexes = new Vertex[max_vertex];
        adjMatrix = new int[max_vertex][max_vertex];
    }

    @Override
    public IVertex[] getVertexes() {
        return Arrays.copyOf(vertexes, vertexes.length);
    }

    @Override
    public void setVertexes(IVertex[] vertexes) {
        this.vertexes = vertexes;
    }

    @Override
    public int[][] getAdjMatrix() {
        return Arrays.copyOf(adjMatrix, adjMatrix.length);
    }

    @Override
    public void setAdjMatrix(int[][] adjMatrix) {
        this.adjMatrix = adjMatrix;
    }

    @Override
    public int getVertexesQuantity() {
        return vertexQuantity;
    }

    @Override
    public void setVertexesQuantity(int num) {
        this.vertexQuantity = num;
    }

    public abstract void displayVertex(int vertexNum);

    public abstract void addEdge(int start, int end);

    @Override
    public void addVertex(String label) {
        vertexes[vertexQuantity++] = new Vertex(label);
    }

    private static class Vertex implements IVertex {
        private final String _label;
        private boolean _isVisited;

        private Vertex(String label) {
            this._label = label;
            this._isVisited = false;
        }

        @Override
        public boolean hasVisited() {
            return _isVisited;
        }

        @Override
        public void setVisited(boolean isVisited) {
            this._isVisited = isVisited;
        }

        @Override
        public String getLabel() {
            return _label;
        }
    }
}

package Structures.Graphs;

public interface IGraph {
    void displayVertex(int vertexNum);
    void addEdge(int start, int end);
    void addVertex(String label);
    int[][] getAdjMatrix();
    void setAdjMatrix(int[][] adjMatrix);
    IVertex[] getVertexes();
    void setVertexes(IVertex[] vertexes);
    int getVertexesQuantity();
    void setVertexesQuantity(int num);
}

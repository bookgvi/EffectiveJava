package Algos.Graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GraphR implements IGraph {
    private final int MAX_SIZE = 20;
    private final int[][] matrixAdj;
    private int numVertex;
    private final Vertex[] vertexes;

    public GraphR() {
        this.matrixAdj = new int[MAX_SIZE][MAX_SIZE];
        this.numVertex = 0;
        vertexes = new Vertex[MAX_SIZE];
    }

    public void addVertex(String label) {
        this.vertexes[numVertex++] = new Vertex(label);
    }

    public void addEdge(int start, int end) {
        matrixAdj[start][end] = 1;
        matrixAdj[end][start] = 1;
    }

    public void displayVertex(int num) {
        System.out.println(vertexes[num].label);
    }

    public int hasVisited(int vertexNum) {
        for (int i = 0; i < numVertex; i += 1) {
            if (!vertexes[i].isVisited && matrixAdj[vertexNum][i] == 1) {
                return i;
            }
        }
        return -1;
    }

    public void dfs(int startVertex) {
        vertexes[startVertex].isVisited = true;
        Stack<Integer> vertexStack = new Stack<>();
        displayVertex(startVertex);
        vertexStack.push(startVertex);
        while (!vertexStack.isEmpty()) {
            int vertex = vertexStack.peek();
            int nextVertex = hasVisited(vertex);
            if (nextVertex == -1) vertexStack.pop();
            else {
                vertexes[nextVertex].isVisited = true;
                vertexStack.push(nextVertex);
                displayVertex(nextVertex);
            }
        }
    }

    public int _setVisited(int vertexNum) {
        for (int i = 0; i < numVertex; i += 1) {
            if (!vertexes[i].isVisited && matrixAdj[vertexNum][i] == 1) {
                return i;
            }
        }
        return -1;
    }

//    public void dfs(int startVertex) {
//        Stack<Integer> vSt = new Stack<>();
//        vertexes[startVertex].isVisited = true;
//        vSt.push(startVertex);
//        displayVertex(startVertex);
//        while (!vSt.isEmpty()) {
//            int nextVertex = setVisited(vSt.peek());
//            if (nextVertex == -1) {
//                vSt.pop();
//            } else {
//                vertexes[nextVertex].isVisited = true;
//                vSt.push(nextVertex);
//                displayVertex(nextVertex);
//            }
//        }
//    }



    private static class Vertex {
        private String label;
        private boolean isVisited;

        public Vertex(String label) {
            this.label = label;
            this.isVisited = false;
        }
    }
}

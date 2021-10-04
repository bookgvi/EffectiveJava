package TestYourSelf.Graph;

import java.util.*;

public class GraphR5 {
    private final Map<String, Vertex> vertexes;
    private final Map<String, Map<String, Edge>> adjMatrix;

    GraphR5() {
        vertexes = new HashMap<>();
        adjMatrix = new HashMap<>();
    }

    public void addVertex(String label) {
        vertexes.putIfAbsent(label, new Vertex(label));
    }

    public void addEdge(String startLabel, String endLabel) {
        Vertex startVertex = vertexes.get(startLabel);
        Vertex endVertex = vertexes.get(endLabel);
        if (startLabel == null || endLabel == null) return;
        Edge edge = new Edge(1, 0);
        Map<String, Edge> col = adjMatrix.getOrDefault(startLabel, new HashMap<>());
        col.putIfAbsent(endLabel, edge);
        adjMatrix.putIfAbsent(startLabel, col);
    }

    public void dfs(String startLabel) {
        Vertex startVertex = vertexes.get(startLabel);
        if (startVertex == null) return;
        Stack<Vertex> vertexStack = new Stack<>();
        startVertex.isVisited = true;
        vertexStack.push(startVertex);
        Vertex nextVertex;
        System.out.print(startLabel);
        while (!vertexStack.isEmpty()) {
            nextVertex = getUnvisited(vertexStack.peek());
            if (nextVertex == null) vertexStack.pop();
            else {
                nextVertex.isVisited = true;
                vertexStack.push(nextVertex);
                System.out.print(nextVertex.label);
            }
        }
        System.out.println();
        setUnvisited();
    }

    private Vertex getUnvisited(Vertex startVertex) {
        Map<String, Edge> cols = adjMatrix.get(startVertex.label);
        if (cols == null) return null;
        for (String nextVertexLabel : cols.keySet()) {
            if (!vertexes.get(nextVertexLabel).isVisited && cols.get(nextVertexLabel).neighbour == 1) {
                return vertexes.get(nextVertexLabel);
            }
        }
        return null;
    }

    private void setUnvisited() {
        for (Vertex nextVertex : vertexes.values()) {
            nextVertex.isVisited = false;
        }
    }

    private static class Vertex {
        private final String label;
        private boolean isVisited;

        Vertex(String label) {
            this.label = label;
            this.isVisited = false;
        }
    }

    private static class Edge {
        private final int neighbour;
        private final int weight;

        Edge(int neighbour, int weight) {
            this.neighbour = neighbour;
            this.weight = weight;
        }
    }
}

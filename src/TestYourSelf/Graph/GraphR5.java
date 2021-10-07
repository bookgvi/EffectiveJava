package TestYourSelf.Graph;

import java.util.*;

public class GraphR5 {
    private Map<String, Vertex> vertexes;
    private Map<String, Map<String, Edge>> adjMatrix;

    GraphR5() {
        this.vertexes = new HashMap<>();
        this.adjMatrix = new HashMap<>();
    }

    public void addVertex(String label) {
        vertexes.putIfAbsent(label, new Vertex(label));
    }

    public void addEdge(String startLabel, String endLabel) {
        Edge edge = new Edge(1, 0);
        Map<String, Edge> col = adjMatrix.getOrDefault(startLabel, new HashMap<>());
        col.putIfAbsent(endLabel, edge);
        adjMatrix.putIfAbsent(startLabel, col);
    }

    public void dfs(String startLabel) {
        Vertex startVertex = vertexes.get(startLabel), curVertex, nextVertex;
        if (startVertex == null) return;
        startVertex.isVisited = true;
        Stack<Vertex> vertexStack = new Stack<>();
        vertexStack.push(startVertex);
        while(!vertexStack.isEmpty()) {
            curVertex = vertexStack.peek();
            nextVertex = getUnvisited(curVertex);
            if (nextVertex == null) vertexStack.pop();
            else {
                nextVertex.isVisited = true;
                vertexStack.push(nextVertex);
                System.out.printf("%s->%s; ", curVertex.label, nextVertex.label);
            }
        }
        System.out.println();
        setUnvisited();
    }

    private Vertex getUnvisited(Vertex startVertex) {
        for (String nextLabel : adjMatrix.get(startVertex.label).keySet()) {
            if (!vertexes.get(nextLabel).isVisited && adjMatrix.get(startVertex.label).get(nextLabel).neighbour == 1) {
                return vertexes.get(nextLabel);
            }
        }
        return null;
    }

    private void setUnvisited() {
        for (Vertex nextVertex : vertexes.values()) nextVertex.isVisited = false;
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

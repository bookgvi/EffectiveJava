package TestYourSelf.Graph;

import java.util.*;

public class GraphR5 {

    private final Map<String, Vertex> vertexes;
    private final Map<String, Map<String, Edge>> adjMatrix;

    GraphR5() {
        this.vertexes = new HashMap<>();
        this.adjMatrix = new HashMap<>();
    }

    void addVertex(String label) {
        vertexes.putIfAbsent(label, new Vertex(label));
    }

    void addEdge(String start, String end) {
        Edge edge = new Edge(1, 0);
        Map<String, Edge> col = adjMatrix.getOrDefault(start, new HashMap<>());
        col.putIfAbsent(end, edge);
        adjMatrix.putIfAbsent(start, col);
    }

    void dfs(String startVertexLabel) {
        Stack<Vertex> vertexStack = new Stack<>();
        Vertex startVertex = vertexes.get(startVertexLabel), curVertex, nextVertex;
        if (startVertex == null) return;
        startVertex.isVisited = true;
        vertexStack.push(startVertex);
        while(!vertexStack.isEmpty()) {
            curVertex = vertexStack.peek();
            if ((nextVertex = getUnvisited(curVertex)) == null) vertexStack.pop();
            else {
                nextVertex.isVisited = true;
                vertexStack.push(nextVertex);
                System.out.printf("%s->%s ", curVertex.label, nextVertex.label);
            }
        }
        System.out.println();
        setUnvisited();
    }

    private Vertex getUnvisited(Vertex curVertex) {
        Map<String, Edge> cols = adjMatrix.get(curVertex.label);
        if (cols == null) return null;
        for (String nextLabel : cols.keySet()) {
            if (!vertexes.get(nextLabel).isVisited && cols.get(nextLabel).neighbours == 1) return vertexes.get(nextLabel);
        }
        return null;
    }

    private void setUnvisited() {
        for(Vertex nextVertex : vertexes.values()) nextVertex.isVisited = false;
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
        private final int neighbours;
        private final int weight;

        Edge(int neighbours, int weight) {
            this.neighbours = neighbours;
            this.weight = weight;
        }
    }
}

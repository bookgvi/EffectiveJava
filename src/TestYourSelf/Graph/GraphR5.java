package TestYourSelf.Graph;

import java.util.*;

public class GraphR5 {
    private final Map<String, Vertex> vertexList;
    private final Map<String, Map<String, Edge>> adjMatrix;

    GraphR5() {
        vertexList = new HashMap<>();
        adjMatrix = new HashMap<>();
    }

    void addVertex(String label) {
        vertexList.putIfAbsent(label, new Vertex(label));
    }

    void addEdge(String startLabel, String endLabel) {
        Edge edge = new Edge(1, 0);
        Map<String, Edge> col = adjMatrix.getOrDefault(startLabel, new HashMap<>());
        col.putIfAbsent(endLabel, edge);
        adjMatrix.putIfAbsent(startLabel, col);
    }

    void dfs(String startLabel) {
        Vertex curVertex = vertexList.get(startLabel), nextVertex;
        if (curVertex == null) return;
        Stack<Vertex> vertexStack = new Stack<>();
        curVertex.isVisited = true;
        vertexStack.push(curVertex);
        while(!vertexStack.isEmpty()) {
            curVertex = vertexStack.peek();
            nextVertex = getUnvisited(curVertex);
            if (nextVertex == null) vertexStack.pop();
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
        Set<String> nextLabels = cols.keySet();
        for (String nextLabel : nextLabels) {
            if (!vertexList.get(nextLabel).isVisited && cols.get(nextLabel).neighbour == 1) return vertexList.get(nextLabel);
        }
        return null;
    }

    private void setUnvisited() {
        for(Vertex v : vertexList.values()) {
            v.isVisited = false;
        }
    }

    private static class Vertex {
        private final String label;
        private Vertex toNext;
        private Vertex parent;
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

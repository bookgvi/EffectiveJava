package Algos.Graph;

import java.util.*;

public class Topo {
    private final Map<String, Vertex> vertexes;
    private final Map<String, Map<String, Edge>> adjMatrix;

    private final Map<String, Vertex> topo;

    Topo() {
        this.vertexes = new HashMap<>();
        this.adjMatrix = new HashMap<>();
        this.topo = new HashMap<>();
    }

    public void addVertex(String label) {
        vertexes.putIfAbsent(label, new Vertex(label));
    }

    public void addEdge(String startLabel, String endLabel) {
        if (vertexes.get(startLabel) == null || vertexes.get(endLabel) == null) return;
        Edge edge = new Edge(1, 0);
        Map<String, Edge> col = adjMatrix.getOrDefault(startLabel, new HashMap<>());
        col.putIfAbsent(endLabel, edge);
        adjMatrix.putIfAbsent(startLabel, col);
    }

    public void dfs(String startLabel) {
        dfs(startLabel, false);
    }

    public void dfs(String startLabel, boolean isDisplay) {
        Vertex currentVertex = vertexes.get(startLabel);
        if (currentVertex == null) return;
        setUnvisited();
        currentVertex.isVisited = true;
        Stack<Vertex> vertexStack = new Stack<>();
        vertexStack.push(currentVertex);
        Vertex nextVertex;
        while (!vertexStack.isEmpty()) {
            currentVertex = vertexStack.peek();
            nextVertex = getUnvisited(currentVertex.label);
            if (nextVertex == null) {
                Vertex v = vertexStack.pop();
                topo.putIfAbsent(v.label, v);
            }
            else {
                nextVertex.isVisited = true;
                vertexStack.push(nextVertex);
                if (isDisplay) System.out.printf("%s -> %s ", currentVertex.label, nextVertex.label);
            }
        }
        if (isDisplay) System.out.println();
    }

    public void displayTopo() {
         for (String label : topo.keySet()) {
             System.out.print(label);
         }
    }

    private Vertex getUnvisited(String startLabel) {
        Map<String, Edge> col = adjMatrix.get(startLabel);
        if (vertexes.get(startLabel) == null || col == null) return null;
        for (String nextLabel : col.keySet()) {
            if (!vertexes.get(nextLabel).isVisited && col.get(nextLabel).neighbour == 1) {
                return vertexes.get(nextLabel);
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

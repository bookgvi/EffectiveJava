package Algos.Graph;

import java.util.*;

public class GraphR5Ref {
    private final Map<String, Vertex> vertexes;
    private final Map<String, Map<String, Edge>> adjMatrix;

    GraphR5Ref() {
        vertexes = new HashMap<>();
        adjMatrix = new HashMap<>();
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
        Vertex curVertex = vertexes.get(startLabel);
        if (curVertex == null) return;
        Stack<Vertex> vertexStack = new Stack<>();
        curVertex.isVisited = true;
        vertexStack.push(curVertex);
        Vertex nextVertex;
        System.out.print(curVertex.label);
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

    public void bfs(String startLabel) {
        Vertex curVertex = vertexes.get(startLabel);
        if (curVertex == null) return;
        VertexQueue<Vertex> vertexQueue = new VertexQueue<>();
        curVertex.isVisited = true;
        vertexQueue.offer(curVertex);
        Vertex nextVertex;
        System.out.print(startLabel);
        while (!vertexQueue.isEmpty()) {
            curVertex = vertexQueue.poll();
            if (curVertex == null) continue;
            while ((nextVertex = getUnvisited(curVertex)) != null) {
                nextVertex.isVisited = true;
                vertexQueue.offer(nextVertex);
                System.out.print(nextVertex.label);
            }
        }
        System.out.println();
        setUnvisited();
    }

    public void mstB(String startLabel) {
        Vertex curVertex = vertexes.get(startLabel);
        if (curVertex == null) return;
        Map<String, Integer> weights = new HashMap<>();
        curVertex.isVisited = true;
        VertexQueue<Vertex> vertexQueue = new VertexQueue<>();
        vertexQueue.offer(curVertex);
        Vertex nextVertex;
        weights.put(startLabel, 0);
        while (!vertexQueue.isEmpty()) {
            curVertex = vertexQueue.poll();
            if (curVertex == null) continue;
            while ((nextVertex = getUnvisited(curVertex)) != null) {
                nextVertex.isVisited = true;
                vertexQueue.offer(nextVertex);
                weights.putIfAbsent(nextVertex.label, weights.getOrDefault(curVertex.label, 0) + 1);
                System.out.printf("%s -> %s ", curVertex.label, nextVertex.label);
            }
        }
        System.out.println();
        System.out.println(weights);
        setUnvisited();
    }

    private Vertex getUnvisited(Vertex startVertex) {
        Map<String, Edge> cols = adjMatrix.get(startVertex.label);
        if (cols == null) return null;
        for (String label : cols.keySet()) {
            if (!vertexes.get(label).isVisited && cols.get(label).neighbour == 1) {
                return vertexes.get(label);
            }
        }
        return null;
    }

    private void setUnvisited() {
        for (Vertex v : vertexes.values()) {
            v.isVisited = false;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////

    private static class Vertex {
        private final String label;
        private boolean isVisited;

        Vertex(String label) {
            this.label = label;
            this.isVisited = false;
        }
    }

    private static class Edge {
        private int neighbour;
        private int weight;

        Edge(int neighbour, int weight) {
            this.neighbour = neighbour;
            this.weight = weight;
        }
    }

    private static class VertexQueue<V> extends AbstractQueue<V> {
        private final LinkedList<V> vList = new LinkedList<>();

        @Override
        public Iterator<V> iterator() {
            return vList.iterator();
        }

        @Override
        public int size() {
            return vList.size();
        }

        @Override
        public boolean offer(V v) {
            boolean res = false;
            if (v != null) {
                res = vList.offer(v);
            }
            return res;
        }

        @Override
        public V poll() {
            if (iterator().hasNext()) {
                V v = iterator().next();
                vList.poll();
                return v;
            }
            return null;
        }

        @Override
        public V peek() {
            return vList.getFirst();
        }
    }
}

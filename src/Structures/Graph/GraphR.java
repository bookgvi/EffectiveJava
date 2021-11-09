package Structures.Graph;

import java.util.*;

public class GraphR {
    private Map<String, Vertex> vertexes;
    private Map<String, Map<String, Edge>> adjMatrix;

    GraphR(int n) {
        vertexes = new HashMap<>();
        adjMatrix = new HashMap<>();
    }

    void addVertex(String label) {
        vertexes.putIfAbsent(label, new Vertex(label));
    }

    void addEdge(String start, String end) {
        Edge edge = new Edge(1, 0);
        Map<String, Edge> cols = adjMatrix.getOrDefault(start, new HashMap<>());
        cols.putIfAbsent(end, edge);
        adjMatrix.putIfAbsent(start, cols);
        cols = adjMatrix.getOrDefault(end, new HashMap<>());
        cols.putIfAbsent(start, edge);
        adjMatrix.putIfAbsent(end, cols);
    }

    void dfs(String start) {
        Vertex startVertex = vertexes.get(start), curVertex, nextVertex;
        if (startVertex == null) {
            System.out.printf("vertex %s not found\n", start);
            return;
        }
        Stack<Vertex> vertexStack = new Stack<>();
        startVertex.isVisited = true;
        vertexStack.push(startVertex);
        while(!vertexStack.isEmpty()) {
            curVertex = vertexStack.peek();
            if ((nextVertex = getUnvisted(curVertex)) == null) vertexStack.pop();
            else {
                nextVertex.isVisited = true;
                vertexStack.push(nextVertex);
                System.out.printf("%s->%s ", curVertex.label, nextVertex.label);
            }
        }
        setUnvisited();
    }

    void bfs(String start) {
        Vertex startVertex = vertexes.get(start), curVertex, nextVertex;
        if (startVertex == null) {
            System.out.printf("vertex %s not found\n", start);
            return;
        }
        VertexQueue<Vertex> vertexQueue = new VertexQueue<>();
        startVertex.isVisited = true;
        vertexQueue.offer(startVertex);
        while(!vertexQueue.isEmpty()) {
            curVertex = vertexQueue.poll();
            if (curVertex == null) continue;
            while((nextVertex = getUnvisted(curVertex)) != null) {
                nextVertex.isVisited = true;
                vertexQueue.offer(nextVertex);
                System.out.printf("%s->%s ", curVertex.label, nextVertex.label);
            }
        }
        setUnvisited();
    }

    private Vertex getUnvisted(Vertex curVertex) {
        Map<String, Edge> cols = adjMatrix.getOrDefault(curVertex.label, new HashMap<>());
        for (String nextLabel : cols.keySet()) {
            Vertex nextVertex = vertexes.get(nextLabel);
            if (!nextVertex.isVisited && cols.get(nextLabel).neighbors == 1) return nextVertex;
        }
        return null;
    }

    private void setUnvisited() {
        for (Vertex nextVertex : vertexes.values())
            nextVertex.isVisited = false;
    }

    private static class Edge {
        private final int neighbors;
        private final int weight;

        Edge(int neighbors, int weight) {
            this.neighbors = neighbors;
            this.weight = weight;
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

    private static class VertexQueue<T> extends AbstractQueue<T> {
        LinkedList<T> vertexes = new LinkedList<>();

        @Override
        public Iterator<T> iterator() {
            return vertexes.iterator();
        }

        @Override
        public int size() {
            return vertexes.size();
        }

        @Override
        public boolean offer(T t) {
            boolean res = false;
            if (t != null) {
                vertexes.add(t);
                res = true;
            }
            return res;
        }

        @Override
        public T poll() {
            if (iterator().hasNext()) {
                T t = iterator().next();
                vertexes.remove();
                return t;
            }
            return null;
        }

        @Override
        public T peek() {
            return vertexes.getFirst();
        }
    }
}

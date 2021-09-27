package TestYourSelf.Graph;

import java.util.*;

public class GraphR5 {
    private final int MAX_SIZE;
    private Vertex[] vertexes;
    private int vertexQuantity;
    private Map<Integer, Map<Integer, IntegerPair>> adjMatrix = new HashMap<>();

    GraphR5(int maxSize) {
        this.MAX_SIZE = maxSize;
        vertexes = new Vertex[MAX_SIZE];
        vertexQuantity = 0;
    }

    public void displayVertex(int num) {
        System.out.printf("%s", vertexes[num].label);
    }

    public void addVertex(String label) {
        vertexes[vertexQuantity++] = new Vertex(label);
    }

    public void addEdge(int start, int end) {
        IntegerPair edge = new IntegerPair(1, 0);
        Map<Integer, IntegerPair> col = adjMatrix.getOrDefault(start, new HashMap<>());
        col.putIfAbsent(end, edge);
        adjMatrix.putIfAbsent(start, col);

        Map<Integer, IntegerPair> symCol = adjMatrix.getOrDefault(end, new HashMap<>());
        symCol.putIfAbsent(start, edge);
        adjMatrix.putIfAbsent(end, symCol);
    }

    public void dfs(int startVertex) {
        Stack<Integer> vertexStack = new Stack<>();
        vertexes[startVertex].isVisited = true;
        vertexStack.push(startVertex);
        displayVertex(startVertex);
        while (!vertexStack.isEmpty()) {
            int nextVertex = getUnvisited(vertexStack.peek());
            if (nextVertex == -1) vertexStack.pop();
            else {
                vertexes[nextVertex].isVisited = true;
                vertexStack.push(nextVertex);
                displayVertex(nextVertex);
            }
        }
        setVertexUnvisited();
    }

    public void mstB(int startVertex) {
        List<String> mst = new ArrayList<>();
        Map<String, Integer> dist = new HashMap<>();
        dist.put(vertexes[startVertex].label, 0);
        VertexQueue<Integer> vertexQueue = new VertexQueue<>();
        vertexes[startVertex].isVisited = true;
        vertexQueue.offer(startVertex);
        int nextVertex;
        while (!vertexQueue.isEmpty()) {
            Integer currentVertex = vertexQueue.poll();
            if (currentVertex == null) continue;
            while ((nextVertex = getUnvisited(currentVertex)) != -1) {
                vertexes[nextVertex].isVisited = true;
                vertexQueue.offer(nextVertex);
                mst.add(vertexes[currentVertex].label + vertexes[nextVertex].label);
                dist.put(vertexes[nextVertex].label, dist.getOrDefault(vertexes[currentVertex].label, 0) + 1);
            }
        }
        System.out.println(mst);
        System.out.println(dist);
        setVertexUnvisited();
    }

    private int getUnvisited(int startVertex) {
        Map<Integer, IntegerPair> cols = adjMatrix.getOrDefault(startVertex, new HashMap<>());
        if (cols.isEmpty()) return -1;
        for (int col : cols.keySet()) {
            if (!vertexes[col].isVisited && cols.get(col).neighbour == 1) {
                return col;
            }
        }
        return -1;
    }

    private void setVertexUnvisited() {
        for (Vertex vertex : vertexes) {
            if (vertex != null) vertex.isVisited = false;
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

    private static class IntegerPair {
        private final int neighbour;
        private int weight = 0;

        IntegerPair(int neighbour, int weight) {
            this.neighbour = neighbour;
            this.weight = weight;
        }
    }

    private static class VertexQueue<V> extends AbstractQueue<V> {
        LinkedList<V> vList = new LinkedList<>();

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
                vList.offer(v);
                res = true;
            }
            return res;
        }

        @Override
        public V poll() {
            if (iterator().hasNext()) {
                V v = iterator().next();
                vList.remove();
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

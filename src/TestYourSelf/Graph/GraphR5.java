package TestYourSelf.Graph;

import java.util.*;

public class GraphR5 {
    private final int MAX_SIZE;
    private int vertexQuntity;
    private Vertex[] vertexes;
    private Map<Integer, Map<Integer, IntegerPair>> adjMatrix = new HashMap<>();

    GraphR5(int maxSize) {
        this.MAX_SIZE = maxSize;
        vertexQuntity = 0;
        vertexes = new Vertex[MAX_SIZE];
    }

    public void addVertex(String label) {
        vertexes[vertexQuntity++] = new Vertex(label);
    }

    public void addEdge(int start, int end) {
        IntegerPair edge = new IntegerPair(1, 0);
        Map<Integer, IntegerPair> col = adjMatrix.getOrDefault(start, new HashMap<>());
        col.putIfAbsent(end, edge);
        adjMatrix.putIfAbsent(start, col);

        Map<Integer, IntegerPair> symmetricCol = adjMatrix.getOrDefault(end, new HashMap<>());
        symmetricCol.putIfAbsent(start, edge);
        adjMatrix.putIfAbsent(end, symmetricCol);
    }

    private void setUnvisited() {
        for (int i = 0; i < vertexQuntity; i += 1) {
            vertexes[i].isVisited = false;
        }
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

    public void dfs(int startVertex) {
        Stack<Integer> vertexStack = new Stack<>();
        vertexes[startVertex].isVisited = true;
        vertexStack.push(startVertex);
        System.out.print(vertexes[startVertex].label);

        while (!vertexStack.isEmpty()) {
            int nextVertex = getUnvisited(vertexStack.peek());
            if (nextVertex == -1) vertexStack.pop();
            else {
                vertexes[nextVertex].isVisited = true;
                vertexStack.push(nextVertex);
                System.out.print(vertexes[nextVertex].label);
            }
        }
        setUnvisited();
    }

    public void mstB(int startVertex) {
        VertexQueue<Integer> vertexQueue = new VertexQueue<>();
        List<String> mst = new ArrayList<>();
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
            }
        }
        setUnvisited();

        System.out.println(mst);
    }

    private static class Vertex {
        private String label;
        private boolean isVisited;

        Vertex(String label) {
            this.label = label;
            this.isVisited = false;
        }
    }

    private static class VertexQueue<V> extends AbstractQueue<V> {
        LinkedList<V> vertexes = new LinkedList<>();

        @Override
        public Iterator<V> iterator() {
            return vertexes.iterator();
        }

        @Override
        public int size() {
            return vertexes.size();
        }

        @Override
        public boolean offer(V v) {
            boolean res = false;
            if (v != null) {
                vertexes.offer(v);
                res = true;
            }
            return res;
        }

        @Override
        public V poll() {
            if (iterator().hasNext()) {
                V v = iterator().next();
                vertexes.remove();
                return v;
            }
            return null;
        }

        @Override
        public V peek() {
            return vertexes.getFirst();
        }
    }

    private static class IntegerPair {
        private int neighbour;
        private int weigth = 0;

        IntegerPair(int neighbour, int weight) {
            this.neighbour = neighbour;
            this.weigth = weight;
        }
    }
}

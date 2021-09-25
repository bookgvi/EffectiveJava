package Algos.Graph;

import java.util.*;

/**
 * Реализована матрица смежности через
 * HasMap<Integer, HashMap<Integer, IntegerPair>>,
 *          где первый ключ (Integer) условно - строка, второй ключ (Integer) - колонка
 * */
public class GraphR5 {
    private final int MAX_SIZE;
    private int vertexQuantity;
    private Vertex[] vertexes;
    private Map<Integer, Map<Integer, IntegerPair>> adjMatrix = new HashMap<>();

    GraphR5(int maxSize) {
        MAX_SIZE = maxSize;
        vertexQuantity = 0;
        vertexes = new Vertex[MAX_SIZE];
    }

    public void displayVertex(int num) {
        System.out.println(vertexes[num].label);
    }

    public void addVertex(String label) {
        vertexes[vertexQuantity++] = new Vertex(label);
    }

    public void addEdge(int start, int end) {
        IntegerPair integerPair = new IntegerPair(1, 0);
        Map<Integer, IntegerPair> col = adjMatrix.getOrDefault(start,  new HashMap<>());
        col.putIfAbsent(end, integerPair);
        adjMatrix.putIfAbsent(start, col);

        Map<Integer, IntegerPair> symmetricCol = adjMatrix.getOrDefault(end,  new HashMap<>());
        symmetricCol.putIfAbsent(start, integerPair);
        adjMatrix.putIfAbsent(end, symmetricCol);
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

    private int getUnvisited(int startVertex) {
        Map<Integer, IntegerPair> columns = adjMatrix.getOrDefault(startVertex, new HashMap<>());
        if (columns.isEmpty()) return -1;
        for (int col : columns.keySet()) {
            if (!vertexes[col].isVisited && columns.get(col).neighbour == 1) {
                return col;
            }
        }
        return -1;
    }

    private void setUnvisited() {
        for (int i = 0; i < vertexQuantity; i += 1) {
            vertexes[i].isVisited = false;
        }
    }

    private static class Vertex {
        private String label;
        private boolean isVisited;

        Vertex(String label) {
            this.label = label;
            isVisited = false;
        }
    }

    /**
     * Класс для доп инфы в таблице смежности, информация о ребре
     * neighbour - смежная вершина, с которой есть ребро
     * weight - вес ребра. Для не взвешенных графов всегда 0
     * */
    private static class IntegerPair {
        private int neighbour = 0;
        private int weight = 0;

        IntegerPair(int i, int j) {
            this.neighbour = i;
            this.weight = j;
        }
    }

    private static class VertexQueue<V> extends AbstractQueue<V> {
        private LinkedList<V> vertexesList = new LinkedList<>();

        @Override
        public Iterator<V> iterator() {
            return vertexesList.iterator();
        }

        @Override
        public int size() {
            return vertexesList.size();
        }

        @Override
        public boolean offer(V v) {
            boolean res = false;
            if (v != null) {
                vertexesList.offer(v);
                res = true;
            }
            return res;
        }

        @Override
        public V poll() {
            if (iterator().hasNext()) {
                V v = iterator().next();
                vertexesList.remove();
                return v;
            }
            return null;
        }

        @Override
        public V peek() {
            return vertexesList.getFirst();
        }
    }
}

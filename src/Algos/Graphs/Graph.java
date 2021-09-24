package Algos.Graphs;

import java.util.*;

public class Graph implements IGraph {
    public final static Graph INSTANCE = new Graph();
    private final int GRAPH_SIZE = 5;
    private final int[][] _matrix;
    private final List<Vertex> _vertexes = new ArrayList<>();

    private Graph() {
        _matrix = new int[GRAPH_SIZE][GRAPH_SIZE];
        for (int i = 0; i < GRAPH_SIZE; i++) {
            for (int j = 0; j < GRAPH_SIZE; j++) {
                _matrix[i][j] = 0;
            }
        }
    }

    public void addVertex(String label) {
        Vertex vertex = new Vertex(label);
        _vertexes.add(vertex);
    }

    public void addEdge(int start, int end) {
        _matrix[start][end] = 1;
        _matrix[end][start] = 1;
    }

    public int[][] get_matrix() {
        return _matrix;
    }

    public void displayVertex(int vertexNum) {
        System.out.println(_vertexes.get(vertexNum)._label);
    }

    private int getMatrixUnvisited(int vertexNum) {
        for (int j = 0; j < GRAPH_SIZE; j++) {
            Vertex vertex = _vertexes.get(j);
            if (!vertex._isVisited && _matrix[vertexNum][j] == 1) {
                return j;
            }
        }
        return -1;
    }

    public void dfs(int startVertex) {
        _vertexes.get(startVertex)._isVisited = true;
        displayVertex(startVertex);
        Stack<Integer> dfsStack = new Stack<>();
        dfsStack.push(startVertex);
        while (!dfsStack.isEmpty()) {
            int currentVertex = dfsStack.peek();
            int nextVertex = getMatrixUnvisited(currentVertex);
            if (nextVertex == -1) dfsStack.pop();
            else {
                _vertexes.get(nextVertex)._isVisited = true;
                dfsStack.push(nextVertex);
                displayVertex(nextVertex);
            }
        }

        for (Vertex vertex : _vertexes) {
            vertex._isVisited = false;
        }
    }

    public void bfs(int startVertex) {
        _vertexes.get(startVertex)._isVisited = true;
        Queue<Integer> vertexQueue = new GraphQueu<>();
        vertexQueue.offer(startVertex);
        displayVertex(startVertex);
        while (!vertexQueue.isEmpty()) {
            Integer v = vertexQueue.poll();
            if (v == null) return;
            int nextVertex;
            while ((nextVertex = getMatrixUnvisited(v)) != -1) {
                _vertexes.get(nextVertex)._isVisited = true;
                displayVertex(nextVertex);
                vertexQueue.offer(nextVertex);
            }
        }

        for (Vertex vertex : _vertexes) {
            vertex._isVisited = false;
        }
    }

    private static class Vertex {
        private final String _label;
        private Boolean _isVisited;

        public Vertex(String label) {
            _label = label;
            _isVisited = false;
        }

        public String get_label() {
            return _label;
        }

        public Boolean get_isVisited() {
            return _isVisited;
        }

        public void set_isVisited(Boolean _isVisited) {
            this._isVisited = _isVisited;
        }
    }

    private static class GraphQueu<T> extends AbstractQueue<T> {
        private final LinkedList<T> list = new LinkedList<>();

        @Override
        public Iterator<T> iterator() {
            return list.iterator();
        }

        @Override
        public int size() {
            return list.size();
        }

        @Override
        public boolean offer(T t) {
            boolean isSucces = false;
            if (t != null) {
                list.add(t);
                isSucces = true;
            }
            return isSucces;
        }

        @Override
        public T poll() {
            Iterator<T> iterator = list.iterator();
            if (iterator.hasNext()) {
                T t = iterator.next();
                iterator.remove();
                return t;
            }
            return null;
        }

        @Override
        public T peek() {
            return list.getFirst();
        }
    }
}

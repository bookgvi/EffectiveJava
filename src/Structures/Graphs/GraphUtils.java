package Structures.Graphs;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class GraphUtils {
    private static IGraph _graph;

    private GraphUtils() {
    }

    public static GraphUtils getInstance(IGraph graph) {
        _graph = graph;
        return new GraphUtils();
    }

    private int getUnvisitedVertex(int startVertex) {
        for (int i = 0; i < _graph.getVertexesQuantity(); i += 1) {
            if (!_graph.getVertexes()[i].hasVisited() && _graph.getAdjMatrix()[startVertex][i] == 1) {
                return i;
            }
        }
        return -1;
    }

    void dfs(int startVertex) {
        _graph.getVertexes()[startVertex].setVisited(true);
        Stack<Integer> vertexStack = new Stack<>();
        _graph.displayVertex(startVertex);
        vertexStack.push(startVertex);
        while (!vertexStack.isEmpty()) {
            int nextVertex = getUnvisitedVertex(vertexStack.peek());
            if (nextVertex == -1) vertexStack.pop();
            else {
                _graph.displayVertex(nextVertex);
                vertexStack.push(nextVertex);
                _graph.getVertexes()[nextVertex].setVisited(true);
            }
        }
        setUnvisited();
    }

    void bfs(int startVertex) {
        _graph.getVertexes()[startVertex].setVisited(true);
        _graph.displayVertex(startVertex);
        VertexQueue<Integer> VertexQueue = new VertexQueue<>();
        VertexQueue.offer(startVertex);
        int nextVertex;
        while (!VertexQueue.isEmpty()) {
            Integer currentVertex = VertexQueue.poll();
            if (currentVertex == null) continue;
            while ((nextVertex = getUnvisitedVertex(currentVertex)) != -1) {
                _graph.getVertexes()[nextVertex].setVisited(true);
                VertexQueue.offer(nextVertex);
                _graph.displayVertex(nextVertex);
            }
        }
        setUnvisited();
    }

    private void setUnvisited() {
        for (IVertex vertex : _graph.getVertexes())
            if (vertex != null) vertex.setVisited(false);
    }

    private static class VertexQueue<T> extends AbstractQueue<T> {

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
            boolean res = false;
            if (t != null) {
                list.add(t);
                res = true;
            }
            return res;
        }

        @Override
        public T poll() {
            if (list.iterator().hasNext()) {
                T t = iterator().next();
                list.remove();
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

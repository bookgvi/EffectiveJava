package Structures.Graph;

import java.util.*;

public class GraphR {
    private final int MAX_VERTEXES;
    private int vertexQuantity = 0;
    private final Vertex[] vertexes;
    private final int[][] adjMatrix;

    GraphR(int maxVertexes) {
        MAX_VERTEXES = maxVertexes;
        vertexes = new Vertex[MAX_VERTEXES];
        adjMatrix = new int[MAX_VERTEXES][MAX_VERTEXES];
    }

    void displayVertex(int num) {
        System.out.printf("%s", vertexes[num].label);
    }

    void addVertex(String label) {
        vertexes[vertexQuantity++] = new Vertex(label);
    }

    void addEdge(int start, int end) {
        adjMatrix[start][end] = 1;
        adjMatrix[end][start] = 1;
    }

    private int getUnvisited(int startVertex) {
        for (int i = 0; i < vertexQuantity; i += 1) {
            if (!vertexes[i].isVisited && adjMatrix[startVertex][i] == 1) {
                return i;
            }
        }
        return -1;
    }

    private void setVertexUnvisited() {
        for (int i = 0; i < vertexQuantity; i += 1) {
            vertexes[i].isVisited = false;
        }
    }

    public void mstB(int startVertex) {
        VertexQueue<Integer> vertexQueue = new VertexQueue<>();
        int[] distance = new int[vertexQuantity];
        Map<String, Integer> dist = new HashMap<>();
        List<String> mst = new ArrayList<>();
        vertexes[startVertex].isVisited = true;
        vertexQueue.offer(startVertex);
        dist.put(vertexes[startVertex].label, 0);
        int nextVertex;
        while (!vertexQueue.isEmpty()) {
            Integer currentVertex = vertexQueue.poll();
            if (currentVertex == null) continue;
            while ((nextVertex = getUnvisited(currentVertex)) != -1) {
                vertexes[nextVertex].isVisited = true;
                vertexQueue.offer(nextVertex);
                mst.add(vertexes[currentVertex].label + vertexes[nextVertex].label);
                distance[nextVertex] = distance[currentVertex] + 1;
                dist.put(vertexes[nextVertex].label, dist.getOrDefault(vertexes[currentVertex].label, 0) + 1);
            }
        }
//        System.out.println(mst);
//        System.out.println(Arrays.toString(distance));
        System.out.println(dist);
        setVertexUnvisited();
    }

    public void mstD(int startVertex) {
        Stack<Integer> vertexStack = new Stack<>();
        vertexes[startVertex].isVisited = true;
        vertexStack.push(startVertex);
        List<String> mst = new ArrayList<>();
        while (!vertexStack.isEmpty()) {
            int currentVertex = vertexStack.peek();
            int nextVertex = getUnvisited(currentVertex);
            if (nextVertex == -1) vertexStack.pop();
            else {
                vertexes[nextVertex].isVisited = true;
                vertexStack.push(nextVertex);
                mst.add(vertexes[currentVertex].label + vertexes[nextVertex].label);
            }
        }
        System.out.printf("MST: %s\n", mst);
        setVertexUnvisited();
    }

    public void dfs(int startVertex) {
        Stack<Integer> vertexStack = new Stack<>();
        displayVertex(startVertex);
        vertexes[startVertex].isVisited = true;
        vertexStack.push(startVertex);
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

    public void bfs(int startVertex) {
        VertexQueue<Integer> vertexesQueue = new VertexQueue<>();
        vertexes[startVertex].isVisited = true;
        vertexesQueue.offer(startVertex);
        displayVertex(startVertex);
        int nextVertex;
        while (!vertexesQueue.isEmpty()) {
            Integer currentVertes = vertexesQueue.poll();
            if (currentVertes == null) continue;
            while ((nextVertex = getUnvisited(currentVertes)) != -1) {
                vertexes[nextVertex].isVisited = true;
                vertexesQueue.offer(nextVertex);
                displayVertex(nextVertex);
            }
        }
        setVertexUnvisited();
    }


    private static class Vertex {
        private String label;
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

package Structures.Bor;

import java.util.*;

public class Bor {
    Vertex root;

    Bor() {
        this.root = new Vertex("root");
    }

    public void addString(String str) {
        Vertex curVertex = root;
        for (String ch : str.split("")) {
            curVertex.toNext.putIfAbsent(ch, new Vertex(ch));
            curVertex = curVertex.toNext.get(ch);
        }
        curVertex.isTerminal = true;
    }

    public boolean findString(String str) {
        Vertex curVertex = root;
        for (String ch : str.split("")) {
            if ((curVertex = curVertex.toNext.get(ch)) == null) return false;
        }
        return curVertex.isTerminal;
    }

    public void dfs(String startVertex) {
        Vertex curVertex = root.toNext.get(startVertex);
        if (curVertex == null) return;
        Stack<Vertex> vertexStack = new Stack<>();
        curVertex.isVisited = true;
        vertexStack.push(curVertex);
        Vertex nexVertex;
        System.out.printf("%s", curVertex.label);
        while (!vertexStack.isEmpty()) {
            curVertex = vertexStack.peek();
            nexVertex = getUnvisited(curVertex);
            if (nexVertex == null) vertexStack.pop();
            else {
                nexVertex.isVisited = true;
                vertexStack.push(nexVertex);
                System.out.printf("%s", nexVertex.label);
            }
        }
        System.out.println();
        setUnvisited(root.toNext.get(startVertex));
    }

    public void bfs(String startVertex) {
        Vertex currentVertex = root.toNext.get(startVertex);
        if (currentVertex == null) return;
        VertexQueue<Vertex> vertexQueue = new VertexQueue<>();
        currentVertex.isVisited = true;
        vertexQueue.offer(currentVertex);
        Vertex nextVertex;
        System.out.print(currentVertex.label);
        while (!vertexQueue.isEmpty()) {
            currentVertex = vertexQueue.poll();
            if (currentVertex == null) continue;
            while ((nextVertex = getUnvisited(currentVertex)) != null) {
                nextVertex.isVisited = true;
                vertexQueue.offer(nextVertex);
                System.out.print(nextVertex.label);
            }
        }
        System.out.println();
        setUnvisited(root.toNext.get(startVertex));
    }

    private Vertex getUnvisited(Vertex startVertex) {
        for (Vertex nextVertex : startVertex.toNext.values()) {
            if (!nextVertex.isVisited) return nextVertex;
        }
        return null;
    }

    private void setUnvisited(Vertex startVertex) {
        if (startVertex == null) return;
        startVertex.isVisited = false;
        if (startVertex.isTerminal) return;
        for (Vertex nextVertex : startVertex.toNext.values())
            setUnvisited(nextVertex);
    }

    private static class Vertex {
        private final String label;
        private final Map<String, Vertex> toNext;
        private boolean isVisited;
        private boolean isTerminal;

        Vertex(String label) {
            this.label = label;
            this.isTerminal = false;
            this.isVisited = false;
            toNext = new HashMap<>();
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
                return vList.offer(v);
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

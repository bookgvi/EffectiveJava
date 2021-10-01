package TestYourSelf.Bor;

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

    public void bfs(String startVertex) {
        VertexQueue<Vertex> vertexQueue = new VertexQueue<>();
        Vertex curVertex = root.toNext.get(startVertex);
        if (curVertex == null) return;
        curVertex.isVisited = true;
        vertexQueue.offer(curVertex);
        Vertex nextVertex;
        System.out.print(curVertex.label);
        while(!vertexQueue.isEmpty()) {
            curVertex = vertexQueue.poll();
            if (curVertex == null) continue;
            while ((nextVertex = getUnvisited(curVertex)) != null) {
                nextVertex.isVisited = true;
                vertexQueue.offer(nextVertex);
                System.out.print(nextVertex.label);
            }
        }
        System.out.println();
        setUnvisited(root.toNext.get(startVertex));
    }

    public void dfs(String startVertex) {
        Vertex curVertex = root.toNext.get(startVertex);
        if (curVertex == null) return;
        Stack<Vertex> vertexStack = new Stack<>();
        curVertex.isVisited = true;
        vertexStack.push(curVertex);
        System.out.printf("%s", curVertex.label);
        while (!vertexStack.isEmpty()) {
            curVertex = vertexStack.peek();
            Vertex nexVertex = getUnvisited(curVertex);
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

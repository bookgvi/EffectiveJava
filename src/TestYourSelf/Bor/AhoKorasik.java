package TestYourSelf.Bor;

import java.util.*;

public class AhoKorasik {
    private final String rootLabel = "root";
    private final Vertex root;

    AhoKorasik() {
        root = new Vertex(rootLabel);
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
            curVertex = curVertex.toNext.get(ch);
            if (curVertex == null) return false;
        }
        return curVertex.isTerminal;
    }

    public boolean findStr(String str) {
        Vertex curVertex = root, suffixTransition;
        Map<String, Vertex> automata = bfs(rootLabel);
        for (String ch : str.split("")) {
            curVertex = curVertex.toNext.get(ch);
            suffixTransition = automata.get(ch);
            if (curVertex == null && suffixTransition == null) return false;
            else curVertex = suffixTransition.toNext.get(ch);
        }
        return true; //curVertex.isTerminal;
    }

    public Map<String, Vertex> bfs(String startLabel) {
        return bfs(startLabel, false);
    }

    public Map<String, Vertex> bfs(String startLabel, boolean isShow) {
        Map<String, Vertex> automata = new HashMap<>();
        Vertex startVertex = startLabel.equals(rootLabel) ? root : root.toNext.get(startLabel);
        if (startVertex == null) return automata;

        automata.putIfAbsent(startLabel, startVertex);

        startVertex.isVisited = true;
        VertexQueue<Vertex> vertexQueue = new VertexQueue<>();
        vertexQueue.offer(startVertex);
        Vertex nextVertex, curVertex;
        while (!vertexQueue.isEmpty()) {
            curVertex = vertexQueue.poll();
            while ((nextVertex = getUnvisited(curVertex)) != null) {
                nextVertex.isVisited = true;
                vertexQueue.offer(nextVertex);
                nextVertex.parent = curVertex;
                if (isShow) System.out.printf("%s->%s; ", curVertex.label, nextVertex.label);

                automata.putIfAbsent(nextVertex.label, curVertex);
            }
        }
        setUnvisited(startVertex);
        return automata;
    }

    private Vertex getUnvisited(Vertex startVertex) {
        if (startVertex == null) return null;
        for (Vertex nextVertex : startVertex.toNext.values()) {
            if (!nextVertex.isVisited) return nextVertex;
        }
        return null;
    }

    private void setUnvisited(Vertex curVertex) {
        if (curVertex == null) return;
        curVertex.isVisited = false;
        for (Vertex nextVertex : curVertex.toNext.values()) {
            setUnvisited(nextVertex);
        }
    }

    private static class Vertex {
        private final String label;
        private final Map<String, Vertex> toNext;
        private Vertex parent;
        private boolean isVisited;
        private boolean isTerminal;

        Vertex(String label) {
            this.label = label;
            this.toNext = new HashMap<>();
            this.isTerminal = false;
            this.isVisited = false;
            this.parent = null;
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

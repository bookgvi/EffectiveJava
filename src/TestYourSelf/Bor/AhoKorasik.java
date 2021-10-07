package TestYourSelf.Bor;

import java.util.*;

public class AhoKorasik {

    private final String rootLabel = "root";
    private final Vertex root;

    AhoKorasik() {
        root = new Vertex(rootLabel);
    }

    public void addKeyWord(String[] str) {
        for (String subStr : str) addKeyWord(subStr);
    }

    public void addKeyWord(List<String> str) {
        for (String subStr : str) addKeyWord(subStr);
    }

    public void addKeyWord(String str) {
        Vertex curVertex = root;
        StringBuilder suffix = new StringBuilder();
        for (String ch : str.split("")) {
            suffix.append(ch);
            curVertex.toNext.putIfAbsent(ch, new Vertex(ch, suffix.toString()));
            Vertex parent = curVertex;
            curVertex = curVertex.toNext.get(ch);
            curVertex.parent = parent;
        }
        curVertex.isTerminal = true;
    }

    public Map<String, Set<Integer>> searchForStrings(String text) {
        Map<String, Set<Integer>> words = new HashMap<>();
        Vertex curVertex = root;
        int index = 0;
        StringBuilder word = new StringBuilder();
        bfs();
        for (String ch : text.split("")) {
            index += 1;
            word.append(ch);
            curVertex = delta(ch, curVertex);
            if (curVertex == root) {
                if (root.toNext.get(ch) == null) {
                    word = new StringBuilder();
                    continue;
                } else {
                    curVertex = curVertex.toNext.get(ch);
                }
            }
            isOut(curVertex, words, index);
        }
        return words;
    }

    private Vertex delta(String ch, Vertex curVertex) {
        if (curVertex.toNext.get(ch) == null && curVertex.sufLink.toNext.get(ch) != null)
            return curVertex.sufLink.toNext.get(ch);
        else if (curVertex.toNext.get(ch) != null) return curVertex.toNext.get(ch);
        return root;
    }

    private void isOut(Vertex curVertex, Map<String, Set<Integer>> words, int index) {
        if (curVertex == null || curVertex == root) return;
        if (curVertex.isTerminal) storeVertex(words, curVertex.suffix, index);
        isOut(curVertex.sufLink, words, index);
    }

    private void storeVertex(Map<String, Set<Integer>> words, String word, int index) {
        int pos = index - word.length();
        Set<Integer> positions = words.getOrDefault(word, new HashSet<>());
        positions.add(pos);
        words.putIfAbsent(word, positions);
    }

    private void bfs() {
        Vertex startVertex = root, curVertex, nextVertex;
        VertexQueue<Vertex> vertexQueue = new VertexQueue<>();
        setRootSuffixLinks();
        setUnvisited(startVertex);
        vertexQueue.offer(startVertex);
        startVertex.isVisited = true;
        while (!vertexQueue.isEmpty()) {
            curVertex = vertexQueue.poll();
            if (curVertex == null) return;
            while ((nextVertex = getUnvisited(curVertex)) != null) {
                nextVertex.isVisited = true;
                vertexQueue.offer(nextVertex);

                Vertex suffixLink = curVertex.sufLink;
                if (nextVertex.sufLink == null) {
                    nextVertex.sufLink = suffixLink.toNext.get(nextVertex.label) != null
                            ? suffixLink.toNext.get(nextVertex.label)
                            : root;
                }
            }
        }
    }

    private void setRootSuffixLinks() {
        root.sufLink = root;
        for (Vertex firstAfterRoot : root.toNext.values()) firstAfterRoot.sufLink = root;
    }

    private Vertex getUnvisited(Vertex curVertex) {
        for (Vertex nextVertex : curVertex.toNext.values()) {
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
        private String suffix = "";
        private final Map<String, Vertex> toNext;
        private Map<String, Vertex> out;
        private Vertex sufLink;
        private Vertex parent;
        private boolean isTerminal;
        private boolean isVisited;

        Vertex(String label) {
            this.label = label;
            this.toNext = new HashMap<>();
            this.out = new HashMap<>();
            this.parent = this;
            this.isTerminal = false;
            this.isVisited = false;
        }

        Vertex(String label, String suffix) {
            this.label = label;
            this.suffix = suffix;
            this.toNext = new HashMap<>();
            this.out = new HashMap<>();
            this.parent = this;
            this.isTerminal = false;
            this.isVisited = false;
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

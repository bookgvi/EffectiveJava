package Structures.Bor;

import java.util.*;

public class AhoKorasik {

    private final String rootLabel = "root";
    private Vertex root;

    AhoKorasik() {
        this.root = new Vertex(rootLabel, "");
    }

    public void addKeyWord(String[] keyWords) {
        for (String keyWord : keyWords) addKeyWord(keyWord);
    }

    public void addKeyWord(String keyWord) {
        Vertex curVertex = root;
        StringBuilder suffix = new StringBuilder();
        for (String ch : keyWord.split("")) {
            suffix.append(ch);
            curVertex.toNext.putIfAbsent(ch, new Vertex(ch, suffix.toString()));
            curVertex = curVertex.toNext.get(ch);
        }
        curVertex.isTerminal = true;
    }

    public void init() {
        bfs();
    }

    public Map<String, List<Integer>> analizeText(String text) {
        init();
        Map<String, List<Integer>> words = new HashMap<>();
        Vertex curVertex = root;
        int index = 0;
        for(String ch : text.split("")) {
            index += 1;
            curVertex = delta(ch, curVertex);
            if (curVertex == root) {
                if (curVertex.toNext.get(ch) == null) continue;
                else curVertex = curVertex.toNext.get(ch);
            }
            isOut(curVertex, words, index);
        }
        return words;
    }

    private void isOut(Vertex curVertex, Map<String, List<Integer>> words, int index) {
        for(Vertex outVertex : curVertex.outArray) {
            storeWordsAndIndex(outVertex, words, index);
        }
    }

    private void storeWordsAndIndex(Vertex outVertex, Map<String, List<Integer>> words, int index) {
        int pos = index - outVertex.suffix.length();
        List<Integer> positions = words.getOrDefault(outVertex.suffix, new ArrayList<>());
        positions.add(pos);
        words.putIfAbsent(outVertex.suffix, positions);
    }

    private Vertex delta(String ch, Vertex curVertex) {
        if (curVertex.toNext.get(ch) == null && curVertex.suffLink.toNext.get(ch) != null) {
            return curVertex.suffLink.toNext.get(ch);
        } else if (curVertex.toNext.get(ch) != null) {
            return curVertex.toNext.get(ch);
        }
        return root;
    }

    private void bfs() {
        Vertex startVertex = root, curVertex, nextVertex;
        startVertex.isVisited = true;
        VertexQueue<Vertex> vertexQueue = new VertexQueue<>();
        vertexQueue.offer(startVertex);
        setRootSuffLinks();
        while (!vertexQueue.isEmpty()) {
            curVertex = vertexQueue.poll();
            if (curVertex == null) continue;
            while ((nextVertex = getUnvisited(curVertex)) != null) {
                nextVertex.isVisited = true;
                vertexQueue.offer(nextVertex);

                Vertex parentSuffLink = curVertex.suffLink;
                if (nextVertex.suffLink == null) {
                    nextVertex.suffLink = parentSuffLink.toNext.get(nextVertex.label);
                    if (nextVertex.suffLink == null) nextVertex.suffLink = root;
                }
            }
        }
        setUnvisited(root);
        initOutArray(root);
    }

    private void initOutArray(Vertex curVertex) {
        if (curVertex == null) return;
        for (Vertex nextVertex : curVertex.toNext.values()) {
            storeOutArray(nextVertex, nextVertex.outArray);
            initOutArray(nextVertex);
        }
    }

    private void storeOutArray(Vertex curVertex, List<Vertex> outArray) {
        if (curVertex.isTerminal) outArray.add(curVertex);
        if (curVertex.suffLink == root) return;
        storeOutArray(curVertex.suffLink, outArray);
    }

    private void setRootSuffLinks() {
        root.suffLink = root;
        for (Vertex firstAfterRoot : root.toNext.values()) firstAfterRoot.suffLink = root;
    }

    private Vertex getUnvisited(Vertex startVertex) {
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
        private final String suffix;
        private Map<String, Vertex> toNext;
        private List<Vertex> outArray;
        private Vertex suffLink;
        private boolean isVisited;
        private boolean isTerminal;

        Vertex(String label, String suffix) {
            this.label = label;
            this.suffix = suffix;
            this.toNext = new HashMap<>();
            this.outArray = new ArrayList<>();
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

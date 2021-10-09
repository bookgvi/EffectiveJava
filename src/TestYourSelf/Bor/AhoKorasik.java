package TestYourSelf.Bor;

import java.util.*;

public class AhoKorasik {

    private final String rootLabel = "root";
    private Vertex root;

    AhoKorasik() {
        this.root = new Vertex(rootLabel, "");
        root.parent = root;
    }

    void addKeyWord(String str) {
        Vertex curVertex = root, parent;
        StringBuilder suffix = new StringBuilder();
        for (String ch : str.split("")) {
            suffix.append(ch);
            curVertex.toNext.putIfAbsent(ch, new Vertex(ch, suffix.toString()));
            parent = curVertex;
            curVertex = curVertex.toNext.get(ch);
            curVertex.parent = parent;
        }
        curVertex.isTerminal = true;
    }

    void addKeyWord(String[] keyWords) {
        for (String keyWord : keyWords) addKeyWord(keyWord);
    }

    Map<String, List<Integer>> textAnalize(String text) {
        Map<String, List<Integer>> words = new HashMap<>();
        Vertex curVertex = root;
        int index = 0;
        for (String ch : text.split("")) {
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

    private Vertex delta(String ch, Vertex curVertex) {
        if (curVertex.toNext.get(ch) == null && curVertex.suffLink.toNext.get(ch) != null)
            return curVertex.suffLink.toNext.get(ch);
        else if (curVertex.toNext.get(ch) != null) return curVertex.toNext.get(ch);
        return root;
    }

    private void isOut(Vertex curVertex, Map<String, List<Integer>> words, int index) {
        for (Vertex outVertex : curVertex.outArr) storeWords(outVertex, words, index);
    }

    private void storeWords(Vertex outVertex, Map<String, List<Integer>> words, int index) {
        int pos = index - outVertex.suffix.length();
        List<Integer> positions = words.getOrDefault(outVertex.suffix, new ArrayList<>());
        positions.add(pos);
        words.putIfAbsent(outVertex.suffix, positions);
    }

    void initBor() {
        Vertex startVertex = root, curVertex, nextVertex;
        VertexQueue<Vertex> vertexQueue = new VertexQueue<>();
        setRootSuffLinks();
        startVertex.isVisited = true;
        vertexQueue.offer(startVertex);
        while (!vertexQueue.isEmpty()) {
            curVertex = vertexQueue.poll();
            if (curVertex == null) continue;
            while ((nextVertex = getUnvisited(curVertex)) != null) {
                nextVertex.isVisited = true;
                vertexQueue.offer(nextVertex);
                setSuffLinks(curVertex, nextVertex);
            }
        }
        setOutFunc(root);
        setUnvisited(root);
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
        curVertex.isVisited = false;
        for (Vertex nextVertex : curVertex.toNext.values()) {
            setUnvisited(nextVertex);
        }
    }

    private void setSuffLinks(Vertex parentVertex, Vertex nextVertex) {
        Vertex parentSuffLink = parentVertex.suffLink;
        if (nextVertex.suffLink == null) {
            nextVertex.suffLink = parentSuffLink.toNext.get(nextVertex.label);
            if (nextVertex.suffLink == null) nextVertex.suffLink = root;
        }
    }

    private void setOutFunc(Vertex curVertex) {
        for (Vertex nextVertex : curVertex.toNext.values()) {
            storeOutArr(nextVertex, nextVertex.outArr);
            setOutFunc(nextVertex);
        }
    }

    private void storeOutArr(Vertex curVertex, List<Vertex> outArr) {
        if (curVertex.isTerminal) outArr.add(curVertex);
        if (curVertex.suffLink == root) return;
        storeOutArr(curVertex.suffLink, outArr);
    }

    private static class Vertex {
        private final String label;
        private final String suffix;
        private Map<String, Vertex> toNext;
        private Vertex suffLink;
        private List<Vertex> outArr;
        private Vertex parent;
        private boolean isVisited;
        private boolean isTerminal;

        Vertex(String label, String suffix) {
            this.label = label;
            this.suffix = suffix;
            this.toNext = new HashMap<>();
            this.outArr = new ArrayList<>();
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

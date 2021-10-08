package TestYourSelf.Bor;

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
        fillOutArray(root);
    }

    public Map<String, List<Integer>> textForAnal(String text) {
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
        for (Vertex terminalVertex : curVertex.outArray) {
            storeWord(terminalVertex, words, index);
        }
    }

    private void storeWord(Vertex curVertex, Map<String, List<Integer>> words, int index) {
        int pos = index - curVertex.suffix.length();
        List<Integer> positions = words.getOrDefault(curVertex.suffix, new ArrayList<>());
        positions.add(pos);
        words.putIfAbsent(curVertex.suffix, positions);
    }

    private void bfs() {
        Vertex startVertex = root, curVertex, nextVertex;
        VertexQueue<Vertex> vertexQueue = new VertexQueue<>();
        vertexQueue.offer(startVertex);
        startVertex.isVisited = true;
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
    }

    private void fillOutArray(Vertex curVertex) {
        if (curVertex == null) return;
        for(Vertex nextVertex : curVertex.toNext.values()) {
            storeToOutArray(nextVertex, nextVertex.outArray);
            fillOutArray(nextVertex);
        }
    }

    private void storeToOutArray(Vertex curVertex, List<Vertex> outArray) {
        if (curVertex.isTerminal) outArray.add(curVertex);
        if (curVertex.suffLink == root) return;
        storeToOutArray(curVertex.suffLink, outArray);
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
        private List<Vertex> outArray;
        private Map<String, Vertex> toNext;
        private Vertex suffLink;
        private boolean isVisited;
        private boolean isTerminal;

        Vertex(String label, String suffix) {
            this.label = label;
            this.suffix = suffix;
            this.toNext = new HashMap<>();
            this.outArray = new ArrayList<>();
            this.isVisited = false;
            this.isTerminal = false;
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

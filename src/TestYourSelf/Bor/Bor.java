package TestYourSelf.Bor;

import java.util.*;

public class Bor {
    private final String rootLabel = "root";
    private final Vertex root;

    Bor() {
        this.root = new Vertex(rootLabel);
    }

    public void addKeyWords(String[] keyWords) {
        for (String keyWord : keyWords) addKeyWords(keyWord);
    }

    public void addKeyWords(String keyWord) {
        Vertex curVertex = root, parent;
        StringBuilder subStr = new StringBuilder();
        for (String ch : keyWord.split("")) {
            subStr.append(ch);
            parent = curVertex;
            curVertex.toNext.putIfAbsent(ch, new Vertex(ch, subStr.toString()));
            curVertex = curVertex.toNext.get(ch);
            curVertex.parent = parent;
        }
        curVertex.isTerminal = true;
    }

    public Map<String, List<Integer>> analizeText(String text) {
        Map<String, List<Integer>> words = new HashMap<>();
        bfs();
        Vertex curVertex = root;
        int index = 0;
        StringBuilder w = new StringBuilder();
        for (String ch : text.split("")) {
            index += 1;
            curVertex = delta(curVertex, ch);
            if (curVertex == root) {
                if (root.toNext.get(ch) == null) {
                    w = new StringBuilder();
                    continue;
                }
                w = new StringBuilder();
            }
            curVertex = curVertex.toNext.get(ch);
            w.append(ch);
            if (curVertex.isTerminal) {
                for (String word : new String[]{curVertex.subString}) {
                    saveWord(word, index, words);
                }
            }
        }
        return words;
    }

    private void saveWord(String word, int index, Map<String, List<Integer>> words) {
        int pos = index - word.length();
        List<Integer> positions = words.getOrDefault(word, new ArrayList<>());
        positions.add(pos);
        words.putIfAbsent(word, positions);
    }

    private Vertex delta(Vertex curVertex, String ch) {
        if (curVertex.suffixLink.toNext.get(ch) != null) return curVertex.suffixLink;
        else if (curVertex.toNext.get(ch) != null) return curVertex;
        return root;
    }

    private void bfs() {
        Vertex startVertex = root, curVertex, nextVertex;
        VertexQueue<Vertex> vertexQueue = new VertexQueue<>();
        setRootSuffixLinks();
        startVertex.isVisited = true;
        vertexQueue.offer(startVertex);
        while (!vertexQueue.isEmpty()) {
            curVertex = vertexQueue.poll();
            if (curVertex == null) continue;
            while ((nextVertex = getUnvisited(curVertex)) != null) {
                nextVertex.isVisited = true;
                vertexQueue.offer(nextVertex);

                Vertex parentSuffix = curVertex.suffixLink;
                if (parentSuffix != null && nextVertex.suffixLink == null) {
                    nextVertex.suffixLink = parentSuffix.toNext.get(nextVertex.parent.label);
                    if (nextVertex.suffixLink == null) nextVertex.suffixLink = root;
//                    else nextVertex.suffixLink.isTerminal |= nextVertex.isTerminal;
                }
            }
        }
        setUnvisited(root);
    }

    private void setRootSuffixLinks() {
        root.suffixLink = root;
        for (Vertex nextVertex : root.toNext.values()) nextVertex.suffixLink = root;
    }

    private Vertex getUnvisited(Vertex startVertex) {
        for (Vertex nextVertex : startVertex.toNext.values()) {
            if (!nextVertex.isVisited) return nextVertex;
        }
        return null;
    }

    private Vertex setUnvisited(Vertex curVertex) {
        if (curVertex == null) return null;
        curVertex.isVisited = false;
        for (Vertex nextVertex : curVertex.toNext.values()) {
            setUnvisited(nextVertex);
        }
        return curVertex;
    }

    private static class Vertex {
        private final String label;
        private String subString;
        private final Map<String, Vertex> toNext;
        private Vertex parent = null;
        private Vertex suffixLink = null;
        private boolean isVisited;
        private boolean isTerminal;

        Vertex(String label) {
            this.label = label;
            this.toNext = new HashMap<>();
            this.isVisited = false;
            this.isTerminal = false;
        }

        Vertex(String label, String subString) {
            this.label = label;
            this.subString = subString;
            this.toNext = new HashMap<>();
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
            if (v != null) res = vList.offer(v);
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

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
        for (String ch : str.split("")) {
            curVertex.toNext.putIfAbsent(ch, new Vertex(ch));
            Vertex parent = curVertex;
            curVertex = curVertex.toNext.get(ch);
            curVertex.parent = parent;
            curVertex.parentChar = parent.label;
        }
        curVertex.isTerminal = true;
    }

    // TODO доделать сохранение слов в MAP - не совсем правильно!!!
    public Map<String, List<Integer>> analizeText(String text) {
        Map<String, List<Integer>> words = new HashMap<>();
        Vertex curVertex = root, suffixTransition;
        Map<String, Vertex> automata = bfs();
        StringBuilder word = new StringBuilder();
        int index = 0;
        for (String ch : text.split("")) {
            index += 1;
            word.append(ch);
            curVertex = curVertex.toNext.get(ch);
            if (curVertex == null) {
                word = new StringBuilder(word.substring(word.length() - 1, word.length()));
                suffixTransition = automata.get(ch);
                if (suffixTransition == null) {
                    word = new StringBuilder();
                    curVertex = root;
                    continue;
                }
                curVertex = suffixTransition.toNext.get(ch);
            }

            if (curVertex.isTerminal) {
                List<Integer> positions = words.getOrDefault(word.toString(), new ArrayList<>());
                positions.add(index - word.length());
                words.putIfAbsent(word.toString(), positions);
            }
        }
        return words;
    }

    public Map<String, List<Integer>> analizeTextExt(String text) {
        Map<String, List<Integer>> words = new HashMap<>();
        Vertex curVertex = root, suffixVertex = null;
        StringBuilder word = new StringBuilder();
        bfs();
        int index = 0;
        for (String ch : text.split("")) {
            index += 1;
            suffixVertex = curVertex.sufLink;
            curVertex = curVertex.toNext.get(ch);
            if (curVertex == null) curVertex = suffixVertex;
            if (curVertex == null) {
                word = new StringBuilder();
                curVertex = root;
                continue;
            }
            word.append(ch);
            if (curVertex.isTerminal) {
                List<Integer> positions = words.getOrDefault(word.toString(), new ArrayList<>());
                positions.add(index - word.length());
                words.putIfAbsent(word.toString(), positions);
            }
        }
        return words;
    }

    private Map<String, Vertex> bfs() {
        Map<String, Vertex> automata = new HashMap<>();
        Vertex curVertex = root, nextVertex;

        VertexQueue<Vertex> vertexQueue = new VertexQueue<>();
        curVertex.isVisited = true;
        setRootSuffix();
        vertexQueue.offer(curVertex);
        while (!vertexQueue.isEmpty()) {
            curVertex = vertexQueue.poll();
            if (curVertex == null) continue;
            while ((nextVertex = getUnvisited(curVertex)) != null) {
                nextVertex.isVisited = true;
                vertexQueue.offer(nextVertex);

                Vertex parentSuffix = curVertex.sufLink;
                if (parentSuffix != null && nextVertex.sufLink == null) {
                    nextVertex.sufLink = parentSuffix.toNext.get(nextVertex.label);
                    if (nextVertex.sufLink == null) nextVertex.sufLink = root;
                }
                automata.putIfAbsent(nextVertex.label, curVertex);
            }
        }
        setUnvisited(root);
        return automata;
    }

    private void setRootSuffix() {
        root.sufLink = root;
        for (Vertex firstAfterRoot : root.toNext.values()) firstAfterRoot.sufLink = root;
    }

    private Vertex getUnvisited(Vertex startVertex) {
        for (Vertex curVertex : startVertex.toNext.values()) {
            if (!curVertex.isVisited) {
                return curVertex;
            }
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
        private Vertex sufLink;
        private Vertex parent;
        private String parentChar;
        private boolean isTerminal;
        private boolean isVisited;

        Vertex(String label) {
            this.label = label;
            this.toNext = new HashMap<>();
            this.parent = null;
            this.isTerminal = false;
            this.isVisited = false;
//            this.sufLink = AhoKorasik.this.root;
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

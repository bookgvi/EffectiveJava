package Structures.Bor;

import java.util.*;

public class AhoKorasik {

    private final Vertex root = new Vertex("root", "");
    private Map<String, List<Integer>> words = new HashMap<>();


    void addKeyWord(String keyWord) {
        Vertex curVertex = root;
        StringBuilder suffix = new StringBuilder();
        for (String ch : keyWord.split("")) {
            suffix.append(ch);
            curVertex.toNext.putIfAbsent(ch, new Vertex(ch, suffix.toString()));
            curVertex = curVertex.toNext.get(ch);
        }
        curVertex.isTerminal = true;
    }

    void addKeyWord(String[] keyWords) {
        for (String keyWord : keyWords) addKeyWord(keyWord);
    }

    Map<String, List<Integer>> analizeText(String text) {
        init();
        Vertex cur = root;
        int index = 0;
        for (String ch : text.split("")) {
            index += 1;
            cur = delta(ch, cur);
            if (cur == root) {
                if (cur.toNext.get(ch) == null) continue;
                else cur = cur.toNext.get(ch);
            }
            isOut(cur, index);
        }
        return words;
    }

    private Vertex delta(String ch, Vertex cur) {
        if (cur.toNext.get(ch) == null && cur.suffLink.toNext.get(ch) != null)
            return cur.suffLink.toNext.get(ch);
        else if (cur.toNext.get(ch) != null)
            return cur.toNext.get(ch);
        return root;
    }

    private void isOut(Vertex cur, int index) {
        for (Vertex out : cur.outArray) fillWords(out, index);
    }

    private void fillWords(Vertex out, int index) {
        int pos = index - out.suffix.length();
        List<Integer> positions = this.words.getOrDefault(out.suffix, new ArrayList<>());
        positions.add(pos);
        this.words.putIfAbsent(out.suffix, positions);
    }

    private void init() {
        this.words = new HashMap<>();
        bfs();
    }

    private void bfs() {
        Vertex start = root, cur, next;
        VertexQueue<Vertex> vertexQueue = new VertexQueue<>();
        setRootSuffLink();
        start.isVisited = true;
        vertexQueue.offer(start);
        while (!vertexQueue.isEmpty()) {
            cur = vertexQueue.poll();
            if (cur == null) continue;
            while ((next = getUnvisited(cur)) != null) {
                next.isVisited = true;
                vertexQueue.offer(next);
                setSuffLink(cur, next);
            }
        }
        setOutArr(root);
        setUnvisited(root);
    }

    private void setSuffLink(Vertex parent, Vertex cur) {
        Vertex parentSufflink = parent.suffLink;
        if (cur.suffLink == null) {
            cur.suffLink = parentSufflink.toNext.get(cur.label);
            if (cur.suffLink == null) cur.suffLink = root;
        }
    }

    private void setRootSuffLink() {
        root.suffLink = root;
        for (Vertex far : root.toNext.values()) far.suffLink = root;
    }

    private void setOutArr(Vertex cur) {
        for (Vertex next : cur.toNext.values()) {
            fillOutArr(next, next.outArray);
            setOutArr(next);
        }
    }

    private void fillOutArr(Vertex cur, List<Vertex> outArr) {
        if (cur.isTerminal) outArr.add(cur);
        if (cur.suffLink == root) return;
        fillOutArr(cur.suffLink, outArr);
    }

    private void setUnvisited(Vertex cur) {
        cur.isVisited = false;
        for (Vertex next : cur.toNext.values()) setUnvisited(next);
    }

    private Vertex getUnvisited(Vertex cur) {
        for (Vertex next : cur.toNext.values())
            if (!next.isVisited) return next;
        return null;
    }

    private static class Vertex {
        private final String label;
        private final String suffix;
        private final Map<String, Vertex> toNext;
        private final List<Vertex> outArray;
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
        private final LinkedList<V> vList = new LinkedList<>();

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
                vList.add(v);
                res = true;
            }
            return res;
        }

        @Override
        public V poll() {
            Iterator<V> iter = iterator();
            V v = iter.next();
            if (v != null) {
                iter.remove();
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

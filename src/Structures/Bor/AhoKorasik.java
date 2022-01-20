package Structures.Bor;

import java.util.*;

public class AhoKorasik {
    private final Vertex root = new Vertex("root", "");

    void addKeyWord(String text) {
        Vertex cur = root;
        StringBuilder suffix = new StringBuilder();
        for (String ch : text.split("")) {
            suffix.append(ch);
            cur.toNext.putIfAbsent(ch, new Vertex(ch, suffix.toString()));
            cur = cur.toNext.get(ch);
        }
        cur.isTerminal = true;
    }

    void addKeyWord(String[] keyWords) {
        for (String keyWord : keyWords) addKeyWord(keyWord);
    }

    Map<String, List<Integer>> analizeText(String text) {
        Map<String, List<Integer>> words = new HashMap<>();
        init();
        Vertex cur = root;
        int index = 0;
        for (String ch : text.split("")) {
            index += 1;
            cur = delta(ch, cur);
            if (cur == root) {
                if (cur.toNext.get(ch) == null) continue;
                cur = cur.toNext.get(ch);
            }
            isOut(cur, words, index);
        }
        return words;
    }

    private void isOut(Vertex cur, Map<String, List<Integer>> words , int index) {
        for (Vertex outV : cur.outArr) fillWords(outV, words, index);
    }

    private void fillWords(Vertex outV, Map<String, List<Integer>> words, int index) {
        int pos = index - outV.suffix.length();
        List<Integer> positions = words.getOrDefault(outV.suffix, new ArrayList<>());
        positions.add(pos);
        words.putIfAbsent(outV.suffix, positions);
    }

    private Vertex delta(String ch, Vertex cur) {
        if (cur.toNext.get(ch) == null && cur.suffLink.toNext.get(ch) != null)
            return cur.suffLink.toNext.get(ch);
        else if (cur.toNext.get(ch) != null)
            return cur.toNext.get(ch);
        return root;
    }

    private void init() {
        Vertex start = root, cur, next;
        VertexQueue<Vertex> queue = new VertexQueue<>();
        setRootSuffLink();
        start.isVisited = true;
        queue.offer(start);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (cur == null) continue;
            while ((next = getUnvisited(cur)) != null) {
                next.isVisited = true;
                queue.offer(next);
                setSuffLink(cur, next);
            }
        }
        setOutArr(root);
        setUnvisited(root);
    }

    private void setSuffLink(Vertex parent, Vertex cur) {
        Vertex parentSuffLink = parent.suffLink;
        if (cur.suffLink == null) {
            cur.suffLink = parentSuffLink.toNext.get(cur.label);
            if (cur.suffLink == null) cur.suffLink = root;
        }
    }

    private void setRootSuffLink() {
        root.suffLink = root;
        for (Vertex firstAfterRoot : root.toNext.values()) firstAfterRoot.suffLink = root;
    }

    private void fillOutArr(Vertex cur, List<Vertex> outArr) {
        if (cur.isTerminal) outArr.add(cur);
        if (cur.suffLink == root) return;
        fillOutArr(cur.suffLink, outArr);
    }

    private void setOutArr(Vertex cur) {
        for (Vertex next : cur.toNext.values()) {
            fillOutArr(next, next.outArr);
            setOutArr(next);
        }
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
        private final List<Vertex> outArr;
        private Vertex suffLink;
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

package Structures.Bor;

import java.util.*;

public class AhoKorasik {
    private final Vertex root = new Vertex("root", "");

    void addKeyWord(String word) {
        Vertex cur = root;
        StringBuilder suffix = new StringBuilder();
        for (String ch : word.split("")) {
            suffix.append(ch);
            cur.toNext.putIfAbsent(ch, new Vertex(ch, suffix.toString()));
            cur = cur.toNext.get(ch);
        }
        cur.isTerminal = true;
    }

    void addKeyWord(String[] strings) {
        for (String word : strings) addKeyWord(word);
    }

    Map<String, List<Integer>> analizeText(String text) {
        init();
        Map<String, List<Integer>> words = new HashMap<>();
        Vertex cur = root;
        int index = 0;
        for (String ch : text.split("")) {
            index += 1;
            cur = delta(ch, cur);
            if (cur == root) {
                if (cur.toNext.get(ch) == null) continue;
                else cur = cur.toNext.get(ch);
            }
            fillWords(words, cur, index);
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

    private void fillWords(Map<String, List<Integer>> words, Vertex cur, int index) {
        for (Vertex outV : cur.outArr) storeWords(words, outV, index);
    }

    private void storeWords(Map<String, List<Integer>> words, Vertex outV, int index) {
        int pos = index - outV.suffix.length();
        List<Integer> positions = words.getOrDefault(outV.suffix, new ArrayList<>());
        positions.add(pos);
        words.putIfAbsent(outV.suffix, positions);
    }

    private void init() {
        Vertex cur = root, next;
        Queue<Vertex> queue = new Queue<>();
        setRootSuffLinks();
        cur.isVisited = true;
        queue.offer(cur);
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

    private void setRootSuffLinks() {
        root.suffLink = root;
        for (Vertex v : root.toNext.values()) v.suffLink = root;
    }

    private void setSuffLink(Vertex parent, Vertex cur) {
        Vertex parentSuffLink = parent.suffLink;
        if (cur.suffLink == null) {
            cur.suffLink = parentSuffLink.toNext.get(cur.label);
            if (cur.suffLink == null) cur.suffLink = root;
        }
    }

    private Vertex getUnvisited(Vertex cur) {
        for (Vertex next : cur.toNext.values())
            if (!next.isVisited) return next;
        return null;
    }

    private void setOutArr(Vertex cur) {
        for (Vertex next : cur.toNext.values()) {
            fillOutArr(next, next.outArr);
            setOutArr(next);
        }
    }

    private void fillOutArr(Vertex cur, List<Vertex> outArr) {
        if (cur.isTerminal) outArr.add(cur);
        if (cur.suffLink == root) return;
        fillOutArr(cur.suffLink, outArr);
    }

    private static void setUnvisited(Vertex cur) {
        cur.isVisited = false;
        for (Vertex next : cur.toNext.values())
            setUnvisited(next);
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

    private static class Queue<V> extends AbstractQueue<V> {
        LinkedList<V> ll = new LinkedList<>();

        @Override
        public Iterator<V> iterator() {
            return ll.iterator();
        }

        @Override
        public int size() {
            return ll.size();
        }

        @Override
        public boolean offer(V v) {
            boolean res = false;
            if (v != null) {
                ll.add(v);
                res = true;
            }
            return res;
        }

        @Override
        public V poll() {
            Iterator<V> it = iterator();
            V v = it.next();
            if (v != null) {
                ll.remove();
                return v;
            }
            return null;
        }

        @Override
        public V peek() {
            return ll.getFirst();
        }
    }
}

package Structures.Bor;

import java.util.*;

public class Bor {
    private static final Vertex root = new Vertex("root", "");

    public static void main(String[] args) {
        String[] words = {"he", "she", "his", "hers" };
        String text = "hershersishehishe";
        for (String word : words) addWord(word);
        Map<String, List<Integer>> wordsInText = analize(text);
        System.out.println(Arrays.toString(words));
        System.out.println(text);
        System.out.println(wordsInText);
    }

    private static void addWord(String str) {
        Vertex cur = root;
        StringBuilder suff = new StringBuilder();
        for (String ch : str.split("")) {
            suff.append(ch);
            cur.toNext.putIfAbsent(ch, new Vertex(ch, suff.toString()));
            cur = cur.toNext.get(ch);
        }
        cur.isTerminal = true;
    }

    private static Map<String, List<Integer>> analize(String text) {
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
            out(cur, words, index);
        }
        return words;
    }

    private static void init() {
        buildBor();
    }

    private static Vertex delta(String ch, Vertex cur) {
        if (cur.toNext.get(ch) == null && cur.suffLink.toNext.get(ch) != null)
            return cur.suffLink.toNext.get(ch);
        else if (cur.toNext.get(ch) != null)
            return cur.toNext.get(ch);
        return root;
    }

    private static void out(Vertex cur, Map<String, List<Integer>> words, int index) {
        for (Vertex outV : cur.outArr) fillWords(outV, words, index);
    }

    private static void fillWords(Vertex outV, Map<String, List<Integer>> words, int index) {
        int pos = index - outV.suffix.length();
        List<Integer> positions = words.getOrDefault(outV.suffix, new ArrayList<>());
        positions.add(pos);
        words.putIfAbsent(outV.suffix, positions);
    }

    private static void buildBor() {
        Vertex start = root, cur, next;
        Queue<Vertex> queue = new Queue<>();
        start.isVisted = true;
        queue.offer(start);
        setRootSuffLink();
        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (cur == null) continue;
            while ((next = getUnvisited(cur)) != null) {
                next.isVisted = true;
                queue.offer(next);
                setSuffLink(next, cur);
            }
        }
        setOutArr(root);
        setUnvisited(root);
    }

    private static void setRootSuffLink() {
        root.suffLink = root;
        for (Vertex v : root.toNext.values()) v.suffLink = root;
    }

    private static void setSuffLink(Vertex cur, Vertex parent) {
        Vertex parentSuffLink = parent.suffLink;
        if (cur.suffLink == null) {
            cur.suffLink = parentSuffLink.toNext.get(cur.label);
            if (cur.suffLink == null) cur.suffLink = root;
        }
    }

    private static void setOutArr(Vertex cur) {
        for (Vertex next : cur.toNext.values()) {
            fillOutArr(next, next.outArr);
            setOutArr(next);
        }
    }

    private static void fillOutArr(Vertex cur, List<Vertex> outArr) {
        if (cur.isTerminal) outArr.add(cur);
        if (cur.suffLink == root) return;
        fillOutArr(cur.suffLink, outArr);
    }

    private static void setUnvisited(Vertex cur) {
        cur.isVisted = false;
        for (Vertex next : cur.toNext.values())
            setUnvisited(next);
    }

    private static Vertex getUnvisited(Vertex cur) {
        for (Vertex next : cur.toNext.values())
            if (!next.isVisted) return next;
        return null;
    }

    private static class Vertex {
        private final String label;
        private final String suffix;
        private final Map<String, Vertex> toNext;
        private final List<Vertex> outArr;
        private Vertex suffLink;
        private boolean isVisted;
        private boolean isTerminal;

        Vertex(String label, String suffix) {
            this.label = label;
            this.suffix = suffix;
            this.toNext = new HashMap<>();
            this.outArr = new ArrayList<>();
            this.isVisted = false;
            this.isTerminal = false;
        }
    }

    private static class Queue<V> extends AbstractQueue<V> {
        LinkedList<V> vl = new LinkedList<>();

        @Override
        public Iterator<V> iterator() {
            return vl.iterator();
        }

        @Override
        public int size() {
            return vl.size();
        }

        @Override
        public boolean offer(V v) {
            boolean res = false;
            if (v != null) {
                vl.add(v);
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
            return vl.getFirst();
        }
    }
}

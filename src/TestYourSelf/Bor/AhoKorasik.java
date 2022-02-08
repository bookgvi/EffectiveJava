package TestYourSelf.Bor;

import java.util.*;

public class AhoKorasik {

    private static final Vertex root = new Vertex("root", "");

    public static void main(String[] args) {
        String[] words = {"he", "she", "his", "hers"};
        String text = "heheshersshis";
        addWords(words);
        Map<String, List<Integer>> res = analizeText(text);
        System.out.println(text);
        System.out.println(Arrays.toString(words));
        System.out.println(res);
    }

    private static void addWord(String word) {
        Vertex cur = root;
        StringBuilder suff = new StringBuilder();
        for (String ch : word.split("")) {
            suff.append(ch);
            cur.toNext.putIfAbsent(ch, new Vertex(ch, suff.toString()));
            cur = cur.toNext.get(ch);
        }
        cur.isTerminal = true;
    }

    private static void addWords(String[] words) {
        for (String word : words) addWord(word);
    }

    private static Map<String, List<Integer>> analizeText(String text) {
        Map<String, List<Integer>> words = new HashMap<>();
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
            isOut(cur, words, index);
        }
        return words;
    }

    private static Vertex delta(String ch, Vertex cur) {
        if (cur.toNext.get(ch) == null && cur.suffLink.toNext.get(ch) != null)
            return cur.suffLink.toNext.get(ch);
        else if (cur.toNext.get(ch) != null)
            return cur.toNext.get(ch);
        return root;
    }

    private static void isOut(Vertex cur, Map<String, List<Integer>> words, int index) {
        for (Vertex out : cur.outArr) fillWords(out, words, index);
    }

    private static void fillWords(Vertex out, Map<String, List<Integer>> words, int index) {
        int pos = index - out.suffix.length();
        List<Integer> positions = words.getOrDefault(out.suffix, new ArrayList<>());
        positions.add(pos);
        words.putIfAbsent(out.suffix, positions);
    }

    private static void init() {
        Vertex start = root, cur, next;
        VQueue<Vertex> vq = new VQueue<>();
        start.isVisited = true;
        vq.offer(start);
        setRootSuffLink();
        while (!vq.isEmpty()) {
            cur = vq.poll();
            if (cur == null) continue;
            while ((next = getNext(cur)) != null) {
                next.isVisited = true;
                vq.offer(next);
                setSuffLink(cur, next);
            }
        }
        setOutArr(root);
        setUnused(root);
    }

    private static Vertex getNext(Vertex cur) {
        for (Vertex next : cur.toNext.values())
            if (!next.isVisited) return next;
        return null;
    }

    private static void setSuffLink(Vertex parent, Vertex cur) {
        Vertex parentSuffLink = parent.suffLink;
        if (cur.suffLink == null) {
            cur.suffLink = parentSuffLink.toNext.get(cur.label);
            if (cur.suffLink == null) cur.suffLink = root;
        }
    }

    private static void setRootSuffLink() {
        root.suffLink = root;
        for (Vertex far : root.toNext.values()) far.suffLink = root;
    }

    private static void fillOutArr(Vertex cur, List<Vertex> outArr) {
        if (cur.isTerminal) outArr.add(cur);
        if (cur.suffLink == root) return;
        fillOutArr(cur.suffLink, outArr);
    }

    private static void setOutArr(Vertex cur) {
        for (Vertex next : cur.toNext.values()) {
            fillOutArr(next, next.outArr);
            setOutArr(next);
        }
    }

    private static void setUnused(Vertex cur) {
        cur.isVisited = false;
        for (Vertex next : cur.toNext.values()) setUnused(next);
    }

    private static class Vertex {
        private final String label;
        private final String suffix;
        private final List<Vertex> outArr;
        private final Map<String, Vertex> toNext;
        private Vertex suffLink;
        private boolean isVisited;
        private boolean isTerminal;

        Vertex(String label, String suffix) {
            this.label = label;
            this.suffix = suffix;
            this.outArr = new ArrayList<>();
            this.toNext = new HashMap<>();
            this.isVisited = false;
            this.isTerminal = false;
        }
    }

    private static class VQueue<V> extends AbstractQueue<V> {
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
                vl.remove();
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

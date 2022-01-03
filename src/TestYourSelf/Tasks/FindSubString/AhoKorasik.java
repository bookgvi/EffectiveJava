package TestYourSelf.Tasks.FindSubString;

import java.util.*;

public class AhoKorasik {

    private static Vertex root = new Vertex("root", "");

    public static void main(String[] args) {
        String str = "abrakadabra";
        String ss = "a";

        Map<String, List<Integer>> res = findSubString(str, ss);
        System.out.println(res);
    }

    private static Map<String, List<Integer>> findSubString(String str, String ss) {
        Map<String, List<Integer>> words = new HashMap<>();
        addKeyWord(ss);
        init();
        Vertex cur = root;
        int index = 0;
        for (String ch : str.split("")) {
            index += 1;
            cur = delta(ch, cur);
            if (cur == root) {
                if (cur.toNext.get(ch) == null) continue;
                else cur = cur.toNext.get(ch);
            }
            isOutArr(cur, words, index);
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

    private static void isOutArr(Vertex cur, Map<String, List<Integer>> words, int index) {
        for (Vertex out: cur.outArr) fillWords(out, words, index);
    }

    private static void fillWords(Vertex out, Map<String, List<Integer>> words, int index) {
        int pos = index - out.suffix.length();
        List<Integer> positions = words.getOrDefault(out.suffix, new ArrayList<>());
        positions.add(pos);
        words.putIfAbsent(out.suffix, positions);
    }

    private static void addKeyWord(String keyWord) {
        Vertex curVertex = root;
        StringBuilder suffix = new StringBuilder();
        for (String ch : keyWord.split("")) {
            suffix.append(ch);
            curVertex.toNext.putIfAbsent(ch, new Vertex(ch, suffix.toString()));
            curVertex = curVertex.toNext.get(ch);
        }
        curVertex.isTerminal = true;
    }

    private static void init() {
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

    private static void setSuffLink(Vertex parent, Vertex cur) {
        Vertex parentSuffLink = parent.suffLink;
        if (cur.suffLink == null) {
            cur.suffLink = parentSuffLink.toNext.get(cur.label);
            if (cur.suffLink == null) cur.suffLink = root;
        }
    }

    private static void setRootSuffLink() {
        root.suffLink = root;
        for (Vertex next : root.toNext.values()) next.suffLink = root;
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
        cur.isVisited = false;
        for (Vertex next : cur.toNext.values()) setUnvisited(next);
    }

    private static Vertex getUnvisited(Vertex cur) {
        for (Vertex next : cur.toNext.values())
            if (!next.isVisited) return next;
        return null;
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
                return true;
            }
            return false;
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

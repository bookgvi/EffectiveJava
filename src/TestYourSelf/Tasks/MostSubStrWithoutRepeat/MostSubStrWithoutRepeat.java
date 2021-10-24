package TestYourSelf.Tasks.MostSubStrWithoutRepeat;

import java.util.*;
import java.util.stream.IntStream;

public class MostSubStrWithoutRepeat {
    private static final Vertex root = new Vertex("root", "");
//    private static final boolean isInit = init();

    public static void main(String[] args) {
        String str = "pwwkew";
        int n = lengthOfLongestSubstring(str);
        System.out.println(n);
    }

    public static int lengthOfLongestSubstring(String s) {
        addKeyWord(s);
        init();
        List<Integer> indexes = analize(s);
        sort(indexes);
        int len = indexes.size();
        return indexes.isEmpty() ? 0 : indexes.get(len - 1);
    }

    private static List<Integer> sort(List<Integer> arr) {
        int len = arr.size(), b = 8, dw = 4;
        int[] t = new int[len];
        for (int p = 0; p < dw; p += 1) {
            int[] count = new int[1 << b];
            for (int elt : arr)
                count[((elt ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)] += 1;
            for (int i = 1; i < 1 << b; i += 1)
                count[i] += count[i - 1];
            for (int i = len - 1; i >= 0; i -= 1)
                t[--count[((arr.get(i) ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)]] = arr.get(i);
            IntStream.range(0, len).forEach(i -> arr.set(i, t[i]));
        }
        return arr;
    }

    private static List<Integer> analize(String text) {
        List<Integer> indexes = new ArrayList<>();
        Vertex curVertex = root;
        int index = 0;
        for (String ch : text.split("")) {
            index += 1;
            curVertex = delta(ch, curVertex);
            if (curVertex == root) {
                if (curVertex.toNext.get(ch) == null) continue;
                else curVertex = curVertex.toNext.get(ch);
            }
            isOutArr(curVertex, indexes, index);
        }
        return indexes;
    }

    private static Vertex delta(String ch, Vertex curVertex) {
        if (curVertex.toNext.get(ch) == null && curVertex.suffLink.toNext.get(ch) != null)
            return curVertex.suffLink.toNext.get(ch);
        else if (curVertex.toNext.get(ch) != null)
            return curVertex.toNext.get(ch);
        return root;
    }

    private static void isOutArr(Vertex curVertex, List<Integer> indexes, int index) {
        for (Vertex outVertex : curVertex.outArr) fillIndexes(outVertex, indexes, index);
    }

    private static void fillIndexes(Vertex outVertex, List<Integer> indexes, int index) {
        int pos = index - outVertex.suffix.length();
        indexes.add(pos);
    }


    private static boolean init() {
//        initAbc();
        Vertex startVertex = root, curVertex, nextVertex;
        VertexQueue<Vertex> vertexQueue = new VertexQueue<>();
        setRootSuffLink();
        startVertex.isVisited = true;
        vertexQueue.offer(startVertex);
        while (!vertexQueue.isEmpty()) {
            curVertex = vertexQueue.poll();
            if (curVertex == null) continue;
            while ((nextVertex = getUnvisited(curVertex)) != null) {
                nextVertex.isVisited = true;
                vertexQueue.offer(nextVertex);
                setSuffLink(curVertex, nextVertex);
            }
        }
        setOutArr(root);
        setUnvisited(root);
        return true;
    }

    private static Vertex getUnvisited(Vertex curVertex) {
        for (Vertex nextVertex : curVertex.toNext.values())
            if (!nextVertex.isVisited) return nextVertex;
        return null;
    }

    private static void setRootSuffLink() {
        root.suffLink = root;
        for (Vertex firstAfterRoot : root.toNext.values()) firstAfterRoot.suffLink = root;
    }

    private static void setSuffLink(Vertex parentVertex, Vertex curVertex) {
        Vertex parentSuffLink = parentVertex.suffLink;
        if (curVertex.suffLink == null) {
            curVertex.suffLink = parentSuffLink.toNext.get(curVertex.label);
            if (curVertex.suffLink == null) curVertex.suffLink = root;
        }
    }

    private static void setOutArr(Vertex curVertex) {
        for (Vertex nextVertex : curVertex.toNext.values()) {
            fillOutArr(nextVertex, nextVertex.outArr);
            setOutArr(nextVertex);
        }
    }

    private static void fillOutArr(Vertex curVertex, List<Vertex> outArr) {
        if (curVertex.isTerminal) outArr.add(curVertex);
        if (curVertex.suffLink == root) return;
        fillOutArr(curVertex.suffLink, outArr);
    }

    private static void setUnvisited(Vertex curVertex) {
        curVertex.isVisited = false;
        for (Vertex nextVertex : curVertex.toNext.values())
            setUnvisited(nextVertex);
    }

    private static void initAbc() {
        for (int i = 33; i < (1 << 8); i += 1) {
            addKeyWord(Character.toString(i));
        }
    }

    private static void addKeyWord(String keyWord) {
        Vertex curVertex = root;
        StringBuilder suffix = new StringBuilder();
        for (String ch : keyWord.split("")) {
            suffix.append(ch);
            curVertex.toNext.putIfAbsent(ch, new Vertex(ch, suffix.toString()));
            curVertex = curVertex.toNext.get(ch);
            curVertex.isTerminal = true;
        }
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
                vList.offer(v);
                res = true;
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

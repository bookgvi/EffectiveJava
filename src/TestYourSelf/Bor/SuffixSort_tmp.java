package TestYourSelf.Bor;

import java.util.*;
import java.util.stream.IntStream;

public class SuffixSort_tmp {

    private final String rootLabel = "root";
    private final String rootSuffix = "";
    private final Vertex root;

    SuffixSort_tmp() {
        this.root = new Vertex(rootLabel, rootSuffix);
    }

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
            isOutArr(curVertex, words, index);
        }
        return words;
    }

    private Vertex delta(String ch, Vertex curVertex) {
        if (curVertex.toNext.get(ch) == null && curVertex.suffLink.toNext.get(ch) != null)
            return curVertex.suffLink.toNext.get(ch);
        else if (curVertex.toNext.get(ch) != null)
            return curVertex.toNext.get(ch);
        return root;
    }

    private void isOutArr(Vertex curVertex, Map<String, List<Integer>> words, int index) {
        for (Vertex outVertex : curVertex.outArr) fillWords(outVertex, words, index);
    }

    private void fillWords(Vertex outVertex, Map<String, List<Integer>> words, int index) {
        int pos = index - outVertex.suffix.length();
        List<Integer> positions = words.getOrDefault(outVertex.suffix, new ArrayList<>());
        positions.add(pos);
        words.putIfAbsent(outVertex.suffix, positions);
    }

    void initBor() {
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
    }

    private Vertex getUnvisited(Vertex curVertex) {
        curVertex.sortedLabels = curVertex.sortedLabels == null
                ? sortChars(new ArrayList<>(curVertex.toNext.keySet()))
                : curVertex.sortedLabels;
        for (String label : curVertex.sortedLabels) {
            Vertex nextVertex = curVertex.toNext.get(label);
            if (!nextVertex.isVisited) return nextVertex;
        }
        return null;
    }

    private List<String> sortChars(List<String> chArr) {
        int d = 8;
        int w = Integer.BYTES;
        int len = chArr.size();
        int[] target = new int[len], arr = new int[len];
        IntStream.range(0, len).forEach(i -> arr[i] = chArr.get(i).getBytes()[0] - "a".getBytes()[0] + 1);
        for (int p = 0; p < w; p += 1) {
            int[] digits = new int[1 << d];
            for (int chCode : arr) {
                digits[((chCode ^ Integer.MIN_VALUE) >> (p * d)) & ((1 << d) - 1)] += 1;
            }
            for (int i = 1; i < digits.length; i += 1) {
                digits[i] += digits[i - 1];
            }
            for (int i = len - 1; i >= 0; i -= 1) {
                target[--digits[((arr[i] ^ Integer.MIN_VALUE) >> (d * p) & ((1 << d) - 1))]] = arr[i];
            }
            System.arraycopy(target, 0, arr, 0, len);
        }
        List<String> res = new ArrayList<>();
        for(int chCode : arr) res.add(Character.toString(chCode + "a".getBytes()[0] - 1));
        return res;
    }

    private void setRootSuffLink() {
        root.suffLink = root;
        for (Vertex firstAfterRoot : root.toNext.values()) firstAfterRoot.suffLink = root;
    }

    private void setSuffLink(Vertex parentVertex, Vertex curVertex) {
        Vertex parentSuffLink = parentVertex.suffLink;
        if (curVertex.suffLink == null) {
            curVertex.suffLink = parentSuffLink.toNext.get(curVertex.label);
            if (curVertex.suffLink == null) {
                curVertex.suffLink = root;
            }
        }
    }

    private void setOutArr(Vertex curVertex) {
        for (Vertex nextVertex : curVertex.toNext.values()) {
            fillOutArr(nextVertex, nextVertex.outArr);
            setOutArr(nextVertex);
        }
    }

    private void fillOutArr(Vertex curVertex, List<Vertex> outArr) {
        if (curVertex.isTerminal) outArr.add(curVertex);
        if (curVertex.suffLink == root) return;
        fillOutArr(curVertex.suffLink, outArr);
    }

    private void setUnvisited(Vertex curVertex) {
        curVertex.isVisited = false;
        for (Vertex nextVertex : curVertex.toNext.values()) {
            setUnvisited(nextVertex);
        }
    }

    void dfs() {
        Vertex startVertex = root, curVertex, nextVertex;
        Stack<Vertex> vertexStack = new Stack<>();
        startVertex.isVisited = true;
        vertexStack.push(startVertex);
        while(!vertexStack.isEmpty()) {
            curVertex = vertexStack.peek();
            if ((nextVertex = getUnvisited(curVertex)) == null) vertexStack.pop();
            else {
                nextVertex.isVisited = true;
                vertexStack.push(nextVertex);
                if (nextVertex.isTerminal) System.out.printf("\n%s ", nextVertex.suffix);
            }
        }
        System.out.println();
        setUnvisited(root);
    }

    private static class Vertex {
        private final String label;
        private final String suffix;
        private final List<Vertex> outArr;
        private final Map<String, Vertex> toNext;
        private Vertex suffLink;
        private boolean isVisited;
        private boolean isTerminal;
        private List<String> sortedLabels;

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

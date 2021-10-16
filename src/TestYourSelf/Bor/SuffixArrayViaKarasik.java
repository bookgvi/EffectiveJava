package TestYourSelf.Bor;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.*;

public class SuffixArrayViaKarasik {

    private final String rootLabel = "root";
    private final String rootSuffix = "";
    private final Vertex root;

    SuffixArrayViaKarasik() {
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
        for(String ch : text.split("")) {
            index += 1;
            curVertex = delta(ch, curVertex);
            if (curVertex == root) {
                if (curVertex.toNext.get(ch) == null) continue;
                else curVertex = curVertex.toNext.get(ch);
            }
            isOut(curVertex, words, index);
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

    private void isOut(Vertex curVertex, Map<String, List<Integer>> words, int index) {
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
        startVertex.isVisited = false;
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

    void dfs() {
        Vertex startVertex = root, curVertex, nextVertex;
        Stack<Vertex> vertexStack = new Stack<>();
        startVertex.isVisited = true;
        vertexStack.push(startVertex);
        while (!vertexStack.isEmpty()) {
            curVertex = vertexStack.peek();
            if ((nextVertex = getUnvisited(curVertex)) == null) {
                vertexStack.pop();
            } else {
                nextVertex.isVisited = true;
                vertexStack.push(nextVertex);

                if (nextVertex.isTerminal) System.out.printf("%s\n", nextVertex.suffix);
            }
        }
        setUnvisited(root);
    }

    private void setRootSuffLink() {
        root.suffLink = root;
        for (Vertex firstAfterRoot : root.toNext.values()) firstAfterRoot.suffLink = root;
    }

    private void setSuffLink(Vertex parentVertex, Vertex curVertex) {
        Vertex parentSuffLink = parentVertex.suffLink;
        if (curVertex.suffLink == null) {
            curVertex.suffLink = parentSuffLink.toNext.get(curVertex.label);
            if (curVertex.suffLink == null) curVertex.suffLink = root;
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

    private Vertex getUnvisited(Vertex curVertex) {
        curVertex.nextVertexLabelsSorted = curVertex.nextVertexLabelsSorted.isEmpty()
                ? sort(new ArrayList<>(curVertex.toNext.keySet()))
                : curVertex.nextVertexLabelsSorted;
        for (String nextLabel : curVertex.nextVertexLabelsSorted) {
            Vertex nextVertex = curVertex.toNext.get(nextLabel);
            if (!nextVertex.isVisited) return nextVertex;
        }
        return null;
    }

    private List<String> sort(List<String> labels) {
        int b = 8, w = Integer.BYTES, len = labels.size();
        int[] target = new int[len];
        int[] arr = new int[len];
        int firstCharByte = "a".getBytes(StandardCharsets.UTF_8)[0];
        IntStream.range(0, len).forEach(i -> arr[i] = labels.get(i).getBytes()[0] - firstCharByte + 1);
        for (int p = 0; p < w; p += 1) {
            int[] count = new int[1 << b];
            for (int elt : arr) {
                count[((elt ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)] += 1;
            }
            for (int i = 1; i < 1 << b; i += 1) {
                count[i] += count[i - 1];
            }
            for (int i = len - 1; i >= 0; i -= 1) {
                target[--count[((arr[i] ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)]] = arr[i];
            }
            System.arraycopy(target, 0, arr, 0, len);
        }
        return Arrays.stream(arr).mapToObj(elt -> Character.toString((char) elt + firstCharByte - 1)).collect(Collectors.toList());
    }

    private static class Vertex {
        private final String label;
        private final String suffix;
        private final List<Vertex> outArr;
        private final Map<String, Vertex> toNext;
        private List<String> nextVertexLabelsSorted;
        private Vertex suffLink;
        private boolean isVisited;
        private boolean isTerminal;

        Vertex(String label, String suffix) {
            this.label = label;
            this.suffix = suffix;
            this.outArr = new ArrayList<>();
            this.toNext = new HashMap<>();
            this.nextVertexLabelsSorted = new ArrayList<>();
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

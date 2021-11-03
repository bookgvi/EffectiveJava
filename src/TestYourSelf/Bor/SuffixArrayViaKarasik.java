package TestYourSelf.Bor;

import java.util.*;
import java.util.stream.*;

public class SuffixArrayViaKarasik {

    private final Vertex root;

    SuffixArrayViaKarasik() {
        this.root = new Vertex("root", "");
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
                if (nextVertex.isTerminal) System.out.println(nextVertex.suffix);
            }
        }
        setUnvisited(root);
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
        setUnvisited(root);
    }

    private void setRootSuffLink() {
        root.suffLink = root;
        for (Vertex firstAfterroot : root.toNext.values()) firstAfterroot.suffLink = root;
    }

    private void setSuffLink(Vertex parentVertex, Vertex curVertex) {
        Vertex parentSuffLink = parentVertex.suffLink;
        if (curVertex.suffLink == null) {
            curVertex.suffLink = parentSuffLink.toNext.get(curVertex.label);
            if (curVertex.suffLink == null) curVertex.suffLink = root;
        }
    }

    private void setUnvisited(Vertex curVertex) {
        curVertex.isVisited = false;
        for (Vertex nextVertex : curVertex.toNext.values()) setUnvisited(nextVertex);
    }

    private Vertex getUnvisited(Vertex curVertex) {
        if (curVertex.sortedToNextLabels.isEmpty()) curVertex.sortedToNextLabels = storeSortedLabels(curVertex);
        for (String nextLabel : curVertex.sortedToNextLabels) {
            Vertex nextVertex = curVertex.toNext.get(nextLabel);
            if (!nextVertex.isVisited) return nextVertex;
        }
        return null;
    }

    private List<String> storeSortedLabels(Vertex curVertex) {
        List<String> sortedLabels = new ArrayList<>(curVertex.toNext.keySet());
        int len = sortedLabels.size(), firstCharByte = "a".getBytes()[0];
        int[] arr = new int[len];
        List<String> finalSortedLabels = sortedLabels;
        IntStream.range(0, len).forEach(i -> arr[i] = finalSortedLabels.get(i).charAt(0) - firstCharByte + 1);
        sort(arr);
        sortedLabels = Arrays.stream(arr).mapToObj(code -> Character.toString(code + firstCharByte - 1)).collect(Collectors.toList());
        return sortedLabels;
    }

    private void sort(int[] arr) {
        int len = arr.length, b = 8, dw = 4;
        int[] t = new int[len];
        for (int p = 0; p < dw; p += 1) {
            int[] count = new int[1 << b];
            for (int elt : arr)
                count[((elt ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)] += 1;
            for (int i = 1; i < 1 << b; i += 1)
                count[i] += count[i - 1];
            for (int i = len - 1; i >= 0; i -= 1)
                t[--count[((arr[i] ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)]] = arr[i];
            System.arraycopy(t, 0, arr, 0, len);
        }
    }

    private static class Vertex {
        private final String label;
        private final String suffix;
        private final Map<String, Vertex> toNext;
        private List<String> sortedToNextLabels;
        private Vertex suffLink;
        private boolean isVisited;
        private boolean isTerminal;

        Vertex(String label, String suffix) {
            this.label = label;
            this.suffix = suffix;
            this.toNext = new HashMap<>();
            this.sortedToNextLabels = new ArrayList<>();
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

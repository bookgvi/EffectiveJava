package TestYourSelf.Bor;

import java.util.*;
import java.util.stream.*;

public class SuffixArrayViaKarasik {

    private final Vertex root = new Vertex("root", "");

    SuffixArrayViaKarasik() {
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

    void dfs() {
        Vertex startVertex = root, nextVertex;
        Stack<Vertex> vertexStack = new Stack<>();
        startVertex.isVisited = true;
        vertexStack.push(startVertex);
        while(!vertexStack.isEmpty()) {
            if ((nextVertex = getUnvisited(vertexStack.peek())) == null) vertexStack.pop();
            else {
                nextVertex.isVisited = true;
                vertexStack.push(nextVertex);
                if (nextVertex.isTerminal) System.out.println(nextVertex.suffix);
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

    private void setUnvisited(Vertex curVertex) {
        curVertex.isVisited = false;
        for (Vertex nextVertex : curVertex.toNext.values())
            setUnvisited(nextVertex);
    }

    private Vertex getUnvisited(Vertex curVertex) {
        curVertex.sortedToNextLabels = curVertex.sortedToNextLabels.isEmpty() ? fillLabels(curVertex) : curVertex.sortedToNextLabels;
        for (String nextLabel : curVertex.sortedToNextLabels) {
            Vertex nextVertex = curVertex.toNext.get(nextLabel);
            if (!nextVertex.isVisited) return nextVertex;
        }
        return null;
    }

    private List<String> fillLabels(Vertex curVertex) {
        List<String> labels = new ArrayList<>(curVertex.toNext.keySet());
        int[] codes = new int[labels.size()];
        int firstCharBite = "a".getBytes()[0];
        IntStream.range(0, labels.size()).forEach(i -> codes[i] = labels.get(i).getBytes()[0] - firstCharBite + 1);
        sort(codes);
        IntStream.range(0, labels.size()).forEach(i -> labels.set(i, Character.toString(codes[i] + firstCharBite - 1)));
        return labels;
    }

    private void sort(int[] arr) {
        for (int i = 0, len = arr.length; i < len; i += 1) {
            for (int j = i; j > 0 && arr[j - 1] - arr[j] > 0; j -= 1)
                swap(j - 1, j, arr);
        }
    }

    private void swap(int i, int j, int[] arr) {
        arr[i] += arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
    }

    private static class Vertex {
        private final String label;
        private final String suffix;
        private final Map<String, Vertex> toNext;
        private final List<Vertex> outArr;
        private List<String> sortedToNextLabels;
        private Vertex suffLink;
        private boolean isVisited;
        private boolean isTerminal;

        Vertex(String label, String suffix) {
            this.label = label;
            this.suffix = suffix;
            this.toNext = new HashMap<>();
            this.outArr = new ArrayList<>();
            this.sortedToNextLabels = new ArrayList<>();
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

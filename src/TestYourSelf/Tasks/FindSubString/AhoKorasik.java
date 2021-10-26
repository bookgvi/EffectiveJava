package TestYourSelf.Tasks.FindSubString;

import java.util.*;

public class AhoKorasik {
    private static final String rootLabel = "root";
    private static final String rootSuffix = "";
    private static final Vertex root = new Vertex(rootLabel, rootSuffix);

    public static void main(String[] args) {
        String str = "abracadabra";
        String ss = "bra";

        List<Integer> index = searchSubStr(str, ss);
        System.out.println(str);
        System.out.println(ss);
        System.out.println(index);
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

    private static List<Integer> searchSubStr(String str, String ss) {
        List<Integer> indexes = new ArrayList<>();
        Vertex curVertex = root;
        int index = 0;
        addKeyWord(ss);
        init();
        for (String ch : str.split("")) {
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

    private static void isOutArr(Vertex curVertex, List<Integer> indexes, int index) {
        for (Vertex outVertex : curVertex.outArr) fillIndexes(outVertex, indexes, index);
    }

    private static void fillIndexes(Vertex outVertex, List<Integer> indexes, int index) {
        int pos = index - outVertex.suffix.length();
        indexes.add(pos);
    }

    private static Vertex delta(String ch, Vertex curVertex) {
        if (curVertex.toNext.get(ch) == null && curVertex.suffLink.toNext.get(ch) != null)
            return curVertex.suffLink.toNext.get(ch);
        else if (curVertex.toNext.get(ch) != null)
            return curVertex.toNext.get(ch);
        return root;
    }

    private static void init() {
        Vertex startVertex = root, curVertex, nextVertex;
        VertexQueue<Vertex> vertexQueue = new VertexQueue<>();
        setRootSuffLink();
        startVertex.isVisited = true;
        vertexQueue.offer(startVertex);
        while(!vertexQueue.isEmpty()) {
            curVertex = vertexQueue.poll();
            if (curVertex == null) continue;
            while((nextVertex = getUnvisited(curVertex)) != null) {
                nextVertex.isVisited = true;
                vertexQueue.offer(nextVertex);
                setSuffLink(curVertex, nextVertex);
            }
        }
        setOutArr(root);
        setUnvisited(root);
    }

    private static void setUnvisited(Vertex curVertex) {
        curVertex.isVisited = false;
        for (Vertex nextVertex : curVertex.toNext.values())
            setUnvisited(nextVertex);
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

    private static void setSuffLink(Vertex parentVertex, Vertex curVertex) {
        Vertex parentSuffLink = parentVertex.suffLink;
        if (curVertex.suffLink == null) {
            curVertex.suffLink = parentSuffLink.toNext.get(curVertex.label);
            if (curVertex.suffLink == null) curVertex.suffLink = root;
        }
    }

    private static void setRootSuffLink() {
        root.suffLink = root;
        for(Vertex firstAfterRoot : root.toNext.values()) firstAfterRoot.suffLink = root;
    }

    private static Vertex getUnvisited(Vertex curVertex) {
        for (Vertex nextVertex : curVertex.toNext.values())
            if (!nextVertex.isVisited) return nextVertex;
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

package Algos.String;

import java.util.*;

public class MostSubStringKarasik {
    private static final String rootLabel = "root";
    private static final String rootSuffix = "";
    private static final Vertex root = new Vertex(rootLabel, rootSuffix);

    public static void main(String[] args) {
        final String str1 = "VOTEFORTHEGREATALBANIAFORYOUVOTEFORTHEGREATALBANIAFORYOUVOTEFORTHEGREATALBANIAFORYOUVOTEFORTHEGREATALBANIAFORYOUVOTEFORTHEGREATALBANIAFORYOUVOTEFORTHEGREATALBANIAFORYOUVOTEFORTHEGREATALBANIAFORYOUVOTEFORTHEGREATALBANIAFORYOUVOTEFORTHEGREATALBANIAFORYOUVOTEFORTHEGREATALBANIAFORYOU";
        final String str2 = "CHOOSETHEGREATALBANIANFUTURECHOOSETHEGREATALBANIANFUTURECHOOSETHEGREATALBANIANFUTURECHOOSETHEGREATALBANIANFUTURECHOOSETHEGREATALBANIANFUTURECHOOSETHEGREATALBANIANFUTURECHOOSETHEGREATALBANIANFUTURECHOOSETHEGREATALBANIANFUTURECHOOSETHEGREATALBANIANFUTURECHOOSETHEGREATALBANIANFUTURE";

        String[] keyWords = getKeyWords(str1);
        addKeyWord(keyWords);

//        String[] keyWords1 = {"i", "in", "tin", "sting"};
//        String[] keyWords2 = {"he", "s", "she", "his", "hers", "him"};
//        String[] keyWords3 = {"a", "aa", "aaa", "aaaa", "hers", "s"};
//        String[] keyWords4 = {"a", "bc", "abc", "dab", "dabc"};
//        String textForAnal = "stinghersstinger dabc";
//        List.of(keyWords1, keyWords2, keyWords3, keyWords4).forEach(MostSubString::addKeyWord);

        intiBor();
        long startTime = System.nanoTime();
        Map<Integer, List<String>> words = analize(str2);
        long endTime = System.nanoTime();
        System.out.println(words.get(words.size()));
        System.out.printf("%.8f\n", (endTime - startTime) / 1e9);
    }

    private static String[] getKeyWords(String str) {
        int len = str.length();
        String[] keyWords = new String[len];
        keyWords[0] = str;
        for (int i = 1; i < len; i += 1) {
            keyWords[i] = str.substring(len - i, len);
        }
        return keyWords;
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

    private static void addKeyWord(String[] keyWords) {
        for (String keyWord : keyWords) addKeyWord(keyWord);
    }

    private static Map<Integer, List<String>> analize(String text) {
        Map<Integer, List<String>> words = new HashMap<>();
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

    private static Vertex delta(String ch, Vertex curVertex) {
        if (curVertex.toNext.get(ch) == null && curVertex.suffLink.toNext.get(ch) != null)
            return curVertex.suffLink.toNext.get(ch);
        else if (curVertex.toNext.get(ch) != null)
            return curVertex.toNext.get(ch);
        return root;
    }

    private static void isOutArr(Vertex curVertex, Map<Integer, List<String>> words, int index) {
        for (Vertex outVertex : curVertex.outArr) fillWords(outVertex, words, index);
    }

    private static void fillWords(Vertex outVertex, Map<Integer, List<String>> words, int index) {
        Integer len = outVertex.suffix.length();
        int pos = index - len;
        List<String> subStrings = words.getOrDefault(len, new ArrayList<>());
        subStrings.add(outVertex.suffix + " (" + pos + ")");
        words.putIfAbsent(len, subStrings);
    }

    private static void intiBor() {
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

    private static void setOutArr(Vertex startVertex) {
        for (Vertex nextVertex : startVertex.toNext.values()) {
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
        for (Vertex nextVertex : curVertex.toNext.values()) {
            setUnvisited(nextVertex);
        }
    }

    private static Vertex getUnvisited(Vertex curVertex) {
        for (Vertex nextVertex : curVertex.toNext.values()) {
            if (!nextVertex.isVisited) return nextVertex;
        }
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
                res = vList.offer(v);
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

package Structures.Suffix;

import java.util.*;

public class SuffixArrayViaBor {

    private static final Vertex root = new Vertex("root", "");
    private static final List<Integer> p = new ArrayList<>();

    private static void addWord(String str, int posInWord) {
        Vertex cur = root;
        StringBuilder suffix = new StringBuilder();
        for (int i = 0, len = str.length(); i < len; i += 1) {
            String ch = str.substring(i, i + 1);
            suffix.append(ch);
            cur.toNext.putIfAbsent(ch, new Vertex(ch, suffix.toString()));
            cur.toNext.get(ch).posInWord = posInWord;
            cur = cur.toNext.get(ch);
        }
        cur.isTerminal = true;
    }

    private static void addWords(String str) {
        String[] suffixes = getSuffixes(str);
        for (int i= 0, len = str.length(); i < len; i += 1) addWord(suffixes[i], i);
    }

    private static void init(String str) {
        Vertex start = root, cur, next;
        Stack<Vertex> stack = new Stack<>();
        addWords(str);
        start.isVisited = true;
        stack.push(start);
        while (!stack.isEmpty()) {
            cur = stack.peek();
            if ((next = getUnvisited(cur)) == null) stack.pop();
            else {
                next.isVisited = true;
                stack.push(next);
                if (next.isTerminal) {
                    System.out.println(next.suffix);
                    p.add(next.posInWord);
                }
            }
        }
        setUnvisited(root);
    }

    private static void setUnvisited(Vertex cur) {
        cur.isVisited = false;
        for (Vertex next : cur.toNext.values()) setUnvisited(next);
    }

    private static Vertex getUnvisited(Vertex cur) {
        cur.sortedLabels = sort(new ArrayList<>(cur.toNext.keySet()));
        for (String nextLabel : cur.sortedLabels) {
            Vertex next = cur.toNext.get(nextLabel);
            if (!next.isVisited) return next;
        }
        return null;
    }

    private static List<String> sort(List<String> arr) {
        int len = arr.size(), b = 5;
        int[] count = new int[1 << b];
        List<String> t = new ArrayList<>(List.copyOf(arr));
        for (String lbl : arr)
            count[lbl.charAt(0) - 'a'] += 1;
        for (int i = 1; i < 1 << b; i += 1)
            count[i] += count[i - 1];
        for (int i = len - 1; i >= 0; i -= 1)
            t.set(--count[arr.get(i).charAt(0) - 'a'], arr.get(i));
        return t;
    }

    private static String[] getSuffixes(String str) {
        int len = str.length();
        String[] res = new String[len];
        for (int i = len - 1; i >= 0; i -= 1) {
            res[i] = str.substring(i, len);
        }
        return res;
    }

    private static class Vertex {
        private final String label;
        private final String suffix;
        private final Map<String, Vertex> toNext;
        private List<String> sortedLabels;
        private boolean isTerminal;
        private boolean isVisited;
        private int posInWord;

        Vertex(String label, String suffix) {
            this.label = label;
            this.suffix = suffix;
            this.toNext = new HashMap<>();
            this.sortedLabels = new ArrayList<>();
            this.isTerminal = false;
            this.isVisited = false;
        }
    }

    public static void main(String[] args) {
        String str = "abracadabra";
        init(str);
        System.out.println(p);
    }
}

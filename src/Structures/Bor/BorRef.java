package Structures.Bor;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BorRef {
    private final String str = "abcdabdefabooo";
    private final String ss = "ab";

    private final Vertex root;

    BorRef() {
        this.root = new Vertex("root");
    }

    public void addString(String str) {
        Vertex curVertex = root;
        for (String ch : str.split("")) {
            curVertex.toNext.putIfAbsent(ch, new Vertex(ch));
            curVertex = curVertex.toNext.get(ch);
        }
        curVertex.isTerminal = true;
    }

    public void dfs(String startLabel) {
        Vertex startVertex = getCurVertex(startLabel, root), curVertex = startVertex;
        if (curVertex == null) return;
        Stack<Vertex> vertexStack = new Stack<>();
        curVertex.isVisited = true;
        vertexStack.push(curVertex);
        Vertex nextVertex;
        System.out.print(startLabel);
        while(!vertexStack.isEmpty()) {
            curVertex = vertexStack.peek();
            nextVertex = getUnvisited(curVertex);
            if (nextVertex == null) vertexStack.pop();
            else {
                nextVertex.isVisited = true;
                vertexStack.push(nextVertex);
                System.out.print(nextVertex.label);
            }
        }
        System.out.println();
        setUnvisited(startVertex);
    }

    private Vertex getCurVertex(String label, Vertex curVertex) {
        if (curVertex.label.equals(label)) return curVertex;
        for (Vertex v : curVertex.toNext.values()) {
            return getCurVertex(label, v);
        }
        return null;
    }

    private Vertex getUnvisited(Vertex startVertex) {
        for (Vertex curVertex : startVertex.toNext.values()) {
            if (!curVertex.isVisited) return curVertex;
        }
        return null;
    }

    private void setUnvisited(Vertex startVertex) {
        startVertex.isVisited = false;
        for (Vertex v : startVertex.toNext.values()) {
            setUnvisited(v);
        }
    }

    private static class Vertex {
        private final String label;
        private final Map<String, Vertex> toNext;
        private boolean isVisited;
        private boolean isTerminal;

        Vertex(String label) {
            this.label = label;
            this.isTerminal = false;
            this.isVisited = false;
            this.toNext = new HashMap<>();
        }
    }
}

package TestYourSelf.Bor;

import java.util.*;

public class Bor {

    private final Vertex root;

    Bor() {
        root = new Vertex("root");
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
        Vertex curVertex = getVertexByLabel(startLabel, root);
        if (curVertex == null) return;
        Stack<Vertex> vertexStack = new Stack<>();
        curVertex.isVisited = true;
        vertexStack.push(curVertex);
        Vertex nextVertex;
        System.out.print(startLabel);
        while(!vertexStack.isEmpty()) {
            nextVertex = getUnvisited(vertexStack.peek());
            if (nextVertex == null) vertexStack.pop();
            else {
                nextVertex.isVisited = true;
                vertexStack.push(nextVertex);
                System.out.print(nextVertex.label);
            }
        }
        System.out.println();
        setUnvisited(curVertex);
    }

    private Vertex getVertexByLabel(String label, Vertex curVertex) {
        if (curVertex == null) return null;
        if (curVertex.label.equals(label)) return curVertex;
        for (Vertex nextVertex : curVertex.toNext.values()) {
            return getVertexByLabel(label, nextVertex);
        }
        return null;
    }

    private Vertex getUnvisited(Vertex startVertex) {
        if (startVertex == null) return null;
        for (Vertex nextVertex : startVertex.toNext.values()) {
            if (!nextVertex.isVisited) return nextVertex;
        }
        return null;
    }

    private void setUnvisited(Vertex curVertex) {
        if (curVertex == null) return;
        curVertex.isVisited = false;
        for (Vertex nextVertex : curVertex.toNext.values()) {
            setUnvisited(nextVertex);
        }
    }

    private static class Vertex {
        private final String label;
        private final Map<String, Vertex> toNext;
        private boolean isVisited;
        private boolean isTerminal;

        Vertex(String label) {
            this.label = label;
            this.toNext = new HashMap<>();
            this.isVisited = false;
            this.isTerminal = false;
        }
    }
}

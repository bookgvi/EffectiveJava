package TestYourSelf.Bor;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Bor {
    Vertex root;

    Bor() {
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

    public void dfs(String startVertex) {
        Vertex curVertex = root.toNext.get(startVertex);
        Stack<Vertex> vertexStack = new Stack<>();
        curVertex.isVisited = true;
        vertexStack.push(curVertex);
        System.out.printf("%s", curVertex.label);
        while (!vertexStack.isEmpty()) {
            curVertex = vertexStack.peek();
            Vertex nexVertex = getUnvisited(curVertex);
            if (nexVertex == null) vertexStack.pop();
            else {
                nexVertex.isVisited = true;
                vertexStack.push(nexVertex);
                System.out.printf("%s", nexVertex.label);
            }
        }
        System.out.println();
        setUnvisited(curVertex);
    }

    private Vertex getUnvisited(Vertex startVertex) {
        for (Vertex nextVertex : startVertex.toNext.values()) {
            if (!nextVertex.isVisited) return nextVertex;
        }
        return null;
    }

    private void setUnvisited(Vertex startVertex) {
        if (startVertex == null) return;
        startVertex.isVisited = false;
        if (startVertex.isTerminal) return;
        for (Vertex nextVertex : startVertex.toNext.values())
            setUnvisited(nextVertex);
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
            toNext = new HashMap<>();
        }
    }
}

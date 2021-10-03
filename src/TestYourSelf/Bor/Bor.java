package TestYourSelf.Bor;

import java.util.*;

public class Bor {
    private Vertex root;

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

    public void dfs(String startLabel) {
        Vertex currentVertex = findVertex(startLabel, root);
        if (currentVertex == null) return;
        Stack<Vertex> vertexStack = new Stack<>();
        currentVertex.isVisited = true;
        vertexStack.push(currentVertex);
        Vertex nextVertex;
        System.out.print(currentVertex.label);
        while (!vertexStack.isEmpty()) {
            currentVertex = vertexStack.peek();
            nextVertex = getUnvisited(currentVertex);
            if (nextVertex == null) vertexStack.pop();
            else {
                nextVertex.isVisited = true;
                vertexStack.push(nextVertex);
                System.out.print(nextVertex.label);
            }
        }
        System.out.println();
        setUnvisited(findVertex(startLabel, root));
    }

    private Vertex findVertex(String startLabel, Vertex curVertex) {
        if (curVertex == null) return null;
        if (curVertex.label.equals(startLabel)) return curVertex;
        if (curVertex.isTerminal) return null;
        Vertex foundV = null;
        for (Vertex nextVertex : curVertex.toNext.values()) {
            foundV = findVertex(startLabel, nextVertex);
        }
        return foundV;
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
        for(Vertex nextVertex : startVertex.toNext.values()) {
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
            this.isVisited = false;
            this.isTerminal = false;
            this.toNext = new HashMap<>();
        }
    }
}

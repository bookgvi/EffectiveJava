package Algos.Bor;

import java.util.*;

public class Bor {
    private Vertex root;
    private Vertex empty = new Vertex("");

    Bor() {
        root = new Vertex("root");
    }

    public void addString(String str) {
        Vertex curVertex = root;
        for (String ch : str.split("")) {
            if (curVertex.toNext.get(ch) == null)
                curVertex.toNext.put(ch, new Vertex(ch));
            curVertex = curVertex.toNext.get(ch);
        }
        curVertex.isTerminal = true;
    }

    public void dfs(String startCh) {
        Stack<Vertex> vertexStack = new Stack<>();
        Vertex currentVertex = root.toNext.get(startCh);
        currentVertex.isVisited = true;
        vertexStack.push(currentVertex);
        System.out.printf("%s", currentVertex.label);
        while (!vertexStack.isEmpty()) {
            currentVertex = vertexStack.peek();
            Vertex nextVertex = getUnvisited(currentVertex);
            if (nextVertex.label.equals("")) vertexStack.pop();
            else {
                nextVertex.isVisited = true;
                vertexStack.push(nextVertex);
                System.out.printf("%s", nextVertex.label);
            }
        }
        System.out.println();
        setUnvisited(currentVertex);
    }

    private Vertex getUnvisited(Vertex startVertex) {
        for (String label : startVertex.toNext.keySet()) {
            Vertex currV = startVertex.toNext.get(label);
            if (!currV.isVisited) return currV;
        }
        return new Vertex("");
    }

    private void setUnvisited(Vertex startV) {
        if (startV == null) return;
        startV.isVisited = false;
        if (startV.isTerminal) return;
        Set<String> rootLabels = startV.toNext.keySet();
        for (String l : rootLabels) {
            setUnvisited(startV.toNext.get(l));
        }
    }

    private class Vertex {
        private final String label;
        private boolean isVisited;
        private Map<String, Vertex> toNext;
        private boolean isTerminal;

        Vertex(String label) {
            this.label = label;
            this.isVisited = false;
            this.isTerminal = false;
            this.toNext = new HashMap<>();
        }
    }
}

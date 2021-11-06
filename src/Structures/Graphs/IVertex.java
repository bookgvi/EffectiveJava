package Structures.Graphs;

public interface IVertex {
    boolean hasVisited();
    void setVisited(boolean isVisited);
    String getLabel();
}

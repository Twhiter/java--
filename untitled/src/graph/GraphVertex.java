package graph;

import java.util.ArrayList;

/**
 * @author T.Whiter
 * @Date 2020/2/18 9:30
 * @Version 1.0
 */
public class GraphVertex<E> extends Vertex<E> {

    private ArrayList<Edge<E>> successors;
    private ArrayList<Edge<E>> precursors;

    public GraphVertex(E label) {
        super(label);
        successors = new ArrayList<>();
        precursors = new ArrayList<>();
    }

    public ArrayList<Edge<E>> getPrecursors() {
        return precursors;
    }

    public ArrayList<Edge<E>> getSuccessors() {
        return successors;
    }

    public void addSuccessor(Edge<E> edge) {
        successors.add(edge);
    }

    public void addPrecursors(Edge<E> edge) {
        precursors.add(edge);
    }

    public int getInDegree() {
        return precursors.size();
    }

    public int getOutDegree() {
        return successors.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GraphVertex))
            return false;
        return ((GraphVertex) obj).getLabel().equals(getLabel());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

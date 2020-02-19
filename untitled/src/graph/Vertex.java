package graph;

/**
 * @author T.Whiter
 * @Date 2020/2/17 14:35
 * @Version 1.0
 */
public class Vertex<E> {

    private E label;

    public Vertex(E label) {
        this.label = label;
    }


    public E getLabel() {
        return label;
    }

    public void setLabel(E label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vertex))
            return false;
        else
            return label.equals(((Vertex) obj).label);
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }
}

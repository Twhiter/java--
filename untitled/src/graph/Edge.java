package graph;

/**
 * @author T.Whiter
 * @Date 2020/2/18 9:21
 * @Version 1.0
 */
public class Edge<E> {
    private E fromLabel,toLabel;
    private double weight;

    public Edge(E fromLabel, E toLabel, double weight) {
        this.fromLabel = fromLabel;
        this.toLabel = toLabel;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public E getFromLabel() {
        return fromLabel;
    }

    public E getToLabel() {
        return toLabel;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Edge))
            return false;
        return weight == ((Edge) obj).weight
                && fromLabel.equals(((Edge) obj).fromLabel)
                    && toLabel.equals(((Edge) obj).toLabel);
    }

    @Override
    public int hashCode() {
        return fromLabel.hashCode() + toLabel.hashCode();
    }
}

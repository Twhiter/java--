package graph;

import refactor.UIEdge;

/**
 * @author T.Whiter
 * @Date 2020/2/21 17:42
 * @Version 1.0
 */
public class GenerifiedEdge<E> {

    private E from,to;
    private double weight;

    public GenerifiedEdge(E from, E to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public E getFrom() {
        return from;
    }

    public E getTo() {
        return to;
    }

    public void setTo(E to) {
        this.to = to;
    }

    public void setFrom(E from) {
        this.from = from;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}

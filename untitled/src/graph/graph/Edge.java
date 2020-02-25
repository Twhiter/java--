package graph.graph;

import graph.node.GraphNode;

/**
 * @author T.Whiter
 * @Date 2020/2/19 15:13
 * @Version 1.0
 */
public class Edge {
    private GraphNode from;
    private GraphNode to;
    private double weight;

    public Edge(GraphNode from,GraphNode to,double weight) {
        this.from = from;
        this.to = to;
        setWeight(weight);
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public GraphNode getFrom() {
        return from;
    }

    public GraphNode getTo() {
        return to;
    }

    public void setFrom(GraphNode from) {
        this.from = from;
    }

    public void setTo(GraphNode to) {
        this.to = to;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("from:%d,to:%d,weight:%f",getFrom().getID(),
                getTo().getID(),weight);
    }
}

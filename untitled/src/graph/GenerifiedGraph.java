package graph;

import collections.BijectiveMap;
import graph.node.GraphNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author T.Whiter
 * @Date 2020/2/21 17:13
 * @Version 1.0
 */
public class GenerifiedGraph<E> {
    private graph.graph.Graph  integerGraph;
    private int vertexNum = 0;
    private BijectiveMap<E,GraphNode> bijectiveMap;


    public GenerifiedGraph(List<GenerifiedEdge<E>> generifiedEdges) {
        this.bijectiveMap = new BijectiveMap<>();

        ArrayList<graph.graph.Edge> integerEdges = new ArrayList<>();
        for (var edge : generifiedEdges) {
            E from = edge.getFrom();
            E to = edge.getTo();

            GraphNode fromNode = getNode(from);
            GraphNode toNode = getNode(to);

            integerEdges.add(new graph.graph.Edge(fromNode,toNode,edge.getWeight()));
        }

        integerGraph = graph.graph.Graph.getInstance(integerEdges);
    }

    public List<GenerifiedEdge<E>> getShortestPath() {
        List<graph.graph.Edge> integerEdges = integerGraph.shortestPath();

        ArrayList<GenerifiedEdge<E>> generifiedEdges = new ArrayList<>();

        for (var integerEdge : integerEdges) {
            GraphNode fromNode = integerEdge.getFrom();
            GraphNode toNode = integerEdge.getTo();
            double distance = integerEdge.getWeight();

            E from  = bijectiveMap.getKeyByValue(fromNode);
            E to = bijectiveMap.getKeyByValue(toNode);

            generifiedEdges.add(new GenerifiedEdge<>(from,to,distance));
        }
        return generifiedEdges;
    }



    private GraphNode getNode(E label) {
        if (bijectiveMap.containsKey(label))
            return bijectiveMap.getValueByKey(label);
        else {
            GraphNode node = new GraphNode(vertexNum);
            vertexNum ++;
            bijectiveMap.put(label,node);
            return node;
        }
    }

    public int getVertexNum() {
        return vertexNum;
    }
}

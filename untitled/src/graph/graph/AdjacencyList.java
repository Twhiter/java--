package graph.graph;

/**
 * @author T.Whiter
 * @Date 2020/2/19 15:32
 * @Version 1.0
 */

import graph.node.GraphNode;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/***
 * 邻接表
 */
public class AdjacencyList implements Iterable<GraphNode> {
    //后继
    private Hashtable<GraphNode, ArrayList<Edge>> successors;
    //前驱
    private Hashtable<GraphNode,ArrayList<Edge>> precursors;
    //存储节点的链表
    private ArrayList<GraphNode> nodes;


    public AdjacencyList() {
        successors = new Hashtable<>();
        precursors = new Hashtable<>();
        nodes = new ArrayList<>();
    }

    /***
     * 获得某一个节点的前驱
     * @param node 节点
     * @return node节点的前驱
     */
    public ArrayList<Edge> getPrecursors(GraphNode node){
        return precursors.get(node);
    }

    /***
     * 获得某一个节点的后继
     * @param node 节点
     * @return node节点的后继
     */
    public ArrayList<Edge> getSuccessors(GraphNode node) {
        return successors.get(node);
    }

    /***
     * 增加一条边至邻接表中，edge.from会把edge增加到它的后继中
     * edge.to会把edge增加到它的前驱中
     * @param edge 边对象
     */
    public void addEdge(@NotNull Edge edge) {
        if (successors.get(edge.getFrom()) == null) {
            ArrayList<Edge> list = new ArrayList<>();
            list.add(edge);
            successors.put(edge.getFrom(),list);
        }
        else
            successors.get(edge.getFrom()).add(edge);

        if (precursors.get(edge.getTo()) == null) {
            ArrayList<Edge> list = new ArrayList<>();
            list.add(edge);
            precursors.put(edge.getTo(),list);
        }
        else
            precursors.get(edge.getTo()).add(edge);
        if (!nodes.contains(edge.getFrom()))
            nodes.add(edge.getFrom());
        if (!nodes.contains(edge.getTo()))
            nodes.add(edge.getTo());
        Collections.sort(nodes);
    }

    public void addEdges(@NotNull List<Edge> edges) {
        for (Edge e : edges)
            addEdge(e);
    }

    /***
     * 在邻接表中移除某个顶点,以及与之相关的边
     * @param graphNode 该顶点
     */
    public void remove(@NotNull GraphNode graphNode) {
        ArrayList<Edge> sucEdges = successors.get(graphNode);
        ArrayList<Edge> preEdges = precursors.get(graphNode);

        for (Edge sucEdge : sucEdges) {
            GraphNode to = sucEdge.getTo();
            precursors.get(to).remove(sucEdge);
        }

        for (Edge preEdge : preEdges) {
            GraphNode from = preEdge.getFrom();
            successors.get(from).remove(preEdge);
        }

        sucEdges.clear();
        preEdges.clear();

        successors.keySet().remove(graphNode);
        precursors.keySet().remove(graphNode);

        nodes.remove(graphNode);
    }

    /***
     * 清除邻接表,包括前驱表和后继表
     */
    public void clear() {
        precursors.clear();
        successors.clear();
        nodes.clear();
    }

    @NotNull
    @Override
    public Iterator<GraphNode> iterator() {
        return nodes.iterator();
    }

    public int getNodeNumber() {
        return nodes.size();
    }

    public GraphNode getNodeById(int ID) {
        return nodes.get(ID);
    }
}

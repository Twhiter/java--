package graph.graph;

import collections.Array;
import collections.Queue;
import collections.TwoDimensionalArray;
import graph.Graphable;
import graph.node.GraphNode;
import graph.node.Node;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author T.Whiter
 * @Date 2020/2/19 17:01
 * @Version 1.0
 */
public class Graph implements Graphable {

    //邻接表
    private AdjacencyList adjacencyList;

    /***
     *
     * @return 包含图dfs遍历顺序的数组
     */
    @Override
    @NotNull
    public ArrayList<Node> dfs() {
        ArrayList<Node> orderList = new ArrayList<>();
        Array<Boolean> visited = new Array<>(adjacencyList.getNodeNumber(),false);
        for (GraphNode node : adjacencyList) {
            if (visited.get(node.getID()).equals(false))
                dfs(visited,node,orderList);
        }
        return orderList;
    }

    /***
     *
     * @param visited 记录节点是否访问的数组,用节点序号做下标，访问过为true,没访问为false，
     * @param current 当前访问的节点
     * @param orderList 包含dfs顺序的数组
     */
    private void dfs(Array<Boolean> visited, GraphNode current, List<Node> orderList) {
        visited.set(current.getID(),true);
        orderList.add(current);

        for (Edge edge : adjacencyList.getSuccessors(current)) {
            GraphNode node = edge.getTo();
            if (visited.get(node.getID()).equals(false))
                dfs(visited, node, orderList);
        }
    }

    @Override
    public ArrayList<Node> bfs() {
        ArrayList<Node> orderList = new ArrayList<>();
        Array<Boolean> visited = new Array<>(adjacencyList.getNodeNumber(),false);

        for (GraphNode node: adjacencyList)
            if (visited.get(node.getID()).equals(false))
                bfs(orderList,visited,node);
        return orderList;
    }

    private void bfs(ArrayList<Node> orderList,Array<Boolean> visited,GraphNode current) {
        Queue<GraphNode> queue = new Queue<>();
        queue.add(current);
        visited.set(current.getID(),true);
        
        while (!queue.isEmpty()) {
            GraphNode node = queue.pop();
            orderList.add(node);
            ArrayList<Edge> edges = adjacencyList.getSuccessors(node);
            if (edges == null)
                continue;
            for (Edge e : adjacencyList.getSuccessors(node)) {

                GraphNode graphNode = e.getTo();
                if (!visited.get(graphNode.getID())) {
                    queue.add(graphNode);
                    visited.set(graphNode.getID(),true);
                }
            }
        }
    }

    @Override
    public int getVertexNum() {
        return adjacencyList.getNodeNumber();
    }

    public static Graph getInstance(@NotNull List<Edge> edges) {
        Graph graph = new Graph();
        graph.adjacencyList = new AdjacencyList();
        graph.adjacencyList.addEdges(edges);
        return graph;
    }


    /***
     * floydWarshall 算法求各点的最短路径
     * @return 包含各个节点最短路径的数组
     */
    public List<Edge> shortestPath() {
        int vNum = getVertexNum();
        TwoDimensionalArray<Double> shortest = new TwoDimensionalArray<>(vNum,vNum,Graphable.INF);

        for (GraphNode node : adjacencyList) {
            ArrayList<Edge> successors = adjacencyList.getSuccessors(node);
            if (successors == null)
                continue;
            for (Edge edge : successors) {
                int fromID = edge.getFrom().getID();
                int toID = edge.getTo().getID();

                double dis = edge.getWeight();
                double original = shortest.get(fromID,toID);
                shortest.set(fromID,toID,Math.min(dis,original));
            }
        }

        //floydWarshall
        for (int k = 0; k < vNum; k++)
            for(int i = 0;i < vNum;i++)
                for (int j = 0; j < vNum; j++) {
                    double original = shortest.get(i,j);
                    if (original == Graphable.INF)
                        continue;

                    double newDis = shortest.get(i,k) + shortest.get(k,j);
                    shortest.set(i,j,Math.min(original,newDis));
                }


        ArrayList<Edge> list = new ArrayList<>();
        for (int i = 0; i < vNum; i++)
            for (int j = 0; j < vNum; j++) {
                GraphNode fromNode = adjacencyList.getNodeById(i);
                GraphNode toNode = adjacencyList.getNodeById(j);
                double weight = shortest.get(i,j);
                list.add(new Edge(fromNode,toNode,weight));
            }

        return list;
    }
}

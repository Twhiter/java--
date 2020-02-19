package graph;

import collections.Queue;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.function.Consumer;

/**
 * @author T.Whiter
 * @Date 2020/2/18 9:10
 * @Version 1.0
 */
public class Graph<E> implements Graphable {



    //邻接矩阵
    private AdjacencyMatrix<E> matrix;

    /***
     * 根据边数组创造图
     * @param edges 边数组
     */
    public Graph(ArrayList<Edge<E>> edges) {
        Hashtable<E,Integer> hashtable = new Hashtable<>();
        int cnt = 0;

        for (Edge<E> e : edges) {
            if (!hashtable.contains(e.getFromLabel()))
                hashtable.put(e.getFromLabel(),cnt ++);
            if (!hashtable.contains(e.getToLabel()))
                hashtable.put(e.getToLabel(),cnt ++);
        }

        this.matrix = new AdjacencyMatrix<>(hashtable, 1.0 * INF,edges);
    }


    public AdjacencyMatrix<E> getMatrix() {
        return matrix;
    }

    /***
     * 返回图的dfs遍历结果
     * @return 记录图的dfs 遍历结果的数组
     */
    @Override
    public ArrayList<Vertex<E>> dfs() {
        HashSet<Integer> visited = new HashSet<>();
        ArrayList<E> nodeList = matrix.getNodeList();
        ArrayList<Vertex<E>> orderList = new ArrayList<>();

        for (var v : nodeList) {
            int ID = matrix.getNodeID(v);
            if (!visited.contains(ID))
                dfs(ID,visited,orderList);
        }
        return orderList;
    }

    /***
     * 对序号为ID的顶点进行DFS
     * @param ID 顶点的ID
     * @param visited 已经访问的顶点集合
     * @param orderList dfs访问的顺序数组
     */
    private void dfs(Integer ID,HashSet<Integer> visited,ArrayList<Vertex<E>> orderList){
        visited.add(ID);
        E label = matrix.getNodeByID(ID);
        orderList.add(new Vertex<>(label));

        for (Edge<E> edge:matrix.getSuccessors(ID)){
            Integer toID = matrix.getNodeID(edge.getToLabel());
            if (!visited.contains(toID))
                dfs(toID,visited,orderList);
        }
    }

    /***
     * 返回图的bfs的顺序结果
     * @return 记录bfs 结果的数组
     */
    @Override
    public ArrayList<Vertex<E>> bfs() {
        ArrayList<Vertex<E>> orderList = new ArrayList<>();
        HashSet<Integer> visited = new HashSet<>();

        for (int i = 0; i < matrix.getLine(); i++) {
            if (!visited.contains(i))
                bfs(i,visited,orderList);
        }
        return orderList;
    }

    /***
     *
     * 对序号为ID的顶点进行bfs
     * @param ID 顶点的ID
     * @param visited 已经访问的顶点集合
     * @param orderList 记录bfs 访问顺序的数组
     */
    private void bfs(int ID,HashSet<Integer> visited,ArrayList<Vertex<E>> orderList) {
        Queue<Integer> queue = new Queue<>();
        queue.add(ID);
        while (!queue.isEmpty()) {
            int index = queue.pop();
            ArrayList<Edge<E>> successors = matrix.getSuccessors(index);
            E label = matrix.getNodeByID(index);
            orderList.add(new Vertex<>(label));

            for (var e : successors) {
                int toID = matrix.getNodeID(e.getToLabel());
                if (!visited.contains(toID))
                    queue.add(toID);
            }
        }
    }


    @Override
    public int getVertexNum() {
        return matrix.getNodeNum();
    }

    public ArrayList<Edge<E>> getShortDis() {
        ArrayList<Edge<E>> edges = new ArrayList<>();
        AdjacencyMatrix<E> matrix;
        try {
             matrix = (AdjacencyMatrix) this.matrix.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return edges;
        }
        assert matrix != null;
        FloydWarshall(matrix);


        for (int i = 0; i < matrix.getLine(); i++) {
            for (int j = 0; j < matrix.getColumn(); j++) {
                E fromLabel = matrix.getNodeByID(i);
                E toLabel = matrix.getNodeByID(j);
                double dis = matrix.get(i,j);

                edges.add(new Edge<>(fromLabel,toLabel,dis));
            }
        }


        return edges;
    }

    /***
     * floydWarshall 算法求所有点的最短路径
     * @param matrix 邻接矩阵
     */
    private void FloydWarshall(AdjacencyMatrix<E> matrix) {

        int v = getVertexNum();
        for (int k = 0; k < v; k++)
            for (int i = 0; i < v; i++)
                for (int j = 0; j < v ; j++) {
                    double dis1 = matrix.get(i,j);
                    double dis2 = matrix.get(i,k) + matrix.get(k,j);
                    matrix.setElement(i,j,Math.min(dis1,dis2));
                }
    }

}

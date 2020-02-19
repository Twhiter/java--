package graph;

/**
 * @author T.Whiter
 * @Date 2020/2/19 9:41
 * @Version 1.0
 */

import java.util.ArrayList;
import java.util.Hashtable;

/***
 * 邻接矩阵类
 */
public class AdjacencyMatrix<E> extends Matrix<Double>   {

    //顶点映射成ID
    private Hashtable<E,Integer> ID;

    //ID映射成顶点
    private ArrayList<E> nodeList;

    private AdjacencyMatrix(AdjacencyMatrix<E> adjacencyMatrix) {
        super(adjacencyMatrix.ID.size(),adjacencyMatrix.ID.size(), 1.0 *  Graphable.INF);
        this.nodeList = (ArrayList<E>) adjacencyMatrix.nodeList.clone();
        this.ID = (Hashtable<E, Integer>) adjacencyMatrix.ID.clone();

    }


    public AdjacencyMatrix(Hashtable<E,Integer> ID,Double initialValue,ArrayList<Edge<E>> edges) {
        super(ID.size(),ID.size(),initialValue);
        this.ID = ID;
        this.nodeList = new ArrayList<>(ID.size());
        for (int i = 0; i < ID.size(); i++)
            nodeList.add(null);

        for (E v : ID.keySet())
            nodeList.set(ID.get(v),v);
        for (Edge<E> e : edges) {
            int fromID = ID.get(e.getFromLabel());
            int toID = ID.get(e.getToLabel());
            setElement(fromID,toID,e.getWeight());
        }
    }

    public E getNodeByID(int ID) {
        return nodeList.get(ID);
    }

    public Integer getNodeID(E node) {
        return ID.get(node);
    }

    public ArrayList<E> getNodeList() {
        return nodeList;
    }

    /***
     * 获得一个顶点的后继
     * @param index 该顶点的下标
     * @return 顶点的后继
     */
    public ArrayList<Edge<E>> getSuccessors(int index) {
        ArrayList<Edge<E>> edges = new ArrayList<>();
        ArrayList<Double> distance = getLineElements(index);

        for (int i = 0; i < distance.size(); i++) {
            E fromLabel = nodeList.get(index);
            E toLabel = nodeList.get(i);

            //可达顶点
            if (distance.get(i) != Graphable.INF)
                edges.add(new Edge<>(fromLabel,toLabel,distance.get(i)));
        }
        return edges;
    }

    /***
     * 获得一个顶点的后继
     * @param v 这个顶点的label
     * @return 顶点的后继
     */
    public ArrayList<Edge<E>> getSuccessors(E v){
        int index = ID.get(v);
        return getSuccessors(index);
    }

    /***
     * 获取顶点数目
     * @return 顶点的数目
     */
    public int getNodeNum(){
        return nodeList.size();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new AdjacencyMatrix<>(this);
    }
}

package graph;

import graph.node.Node;

import java.util.ArrayList;

/**
 * @author T.Whiter
 * @Date 2020/2/19 13:37
 * @Version 1.0
 */
public interface Graphable {
    public static final double INF = 1 << 20;

    /***
     *
     * @return array which contains dfs sequence of graph
     */
    public ArrayList<Node> dfs();

    /***
     *
     * @return list which contains bfs sequence of graph
     */
    public ArrayList<Node> bfs();

    /***
     *
     * @return the num of vertex in total
     */
    public int getVertexNum();
}

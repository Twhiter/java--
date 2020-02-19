package graph;

import java.util.ArrayList;

/**
 * @author T.Whiter
 * @Date 2020/2/17 14:11
 * @Version 1.0
 */
public interface Graphable<E> {

    public static final int INF = 1 << 10;

    /***
     *
     * @return array which contains dfs sequence of graph
     */
    public ArrayList<Vertex<E>> dfs();

    /***
     *
     * @return list which contains bfs sequence of graph
     */
    public ArrayList<Vertex<E>> bfs();

    /***
     *
     * @return the num of vertex in total
     */
    public int getVertexNum();

}

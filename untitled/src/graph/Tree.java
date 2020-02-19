package graph;

import java.util.ArrayList;

/**
 * @author T.Whiter
 * @Date 2020/2/17 14:55
 * @Version 1.0
 */
public  interface Tree<E> extends Graphable<E> {

    /***
     *
     * @return
     */
    public ArrayList<TreeVertex<E>> inOrderVisit();

    /***
     *
     * @return
     */
    public ArrayList<TreeVertex<E>> preOrderVisit();


    /***
     *
     * @return
     */
    public ArrayList<TreeVertex<E>> postOrderVisit();

}

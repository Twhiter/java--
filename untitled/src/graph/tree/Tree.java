package graph.tree;

import graph.Graphable;
import graph.node.AbstractTreeNode;

import java.util.ArrayList;

/**
 * @author T.Whiter
 * @Date 2020/2/19 13:38
 * @Version 1.0
 */
public interface Tree extends Graphable {

    /***
     *
     * @return 返回中序遍历的序列的数组
     */
    public ArrayList<AbstractTreeNode> inOrderVisit();

    /***
     *
     * @return 返回前序遍历的序列的数组
     */
    public ArrayList<AbstractTreeNode> preOrderVisit();


    /***
     *
     * @return 返回后序遍历的序列的数组
     */
    public ArrayList<AbstractTreeNode> postOrderVisit();
}

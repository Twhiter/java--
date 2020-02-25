package graph.tree;

import collections.Queue;
import collections.Stack;
import collections.Array;
import graph.node.AbstractTreeNode;
import graph.node.BitTreeNode;
import graph.node.Node;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.ArrayList;
import java.util.List;

/**
 * @author T.Whiter
 * @Date 2020/2/19 13:59
 * @Version 1.0
 */
public class BitTree implements Tree {

    private BitTreeNode root = null;
    private int vertexNum = 0;


    /***
     * 使用栈模拟中序遍历
     * @return 包含中序序列顺序的数组
     */
    @Override
    public ArrayList<AbstractTreeNode> inOrderVisit() {
        if (root == null || root.isNull())
            return new ArrayList<>();
        return complexVisit(2);
    }

    /***
     * 使用栈模拟先序遍历
     * @return 包含先序序列顺序的数组
     */
    @Override
    public ArrayList<AbstractTreeNode> preOrderVisit() {
        if (root == null || root.isNull())
            return new ArrayList<>();
        return complexVisit(1);
    }

    /***
     * 使用栈模拟后序序列
     * @return 包含后序序列顺序的数组
     */
    @Override
    public ArrayList<AbstractTreeNode> postOrderVisit() {
        if (root == null || root.isNull())
            return new ArrayList<>();
        return complexVisit(3);
    }

    /***
     *
     * @return 包含dfs遍历序列的数组
     */
    @Override
    public ArrayList<Node> dfs() {
        return new ArrayList<>(preOrderVisit());
    }

    /***
     * 使用层次遍历获取，bfs遍历序列
     * @return 包含bfs遍历序列的数组
     */
    @Override
    public ArrayList<Node> bfs() {
        if (root == null || root.isNull())
            return new ArrayList<>();
        Queue<BitTreeNode> queue = new Queue<>();
        ArrayList<Node> nodes = new ArrayList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BitTreeNode node = queue.pop();
            nodes.add(node);
            if (node.left() != null && !node.left().isNull())
                queue.add(node.left());
            if (node.right() != null && !node.left().isNull())
                queue.add(node.right());
        }
        return nodes;
    }

    @Override
    public int getVertexNum() {
        return vertexNum;
    }

    /***
     * 使用栈模拟二叉树的三种遍历方式，并根据index返回对应包含对应遍历方式的数组
     * @param index 遍历方式,1代表先序遍历，2代表中序遍历，3代表后序遍历
     * @return 包含对应遍历遍历方式序列的数组
     */
    private ArrayList<AbstractTreeNode> complexVisit(@Range(from = 1,to = 3) int index) {
        ArrayList<AbstractTreeNode> list = new ArrayList<>();
        if (root == null || root.isNull())
            return list;

        Array<Integer> count = new Array<>(vertexNum,0);
        Stack<BitTreeNode> s = new Stack<>();
        s.add(root);
        while (! s.isEmpty()) {
            BitTreeNode e = s.get();

            if (count.get(e.getID()) == 0) {
                if (e.left() != null && !e.left().isNull())
                    s.add(e.left());
                if (index == 1)
                    list.add(e);
            }
            else if (count.get(e.getID()) == 1) {
                if (e.right() != null && !e.right().isNull())
                    s.add(e.right());
                if (index == 2)
                    list.add(e);
            }
            else {
                s.pop();
                if (index == 3)
                    list.add(e);
            }
            count.set(e.getID(),count.get(e.getID()) + 1);
        }
        return list;
    }

    public BitTreeNode getRoot() {
        return root;
    }

    /***
     * 按照元素逐层的顺序建立二叉树
     * @param nodes 包含逐层元素的数组
     * @return 建成的二叉树
     */
    @NotNull
    public static BitTree getInstance(@NotNull List<BitTreeNode> nodes) {
        BitTree tree = new BitTree();
        int j = 1;

        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).isNull())
                continue;
            tree.vertexNum ++;

            if (j < nodes.size()) {
                setLeft(nodes.get(i),nodes.get(j));
                setParent(nodes.get(j),nodes.get(i));
                j ++;
            }

            if (j  < nodes.size()) {
                setRight(nodes.get(i),nodes.get(j));
                setParent(nodes.get(j),nodes.get(i));
                j ++;
            }
        }

        if (nodes.size() >= 1)
            tree.root = nodes.get(0);
        return tree;
    }

    private static void setParent(@NotNull BitTreeNode node,@NotNull BitTreeNode parent) {
        if (!node.isNull() && !parent.isNull())
            node.setParent(parent);
    }

    private static void setLeft(@NotNull BitTreeNode node,@NotNull BitTreeNode left) {
        if (!node.isNull())
            node.setLeft(left);
    }

    private static void setRight(@NotNull BitTreeNode node,@NotNull BitTreeNode right) {
        if (!node.isNull())
            node.setRight(right);
    }


}

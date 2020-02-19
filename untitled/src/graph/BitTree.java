package graph;

import collections.Queue;
import collections.Stack;

import java.util.ArrayList;

/**
 * @author T.Whiter
 * @Date 2020/2/17 15:03
 * @Version 1.0
 */
public class BitTree<E> implements Tree<E> {

    private TreeVertex<E> root;

    @Override
    public ArrayList<Vertex<E>> dfs() {
        return new ArrayList<>(preOrderVisit());
    }

    /***
     * 使用层次遍历确定bfs序列
     * @return bfs序列数组
     */
    @Override
    public ArrayList<Vertex<E>> bfs() {
        ArrayList<Vertex<E>> vertices = new ArrayList<>();
        if (root == null)
            return vertices;

        Queue<TreeVertex<E>> q = new Queue<>();
        q.add(root);

        while (!q.isEmpty()) {
            TreeVertex<E> v = q.pop();
            vertices.add(v);

            if (v.getLeftChild() != null) {
                q.add(v.getLeftChild());
            }
            if (v.getRightChild() != null) {
                q.add(v.getRightChild());
            }
        }
        return vertices;
    }

    /***
     *
     * @return 二叉树节点数
     */
    @Override
    public int getVertexNum() {
        return bfs().size();
    }

    /***
     *
     * @return 中序遍历序列
     */
    @Override
    public ArrayList<TreeVertex<E>> inOrderVisit() {
        Stack<TreeVertex<E>> s = new Stack<>();
        ArrayList<TreeVertex<E>> list = new ArrayList<>();
        TreeVertex<E> v = root;

        while (v != null || !s.isEmpty()) {
           if (v != null) {
               s.add(v);
               v = v.getLeftChild();
           }
           else {
               v = s.pop();
               list.add(v);
               v = v.getRightChild();
           }
        }
        return list;
    }

    /***
     *
     * @return 先序遍历序列
     */
    @Override
    public ArrayList<TreeVertex<E>> preOrderVisit() {
        ArrayList<TreeVertex<E>> list = new ArrayList<>();
        Stack<TreeVertex> s = new Stack<>();
        TreeVertex<E> v = root;

        while (v != null || !s.isEmpty()) {
            if (v != null) {
                list.add(v);
                s.add(v);
                v = v.getLeftChild();
            }
            else {
                v = s.pop();
                v = v.getRightChild();
            }
        }
        return list;
    }

    /***
     * 后序遍历序列
     * @return
     */
    @Override
    public ArrayList<TreeVertex<E>> postOrderVisit() {
        ArrayList<TreeVertex<E>> list = new ArrayList<>();
        Stack<TreeVertex> s = new Stack<>();
        TreeVertex<E> v = root;
        TreeVertex lastVertex = root;

        while (v != null || !s.isEmpty()) {
            while (v != null) {
                s.add(v);
                v = v.getLeftChild();
            }
            v = s.get();
            if (v.getRightChild() == null || v.getRightChild() == lastVertex) {
                list.add(v);
                s.pop();
                lastVertex = v;
                v = null;
            }
            else v = v.getRightChild();
        }
        return list;
    }

    /***
     * 通过按层次输入的数据建立二叉树
     * @param nodes 按层次输入的序列
     */
    public void buildTree(ArrayList<E> nodes) {
        ArrayList<TreeVertex<E>> treeVertices = new ArrayList<>();

        for (E node : nodes) treeVertices.add(new TreeVertex<>(node));

        for (int i = 0; i < treeVertices.size(); i++) {
            int p = (i - 1) / 2;
            if (p >= 0)
                treeVertices.get(i).setParent(treeVertices.get(p));
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < treeVertices.size())
                treeVertices.get(i).setLeftChild(treeVertices.get(left));
            if (right < treeVertices.size())
                treeVertices.get(i).setRightChild(treeVertices.get(right));

            root = treeVertices.get(0);
        }
    }
}

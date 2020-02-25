package graph;

import collections.BijectiveMap;
import graph.node.BitTreeNode;
import graph.node.Node;
import graph.tree.BitTree;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author T.Whiter
 * @Date 2020/2/24 18:02
 * @Version 1.0
 */
abstract public class GenerifiedBitTree<E> {

    private BitTree bitTree;
    private int vertexNum;
    private E nullVal;

    private BijectiveMap<E,Integer> bijectiveMap;

    /***
     *
     * @param order 层次遍历顺序
     */
    public GenerifiedBitTree(List<E> order) {

        nullVal = asNullVal();

        bijectiveMap = new BijectiveMap<>();
        ArrayList<BitTreeNode> bitTreeNodes = new ArrayList<>();

        int cnt = 0;
        for (E item:order) {
            if (!item.equals(nullVal)) {
                bitTreeNodes.add(new BitTreeNode(cnt));
                bijectiveMap.put(item,cnt);
                cnt ++;
            }
            else
                bitTreeNodes.add(new BitTreeNode(-1));
        }

        bitTree = BitTree.getInstance(bitTreeNodes);
    }

    public List<E> dfs() {
        List<Node> order = bitTree.dfs();

        ArrayList<E> dfsOrder = new ArrayList<>();

        order.forEach(node -> dfsOrder.add(bijectiveMap.getKeyByValue(node.getID())));

        return dfsOrder;
    }

    @NotNull abstract public E asNullVal();
}

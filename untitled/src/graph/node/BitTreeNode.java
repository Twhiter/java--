package graph.node;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author T.Whiter
 * @Date 2020/2/19 13:53
 * @Version 1.0
 */
public class BitTreeNode extends AbstractTreeNode {

    private BitTreeNode left = null;
    private BitTreeNode right = null;
    private BitTreeNode parent = null;

    //节点是否为空节点
    private boolean isNull = true;

    public BitTreeNode(int ID) {
        super(ID);
        if (ID != -1)
            isNull = false;
    }

    @Override
    public List<AbstractTreeNode> getChildren() {
        ArrayList<AbstractTreeNode> children = new ArrayList<>();
        children.add(left);
        children.add(right);
        return children;
    }

    @Override
    public AbstractTreeNode getParent() {
        return parent;
    }

    public void setParent(@NotNull BitTreeNode parent) {
        if (parent.isNull)
            throw  new IllegalArgumentException("parent is supposed not to be a null node ");
        if (this.isNull)
            throw new IllegalCallerException("null node can't set parent");

        this.parent = parent;

    }

    public void setLeft(BitTreeNode left) {
        if (this.isNull)
            throw new IllegalCallerException("null node can't set left");
        this.left = left;
    }

    public void setRight(BitTreeNode right) {
        if (this.isNull)
            throw new IllegalCallerException("null node can't set right");
        this.right = right;
    }

    public BitTreeNode left(){
        return left;
    }

    public BitTreeNode right() {
        return right;
    }

    public boolean isNull() {
        return isNull;
    }

    public void setNull(boolean aNull) {
        isNull = aNull;
    }
}

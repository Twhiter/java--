package graph.node;

import java.util.List;

/**
 * @author T.Whiter
 * @Date 2020/2/19 13:43
 * @Version 1.0
 */
public abstract class AbstractTreeNode extends Node {
    protected AbstractTreeNode(int ID) {
        super(ID);
    }

    abstract protected List<AbstractTreeNode> getChildren();
    abstract protected AbstractTreeNode getParent();

}

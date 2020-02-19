package graph;

/**
 * @author T.Whiter
 * @Date 2020/2/17 17:43
 * @Version 1.0
 */
public class TreeVertex<E> extends Vertex<E> {

    private TreeVertex<E> leftChild;
    private TreeVertex<E> rightChild;
    private TreeVertex<E> parent;

    public TreeVertex(E label) {
        super(label);
        leftChild = null;
        rightChild = null;
        parent = null;
    }


    public TreeVertex(Vertex<E> vertex) {
        super(vertex.getLabel());
    }

    public TreeVertex<E> getLeftChild() {
        return leftChild;
    }

    public TreeVertex<E> getRightChild() {
        return rightChild;
    }

    public TreeVertex<E> getParent() {
        return parent;
    }

    public void setLeftChild(TreeVertex<E> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(TreeVertex<E> rightChild) {
        this.rightChild = rightChild;
    }

    public void setParent(TreeVertex<E> parent) {
        this.parent = parent;
    }
}

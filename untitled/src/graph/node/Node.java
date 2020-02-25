package graph.node;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * @author T.Whiter
 * @Date 2020/2/19 13:40
 * @Version 1.0
 */
public class Node implements Comparable<Node>{
    //顶点编号
    protected int ID;
    private final int hashCode;

    public Node(int ID) {
        this.ID = ID;
        hashCode = new Random().nextInt();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node)
            return ID == ((Node) obj).ID;
        return false;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public int compareTo(@NotNull Node o) {
        return Integer.compare(this.ID,o.ID);
    }
}

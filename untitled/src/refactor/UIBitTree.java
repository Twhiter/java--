package refactor;

import graph.GenerifiedBitTree;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author T.Whiter
 * @Date 2020/2/25 11:33
 * @Version 1.0
 */
public class UIBitTree extends GenerifiedBitTree<String> {
    /***
     *
     * @param order 层次遍历顺序
     */
    public UIBitTree(List<String> order) {
        super(order);
    }

    @NotNull
    @Override
    public String asNullVal() {
        return "#";
    }
}

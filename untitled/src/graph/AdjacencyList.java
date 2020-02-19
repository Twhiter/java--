package graph;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author T.Whiter
 * @Date 2020/2/19 0:23
 * @Version 1.0
 */

/***
 * 邻接表类
 * @param <E>
 */
public class AdjacencyList<E> implements Iterable<GraphVertex<E>> {
    private ArrayList<GraphVertex<E>> list;

    public AdjacencyList() {
        list = new ArrayList<>();
    }

    public AdjacencyList(int initialSize) {
        list = new ArrayList<>(initialSize);
    }

    public GraphVertex<E> getVertex(int index) {
        return list.get(index);
    }

    public void addVertex(GraphVertex<E> v) {
        list.add(v);
    }

    public int size() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    @Override
    public Iterator<GraphVertex<E>> iterator() {
        return list.iterator();
    }
}

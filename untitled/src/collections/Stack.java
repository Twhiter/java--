package collections;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author T.Whiter
 * @Date 2020/2/17 11:08
 * @Version 1.0
 */
public class Stack<E> implements Collection<E> {
    private ArrayList<E> items;

    public Stack() {
        items = new ArrayList<>();
    }

    public Stack(int initialCapacity) {
        items = new ArrayList<>(initialCapacity);
    }

    /***
     *
     * @return the element on the stack top
     */
    @Override
    public E get() {
        return items.get(items.size() - 1);
    }

    /***
     *
     * add the element to the top of the stack
     * @param e the element to add
     */
    @Override
    public void add(E e) {
        items.add(e);
    }

    /***
     * get the top element and remove it
     * @return the element on the top
     */
    @Override
    public E pop() {
        E item = get();
        items.remove(items.size() - 1);
        return item;
    }

    /***
     *
     * @return if stack is empty,return true,else return false
     */
    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /***
     *
     * @return the array which contains all the element in order
     */
    @Override
    public E[] toArray() {
        return (E[]) items.toArray();
    }

    /***
     *
     * @return 返回迭代器
     */
    @Override
    public Iterator<E> iterator() {
        return items.iterator();
    }
}

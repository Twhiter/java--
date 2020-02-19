package collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * @author T.Whiter
 * @Date 2020/2/17 12:02
 * @Version 1.0
 */
public class Queue<E> extends AbstractQueue<E> {
    private LinkedList<E> items;


    public Queue() {
        super();
        items = new LinkedList<>();
    }

    @Override
    public E rear() {
        return items.getLast();
    }


    @Override
    public E get()  {
        return items.getFirst();
    }

    @Override
    public void add(E e)   {
            items.add(e);
    }

    @Override
    public E pop() throws NoSuchElementException{
        return items.remove(0);
    }

    @Override
    public boolean isEmpty() {
       return items.isEmpty();
    }

    @Override
    public E[] toArray() {
        return ((E[]) items.toArray());
    }

    @Override
    public Iterator<E> iterator() {
        return items.iterator();
    }


}





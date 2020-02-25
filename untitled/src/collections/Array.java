package collections;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author T.Whiter
 * @Date 2020/2/19 13:29
 * @Version 1.0
 */

/***
 * 定长数组
 * @param <E>
 */
public class Array<E> implements Iterable<E>{
    private ArrayList<E> list;
    private final int length;

    public Array(int initialCapacity) {
        list = new ArrayList<>(initialCapacity);
        length = initialCapacity;
        for (int i = 0; i < initialCapacity; i++)
            list.add(null);
    }

    public Array(int initialCapacity,E initialValue) {
       this(initialCapacity);
        for (int i = 0; i < initialCapacity; i++)
            list.set(i,initialValue);
    }

    public E get(int index){
        return list.get(index);
    }

    public void set(int index,E value) {
        list.set(index, value);
    }

    public void remove(@NotNull E o) {
        list.add(null);
        list.remove(o);
    }

    public void remove(int index) {
        list.remove(index);
        list.add(null);
    }

    public int length() {
        return length;
    }

    public void fill(E value) {
        for (int i = 0; i < length; i++)
            list.set(i,value);
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }
}

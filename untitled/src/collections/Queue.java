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

    /***
     *
     * @return 返回队列的尾部
     */
    @Override
    public E rear() {
        return items.getLast();
    }


    /***
     *
     * @return 返回队列头部的第一个元素
     */
    @Override
    public E get()  {
        return items.getFirst();
    }

    /***
     * 将e加入到队列的尾部
     * @param e the element to add
     */
    @Override
    public void add(E e)   {
            items.add(e);
    }

    /***
     *将队列头部的第一个元素出列(从队列中删除）
     * @return 将队列头部的第一个元素
     * @throws NoSuchElementException 队列中没有元素
     */
    @Override
    public E pop() throws NoSuchElementException{
        return items.remove(0);
    }

    /***
     *
     * @return 队列是否为空
     */
    @Override
    public boolean isEmpty() {
       return items.isEmpty();
    }

    /***
     *
     * @return 包含队列中的元素按以队列的头部到尾部的顺序的一个数组
     */
    @Override
    public E[] toArray() {
        return ((E[]) items.toArray());
    }

    /***
     *
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return items.iterator();
    }


}





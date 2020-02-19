package collections;

import java.util.NoSuchElementException;

/**
 * @author T.Whiter
 * @Date 2020/2/17 11:52
 * @Version 1.0
 */
public abstract class AbstractQueue<E> implements Collection<E> {
    public AbstractQueue() {

    }

    public E front() {
        return this.get();
    }

    abstract public E rear();
}

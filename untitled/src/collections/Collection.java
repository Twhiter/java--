package collections;

/**
 * @author T.Whiter
 * @Date 2020/2/17 10:50
 * @Version 1.0
 */
public interface Collection<E> extends Iterable<E> {
    /***
     *
     * @return return the "last" element
     */
    public E get();


    /***
     *
     * @param e the element to add
     */
    public void add(E e)  ;



    /***
     *
     * return the last element and remove it from the collections
     * @return return the element as get()
     */
    public E pop();

    /***
     *
     * @return if the collection is empty ,return true,
     * else return false
     */
    public boolean isEmpty();



    public E[] toArray();
}

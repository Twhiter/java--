package collections;

/**
 * @author T.Whiter
 * @Date 2020/2/20 8:18
 * @Version 1.0
 */

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;

/***
 * 定长二维数组
 */
public class TwoDimensionalArray<E> implements Iterable<E> {
    private final int line,column;
    private ArrayList<E> list;


    /***
     *
     * @param line line 二维数组的的长
     * @param column column 二维数组的宽
     */
    public TwoDimensionalArray(int line, int column) {
        this.line = line;
        this.column = column;
        this.list = new ArrayList<>(line * column);
    }

    /***
     *
     * @param line line 二维数组的的长
     * @param column column 二维数组的宽
     * @param initialValue 初始的值
     */
    public TwoDimensionalArray(int line, int column,E initialValue) {
        this(line, column);
        for (int i = 0; i < line * column; i++)
            list.add(initialValue);
    }

    /***
     *
     * @return 二维数组的的长
     */
    public int getLine() {
        return line;
    }

    /***
     *
     * @return 二维数组的宽
     */
    public int getColumn() {
        return column;
    }

    /***
     *
     * @param line 二维数组的横坐标
     * @param column 二维数组的纵坐标
     * @return element[line][column]
     */
    public E get(int line,int column) {
        return list.get(line * this.column + column);
    }

    /***
     *
     * @param line 二维数组的横坐标
     * @param column 二维数组的纵坐标
     * @param value 将element[line][column]设置为 value
     */
    public void set(int line,int column,E value) {
        list.set(line * this.column + column,value);
    }

    /***
     *
     * @return 返回二维数组的迭代器
     */
    @NotNull
    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }
}

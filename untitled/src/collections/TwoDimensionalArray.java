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


    public TwoDimensionalArray(int line, int column) {
        this.line = line;
        this.column = column;
        this.list = new ArrayList<>(line * column);
    }

    public TwoDimensionalArray(int line, int column,E initialValue) {
        this(line, column);
        for (int i = 0; i < line * column; i++)
            list.add(initialValue);
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public E get(int line,int column) {
        return list.get(line * this.column + column);
    }

    public void set(int line,int column,E value) {
        list.set(line * this.column + column,value);
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }
}

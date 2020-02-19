package graph;

import java.util.ArrayList;

/**
 * @author T.Whiter
 * @Date 2020/2/19 0:51
 * @Version 1.0
 */

/***
 * 矩阵类
 * @param <E>
 */
public class Matrix<E> {
    private ArrayList<ArrayList<E>>elements;
    private int column;
    private int line;

    public Matrix(int line,int column) {
        this.column = column;
        this.line = line;
        elements = new ArrayList<>(line);
        for (int i = 0; i < line; i++) {
            elements.add(new ArrayList<>());
            for (int j = 0; j < column; j++)
                elements.get(i).add(null);
        }
    }

    public Matrix(int line,int column,E initialValue) {
        this(line, column);
        for (int i = 0; i < line; i++)
            for (int j = 0; j < column; j++)
                setElement(i,j,initialValue);
    }

    public Matrix(Matrix<E> matrix){
        this.elements = (ArrayList<ArrayList<E>>) matrix.elements.clone();
        this.column = matrix.column;
        this.line = matrix.line;
    }

    public E get(int line,int column) {
        return this.elements.get(line).get(column);
    }

    public void setElement(int line,int column,E element) {
        this.elements.get(line).set(column,element);
    }

    public int getColumn() {
        return column;
    }

    public int getLine() {
        return line;
    }


    /***
     * 获得某一行的所有元素
     * @return
     */
    public ArrayList<E> getLineElements(int line) {
        return elements.get(line);
    }

    /***
     * 获得某一列的所有元素
     * @param column
     * @return
     */
    public ArrayList<E> getColumnElements(int column) {
        ArrayList<E> list = new ArrayList<>();
        for (int i = 0; i < line; i++) {
            list.add(get(i,column));
        }
        return list;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Matrix<>(this);
    }
}

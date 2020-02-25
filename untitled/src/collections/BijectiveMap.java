package collections;

/**
 * @author T.Whiter
 * @Date 2020/2/21 17:35
 * @Version 1.0
 */

import com.sun.javafx.css.StyleCache;

import java.util.Hashtable;

/***
 * 双射map
 */
public class BijectiveMap<k,v> {
    private Hashtable<k,v> fromTable;
    private Hashtable<v,k> toTable;


    public BijectiveMap() {
        fromTable = new Hashtable<>();
        toTable = new Hashtable<>();
    }


    public v getValueByKey(k e) {
        return fromTable.get(e);
    }

    public k getKeyByValue(v e) {
        return toTable.get(e);
    }

    public void put(k key,v val) {
        fromTable.put(key, val);
        toTable.put(val,key);
    }

    public boolean containsKey(k key) {
        return fromTable.containsKey(key);
    }

    public boolean containsValue(v value) {
        return toTable.containsKey(value);
    }
}

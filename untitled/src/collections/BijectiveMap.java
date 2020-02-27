package collections;

/**
 * @author T.Whiter
 * @Date 2020/2/21 17:35
 * @Version 1.0
 */

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


    /***
     * 通过 key e来获得值 v
     * @param e key
     * @return e所对应的值
     */
    public v getValueByKey(k e) {
        return fromTable.get(e);
    }

    /***
     * 通过val e 获取键 k
     * @param e val
     * @return e的键
     */
    public k getKeyByValue(v e) {
        return toTable.get(e);
    }

    /***
     * 形成双射键值对 key->val 和 val->key
     * @param key
     * @param val
     */
    public void put(k key,v val) {
        fromTable.put(key, val);
        toTable.put(val,key);
    }

    /***
     * 判断键值对中 是否含有键值为 key的映射
     * @param key 键值
     * @return true 如果含有键值为 key的映射 .
     * 否则,false
     */
    public boolean containsKey(k key) {
        return fromTable.containsKey(key);
    }

    /***
     * 判断键值对中 是否含有值为 value的映射
     * @param value 值
     * @return true 如果含有值为 value的映射
     *  否则,false
     */
    public boolean containsValue(v value) {
        return toTable.containsKey(value);
    }
}

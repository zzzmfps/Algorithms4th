package common;

public class TreeNodeKV<K, V> {

    public K key;
    public V value;
    public TreeNodeKV<K, V> lchild, rchild;

    public int nodeCount = 1;

    public TreeNodeKV(K k, V v) {
        this.key = k;
        this.value = v;
        this.lchild = this.rchild = null;
    }

    @Override
    public String toString() {
        return "(" + key + ", " + value + ", " + nodeCount + ")";
    }

}

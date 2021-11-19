package common;

public class TreeNodeRB<K, V> {

    public K key;
    public V value;
    public TreeNodeRB<K, V> lchild, rchild;

    public int nodeCount = 1;
    public boolean red = true;

    public TreeNodeRB(K k, V v) {
        this.key = k;
        this.value = v;
        this.lchild = this.rchild = null;
    }

    @Override
    public String toString() {
        String color = this.red ? "R" : "B";
        if (null == this.value) {
            return String.format("[%s, %s]", this.key, color);
        }
        return String.format("[%s, %s, %s]", this.key, this.value, color);
    }

}

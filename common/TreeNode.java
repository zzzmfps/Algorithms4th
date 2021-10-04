package common;

public class TreeNode<V> {

    public V value;
    public TreeNode<V> lchild, rchild;

    public TreeNode(final V v) {
        this.value = v;
        this.lchild = this.rchild = null;
    }

}

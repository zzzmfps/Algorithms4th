package chapter3.section3;

import chapter1.section3.Queue;
import common.TreeNodeRB;
import convention.OrderedSymbolTableConv;

public class RedBlackTree<K extends Comparable<K>, V> implements OrderedSymbolTableConv<K, V> {

    private TreeNodeRB<K, V> root;
    private TreeNodeRB<K, V> cache;

    public RedBlackTree() {
        this.root = null;
        this.cache = null;
    }

    @Override
    public boolean isEmpty() {
        return null == this.root;
    }

    @Override
    public int size() {
        return this.isEmpty() ? 0 : this.root.nodeCount;
    }

    @Override
    public void clear() {
        this.root = null;
        this.cache = null;
    }

    @Override
    public void put(K key, V value) {
        if (null != this.cache && 0 == key.compareTo(this.cache.key)) {
            this.cache.value = value;
            return;
        }
        this.root = this.put(this.root, key, value);
        this.root.red = false;
    }

    private TreeNodeRB<K, V> put(TreeNodeRB<K, V> node, K key, V val) {
        if (null == node) {
            return this.cache = new TreeNodeRB<>(key, val);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.lchild = this.put(node.lchild, key, val);
        } else if (cmp > 0) {
            node.rchild = this.put(node.rchild, key, val);
        } else {
            node.value = val;
            this.cache = node;
        }

        return this.balance(node);
    }

    private TreeNodeRB<K, V> balance(TreeNodeRB<K, V> node) {
        if (!this.isRed(node.lchild) && this.isRed(node.rchild)) {
            node = this.rotateLeft(node);
        }
        if (this.isRed(node.lchild) && this.isRed(node.lchild.lchild)) {
            node = this.rotateRight(node);
        }
        if (this.isRed(node.lchild) && this.isRed(node.rchild)) {
            this.flipColor(node);
        }
        node.nodeCount = 1 + this.getCount(node.lchild) + this.getCount(node.rchild);
        return node;
    }

    private boolean isRed(TreeNodeRB<K, V> node) {
        return null == node ? false : node.red;
    }

    private void flipColor(TreeNodeRB<K, V> node) {
        node.red = true;
        node.lchild.red = node.rchild.red = false;
    }

    private TreeNodeRB<K, V> rotateLeft(TreeNodeRB<K, V> node) {
        TreeNodeRB<K, V> newNode = node.rchild;
        node.rchild = newNode.lchild;
        newNode.lchild = node;
        newNode.red = node.red;
        node.red = true;
        newNode.nodeCount = node.nodeCount;
        node.nodeCount = 1 + this.getCount(node.lchild) + this.getCount(node.rchild);
        return newNode;
    }

    private TreeNodeRB<K, V> rotateRight(TreeNodeRB<K, V> node) {
        TreeNodeRB<K, V> newNode = node.lchild;
        node.lchild = newNode.rchild;
        newNode.rchild = node;
        newNode.red = node.red;
        node.red = true;
        newNode.nodeCount = node.nodeCount;
        node.nodeCount = 1 + this.getCount(node.lchild) + this.getCount(node.rchild);
        return newNode;
    }

    private int getCount(TreeNodeRB<K, V> node) {
        return null == node ? 0 : node.nodeCount;
    }

    @Override
    public V get(K key) {
        if (null != this.cache && 0 == key.compareTo(this.cache.key)) {
            return this.cache.value;
        }
        return this.get(this.root, key);
    }

    private V get(TreeNodeRB<K, V> node, K key) {
        if (null == node) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (0 == cmp) {
            this.cache = node;
            return node.value;
        }
        return this.get((cmp < 0) ? node.lchild : node.rchild, key);
    }

    @Override
    public void delete(K key) {
        if (!this.isRed(this.root.lchild) && !this.isRed(this.root.rchild)) {
            this.root.red = true;
        }
        this.root = this.delete(this.root, key);
        if (!this.isEmpty()) {
            this.root.red = false;
        }
    }

    private TreeNodeRB<K, V> delete(TreeNodeRB<K, V> node, K key) {
        if (key.compareTo(node.key) < 0) {
            if (!this.isRed(node.lchild) && !this.isRed(node.lchild.lchild)) {
                node = this.moveRedLeft(node);
            }
            node.lchild = this.delete(node.lchild, key);
        } else {
            if (this.isRed(node.lchild)) {
                node = this.rotateRight(node);
            }
            if (0 == key.compareTo(node.key) && null == node.rchild) {
                return null;
            }
            if (!this.isRed(node.rchild) && !this.isRed(node.rchild.lchild)) {
                node = this.moveRedRight(node);
            }
            if (0 == key.compareTo(node.key)) {
                TreeNodeRB<K, V> tmp = node.rchild;
                while (null != tmp.lchild) {
                    tmp = tmp.lchild;
                }
                node.key = tmp.key;
                node.value = tmp.value;
                node.rchild = this.deleteMin(node.rchild);
            } else {
                node.rchild = this.delete(node.rchild, key);
            }
        }
        return this.balance(node);
    }

    @Override
    public boolean contains(K key) {
        return null != this.get(key);
    }

    @Override
    public Iterable<K> keys() {
        return this.keys(null, null);
    }

    @Override
    public K min() {
        if (this.isEmpty()) {
            return null;
        }
        TreeNodeRB<K, V> node = this.root;
        while (null != node.lchild) {
            node = node.lchild;
        }
        return node.key;
    }

    @Override
    public K max() {
        if (this.isEmpty()) {
            return null;
        }
        TreeNodeRB<K, V> node = this.root;
        while (null != node.rchild) {
            node = node.rchild;
        }
        return node.key;
    }

    @Override
    public K floor(K key) {
        TreeNodeRB<K, V> node = this.root;
        while (null != node) {
            int cmp = key.compareTo(node.key);
            if (0 == cmp || cmp > 0 && null == node.rchild) {
                return node.key;
            }
            if (cmp < 0) {
                node = node.lchild;
            } else {
                node = node.rchild;
            }
        }
        return null;
    }

    @Override
    public K ceiling(K key) {
        TreeNodeRB<K, V> node = this.root;
        while (null != node) {
            int cmp = key.compareTo(node.key);
            if (0 == cmp || cmp < 0 && null == node.lchild) {
                return node.key;
            }
            if (cmp < 0) {
                node = node.lchild;
            } else {
                node = node.rchild;
            }
        }
        return null;
    }

    @Override
    public int rank(K key) { // returns [1 ~ size]
        TreeNodeRB<K, V> node = this.root;
        int ret = 0;
        while (null != node) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.lchild;
            } else {
                ret += 1 + (null == node.lchild ? 0 : node.lchild.nodeCount);
                if (0 == cmp) {
                    break;
                }
                node = node.rchild;
            }
        }
        return ret;
    }

    @Override
    public K select(int k) { // requires [1 ~ size]
        if (k < 1 || k > this.size()) {
            return null;
        }
        TreeNodeRB<K, V> node = this.root;
        while (true) {
            int r = 1 + this.getCount(node.lchild);
            if (r == k) {
                break;
            }
            if (r < k) {
                k -= r;
                node = node.rchild;
            } else {
                node = node.lchild;
            }
        }
        return node.key;
    }

    @Override
    public void deleteMin() {
        if (this.isRed(this.root.lchild) && this.isRed(this.root.rchild)) {
            this.root.red = true;
        }
        this.root = this.deleteMin(this.root);
        if (!this.isEmpty()) {
            this.root.red = false;
        }
    }

    private TreeNodeRB<K, V> deleteMin(TreeNodeRB<K, V> node) {
        if (null == node.lchild) {
            return null;
        }
        if (!this.isRed(node.lchild) && !this.isRed(node.lchild.lchild)) {
            node = this.moveRedLeft(node);
        }
        node.lchild = deleteMin(node.lchild);
        if (this.isRed(node.rchild)) {
            node = rotateLeft(node);
        }
        return this.balance(node);
    }

    private TreeNodeRB<K, V> moveRedLeft(TreeNodeRB<K, V> node) {
        this.flipColor2(node);
        if (this.isRed(node.rchild.lchild)) {
            node.rchild = this.rotateRight(node.rchild);
            node = this.rotateLeft(node);
        }
        return node;
    }

    private void flipColor2(TreeNodeRB<K, V> node) {
        node.red = false;
        node.lchild.red = node.rchild.red = true;
    }

    @Override
    public void deleteMax() {
        if (!this.isRed(this.root.lchild) && !this.isRed(this.root.rchild)) {
            this.root.red = true;
        }
        this.root = this.deleteMax(this.root);
        if (!this.isEmpty()) {
            this.root.red = false;
        }
    }

    private TreeNodeRB<K, V> deleteMax(TreeNodeRB<K, V> node) {
        if (this.isRed(node.lchild)) {
            node = this.rotateRight(node);
        }
        if (null == node.rchild) {
            return null;
        }
        if (!this.isRed(node.rchild) && !this.isRed(node.rchild.lchild)) {
            node = this.moveRedRight(node);
        }
        node.rchild = deleteMax(node.rchild);
        return this.balance(node);
    }

    private TreeNodeRB<K, V> moveRedRight(TreeNodeRB<K, V> node) {
        this.flipColor2(node);
        if (!this.isRed(node.rchild.lchild)) {
            node = this.rotateRight(node);
        }
        return node;
    }

    @Override
    public int size(K lo, K hi) {
        K l = this.ceiling(lo);
        K r = this.floor(hi);
        return this.rank(r) - this.rank(l);
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        Queue<K> q = new Queue<>();
        this.makeKeys(q, this.root, lo, hi);
        return q;
    }

    private void makeKeys(Queue<K> q, TreeNodeRB<K, V> node, K _min, K _max) {
        if (null == node) {
            return;
        }
        int cmp1 = (null == _min) ? 0 : node.key.compareTo(_min);
        int cmp2 = (null == _max) ? 0 : node.key.compareTo(_max);
        if (cmp1 < 0) {
            this.makeKeys(q, node.lchild, _min, _max);
        }
        if (cmp1 >= 0 && cmp2 <= 0) {
            q.offer(node.key);
        }
        if (cmp2 > 0) {
            this.makeKeys(q, node.rchild, _min, _max);
        }
    }

    public void printLevel() {
        this.printLevel(this.root);
    }

    private void printLevel(TreeNodeRB<K, V> node) {
        Queue<TreeNodeRB<K, V>> q = new Queue<>();
        q.offer(node);
        while (!q.isEmpty()) {
            boolean hasNode = false;
            StringBuilder sb = new StringBuilder();
            for (int i = q.size(); i > 0; --i) {
                TreeNodeRB<K, V> cur = q.poll();
                sb.append((i == q.size() ? "" : " ") + cur);
                if (null != cur) {
                    hasNode = true;
                    q.offer(cur.lchild);
                    q.offer(cur.rchild);
                }
            }
            if (hasNode) {
                System.out.println(sb.toString());
            }
        }
    }

}

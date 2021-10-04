package chapter3.section2;

import java.util.Arrays;

import chapter1.section3.Queue;
import chapter1.section3.Stack;
import common.TreeNodeKV;
import convention.OrderedSymbolTableConv;
import edu.princeton.cs.algs4.StdRandom;

public class BinarySearchTree<K extends Comparable<K>, V> implements OrderedSymbolTableConv<K, V> {

    private TreeNodeKV<K, V> root;

    private TreeNodeKV<K, V> recentNode = null;

    public BinarySearchTree() {
        this.root = null;
    }

    public BinarySearchTree(K[] keys) {
        K[] input = Arrays.copyOf(keys, keys.length);
        Arrays.sort(input);
        int height = 1 + Double.valueOf(Math.log(keys.length) / Math.log(2)).intValue();
        this.root = this.buildBalancedBST(input, 0, keys.length, height);
    }

    private TreeNodeKV<K, V> buildBalancedBST(K[] keys, int begin, int end, int height) {
        if (begin >= end) {
            return null;
        }
        int mid = begin + (end - begin) / 2;
        TreeNodeKV<K, V> node = new TreeNodeKV<K, V>(keys[mid], null);
        node.nodeCount = end - begin;
        node.lchild = this.buildBalancedBST(keys, begin, mid, height - 1);
        node.rchild = this.buildBalancedBST(keys, mid + 1, end, height - 1);
        return node;
    }

    @Override
    public boolean isEmpty() {
        return null == this.root;
    }

    @Override
    public int size() {
        return isEmpty() ? 0 : this.root.nodeCount;
    }

    @Override
    public void clear() {
        this.root = null;
    }

    public void put(K key) {
        this.put(key, null);
    }

    @Override
    public void put(K key, V value) {
        if (null != this.recentNode && 0 == key.compareTo(this.recentNode.key)) { // cache
            this.recentNode.value = value;
        }
        TreeNodeKV<K, V> node = new TreeNodeKV<K, V>(key, value);
        this.recentNode = node; // set cache
        if (this.isEmpty()) { // first node
            this.root = node;
            return;
        }

        TreeNodeKV<K, V> prev = this.root;
        Queue<TreeNodeKV<K, V>> path = new Queue<>();

        while (true) {
            int cmp = key.compareTo(prev.key);
            if (0 == cmp) { // update value
                prev.value = value;
                this.recentNode = prev;
                return; // return and not increase nodeCount
            }

            path.offer(prev);
            if (cmp < 0) {
                if (null == prev.lchild) { // insert
                    prev.lchild = node;
                    break;
                }
                prev = prev.lchild;
            } else {
                if (null == prev.rchild) { // insert
                    prev.rchild = node;
                    break;
                }
                prev = prev.rchild;
            }
        }
        while (!path.isEmpty()) {
            path.poll().nodeCount += 1;
        }
    }

    @Override
    public V get(K key) {
        if (null != this.recentNode && 0 == key.compareTo(this.recentNode.key)) { // cache
            return this.recentNode.value;
        }
        TreeNodeKV<K, V> node = this.root;
        while (null != node) {
            int cmp = key.compareTo(node.key);
            if (0 == cmp) {
                break;
            }
            if (cmp < 0) {
                node = node.lchild;
            } else {
                node = node.rchild;
            }
        }
        this.recentNode = node; // set cache, will set to null if key is missing
        return null == node ? null : node.value;
    }

    @Override
    public void delete(K key) {
        if (this.isEmpty()) {
            return;
        }
        if (0 == key.compareTo(this.root.key)) { // delete root itself
            if (null == this.root.rchild) {
                this.root = this.root.lchild;
                return;
            }
            if (null == this.root.rchild.lchild) {
                this.root.rchild.lchild = this.root.lchild;
                this.root.rchild.nodeCount = this.root.nodeCount - 1;
                this.root = this.root.rchild;
                return;
            }

            TreeNodeKV<K, V> node = this.root.rchild;
            node.nodeCount -= 1;
            while (null != node.lchild.lchild) {
                node = node.lchild;
                node.nodeCount -= 1;
            }
            TreeNodeKV<K, V> newRoot = node.lchild;
            node.lchild = newRoot.rchild;
            newRoot.lchild = this.root.lchild;
            newRoot.rchild = this.root.rchild;
            newRoot.nodeCount = this.root.nodeCount - 1;
            this.root = newRoot;
            return;
        }

        TreeNodeKV<K, V> node1 = this.root;
        Queue<TreeNodeKV<K, V>> path = new Queue<>();

        int cmp = key.compareTo(node1.key);
        while (true) {
            path.offer(node1);
            TreeNodeKV<K, V> node2 = (cmp < 0) ? node1.lchild : node1.rchild;
            if (null == node2) { // key not exist
                return;
            }
            if (0 == (cmp = key.compareTo(node2.key))) { // delete node2
                TreeNodeKV<K, V> node3 = node2.rchild;
                if (null == node3) {
                    this.replaceNode(node1, node2, node2.lchild);
                } else if (null == node3.lchild) {
                    node3.lchild = node2.lchild;
                    node3.nodeCount = node2.nodeCount - 1;
                    this.replaceNode(node1, node2, node3);
                } else {
                    TreeNodeKV<K, V> node4 = node3;
                    path.offer(node4);
                    while (null != node4.lchild.lchild) {
                        node4 = node4.lchild;
                        path.offer(node4);
                    }
                    TreeNodeKV<K, V> newRoot = node4.lchild;
                    node4.lchild = newRoot.rchild;
                    newRoot.lchild = node2.lchild;
                    newRoot.rchild = node2.rchild;
                    newRoot.nodeCount = node2.nodeCount - 1;
                    this.replaceNode(node1, node2, newRoot);
                }
                break;
            }
            node1 = node2;
        }
        while (!path.isEmpty()) {
            path.poll().nodeCount -= 1;
        }
    }

    private int getCount(TreeNodeKV<K, V> node) {
        return null == node ? 0 : node.nodeCount;
    }

    private void replaceNode(TreeNodeKV<K, V> parent, TreeNodeKV<K, V> oldNode, TreeNodeKV<K, V> newNode) {
        if (parent.lchild == oldNode) {
            parent.lchild = newNode;
        } else {
            parent.rchild = newNode;
        }
    }

    @Override
    public boolean contains(K key) {
        return null != this.get(key);
    }

    @Override
    public Iterable<K> keys() {
        Queue<K> q = new Queue<>();
        BinarySearchTree.makeKeys(q, this.root);
        return q;
    }

    private static <K, V> void makeKeys(Queue<K> q, TreeNodeKV<K, V> node) {
        if (null == node) {
            return;
        }
        BinarySearchTree.makeKeys(q, node.lchild);
        q.offer(node.key);
        BinarySearchTree.makeKeys(q, node.rchild);
    }

    public Iterable<K> keysNoRecursion() {
        Queue<K> q = new Queue<>();

        Stack<TreeNodeKV<K, V>> st = new Stack<>();
        st.push(this.root);
        while (!st.isEmpty()) {
            TreeNodeKV<K, V> cur = st.peek();
            if (null != cur.lchild) {
                st.push(cur.lchild);
                continue;
            }
            while (!st.isEmpty()) {
                cur = st.pop();
                q.offer(cur.key);
                if (null != cur.rchild) {
                    st.push(cur.rchild);
                    break;
                }
            }
        }

        return q;
    }

    @Override
    public K min() {
        TreeNodeKV<K, V> node = this.root;
        while (null != node && null != node.lchild) {
            node = node.lchild;
        }
        return null == node ? null : node.key;
    }

    @Override
    public K max() {
        TreeNodeKV<K, V> node = this.root;
        while (null != node && null != node.rchild) {
            node = node.rchild;
        }
        return null == node ? null : node.key;
    }

    @Override
    public K floor(K key) {
        TreeNodeKV<K, V> node = this.root;
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
        TreeNodeKV<K, V> node = this.root;
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
        TreeNodeKV<K, V> node = this.root;
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
        TreeNodeKV<K, V> node = this.root;
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
        if (this.isEmpty()) {
            return;
        }
        if (null == this.root.lchild) {
            this.root = this.root.rchild;
            return;
        }
        TreeNodeKV<K, V> node = this.root;
        while (null != node.lchild.lchild) {
            node = node.lchild;
        }
        node.lchild = node.lchild.rchild;
    }

    @Override
    public void deleteMax() {
        if (this.isEmpty()) {
            return;
        }
        if (null == this.root.rchild) {
            this.root = this.root.lchild;
            return;
        }
        TreeNodeKV<K, V> node = this.root;
        while (null != node.rchild.rchild) {
            node = node.rchild;
        }
        node.rchild = node.rchild.lchild;
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
        BinarySearchTree.makeKeys(q, this.root, lo, hi);
        return q;
    }

    private static <K extends Comparable<K>, V> void makeKeys(Queue<K> q, TreeNodeKV<K, V> node, K lo, K hi) {
        if (null == node) {
            return;
        }
        int cmp1 = lo.compareTo(node.key), cmp2 = hi.compareTo(node.key);
        if (cmp1 < 0) {
            BinarySearchTree.makeKeys(q, node.lchild);
        }
        if (cmp1 <= 0 && 0 <= cmp2) {
            q.offer(node.key);
        }
        if (0 < cmp2) {
            BinarySearchTree.makeKeys(q, node.rchild);
        }
    }

    public int height() {
        return this.height(this.root);
    }

    public int height(TreeNodeKV<K, V> node) {
        if (null == node) {
            return 0;
        }
        return 1 + Math.max(this.height(node.lchild), this.height(node.rchild));
    }

    public K randomKey() {
        int rank = 1 + StdRandom.uniform(this.size());
        return this.select(rank);
    }

    public TreeNodeKV<K, V> getRoot() {
        return this.root;
    }

    public static <K extends Comparable<K>, V> boolean isBinaryTree(TreeNodeKV<K, V> node) {
        if (null == node) {
            return true;
        }
        if (null == node.lchild && null == node.rchild) {
            return 1 == node.nodeCount;
        }
        int lCount = (null == node.lchild) ? 0 : node.lchild.nodeCount;
        int rCount = (null == node.rchild) ? 0 : node.rchild.nodeCount;
        return 1 + lCount + rCount == node.nodeCount;
    }

    public static <K extends Comparable<K>, V> boolean isOrdered(TreeNodeKV<K, V> node, K min, K max) {
        Queue<K> q = new Queue<>();
        BinarySearchTree.makeKeys(q, node);

        K last = q.poll();
        if (0 != min.compareTo(last)) {
            return false;
        }
        while (!q.isEmpty()) {
            K cur = q.poll();
            if (last.compareTo(cur) > 0) {
                return false;
            }
            last = cur;
        }
        if (0 != max.compareTo(last)) {
            return false;
        }
        return true;
    }

    public static <K extends Comparable<K>, V> boolean hasNoDuplicates(TreeNodeKV<K, V> node) {
        Queue<K> q = new Queue<>();
        BinarySearchTree.makeKeys(q, node);

        K last = q.poll();
        while (!q.isEmpty()) {
            K cur = q.poll();
            if (0 == cur.compareTo(last)) {
                return false;
            }
            last = cur;
        }
        return true;
    }

    public static <K, V> void printLevel(TreeNodeKV<K, V> node) {
        Queue<TreeNodeKV<K, V>> q = new Queue<>();
        q.offer(node);
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; --i) {
                TreeNodeKV<K, V> cur = q.poll();
                System.out.print((i == q.size() ? "" : " ") + cur);
                if (null != cur.lchild) {
                    q.offer(cur.lchild);
                }
                if (null != cur.rchild) {
                    q.offer(cur.rchild);
                }
            }
            System.out.println();
        }
    }

}

package chapter3.section1;

import chapter1.section3.Queue;
import convention.OrderedSymbolTableConv;

public class BinarySearchST<K extends Comparable<K>, V> implements OrderedSymbolTableConv<K, V> {

    private K[] keys;
    private V[] vals;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public BinarySearchST(int capacity) {
        this.keys = (K[]) new Comparable[capacity];
        this.vals = (V[]) new Object[capacity];
        this.capacity = capacity;
    }

    @Override
    public boolean isEmpty() {
        return 0 == this.size;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        this.keys = (K[]) new Comparable[capacity];
        this.vals = (V[]) new Object[capacity];
        this.size = 0;
    }

    @Override
    public void put(K key, V value) {
        int i = (this.isEmpty() || key.compareTo(this.max()) >= 0) ? this.size : rank(key);
        if (i < this.size && 0 == this.keys[i].compareTo(key)) {
            this.vals[i] = value;
            return;
        }
        if (this.size == this.keys.length) {
            this.resize(this.keys.length * 2);
        }
        for (int j = this.size; j > i; --j) {
            this.keys[j] = this.keys[j - 1];
            this.vals[j] = this.vals[j - 1];
        }
        this.keys[i] = key;
        this.vals[i] = value;
        ++this.size;
    }

    @Override
    public V get(K key) {
        if (this.isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i == this.size || this.keys[i].compareTo(key) != 0) {
            return null;
        }
        return vals[i];
    }

    @Override
    public void delete(K key) {
        int i = this.rank(key);
        if (i == this.size || 0 != key.compareTo(this.keys[i])) {
            return;
        }
        for (int j = i + 1; j < this.size; ++j) {
            this.keys[j - 1] = this.keys[j];
            this.vals[j - 1] = this.vals[j];
        }
        this.keys[--this.size] = null;
        this.vals[this.size] = null;
        if (this.size < this.keys.length / 4) {
            this.resize(this.keys.length / 2);
        }
    }

    @Override
    public boolean contains(K key) {
        return 0 == key.compareTo(this.keys[this.rank(key)]);
    }

    @Override
    public Iterable<K> keys() {
        Queue<K> q = new Queue<>();
        for (int i = 0; i < this.size; ++i) {
            q.offer(this.keys[i]);
        }
        return q;
    }

    @Override
    public K min() {
        return this.isEmpty() ? null : this.keys[0];
    }

    @Override
    public K max() {
        return this.isEmpty() ? null : this.keys[this.size - 1];
    }

    @Override
    public K floor(K key) {
        int i = this.rank(key);
        if (i < this.size && 0 == key.compareTo(this.keys[i])) {
            return this.keys[i];
        }
        return i > 0 ? this.keys[i - 1] : null;
    }

    @Override
    public K ceiling(K key) {
        int i = this.rank(key);
        return i < this.size ? this.keys[i] : null;
    }

    @Override
    public int rank(K key) {
        int l = 0, r = this.size - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int cmp = key.compareTo(this.keys[mid]);
            if (cmp < 0) {
                r = mid - 1;
            } else if (cmp > 0) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return l;
    }

    @Override
    public K select(int k) {
        return k < this.size ? this.keys[k] : null;
    }

    @Override
    public void deleteMin() {
        this.delete(this.keys[0]);
    }

    @Override
    public void deleteMax() {
        this.delete(this.keys[this.size - 1]);
    }

    @Override
    public int size(K lo, K hi) {
        return this.rank(hi) - this.rank(lo);
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        Queue<K> q = new Queue<>();
        for (int i = this.rank(lo), j = this.rank(hi); i < j; ++i) {
            q.offer(this.keys[i]);
        }
        if (this.contains(hi)) {
            q.offer(hi);
        }
        return q;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        if (this.keys.length == newSize) {
            return;
        }
        K[] newKeys = (K[]) new Comparable[capacity];
        V[] newVals = (V[]) new Object[capacity];
        for (int i = 0; i < this.size; ++i) {
            newKeys[i] = this.keys[i];
            newVals[i] = this.vals[i];
        }
        this.keys = newKeys;
        this.vals = newVals;
    }

}

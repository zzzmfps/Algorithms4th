package chapter3.section1;

import chapter1.section3.Queue;
import convention.SymbolTableConv;

public class ArrayST<K, V> implements SymbolTableConv<K, V> {

    protected K[] keys;
    protected V[] vals;
    private int capacity;
    protected int size;

    @SuppressWarnings("unchecked")
    public ArrayST(int capacity) {
        this.capacity = capacity;
        this.keys = (K[]) new Object[capacity];
        this.vals = (V[]) new Object[capacity];
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
        this.keys = (K[]) new Object[this.capacity];
        this.vals = (V[]) new Object[this.capacity];
        this.size = 0;
    }

    @Override
    public void put(K key, V value) {
        int i = 0;
        for (; i < this.size; ++i) {
            if (this.keys[i].equals(key)) {
                break;
            }
        }
        if (i == this.keys.length) {
            this.resize(2 * this.keys.length);
        }
        if (i == this.size) {
            this.keys[this.size++] = key;
        }
        this.vals[i] = value;
    }

    @Override
    public V get(K key) {
        V value = null;
        for (int i = 0; i < this.size; ++i) {
            if (this.keys[i].equals(key)) {
                value = this.vals[i];
                break;
            }
        }
        return value;
    }

    @Override
    public void delete(K key) {
        int i = 0;
        for (; i < this.size; ++i) {
            if (this.keys[i].equals(key)) {
                break;
            }
        }

        if (i == this.size) {
            return;
        }
        while (++i < this.size) {
            this.keys[i - 1] = this.keys[i];
            this.vals[i - 1] = this.vals[i];
        }
        this.keys[--this.size] = null;
        this.vals[this.size] = null;

        if (this.size < this.keys.length / 4) {
            this.resize(this.keys.length / 2);
        }
    }

    @Override
    public boolean contains(K key) {
        for (int i = 0; i < this.size; ++i) {
            if (this.keys[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterable<K> keys() {
        Queue<K> q = new Queue<>();
        for (int i = 0; i < this.size; ++i) {
            q.offer(this.keys[i]);
        }
        return q;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        if (this.keys.length == newSize) {
            return;
        }
        K[] newKeys = (K[]) new Object[newSize];
        V[] newVals = (V[]) new Object[newSize];
        for (int i = 0; i < this.size; ++i) {
            newKeys[i] = this.keys[i];
            newVals[i] = this.vals[i];
        }
        this.keys = newKeys;
        this.vals = newVals;
    }

}

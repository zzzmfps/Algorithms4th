package chapter3.section1;

import chapter1.section3.Queue;
import chapter2.section2.Merge;
import common.Item;
import convention.OrderedSymbolTableConv;

public class BinarySearchSTWithOneArray<K extends Comparable<K>, V> implements OrderedSymbolTableConv<K, V> {

    private Item<K, V>[] elems;
    private int capacity;
    private int size;

    public BinarySearchSTWithOneArray(int capacity) {
        this.elems = Item.makeArray(capacity);
        this.capacity = capacity;
    }

    public BinarySearchSTWithOneArray(Item<K, V>[] array) {
        this.elems = Item.makeArray(array.length);
        this.capacity = array.length;
        this.size = array.length;
        for (int i = 0; i < array.length; ++i) {
            this.elems[i] = array[i];
        }
        Merge.sort(this.elems);
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
        this.elems = (Item<K, V>[]) new Object[this.capacity];
        this.size = 0;
    }

    @Override
    public void put(K key, V value) {
        Item<K, V> item = new Item<>(key, value);
        int index = BinarySearch.lowerBound(this.elems, 0, this.size, item);

        if (index < this.size && this.elems[index].x.equals(key)) {
            this.elems[index].y = value;
            return;
        }

        if (this.size == this.elems.length) {
            this.resize(2 * this.elems.length);
        }
        for (int i = this.size; i > index; --i) {
            this.elems[i] = this.elems[i - 1];
        }
        this.elems[index] = item;
        ++this.size;
    }

    @Override
    public V get(K key) {
        Item<K, V> item = new Item<>(key, null);
        int index = BinarySearch.indexOf(this.elems, 0, this.size, item);
        return -1 == index ? null : this.elems[index].y;
    }

    @Override
    public void delete(K key) {
        if (this.isEmpty()) {
            return;
        }
        Item<K, V> item = new Item<>(key, null);
        int index = BinarySearch.indexOf(this.elems, 0, this.size, item);
        if (-1 != index) {
            --this.size;
            for (int i = index; i < this.size; ++i) {
                this.elems[i] = this.elems[i + 1];
            }
        }
        if (this.size < this.elems.length / 4) {
            this.resize(this.elems.length / 2);
        }
    }

    @Override
    public boolean contains(K key) {
        Item<K, V> item = new Item<>(key, null);
        return BinarySearch.contains(this.elems, 0, this.size, item);
    }

    @Override
    public Iterable<K> keys() {
        Queue<K> q = new Queue<>();
        for (int i = 0; i < this.size; ++i) {
            q.offer(this.elems[i].x);
        }
        return q;
    }

    @Override
    public K min() {
        return this.isEmpty() ? null : this.elems[0].x;
    }

    @Override
    public K max() {
        return this.isEmpty() ? null : this.elems[this.size - 1].x;
    }

    @Override
    public K floor(K key) {
        Item<K, V> item = new Item<>(key, null);
        int index = BinarySearch.upperBound(this.elems, 0, this.size, item);
        return 0 == index ? null : this.elems[index - 1].x;
    }

    @Override
    public K ceiling(K key) {
        Item<K, V> item = new Item<>(key, null);
        int index = BinarySearch.lowerBound(this.elems, 0, this.size, item);
        return this.size == index ? null : this.elems[index].x;
    }

    @Override
    public int rank(K key) {
        Item<K, V> item = new Item<>(key, null);
        return BinarySearch.lowerBound(this.elems, 0, this.size, item);
    }

    @Override
    public K select(int k) {
        return k < this.size ? this.elems[k].x : null;
    }

    @Override
    public void deleteMin() {
        this.delete(this.min());
    }

    @Override
    public void deleteMax() {
        this.delete(this.max());
    }

    @Override
    public int size(K lo, K hi) {
        if (lo.compareTo(hi) > 0) {
            return 0;
        }
        int l = BinarySearch.lowerBound(this.elems, 0, this.size, new Item<>(lo, null));
        if (l == this.size) {
            return 0;
        }
        int r = BinarySearch.upperBound(this.elems, 0, this.size, new Item<>(hi, null));
        return r - l;
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        Queue<K> q = new Queue<>();
        if (lo.compareTo(hi) > 0) {
            return q;
        }
        int l = BinarySearch.lowerBound(this.elems, 0, this.size, new Item<>(lo, null));
        if (l < this.size) {
            int r = BinarySearch.upperBound(this.elems, 0, this.size, new Item<>(hi, null));
            for (int i = l; i < r; ++i) {
                q.offer(this.elems[i].x);
            }
        }
        return q;
    }

    private void resize(int newSize) {
        if (this.elems.length == newSize) {
            return;
        }
        Item<K, V>[] newElems = Item.makeArray(newSize);
        for (int i = 0; i < this.size; ++i) {
            newElems[i] = this.elems[i];
        }
        this.elems = newElems;
    }

}

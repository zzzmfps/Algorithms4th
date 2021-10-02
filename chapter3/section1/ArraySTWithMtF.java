package chapter3.section1;

public class ArraySTWithMtF<K, V> extends ArrayST<K, V> {

    public ArraySTWithMtF(int capacity) {
        super(capacity);
    }

    @Override
    public V get(K key) {
        V value = null;
        for (int i = 0; i < this.size; ++i) {
            if (this.keys[i].equals(key)) {
                value = this.vals[i];
                for (int j = i; j > 0; --j) { // move to front
                    this.keys[j] = this.keys[j - 1];
                    this.vals[j] = this.vals[j - 1];
                }
                this.keys[0] = key;
                this.vals[0] = value;
                break;
            }
        }
        return value;
    }

}

package convention;

public interface OrderedSymbolTableConv<K extends Comparable<K>, V> extends SymbolTableConv<K, V> {

    public K min();

    public K max();

    public K floor(K key);

    public K ceiling(K key);

    public int rank(K key);

    public K select(int k);

    public void deleteMin();

    public void deleteMax();

    public int size(K lo, K hi);

    public Iterable<K> keys(K lo, K hi);

}

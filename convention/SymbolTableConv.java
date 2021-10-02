package convention;

public interface SymbolTableConv<K, V> {

    public boolean isEmpty();

    public int size();

    public void clear();

    public void put(K key, V value);

    public V get(K key);

    public void delete(K key);

    public boolean contains(K key);

    public Iterable<K> keys();

}

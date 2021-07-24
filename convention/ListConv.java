package convention;

public interface ListConv<T> {

    public boolean isEmpty();

    public int size();

    public void add(T value);

    public T get(int index);

    public T remove(int index);

}

package convention;

public interface ListConv<T> extends ContainerConv<T> {

    public void add(T value);

    public T get(int index);

    public T remove(int index);

}

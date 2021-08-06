package convention;

public interface IndexPriorityQueueConv<T extends Comparable<T>> extends ContainerConv<T> {

    public void insert(int k, T value);

    public void change(int k, T value);

    public boolean contains(int k);

    public T peek();

    public int peekIndex();

    public int delete();

    public void delete(int k);

}

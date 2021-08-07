package convention;

public interface PriorityQueueConv<T> extends ContainerConv<T> {

    public void insert(T value);

    public T peek();

    public T delete();

}

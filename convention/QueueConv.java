package convention;

public interface QueueConv<T> extends ContainerConv<T> {

    public void offer(T value);

    public T peek();

    public T poll();

}

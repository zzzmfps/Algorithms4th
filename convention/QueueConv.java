package convention;

public interface QueueConv<T> {

    public boolean isEmpty();

    public int size();

    public void offer(T value);

    public T peek();

    public T poll();

}

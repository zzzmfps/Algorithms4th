package convention;

public interface DequeConv<T> extends ContainerConv<T> {

    public void offerFirst(T value);

    public void offerLast(T value);

    public T peekFirst();

    public T peekLast();

    public T pollFirst();

    public T pollLast();

}

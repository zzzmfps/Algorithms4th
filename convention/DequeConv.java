package convention;

public interface DequeConv<T> {

    public boolean isEmpty();

    public int size();

    public void offerFirst(T value);

    public void offerLast(T value);

    public T peekFirst();

    public T peekLast();

    public T pollFirst();

    public T pollLast();

}

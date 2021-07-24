package convention;

public interface StackConv<T> {

    public boolean isEmpty();

    public int size();

    public void push(T value);

    public T peek();

    public T pop();

}

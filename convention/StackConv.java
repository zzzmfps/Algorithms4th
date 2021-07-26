package convention;

public interface StackConv<T> extends ContainerConv<T> {

    public void push(T value);

    public T peek();

    public T pop();

}

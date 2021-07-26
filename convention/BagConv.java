package convention;

public interface BagConv<T> extends ContainerConv<T>, Iterable<T> {

    public void add(T value);

}

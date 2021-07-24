package convention;

public interface BagConv<T> extends Iterable<T> {

    public boolean isEmpty();

    public int size();

    public void add(T value);

}

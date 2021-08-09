package chapter2.section5;

import java.lang.reflect.Array;

public class StableBox<T extends Comparable<T>> implements Comparable<StableBox<T>> {

    private final Integer index;
    private final T value;

    public StableBox(final int index, final T value) {
        this.index = index;
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    @Override
    public int compareTo(final StableBox<T> o) {
        final int cmp = this.value.compareTo(o.value);
        return 0 != cmp ? cmp : this.index.compareTo(o.index);
    }

    @Override
    public String toString() {
        return "StableBox [index=" + index + ", value=" + value + "]";
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> StableBox<T>[] box(final T[] array) {
        final StableBox<T>[] target = (StableBox<T>[]) Array.newInstance(StableBox.class, array.length);
        for (int i = 0; i < target.length; ++i) {
            target[i] = new StableBox<T>(i, array[i]);
        }
        return target;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> T[] unbox(final StableBox<T>[] array, final Class<T> type) {
        final T[] target = (T[]) Array.newInstance(type, array.length);
        for (int i = 0; i < target.length; ++i) {
            target[i] = array[i].value;
        }
        return target;
    }

}

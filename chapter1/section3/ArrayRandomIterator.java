package chapter1.section3;

import edu.princeton.cs.algs4.StdRandom;

public class ArrayRandomIterator<T> extends ArrayIterator<T> {

    public ArrayRandomIterator(final T[] array, final int size) {
        super(array, 0, size); // begins at 0 only
        StdRandom.shuffle(this.array, 0, size);
    }

}

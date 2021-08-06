package chapter2.section4;

import convention.base.AbstractSort;

public class Heap extends AbstractSort {

    public static <T extends Comparable<T>> void sort(T[] array) {
        Heap.sort(array, 0, array.length, Mode.Less);
    }

    public static <T extends Comparable<T>> void sort(T[] array, Mode mode) {
        Heap.sort(array, 0, array.length, mode);
    }

    public static <T extends Comparable<T>> void sort(T[] array, int begin, int end) {
        Heap.sort(array, begin, end, Mode.Less);
    }

    public static <T extends Comparable<T>> void sort(T[] array, int begin, int end, Mode mode) {
        int N = end - begin;
        Heap.make(array, begin, end);
        while (N-- > 1) {
            AbstractSort.exch(array, begin, begin + N);
            Heap.sink(array, begin, begin + N, begin, mode);
        }
    }

    public static <T extends Comparable<T>> void make(T[] array) {
        Heap.make(array, 0, array.length, Mode.Less);
    }

    public static <T extends Comparable<T>> void make(T[] array, Mode mode) {
        Heap.make(array, 0, array.length, mode);
    }

    public static <T extends Comparable<T>> void make(T[] array, int begin, int end) {
        Heap.make(array, begin, end, Mode.Less);
    }

    public static <T extends Comparable<T>> void make(T[] array, int begin, int end, Mode mode) {
        int N = end - begin;
        for (int i = begin + N / 2 - 1; i >= begin; --i) {
            Heap.sink(array, i, end, begin, mode);
        }
    }

    public static <T extends Comparable<T>> boolean check(T[] array) {
        return Heap.check(array, 0, array.length, Mode.Less);
    }

    public static <T extends Comparable<T>> boolean check(T[] array, Mode mode) {
        return Heap.check(array, 0, array.length, mode);
    }

    public static <T extends Comparable<T>> boolean check(T[] array, int begin, int end) {
        return Heap.check(array, begin, end, Mode.Less);
    }

    public static <T extends Comparable<T>> boolean check(T[] array, int begin, int end, Mode mode) {
        if (begin >= end) {
            return true;
        }
        int l = 2 * begin + 1, r = l + 1;
        if (l < end && Heap.compare(array[begin], array[l], mode)) {
            return false;
        }
        if (r < end && Heap.compare(array[begin], array[r], mode)) {
            return false;
        }
        return Heap.check(array, l, end) && Heap.check(array, r, end);
    }

    private static <T extends Comparable<T>> void sink(T[] array, int i, int end, int offset, Mode mode) {
        int j;
        while ((j = 2 * (i + 1) - 1 - offset) < end) {
            if (j + 1 < end && Heap.compare(array[j], array[j + 1], mode)) {
                ++j;
            }
            if (!Heap.compare(array[i], array[j], mode)) {
                break;
            }
            AbstractSort.exch(array, i, j);
            i = j;
        }
    }

    private static <T extends Comparable<T>> boolean compare(final T x, final T y, Mode mode) {
        if (mode == Mode.Less) {
            return AbstractSort.less(x, y);
        }
        return AbstractSort.greater(x, y);
    }

}

package chapter2.section1;

import convention.SortAbstract;

public class Insertion extends SortAbstract {

    public static void sort(int[] array) {
        Insertion.sort(array, 0, array.length);
    }

    public static void sort(int[] array, int begin, int end) {
        for (int i = 1, j; i < end; ++i) {
            int value = array[i];
            for (j = i; j > 0 && value < array[j - 1]; j -= 1) {
                array[j] = array[j - 1]; // no exch
            }
            array[j] = value;
        }
    }

    public static <T extends Comparable<T>> void sort(T[] array) {
        Insertion.sort(array, 0, array.length);
    }

    public static <T extends Comparable<T>> void sort(T[] array, int begin, int end) {
        SortAbstract.exch(array, begin, Insertion.minIndex(array, begin, end)); // sentinel
        for (int i = begin + 1, j; i < end; ++i) {
            T value = array[i];
            for (j = i; SortAbstract.less(value, array[j - 1]); j -= 1) {
                array[j] = array[j - 1]; // no exch
            }
            array[j] = value;
            if (SortAbstract.detail) {
                SortAbstract.show(array);
            }
        }
        SortAbstract.detail = false;
    }

    private static <T extends Comparable<T>> int minIndex(T[] array, int begin, int end) {
        int index = begin;
        for (int i = begin + 1; i < end; ++i) {
            if (SortAbstract.less(array[i], array[index])) {
                index = i;
            }
        }
        return index;
    }

}

package chapter2.section1;

import convention.SortAbstract;

public class Insertion extends SortAbstract {

    public static void sort(int[] array) {
        int N = array.length;
        for (int i = 1, j; i < N; ++i) {
            int value = array[i];
            for (j = i; j > 0 && value < array[j - 1]; j -= 1) {
                array[j] = array[j - 1]; // no exch
            }
            array[j] = value;
        }
    }

    public static <T extends Comparable<T>> void sort(T[] array) {
        int N = array.length;
        SortAbstract.exch(array, 0, Insertion.minIndex(array)); // sentinel
        for (int i = 1, j; i < N; ++i) {
            T value = array[i];
            for (j = i; j > 0 && SortAbstract.less(value, array[j - 1]); j -= 1) {
                array[j] = array[j - 1]; // no exch
            }
            array[j] = value;
            if (SortAbstract.detail) {
                SortAbstract.show(array);
            }
        }
        SortAbstract.detail = false;
    }

    private static <T extends Comparable<T>> int minIndex(T[] array) {
        int index = 0;
        for (int i = 1; i < array.length; ++i) {
            if (SortAbstract.less(array[i], array[index])) {
                index = i;
            }
        }
        return index;
    }

}

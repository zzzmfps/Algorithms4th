package chapter2.section1;

import convention.base.AbstractSort;

public class Selection extends AbstractSort {

    public static <T extends Comparable<T>> void sort(T[] array) {
        int N = array.length;
        for (int i = 0; i < N; ++i) {
            int min = i;
            for (int j = i + 1; j < N; ++j) {
                if (AbstractSort.less(array[j], array[min])) {
                    min = j;
                }
            }
            AbstractSort.exch(array, i, min);
            if (AbstractSort.detail) {
                AbstractSort.show(array);
            }
        }
        AbstractSort.detail = false;
    }

}

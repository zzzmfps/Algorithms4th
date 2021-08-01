package chapter2.section1;

import convention.SortAbstract;

public class Selection extends SortAbstract {

    public static <T extends Comparable<T>> void sort(T[] array) {
        int N = array.length;
        for (int i = 0; i < N; ++i) {
            int min = i;
            for (int j = i + 1; j < N; ++j) {
                if (SortAbstract.less(array[j], array[min])) {
                    min = j;
                }
            }
            SortAbstract.exch(array, i, min);
            if (SortAbstract.detail) {
                SortAbstract.show(array);
            }
        }
        SortAbstract.detail = false;
    }

}

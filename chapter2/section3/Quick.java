package chapter2.section3;

import chapter1.section3.Stack;
import chapter2.section1.Insertion;
import convention.base.AbstractSort;
import edu.princeton.cs.algs4.StdRandom;

public class Quick extends AbstractSort {

    private static final int LENGTH_SWITCH_TO_INSERTION = 8;
    private static final int LENGTH_SAMPLING = 63; // k=6, 2^6 - 1

    public static <T extends Comparable<T>> void sort(T[] array) {
        Quick.sort(array, 0, array.length);
    }

    public static <T extends Comparable<T>> void sort(T[] array, int begin, int end) {
        if (end - begin <= Quick.LENGTH_SWITCH_TO_INSERTION) {
            if (begin < end) {
                Insertion.sort(array, begin, end);
            }
            return;
        }
        int p = Quick.partition(array, begin, end);
        if (AbstractSort.detail) {
            AbstractSort.show(array);
        }
        Quick.sort(array, begin, p);
        Quick.sort(array, p + 1, end);
        if (0 == begin && array.length == end) {
            AbstractSort.detail = false;
        }
    }

    public static <T extends Comparable<T>> void sortNoRecursion(T[] array) {
        Quick.sortNoRecursion(array, 0, array.length);
    }

    public static <T extends Comparable<T>> void sortNoRecursion(T[] array, int begin, int end) {
        Stack<int[]> st = new Stack<>();
        st.push(new int[] { begin, end });
        while (!st.isEmpty()) {
            int[] range = st.pop();
            if (range[1] - range[0] <= Quick.LENGTH_SWITCH_TO_INSERTION) {
                if (range[0] < range[1]) {
                    Insertion.sort(array, range[0], range[1]);
                }
                continue;
            }
            int p = Quick.partition(array, range[0], range[1]);
            if (AbstractSort.detail) {
                AbstractSort.show(array);
            }
            st.push(new int[] { p + 1, range[1] });
            st.push(new int[] { range[0], p });
        }
        AbstractSort.detail = false;
    }

    private static <T extends Comparable<T>> int partition(T[] array, int begin, int end) { // length >= 3
        int pivot = Quick.median(array, begin, begin + (end - begin) / 2, end - 1);
        AbstractSort.exch(array, begin, pivot);
        int i = begin, j = end;
        while (true) {
            while (i < j && AbstractSort.less(array[++i], array[begin])) {
            }
            while (i < j && AbstractSort.less(array[begin], array[--j])) {
            }
            if (i == j) {
                break;
            }
            AbstractSort.exch(array, i, j);
        }
        pivot = AbstractSort.less(array[i], array[begin]) ? i : i - 1;
        AbstractSort.exch(array, begin, pivot);
        return pivot;
    }

    public static <T extends Comparable<T>> void sort3Way(T[] array) {
        Quick.sort3Way(array, 0, array.length);
    }

    public static <T extends Comparable<T>> void sort3Way(T[] array, int begin, int end) {
        if (end - begin <= Quick.LENGTH_SWITCH_TO_INSERTION) {
            if (begin < end) {
                Insertion.sort(array, begin, end);
            }
            return;
        }
        int pivot = Quick.median(array, begin, begin + (end - begin) / 2, end - 1);
        AbstractSort.exch(array, begin, pivot);
        T value = array[begin];
        int lt = begin, i = begin + 1, gt = end - 1;
        while (i <= gt) {
            int cmp = array[i].compareTo(value);
            if (cmp < 0) {
                AbstractSort.exch(array, lt++, i++); // swap not equal elems
            } else if (cmp > 0) {
                AbstractSort.exch(array, i, gt--);
            } else {
                ++i;
            }
        }
        if (AbstractSort.detail) {
            AbstractSort.show(array);
        }
        Quick.sort3Way(array, begin, lt);
        Quick.sort3Way(array, gt + 1, end);
        if (0 == begin && array.length == end) {
            AbstractSort.detail = false;
        }
    }

    public static <T extends Comparable<T>> void sort3Way2(T[] array) {
        Quick.sort3Way2(array, 0, array.length);
    }

    public static <T extends Comparable<T>> void sort3Way2(T[] array, int begin, int end) { // 1990s
        if (end - begin <= Quick.LENGTH_SWITCH_TO_INSERTION) {
            if (begin < end) {
                Insertion.sort(array, begin, end);
            }
            return;
        }
        int pivot = Quick.tukeysNinther(array, begin, end - 1);
        AbstractSort.exch(array, begin, pivot);
        T value = array[begin];
        int p = begin + 1, i = begin + 1, j = end - 1, q = end - 1;
        while (i <= j) {
            int cmp1 = array[i].compareTo(value);
            if (0 == cmp1) {
                AbstractSort.exch(array, p++, i++); // swap equal elems
            } else if (cmp1 < 0) {
                ++i;
            }
            int cmp2 = array[j].compareTo(value);
            if (0 == cmp2) {
                AbstractSort.exch(array, j--, q--);
            } else if (cmp2 > 0) {
                --j;
            }
            if (cmp1 > 0 && cmp2 < 0) {
                AbstractSort.exch(array, i++, j--);
            }
        }
        for (int k = begin; k < Math.min(p, j - p + 1 + begin); ++k, --j) {
            AbstractSort.exch(array, k, j - begin);
        }
        for (int k = end - 1; k > Math.min(q, q - i + end); --k, ++i) {
            AbstractSort.exch(array, k, i + begin);
        }
        if (AbstractSort.detail) {
            AbstractSort.show(array);
        }
        Quick.sort3Way(array, begin, j + 1);
        Quick.sort3Way(array, i, end);
        if (0 == begin && array.length == end) {
            AbstractSort.detail = false;
        }
    }

    private static <T extends Comparable<T>> int median(T[] array, int x, int y, int z) {
        int[] count = { 0, 0, 0 }; // 0 for max, 2 for min
        ++count[AbstractSort.less(array[x], array[y]) ? 0 : 1];
        ++count[AbstractSort.less(array[x], array[z]) ? 0 : 2];
        ++count[AbstractSort.less(array[y], array[z]) ? 1 : 2];
        return 1 == count[0] ? x : (1 == count[1] ? y : z);
    }

    private static <T extends Comparable<T>> int tukeysNinther(T[] array, int x, int z) {
        // z - x >= Quick.LENGTH_SWITCH_TO_INSERTION
        int p1 = Quick.median(array, x, x + 1, x + 2);
        int y = x + (z - x) / 2;
        int p3 = Quick.median(array, y - 1, y, y + 1);
        int p2 = Quick.median(array, z - 2, z - 1, z);
        return Quick.median(array, p1, p2, p3);
    }

    public static <T extends Comparable<T>> void sortWithSampling(T[] array) {
        Quick.sortWithSampling(array, 0, array.length); // k=1
    }

    public static <T extends Comparable<T>> void sortWithSampling(T[] array, int begin, int end) {
        if (end - begin <= Quick.LENGTH_SWITCH_TO_INSERTION) {
            if (begin < end) {
                Insertion.sort(array, begin, end);
            }
            return;
        }
        int p;
        if (end - begin <= Quick.LENGTH_SAMPLING) {
            p = Quick.partition(array, begin, end);
        } else {
            p = Quick.sample(array, begin, end); // sample sort
        }
        if (AbstractSort.detail) {
            AbstractSort.show(array);
        }
        Quick.sort(array, begin, p);
        Quick.sort(array, p + 1, end);
        if (0 == begin && array.length == end) {
            AbstractSort.detail = false;
        }
    }

    private static <T extends Comparable<T>> int sample(T[] array, int begin, int end) {
        Quick.sort(array, begin, begin + Quick.LENGTH_SAMPLING); // left Quick.LENGTH_SAMPLING elems
        int pivot = begin + Quick.LENGTH_SAMPLING / 2;
        AbstractSort.exch(array, begin, pivot);
        int k = pivot + 1; // start of elems to be moved
        int count = Math.min(begin + Quick.LENGTH_SAMPLING - k, end - begin - Quick.LENGTH_SAMPLING);
        int h = end - count; // start of target position
        for (int i = 0; i < count; ++i) {
            AbstractSort.exch(array, k + i, h + i); // move right-half of samples to the right-side of array
        }
        int i = k - 1, j = h;
        while (true) {
            while (i < j && AbstractSort.less(array[++i], array[begin])) {
            }
            while (i < j && AbstractSort.less(array[begin], array[--j])) {
            }
            if (i == j) {
                break;
            }
            AbstractSort.exch(array, i, j);
        }
        pivot = AbstractSort.less(array[i], array[begin]) ? i : i - 1;
        AbstractSort.exch(array, begin, pivot);
        return pivot;
    }

    public static <T extends Comparable<T>> T select(T[] array, int k) {
        if (k < 0 || k >= array.length) {
            return null;
        }
        StdRandom.shuffle(array);
        return Quick.select(array, k, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> T select(T[] array, int k, int l, int r) { // as `select`
        if (l == r) {
            return array[l];
        }
        int i = Quick.partition(array, l, r + 1);
        if (i == k) {
            return array[i];
        }
        if (i < k) {
            return Quick.select(array, k, i + 1, r);
        }
        return Quick.select(array, k, l, i - 1);
    }

}

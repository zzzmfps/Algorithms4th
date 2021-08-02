package chapter2.section2;

import java.util.Arrays;

import convention.SortAbstract;

public class Merge extends SortAbstract {

    public static <T extends Comparable<T>> void sort(T[] array) {
        Merge.sort(array, 0, array.length - 1);
        SortAbstract.detail = false;
    }

    private static <T extends Comparable<T>> void sort(T[] array, int l, int r) {
        if (l < r) {
            int mid = l + (r - l) / 2;
            Merge.sort(array, l, mid);
            Merge.sort(array, mid + 1, r);
            if (SortAbstract.less(array[mid + 1], array[mid])) {
                Merge.merge(array, l, mid, r);
            }
        }
    }

    public static <T extends Comparable<T>> void sortBU(T[] array) {
        Merge.sortBU(array, 0, array.length - 1);
        SortAbstract.detail = false;
    }

    private static <T extends Comparable<T>> void sortBU(T[] array, int l, int r) {
        int N = array.length;
        for (int i = 1; i < N; i <<= 1) {
            for (int j = 0; j < N - i; j += 2 * i) {
                if (SortAbstract.less(array[j + i], array[j + i - 1])) {
                    Merge.merge(array, j, j + i - 1, Math.min(j + 2 * i, N) - 1);
                }
            }
        }
    }

    public static <T extends Comparable<T>> void sortNatural(T[] array) {
        Merge.sortNatural(array, 0, array.length - 1);
        SortAbstract.detail = false;
    }

    private static <T extends Comparable<T>> void sortNatural(T[] array, int l, int r) {
        while (true) {
            int i = l, j = findFirstReverse(array, l, r);
            if (j > r) {
                break;
            }
            for (int k; j <= r; i = k, j = findFirstReverse(array, k, r)) {
                k = Merge.findFirstReverse(array, j, r);
                Merge.merge(array, i, j - 1, k - 1);
            }
        }
    }

    private static <T extends Comparable<T>> int findFirstReverse(T[] array, int begin, int end) {
        while (++begin <= end) {
            if (SortAbstract.less(array[begin], array[begin - 1])) {
                break;
            }
        }
        return begin;
    }

    private static <T extends Comparable<T>> void merge(T[] array, int l, int mid, int r) {
        T[] aux = Arrays.copyOfRange(array, l, mid + 1);
        int i = 0, j = mid + 1;
        for (int k = l; k <= r; ++k) {
            if (i >= aux.length) {
                array[k] = array[j++];
            } else if (j > r) {
                array[k] = aux[i++];
            } else if (SortAbstract.less(aux[i], array[j])) {
                array[k] = aux[i++];
            } else {
                array[k] = array[j++];
            }
        }
        if (SortAbstract.detail) {
            SortAbstract.show(array);
        }
    }

    public static <T extends Comparable<T>> int[] sortIndex(T[] array) {
        return Merge.sortIndex(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> int[] sortIndex(T[] array, int l, int r) {
        int N = array.length;
        int[] index = new int[N];
        for (int i = 0; i < index.length; ++i) {
            index[i] = i;
        }
        for (int i = 1; i < N; i <<= 1) {
            for (int j = 0; j < N - i; j += 2 * i) {
                if (SortAbstract.less(array[index[j + i]], array[index[j + i - 1]])) {
                    Merge.merge(array, index, j, j + i - 1, Math.min(j + 2 * i, N) - 1);
                }
            }
        }
        return index;
    }

    private static <T extends Comparable<T>> void merge(T[] array, int[] index, int l, int mid, int r) {
        int[] aux = Arrays.copyOfRange(index, l, mid + 1);
        int i = 0, j = mid + 1;
        for (int k = l; k <= r; ++k) {
            if (i >= aux.length) {
                index[k] = index[j++];
            } else if (j > r) {
                index[k] = aux[i++];
            } else if (SortAbstract.less(array[aux[i]], array[index[j]])) {
                index[k] = aux[i++];
            } else {
                index[k] = index[j++];
            }
        }
    }

    public static <T extends Comparable<T>> int inversePair(T[] array) {
        return Merge.inversePair(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> int inversePair(T[] array, int l, int r) {
        int N = array.length, count = 0;
        for (int i = 1; i < N; i <<= 1) {
            for (int j = 0; j < N - i; j += 2 * i) {
                if (SortAbstract.less(array[j + i], array[j + i - 1])) {
                    count += Merge.mergeAndCount(array, j, j + i - 1, Math.min(j + 2 * i, N) - 1);
                }
            }
        }
        return count;
    }

    private static <T extends Comparable<T>> int mergeAndCount(T[] array, int l, int mid, int r) {
        T[] aux = Arrays.copyOfRange(array, l, mid + 1);
        int i = 0, j = mid + 1, count = 0;
        for (int k = l; k <= r; ++k) {
            if (i >= aux.length) {
                array[k] = array[j++];
            } else if (j > r) {
                array[k] = aux[i++];
            } else if (SortAbstract.less(array[j], aux[i])) {
                count += mid - (i + l) + 1; // less than unsorted elements in [i+l, mid]
                array[k] = array[j++];
            } else {
                array[k] = aux[i++];
            }
        }
        return count;
    }

}

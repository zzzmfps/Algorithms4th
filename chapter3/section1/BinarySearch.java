package chapter3.section1;

public class BinarySearch {

    public static <T extends Comparable<T>> boolean isSorted(T[] array) {
        return BinarySearch.isSorted(array, 0, array.length);
    }

    public static <T extends Comparable<T>> boolean isSorted(T[] array, int begin, int end) {
        for (int i = begin + 1; i < end; ++i) {
            if (array[i - 1].compareTo(array[i]) > 0) {
                return false;
            }
        }
        return true;
    }

    public static <T extends Comparable<T>> int indexOf(T[] array, T target) {
        return BinarySearch.indexOf(array, 0, array.length, target);
    }

    public static <T extends Comparable<T>> int indexOf(T[] array, int begin, int end, T target) {
        int l = begin, r = end - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int cmp = array[mid].compareTo(target);
            if (cmp < 0) {
                l = mid + 1;
            } else if (cmp > 0) {
                r = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static <T extends Comparable<T>> boolean contains(T[] array, T target) {
        return -1 != BinarySearch.indexOf(array, 0, array.length, target);
    }

    public static <T extends Comparable<T>> boolean contains(T[] array, int begin, int end, T target) {
        return -1 != BinarySearch.indexOf(array, begin, end, target);
    }

    public static <T extends Comparable<T>> int lowerBound(T[] array, T target) {
        return BinarySearch.lowerBound(array, 0, array.length, target);
    }

    public static <T extends Comparable<T>> int lowerBound(T[] array, int begin, int end, T target) {
        int l = begin, r = end - 1;
        if (l > r || array[r].compareTo(target) < 0) {
            return end;
        }
        while (l < r) {
            int mid = l + (r - l) / 2;
            int cmp = array[mid].compareTo(target);
            if (cmp < 0) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    public static <T extends Comparable<T>> int upperBound(T[] array, T target) {
        return BinarySearch.upperBound(array, 0, array.length, target);
    }

    public static <T extends Comparable<T>> int upperBound(T[] array, int begin, int end, T target) {
        int l = begin, r = end - 1;
        if (l > r || array[r].compareTo(target) <= 0) {
            return end;
        }
        while (l < r) {
            int mid = l + (r - l) / 2;
            int cmp = array[mid].compareTo(target);
            if (cmp <= 0) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

}

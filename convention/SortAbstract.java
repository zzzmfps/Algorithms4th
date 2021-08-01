package convention;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public abstract class SortAbstract {

    protected static boolean detail;

    private static boolean animation;
    private static int delay = 1000;

    public static void requireDetails(boolean useAnimation) {
        SortAbstract.detail = true;
        SortAbstract.animation = useAnimation;
    }

    public static <T extends Comparable<T>> boolean isSorted(T[] array) {
        for (int i = 1; i < array.length; ++i) {
            if (SortAbstract.less(array[i], array[i - 1])) {
                return false;
            }
        }
        return true;
    }

    protected static <T extends Comparable<T>> void show(T[] array) {
        if (SortAbstract.animation && Number.class.isInstance(array[0])) {
            StdDraw.setXscale(-1.0, Integer.valueOf(array.length).doubleValue());
            StdDraw.setYscale(-1.0, 1.1 * ((Number) SortAbstract.max(array)).doubleValue());
            StdDraw.setPenRadius(0.02);
            StdDraw.clear();
            for (int i = 0; i < array.length; ++i) {
                StdDraw.line(i, 0.0, i, ((Number) array[i]).doubleValue());
            }
            StdDraw.setPenRadius();
            StdDraw.setYscale();
            StdDraw.setXscale();
            try {
                Thread.sleep(SortAbstract.delay);
            } catch (InterruptedException e) {
            }
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            StdOut.print(array[i] + ((i < array.length - 1) ? " " : "\n"));
        }
    }

    private static <T extends Comparable<T>> T max(T[] array) {
        T max = array[0];
        for (int i = 1; i < array.length; ++i) {
            if (SortAbstract.less(max, array[i])) {
                max = array[i];
            }
        }
        return max;
    }

    protected static <T extends Comparable<T>> boolean less(T x, T y) {
        return x.compareTo(y) < 0;
    }

    protected static <T extends Comparable<T>> void exch(T[] array, int i, int j) {
        T value = array[i];
        array[i] = array[j];
        array[j] = value;
    }

}

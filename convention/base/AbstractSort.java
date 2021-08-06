package convention.base;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public abstract class AbstractSort {

    public static enum Mode {
        Less, Greater
    }

    protected static boolean detail;

    private static boolean animation;
    private static int delay = 1000;

    public static void requireDetails(boolean useAnimation) {
        AbstractSort.detail = true;
        AbstractSort.animation = useAnimation;
    }

    public static <T extends Comparable<T>> boolean isSorted(T[] array) {
        for (int i = 1; i < array.length; ++i) {
            if (AbstractSort.less(array[i], array[i - 1])) {
                return false;
            }
        }
        return true;
    }

    protected static <T extends Comparable<T>> void show(T[] array) {
        if (AbstractSort.animation && Number.class.isInstance(array[0])) {
            StdDraw.setXscale(-1.0, Integer.valueOf(array.length).doubleValue());
            StdDraw.setYscale(-1.0, 1.1 * ((Number) AbstractSort.max(array)).doubleValue());
            StdDraw.setPenRadius(0.02);
            StdDraw.clear();
            for (int i = 0; i < array.length; ++i) {
                StdDraw.line(i, 0.0, i, ((Number) array[i]).doubleValue());
            }
            StdDraw.setPenRadius();
            StdDraw.setYscale();
            StdDraw.setXscale();
            try {
                Thread.sleep(AbstractSort.delay);
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
            if (AbstractSort.less(max, array[i])) {
                max = array[i];
            }
        }
        return max;
    }

    protected static <T extends Comparable<T>> boolean less(T x, T y) {
        return x.compareTo(y) < 0;
    }

    protected static <T extends Comparable<T>> boolean greater(T x, T y) {
        return x.compareTo(y) > 0;
    }

    protected static <T extends Comparable<T>> void exch(T[] array, int i, int j) {
        T value = array[i];
        array[i] = array[j];
        array[j] = value;
    }

}

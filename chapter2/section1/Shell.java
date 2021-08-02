package chapter2.section1;

import chapter1.section3.List;
import convention.SortAbstract;
import edu.princeton.cs.algs4.StdOut;

public class Shell extends SortAbstract {

    private static boolean normal;

    public static void useNormal() {
        Shell.normal = true;
    }

    public static <T extends Comparable<T>> void sort(T[] array) {
        int N = array.length;
        int[] hs = Shell.generator(N);
        for (int i = hs.length - 1; i > -1; --i) {
            int h = hs[i];
            if (SortAbstract.detail) {
                StdOut.printf("h = %d\n", h);
            }
            for (int j = h; j < N; ++j) {
                for (int k = j; k >= h && SortAbstract.less(array[k], array[k - h]); k -= h) {
                    SortAbstract.exch(array, k, k - h);
                }
                if (SortAbstract.detail) {
                    SortAbstract.show(array);
                }
            }
        }
        SortAbstract.detail = false;
    }

    private static int[] generator(int limit) {
        List<Integer> hs = new List<>();
        hs.add(1);
        if (Shell.normal) {
            while (hs.last().value < limit / 3) {
                hs.add(3 * hs.last().value + 1);
            }
            Shell.normal = false;
        } else {
            int i = 1, j = 2;
            while (true) {
                int h1 = Double.valueOf(9 * Math.pow(4, i) - 9 * Math.pow(2, i) + 1).intValue();
                int h2 = Double.valueOf(Math.pow(4, j) - 3 * Math.pow(2, j) + 1).intValue();
                if (h1 >= limit && h2 >= limit) {
                    break;
                }
                if (h1 < h2) {
                    hs.add(h1);
                    ++i;
                } else {
                    hs.add(h2);
                    ++j;
                }
            }

        }
        int[] hs2 = new int[hs.size()];
        for (int i = 0; i < hs2.length; ++i) {
            hs2[i] = hs.deleteHead();
        }
        return hs2;
    }

}

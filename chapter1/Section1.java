package chapter1;

import java.util.LinkedList;
import java.util.List;

import chapter1.section1.Matrix;
import chapter1.section1.Person;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class Section1 {

    public static void exercise3() {
        final int[] ints = new int[3];
        for (int i = 0; i < 3; ++i) {
            ints[i] = StdIn.readInt();
        }
        if (ints[0] == ints[1] && ints[1] == ints[2]) {
            StdOut.println("equal");
        } else {
            StdOut.println("not equal");
        }
    }

    public static void exercise5(final double x, final double y) {
        if (x > 0.0 && x < 1.0 && y > 0.0 && y < 1.0) {
            StdOut.println("true");
        } else {
            StdOut.println("false");
        }
    }

    public static String exercise9(final int N) {
        // Integer.toBinaryString(i);
        final StringBuilder sb = new StringBuilder();
        for (int n = N; n > 0; n >>= 1) {
            sb.append(0 == (n & 1) ? '0' : '1');
        }
        return sb.reverse().toString();
    }

    public static void exercise11(boolean[][] mat) {
        if (null == mat) {
            mat = new boolean[][] { { true, true, false }, { false, false, true }, { true, false, true } };
        }
        for (int i = -1; i < mat.length; ++i) {
            for (int j = -1; j < mat[0].length; ++j) {
                if (j >= 0) {
                    StdOut.print('\t');
                }
                if (-1 == i) {
                    StdOut.print(j);
                } else if (-1 == j) {
                    StdOut.print(i);
                } else {
                    StdOut.print(mat[i][j] ? '*' : ' ');
                }
            }
            StdOut.println();
        }

    }

    public static void exercise13(int[][] mat) {
        if (null == mat) {
            mat = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 0, 1, 2 } };
        }
        final int[][] newMat = new int[mat[0].length][mat.length];
        for (int i = 0; i < mat.length; ++i) {
            for (int j = 0; j < mat[0].length; ++j) {
                newMat[j][i] = mat[i][j];
            }
        }
        for (final int[] nm : newMat) {
            for (int i = 0; i < nm.length; ++i) {
                StdOut.print((i > 0 ? " " : "") + nm[i]);
            }
            StdOut.println();
        }
    }

    public static int exercise14(int N) {
        // N > 0
        int res = 0;
        for (; N > 1; N >>= 1) {
            ++res;
        }
        return res;
    }

    public static int[] exercise15(int[] a, int M) {
        if (null == a) {
            M = 10;
            a = new int[] { 0, 5, 2, 3, 6, 9, 7, 0, 1, 1, 2, 3, 6, 8, 2, 3, 4, 0, 1, 4 };
        }
        final int[] res = new int[M];
        for (final int v : a) {
            ++res[v];
        }
        return res;
    }

    public static double exercise20(final int N) {
        if (1 == N) {
            return 0.0;
        }
        return Math.log(N) + exercise20(N - 1);
    }

    public static void exercise21() {
        final List<Person> list = new LinkedList<>();
        while (!StdIn.isEmpty()) {
            list.add(new Person());
        }
        for (final Person person : list) {
            person.print();
        }
    }

    public static int exercise22(int[] arr, final int target) {
        if (null == arr) {
            arr = new int[] { 1, 3, 3, 5, 6, 8, 12, 19, 20, 20 };
        }
        return __exercise22(arr, target, 0, arr.length, 0);
    }

    private static int __exercise22(final int[] arr, final int target, int l, int r, final int depth) {
        for (int i = 0; i < depth; ++i) {
            StdOut.print(' ');
        }
        StdOut.printf("%d %d\n", l, r);
        if (l >= r) {
            return -1;
        }
        final int mid = l + (r - l) / 2;
        if (target == arr[mid]) {
            return mid;
        }
        if (target < arr[mid]) {
            r = mid;
        } else {
            l = mid + 1;
        }
        return __exercise22(arr, target, l, r, depth + 1);
    }

    public static int exercise23(final int[] arr, final int target, final char mark) {
        final int res = exercise22(arr, target);
        if (res < 0 && '+' == mark || res >= 0 && '-' == mark) {
            StdOut.println(target);
        }
        return res;
    }

    public static int exercise24(final int p, final int q, final boolean printCallStack) {
        if (printCallStack) {
            StdOut.printf("%d %d\n", p, q);
        }
        if (0 == q) {
            return p;
        }
        return exercise24(q, p % q, printCallStack);
    }

    public static int exercise28(final int[] arr) { // arr is sorted
        int last = Integer.MIN_VALUE;
        int p1 = 0;
        for (int p2 = 0; p2 < arr.length; ++p2) {
            if (arr[p2] != last) {
                last = arr[p1] = arr[p2];
                ++p1;
            }
        }
        return p1; // as new end of uniqued `arr`
    }

    public static int exercise29_1(int[] arr, final int key) { // as `rank`
        if (null == arr) {
            arr = new int[] { 1, 3, 3, 5, 6, 8, 12, 19, 20, 20 };
        }
        return 1 + __exercise29_1(arr, key);
    }

    public static int exercise29_2(int[] arr, final int key) { // as `count`
        if (null == arr) {
            arr = new int[] { 1, 3, 3, 5, 6, 8, 12, 19, 20, 20 };
        }
        final int lt = 1 + __exercise29_1(arr, key);
        final int le = __exercise29_2(arr, key);
        return le - lt;
    }

    private static int __exercise29_1(final int[] arr, final int key) { // find index of max({n < key})
        for (int l = 0, r = arr.length; l < r;) {
            final int mid = l + (r - l) / 2;
            if (arr[mid] < key && (mid + 1 == arr.length || arr[mid + 1] >= key)) {
                return mid;
            }
            if (arr[mid] < key) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return -1;
    }

    private static int __exercise29_2(final int[] arr, final int key) { // find index of min({n > key})
        for (int l = 0, r = arr.length; l < r;) {
            final int mid = l + (r - l) / 2;
            if (arr[mid] > key && (0 == mid || arr[mid - 1] <= key)) {
                return mid;
            }
            if (arr[mid] <= key) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return arr.length;
    }

    public static boolean[][] exercise30(final int i, final int j) {
        final boolean[][] res = new boolean[i][j];
        for (int m = 2; m < i; ++m) {
            for (int n = 2; n < j; ++n) {
                res[m][n] = (1 != exercise24(m, n, false));
            }
        }
        return res;
    }

    public static void exercise31(final int N, final double p) {
        final double x = 0.5, y = 0.5, r = 0.3;
        StdDraw.circle(x, y, r);

        final double radius = 2.0 * Math.PI / N;
        final List<double[]> points = new LinkedList<>();
        for (int i = 0; i < N; ++i) {
            final double cur = i * radius;
            final double cos = Math.cos(cur), sin = Math.sin(cur);
            points.add(new double[] { x + r * cos, y + r * sin });
        }

        for (final double[] ds : points) {
            StdDraw.filledCircle(ds[0], ds[1], 0.05);
        }
        StdDraw.setPenColor(StdDraw.GRAY);
        for (int i = 0; i < N; ++i) {
            for (int j = i + 1; j < N; ++j) {
                if (StdRandom.bernoulli(p)) {
                    StdDraw.line(points.get(i)[0], points.get(i)[1], points.get(j)[0], points.get(j)[1]);
                }
            }
        }
        StdDraw.setPenColor();
    }

    public static void exercise32(final int N, final double l, final double r) {
        final double range = (r - l) / N;
        final double[] counts = new double[N];
        while (!StdIn.isEmpty()) {
            final double val = StdIn.readDouble();
            if (val > l & val < r) {
                counts[Double.valueOf(val / range).intValue()] += 1;
            }
        }
        final double max = StdStats.max(counts);
        for (int i = 0; i < N; ++i) {
            counts[i] /= max;
        }
        StdStats.plotBars(counts);
    }

    public static void exercise33() {
        final double[] v1 = { 1.0, 2.0, 3.0 };
        final double[] v2 = { 4.0, 5.0, 6.0 };
        final double[][] m1 = { { 1.0, 2.0, 3.0 }, { 4.0, 5.0, 6.0 } };
        final double[][] m2 = { { 1.0, 2.0 }, { 3.0, 4.0 }, { 5.0, 6.0 } };
        StdOut.println(Matrix.dot(v1, v2));
        StdOut.println("***************");
        __exercise33(Matrix.mult(m1, m2));
        StdOut.println("***************");
        __exercise33(Matrix.mult(v1, m2));
        StdOut.println("***************");
        __exercise33(Matrix.mult(m1, v2));
        StdOut.println("***************");
        __exercise33(Matrix.transpose(m1));
    }

    private static void __exercise33(final double[] x) {
        for (int i = 0; i < x.length; ++i) {
            StdOut.printf("%.3f%s", x[i], x.length == i + 1 ? "\n" : " ");
        }
    }

    private static void __exercise33(final double[][] x) {
        for (int i = 0; i < x.length; ++i) {
            for (int j = 0; j < x[i].length; ++j) {
                StdOut.printf("%.3f%s", x[i][j], x[i].length == j + 1 ? "\n" : " ");
            }
        }
    }

    public static void exercise36(final int M, final int N) { // as `ShuffleTest`
        final int[][] counts = new int[M][M];
        final int[] arr = new int[M];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                arr[j] = j;
            }
            __exercise36(arr);
            for (int j = 0; j < M; ++j) {
                ++counts[arr[j]][j];
            }
        }
        for (final int[] row : counts) {
            for (int i = 0; i < M; ++i) {
                StdOut.print((i > 0 ? "\t" : "") + row[i]);
            }
            StdOut.println();
        }
    }

    private static void __exercise36(final int[] a) { // p19 sheet 1.1.10 shuffle for int
        final int N = a.length;
        for (int i = 0; i < N; ++i) {
            final int r = i + StdRandom.uniform(N - i);
            final int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void exercise37(final int M, final int N) { // exactly same with exercise36
        final int[][] counts = new int[M][M];
        final int[] arr = new int[M];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                arr[j] = j;
            }
            __exercise37(arr);
            for (int j = 0; j < M; ++j) {
                ++counts[arr[j]][j];
            }
        }
        for (final int[] row : counts) {
            for (int i = 0; i < M; ++i) {
                StdOut.print((i > 0 ? "\t" : "") + row[i]);
            }
            StdOut.println();
        }
    }

    private static void __exercise37(final int[] a) { // random in [0, N), instead of [i, N)
        final int N = a.length;
        for (int i = 0; i < N; ++i) {
            final int r = StdRandom.uniform(N);
            final int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void exercise38(int[] W, int[] T, int round) {
        if (null == W) {
            W = new int[] { 1, 1, 2, 4, 4, 4, 5, 5, 7, 9, 9, 10, 11, 12, 13, 13, 15, 15, 17, 19, 19, 20, 21, 25, 29, 30,
                    30, 30, 35, 36, 36, 39, 40, 41, 41, 42, 42, 44, 49, 49, 50, 50, 55, 57, 58, 62, 63, 65, 66, 68, 70,
                    72, 73, 73, 74, 79, 79, 81, 82, 82, 85, 86, 88, 91, 92, 92, 94, 95, 96, 98, 99 };
        }
        if (null == T) {
            T = new int[] { 9, 5, 3, 2, 7, 5, 3, 12, 17, 30, 20, 14, 7, 22, 28, 25, 23, 1, 36, 21, 42, 77, 23, 94, 24,
                    78, 53, 43, 93, 99, 100, 0, 85, 12, 35, 88, 65, 32, 91, 17, 41, 29, 37, 70, 4, 95, 2, 22 };
        }
        if (round <= 0) {
            round = 1;
        }
        final long t1 = System.currentTimeMillis();
        for (int i = 0; i < round; ++i) {
            for (final int val : T) {
                __exercise38_1(W, val);
            }
        }
        final long t2 = System.currentTimeMillis();
        for (int i = 0; i < round; ++i) {
            for (final int val : T) {
                __exercise38_2(W, val);
            }
        }
        final long t3 = System.currentTimeMillis();
        StdOut.printf("binary search:\t%d ms\n", t2 - t1);
        StdOut.printf("brute force:\t%d ms\n", t3 - t2);
    }

    private static boolean __exercise38_1(final int[] arr, final int target) { // binary search
        int l = 0, r = arr.length;
        while (l < r) {
            final int mid = l + (r - l) / 2;
            if (target == arr[mid]) {
                return true;
            }
            if (target < arr[mid]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return false;
    }

    private static boolean __exercise38_2(final int[] arr, final int target) { // brute force
        for (final int val : arr) {
            if (target == val) {
                return true;
            }
        }
        return false;
    }

    public static void exercise39(final int T) {
        final int[] N = { 1000, 10000, 100000, 1000000 };
        final int[] counts = { 0, 0, 0, 0 };
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < T; ++j) {
                counts[i] += __exercise39(N[i]);
            }
        }
        for (int i = 0; i < 4; ++i) {
            StdOut.printf("N = %d:\t%.3f\n", N[i], counts[i] / Integer.valueOf(T).doubleValue());
        }
    }

    private static int __exercise39(final int N) {
        final int[] arr1 = new int[N], arr2 = new int[N];
        for (int i = 0; i < N; ++i) {
            arr1[i] = 100000 + StdRandom.uniform(900000);
            arr2[i] = 100000 + StdRandom.uniform(900000);
        }
        int count = 0;
        for (final int i : arr1) {
            if (__exercise38_1(arr2, i)) {
                ++count;
            }
        }
        return count;
    }

    public static void main(final String[] args) {
    }

}

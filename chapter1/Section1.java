package chapter1;

import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class Section1 {

    public static void exercise3() {
        int[] ints = new int[3];
        for (int i = 0; i < 3; ++i) {
            ints[i] = StdIn.readInt();
        }
        if (ints[0] == ints[1] && ints[1] == ints[2]) {
            StdOut.println("equal");
        } else {
            StdOut.println("not equal");
        }
    }

    public static void exercise5(double x, double y) {
        if (x > 0.0 && x < 1.0 && y > 0.0 && y < 1.0) {
            StdOut.println("true");
        } else {
            StdOut.println("false");
        }
    }

    public static String exercise9(int N) {
        // Integer.toBinaryString(i);
        StringBuilder sb = new StringBuilder();
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
        int[][] newMat = new int[mat[0].length][mat.length];
        for (int i = 0; i < mat.length; ++i) {
            for (int j = 0; j < mat[0].length; ++j) {
                newMat[j][i] = mat[i][j];
            }
        }
        for (int[] nm : newMat) {
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
        int[] res = new int[M];
        for (int v : a) {
            ++res[v];
        }
        return res;
    }

    public static double exercise20(int N) {
        if (1 == N) {
            return 0.0;
        }
        return Math.log(N) + exercise20(N - 1);
    }

    public static void exercise21() {
        class Person {
            private String name;
            private int x, y;
            private double ratio;

            Person() {
                this.name = StdIn.readString();
                this.x = StdIn.readInt();
                this.y = StdIn.readInt();
                this.ratio = Double.valueOf(x) / Double.valueOf(y);
            }

            public void print() {
                StdOut.printf("%s\t%d\t%d\t%.3f\n", this.name, this.x, this.y, this.ratio);
            }
        }
        List<Person> list = new LinkedList<>();
        while (!StdIn.isEmpty()) {
            list.add(new Person());
        }
        for (Person person : list) {
            person.print();
        }
    }

    public static int exercise22(int[] arr, int target) {
        if (null == arr) {
            arr = new int[] { 1, 3, 3, 5, 6, 8, 12, 19, 20, 20 };
        }
        return __exercise22(arr, target, 0, arr.length, 0);
    }

    private static int __exercise22(int[] arr, int target, int l, int r, int depth) {
        for (int i = 0; i < depth; ++i) {
            StdOut.print(' ');
        }
        StdOut.printf("%d %d\n", l, r);
        if (l >= r) {
            return -1;
        }
        int mid = l + (r - l) / 2;
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

    public static int exercise23(int[] arr, int target, char mark) {
        int res = exercise22(arr, target);
        if (res < 0 && '+' == mark || res >= 0 && '-' == mark) {
            StdOut.println(target);
        }
        return res;
    }

    public static int exercise24(int p, int q, boolean printCallStack) {
        if (printCallStack) {
            StdOut.printf("%d %d\n", p, q);
        }
        if (0 == q) {
            return p;
        }
        return exercise24(q, p % q, printCallStack);
    }

    public static int exercise28(int[] arr) { // arr is sorted
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

    public static int exercise29_1(int[] arr, int key) { // as `rank`
        if (null == arr) {
            arr = new int[] { 1, 3, 3, 5, 6, 8, 12, 19, 20, 20 };
        }
        return 1 + __exercise29_1(arr, key);
    }

    public static int exercise29_2(int[] arr, int key) { // as `count`
        if (null == arr) {
            arr = new int[] { 1, 3, 3, 5, 6, 8, 12, 19, 20, 20 };
        }
        int lt = 1 + __exercise29_1(arr, key);
        int le = __exercise29_2(arr, key);
        return le - lt;
    }

    private static int __exercise29_1(int[] arr, int key) { // find index of max({n < key})
        for (int l = 0, r = arr.length; l < r;) {
            int mid = l + (r - l) / 2;
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

    private static int __exercise29_2(int[] arr, int key) { // find index of min({n > key})
        for (int l = 0, r = arr.length; l < r;) {
            int mid = l + (r - l) / 2;
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

    public static boolean[][] exercise30(int i, int j) {
        boolean[][] res = new boolean[i][j];
        for (int m = 2; m < i; ++m) {
            for (int n = 2; n < j; ++n) {
                res[m][n] = (1 != exercise24(m, n, false));
            }
        }
        return res;
    }

    public static void exercise31(int N, double p) {
        double x = 0.5, y = 0.5, r = 0.3;
        StdDraw.circle(x, y, r);

        double radius = 2.0 * Math.PI / N;
        List<double[]> points = new LinkedList<>();
        for (int i = 0; i < N; ++i) {
            double cur = i * radius;
            double cos = Math.cos(cur), sin = Math.sin(cur);
            points.add(new double[] { x + r * cos, y + r * sin });
        }

        for (double[] ds : points) {
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

    public static void exercise32(int N, double l, double r) {
        double range = (r - l) / N;
        double[] counts = new double[N];
        while (!StdIn.isEmpty()) {
            double val = StdIn.readDouble();
            if (val > l & val < r) {
                counts[Double.valueOf(val / range).intValue()] += 1;
            }
        }
        double max = StdStats.max(counts);
        for (int i = 0; i < N; ++i) {
            counts[i] /= max;
        }
        StdStats.plotBars(counts);
    }

    static class exercise33 {

        public static double dot(double[] x, double[] y) {
            // assert x.length == y.length;
            double res = 0.0;
            for (int i = 0; i < x.length; ++i) {
                res += x[i] * y[i];
            }
            return res;
        }

        public static double[][] mult(double[][] a, double[][] b) {
            // assert 0 != a.length && a[0].length == b.length;
            double[][] res = new double[a.length][b[0].length];
            for (int i = 0; i < a.length; ++i) {
                for (int j = 0; j < b[0].length; ++j) {
                    for (int k = 0; k < b.length; ++k) {
                        res[i][j] += a[i][k] * b[k][j];
                    }
                }
            }
            return res;
        }

        public static double[][] transpose(double[][] a) {
            // assert 0 != a.length;
            double[][] res = new double[a[0].length][a.length];
            for (int i = 0; i < a.length; ++i) {
                for (int j = 0; j < a[0].length; ++j) {
                    res[j][i] = a[i][j];
                }
            }
            return res;
        }

        public static double[] mult(double[][] a, double[] x) {
            // assert 0 != a.length && a[0].length == x.length;
            double[] res = new double[a.length];
            for (int i = 0; i < a.length; ++i) {
                for (int k = 0; k < x.length; ++k) {
                    res[i] += a[i][k] * x[k];
                }
            }
            return res;
        }

        public static double[] mult(double[] y, double[][] a) {
            // assert 0 != y.length && y.length == a.length;
            double[] res = new double[a[0].length];
            for (int i = 0; i < a[0].length; ++i) {
                for (int k = 0; k < y.length; ++k) {
                    res[i] += y[k] * a[k][i];
                }
            }
            return res;
        }

    }

    public static void exercise36(int M, int N) { // as `ShuffleTest`
        int[][] counts = new int[M][M];
        int[] arr = new int[M];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                arr[j] = j;
            }
            __exercise36(arr);
            for (int j = 0; j < M; j++) {
                ++counts[arr[j]][j];
            }
        }
        for (int[] row : counts) {
            for (int i = 0; i < M; ++i) {
                StdOut.print((i > 0 ? "\t" : "") + row[i]);
            }
            StdOut.println();
        }
    }

    private static void __exercise36(int[] a) { // p19 sheet 1.1.10 shuffle for int
        int N = a.length;
        for (int i = 0; i < N; ++i) {
            int r = i + StdRandom.uniform(N - i);
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void exercise37(int M, int N) { // exactly same with exercise36
        int[][] counts = new int[M][M];
        int[] arr = new int[M];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                arr[j] = j;
            }
            __exercise37(arr);
            for (int j = 0; j < M; j++) {
                ++counts[arr[j]][j];
            }
        }
        for (int[] row : counts) {
            for (int i = 0; i < M; ++i) {
                StdOut.print((i > 0 ? "\t" : "") + row[i]);
            }
            StdOut.println();
        }
    }

    private static void __exercise37(int[] a) { // random in [0, N), instead of [i, N)
        int N = a.length;
        for (int i = 0; i < N; ++i) {
            int r = StdRandom.uniform(N);
            int temp = a[i];
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
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < round; ++i) {
            for (int val : T) {
                __exercise38_1(W, val);
            }
        }
        long t2 = System.currentTimeMillis();
        for (int i = 0; i < round; ++i) {
            for (int val : T) {
                __exercise38_2(W, val);
            }
        }
        long t3 = System.currentTimeMillis();
        System.out.println("binary search: " + (t2 - t1));
        System.out.println("brute force: " + (t3 - t2));
    }

    private static boolean __exercise38_1(int[] arr, int target) { // binary search
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
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

    private static boolean __exercise38_2(int[] arr, int target) { // brute force
        for (int val : arr) {
            if (target == val) {
                return true;
            }
        }
        return false;
    }

    public static void exercise39(int T) {
        int[] N = { 1000, 10000, 100000, 1000000 };
        int[] counts = { 0, 0, 0, 0 };
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < T; ++j) {
                counts[i] += __exercise39(N[i]);
            }
        }
        for (int i = 0; i < 4; ++i) {
            StdOut.printf("N = %d:\t%.3f\n", N[i], counts[i] / Integer.valueOf(T).doubleValue());
        }
    }

    private static int __exercise39(int N) {
        int[] arr1 = new int[N], arr2 = new int[N];
        for (int i = 0; i < N; ++i) {
            arr1[i] = 100000 + StdRandom.uniform(900000);
            arr2[i] = 100000 + StdRandom.uniform(900000);
        }
        int count = 0;
        for (int i : arr1) {
            if (__exercise38_1(arr2, i)) {
                ++count;
            }
        }
        return count;
    }

    public static void main(String[] args) {
    }

}

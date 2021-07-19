package chapter1.section1;

public class Matrix {

    public static double dot(final double[] x, final double[] y) {
        // assert x.length == y.length;
        double res = 0.0;
        for (int i = 0; i < x.length; ++i) {
            res += x[i] * y[i];
        }
        return res;
    }

    public static double[][] mult(final double[][] a, final double[][] b) {
        // assert 0 != a.length && a[0].length == b.length;
        final double[][] res = new double[a.length][b[0].length];
        for (int i = 0; i < a.length; ++i) {
            for (int j = 0; j < b[0].length; ++j) {
                for (int k = 0; k < b.length; ++k) {
                    res[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return res;
    }

    public static double[][] transpose(final double[][] a) {
        // assert 0 != a.length;
        final double[][] res = new double[a[0].length][a.length];
        for (int i = 0; i < a.length; ++i) {
            for (int j = 0; j < a[0].length; ++j) {
                res[j][i] = a[i][j];
            }
        }
        return res;
    }

    public static double[] mult(final double[][] a, final double[] x) {
        // assert 0 != a.length && a[0].length == x.length;
        final double[] res = new double[a.length];
        for (int i = 0; i < a.length; ++i) {
            for (int k = 0; k < x.length; ++k) {
                res[i] += a[i][k] * x[k];
            }
        }
        return res;
    }

    public static double[] mult(final double[] y, final double[][] a) {
        // assert 0 != y.length && y.length == a.length;
        final double[] res = new double[a[0].length];
        for (int i = 0; i < a[0].length; ++i) {
            for (int k = 0; k < y.length; ++k) {
                res[i] += y[k] * a[k][i];
            }
        }
        return res;
    }

}

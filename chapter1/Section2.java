package chapter1;

import chapter1.section2.Counter;
import chapter1.section2.Date;
import chapter1.section2.Interval1D;
import chapter1.section2.Interval2D;
import chapter1.section2.InvalidDateException;
import chapter1.section2.Point2D;
import chapter1.section2.Rational;
import chapter1.section2.SmartDate;
import chapter1.section2.Transaction;
import chapter1.section2.VisualCounter;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Section2 {

    public static void exercise1(final int N) {
        StdDraw.setXscale(0.0, 2.0);
        StdDraw.setYscale(0.0, 2.0);
        StdDraw.square(1.0, 1.0, 0.5);

        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.RED);
        final Point2D[] point2ds = new Point2D[N];
        for (int i = 0; i < N; ++i) {
            point2ds[i] = new Point2D(0.0, 1.5, 0.0, 1.5);
            point2ds[i].draw();
        }
        StdDraw.setPenColor();
        StdDraw.setPenRadius();

        int x = 0, y = 1;
        double dist = Double.POSITIVE_INFINITY;
        for (int i = 0; i < N; ++i) {
            for (int j = i + 1; j < N; ++j) {
                final double d = point2ds[i].distTo(point2ds[j]);
                if (d < dist) {
                    dist = d;
                    x = i;
                    y = j;
                }
            }
        }
        StdOut.printf("%s -> %s = %f\n", point2ds[x], point2ds[y], dist);
    }

    public static void exercise2(final int N) {
        final Interval1D[] interval1ds = new Interval1D[N];
        for (int i = 0; i < N; ++i) {
            interval1ds[i] = new Interval1D();
        }
        for (int i = 0; i < N; ++i) {
            for (int j = i + 1; j < N; ++j) {
                if (interval1ds[i].intersect(interval1ds[j])) {
                    StdOut.printf("%s intersects with %s\n", interval1ds[i], interval1ds[j]);
                }
            }
        }
    }

    public static void exercise3(final int N, final double min, final double max) {
        // assert 0.0 < min && min <= 1.0 && min < max && 0.0 < max && max <= 1.0;
        StdDraw.setXscale(0.0, 2.0);
        StdDraw.setYscale(0.0, 2.0);
        StdDraw.square(1.0, 1.0, 0.5);

        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.RED);
        final Interval2D[] interval2ds = new Interval2D[N];
        for (int i = 0; i < N; ++i) {
            interval2ds[i] = new Interval2D(min, max);
            interval2ds[i].makeOffset(0.5, 0.5);
            interval2ds[i].draw();
        }
        StdDraw.setPenColor();
        StdDraw.setPenRadius();

        for (int i = 0; i < N; ++i) {
            for (int j = i + 1; j < N; ++j) {
                if (!interval2ds[i].intersect(interval2ds[j]) && !interval2ds[j].intersect(interval2ds[i])) {
                    continue;
                }
                if (interval2ds[i].contains(interval2ds[j])) {
                    StdOut.printf("%s contains %s\n", interval2ds[i], interval2ds[j]);
                } else if (interval2ds[j].contains(interval2ds[i])) {
                    StdOut.printf("%s contains %s\n", interval2ds[j], interval2ds[i]);
                } else {
                    StdOut.printf("%s intersects with %s\n", interval2ds[i], interval2ds[j]);
                }
            }
        }
    }

    public static boolean exercise6(final String s, final String t) {
        final String full = s + s.substring(0, s.length() - 1);
        return full.contains(t);
    }

    public static void exercise9(int[] arr, final int target) {
        if (null == arr) {
            arr = new int[] { 1, 1, 2, 4, 4, 4, 5, 5, 7, 9, 9, 10, 11, 12, 13, 13, 15, 15, 17, 19, 19, 20, 21, 25, 29,
                    30, 30, 30, 35, 36, 36, 39, 40, 41, 41, 42, 42, 44, 49, 49, 50, 50, 55, 57, 58, 62, 63, 65, 66, 68,
                    70, 72, 73, 73, 74, 79, 79, 81, 82, 82, 85, 86, 88, 91, 92, 92, 94, 95, 96, 98, 99 };
        }
        final Counter counter = new Counter("ex-9: to find " + target);
        __exercise9(arr, target, counter);
        StdOut.println(counter.toString());
    }

    private static boolean __exercise9(final int[] arr, final int target, final Counter counter) {
        int l = 0, r = arr.length;
        while (l < r) {
            counter.increment();
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

    public static void exercise10(final int N, final int max) {
        StdDraw.setXscale(-1.0, N + 3.0);
        StdDraw.setYscale(-1.0 - max, 1.0 + max);
        StdDraw.line(-1.0, 0.0, N + 3.0, 0.0);
        StdDraw.line(0.0, -1.0 - max, 0.0, 1.0 + max);

        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.RED);
        final VisualCounter visualCounter = new VisualCounter("ex-10", N, max);
        for (int i = 0; i < N + 2; ++i) {
            final double p = StdRandom.uniform();
            if (p > 0.5) {
                visualCounter.increment();
            } else {
                visualCounter.decrement();
            }
            visualCounter.draw();
        }
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.005);
    }

    public static void exercise11(final int year, final int month, final int day) {
        SmartDate sd = null;
        try {
            sd = new SmartDate(year, month, day);
            StdOut.println(sd);
        } catch (final InvalidDateException e) {
            StdOut.println(e.getMessage());
        }
    }

    public static void exercise12(final int year, final int month, final int day) { // 2020/1/1 - ?
        SmartDate sd = null;
        try {
            sd = new SmartDate(year, month, day);
            StdOut.printf("%s: %s\n", sd, sd.dayOfTheWeek());
        } catch (final InvalidDateException e) {
            StdOut.println(e.getMessage());
        }
    }

    public static void exercise13(final String who, final Date date, final double amount) {
        final Transaction tx = new Transaction(who, date, amount);
        StdOut.println(tx);
        StdOut.printf("who: %s, date: %s, amount: %s\n", tx.who(), tx.date(), tx.amount());
        final Transaction tx1 = new Transaction(who, date, amount);
        final Transaction tx2 = new Transaction(who, date, amount - 1);
        final Transaction tx3 = new Transaction(who, date, amount + 1);
        StdOut.println(tx.compareTo(tx1));
        StdOut.println(tx.compareTo(tx2));
        StdOut.println(tx.compareTo(tx3));
    }

    public static void exercise14(final String who, final Date date, final double amount) {
        final Transaction tx = new Transaction(who, date, amount);
        final Transaction tx1 = new Transaction(who, date, amount);
        final Transaction tx2 = new Transaction(who, date, amount - 1);
        final Transaction tx3 = null;
        final Object tx4 = new Date(2000, 1, 1);
        final Object tx5 = new Transaction(who, date, amount);
        StdOut.println(tx.equals(tx));
        StdOut.println(tx.equals(tx1));
        StdOut.println(tx.equals(tx2));
        StdOut.println(tx.equals(tx3));
        StdOut.println(tx.equals(tx4));
        StdOut.println(tx.equals(tx5));
    }

    public static void exercise16() {
        final Rational r1 = new Rational(1, 2);
        final Rational r2 = new Rational(1, 3);
        StdOut.println(r1.plus(r2));
        StdOut.println(r1.minus(r2));
        StdOut.println(r1.times(r2));
        StdOut.println(r1.divides(r2));
        StdOut.println(r1.equals(r2));
    }

    public static void exercise17() {
        try {
            final Rational r = new Rational(1, 0);
            StdOut.println(r);
        } catch (final AssertionError e) {
            StdOut.println(e.getMessage());
        }
        try {
            final Rational r1 = new Rational(1, 1);
            final Rational r2 = new Rational(0, 1);
            final Rational r3 = r1.divides(r2);
            StdOut.println(r3);
        } catch (final AssertionError e) {
            StdOut.println(e.getMessage());
        }
    }

    public static void exercise19() {
        final Date date = new Date("2021/07/16");
        final Transaction tx = new Transaction("Alice 2021/07/16 19.98");
        StdOut.println(date);
        StdOut.println(tx);
    }

    public static void main(final String[] args) {
    }

}

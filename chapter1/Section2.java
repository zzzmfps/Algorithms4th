package chapter1;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Section2 {

    public static void exercise1(int N) {
        StdDraw.setXscale(0.0, 2.0);
        StdDraw.setYscale(0.0, 2.0);
        StdDraw.square(1.0, 1.0, 0.5);

        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.RED);
        Point2D[] point2ds = new Point2D[N];
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
                double d = point2ds[i].distTo(point2ds[j]);
                if (d < dist) {
                    dist = d;
                    x = i;
                    y = j;
                }
            }
        }
        StdOut.printf("%s -> %s = %f\n", point2ds[x], point2ds[y], dist);
    }

    public static void exercise2(int N) {
        Interval1D[] interval1ds = new Interval1D[N];
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

    public static void exercise3(int N, double min, double max) {
        // assert 0.0 < min && min <= 1.0 && min < max && 0.0 < max && max <= 1.0;
        StdDraw.setXscale(0.0, 2.0);
        StdDraw.setYscale(0.0, 2.0);
        StdDraw.square(1.0, 1.0, 0.5);

        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.RED);
        Interval2D[] interval2ds = new Interval2D[N];
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

    public static boolean exercise6(String s, String t) {
        String full = s + s.substring(0, s.length() - 1);
        return full.contains(t);
    }

    public static void exercise9(int[] arr, int target) {
        if (null == arr) {
            arr = new int[] { 1, 1, 2, 4, 4, 4, 5, 5, 7, 9, 9, 10, 11, 12, 13, 13, 15, 15, 17, 19, 19, 20, 21, 25, 29,
                    30, 30, 30, 35, 36, 36, 39, 40, 41, 41, 42, 42, 44, 49, 49, 50, 50, 55, 57, 58, 62, 63, 65, 66, 68,
                    70, 72, 73, 73, 74, 79, 79, 81, 82, 82, 85, 86, 88, 91, 92, 92, 94, 95, 96, 98, 99 };
        }
        Counter counter = new Counter("ex-9: to find " + target);
        __exercise9(arr, target, counter);
        StdOut.println(counter.toString());
    }

    private static boolean __exercise9(int[] arr, int target, Counter counter) {
        int l = 0, r = arr.length;
        while (l < r) {
            counter.increment();
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

    public static void exercise10(int N, int max) {
        StdDraw.setXscale(-1.0, N + 3.0);
        StdDraw.setYscale(-1.0 - max, 1.0 + max);
        StdDraw.line(-1.0, 0.0, N + 3.0, 0.0);
        StdDraw.line(0.0, -1.0 - max, 0.0, 1.0 + max);

        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.RED);
        VisualCounter visualCounter = new VisualCounter("ex-10", N, max);
        for (int i = 0; i < N + 2; ++i) {
            double p = StdRandom.uniform();
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

    public static void exercise11(int year, int month, int day) {
        SmartDate sd = null;
        try {
            sd = new SmartDate(year, month, day);
            StdOut.println(sd);
        } catch (InvalidDateException e) {
            StdOut.println(e.getMessage());
        }
    }

    public static void exercise12(int year, int month, int day) { // 2020/1/1 - ?
        SmartDate sd = null;
        try {
            sd = new SmartDate(year, month, day);
            StdOut.printf("%s: %s\n", sd, sd.dayOfTheWeek());
        } catch (InvalidDateException e) {
            StdOut.println(e.getMessage());
        }
    }

    public static void exercise13(String who, Date date, double amount) {
        Transaction tx = new Transaction(who, date, amount);
        StdOut.println(tx);
        StdOut.printf("who: %s, date: %s, amount: %s\n", tx.who(), tx.date(), tx.amount());
        Transaction tx1 = new Transaction(who, date, amount);
        Transaction tx2 = new Transaction(who, date, amount - 1);
        Transaction tx3 = new Transaction(who, date, amount + 1);
        StdOut.println(tx.compareTo(tx1));
        StdOut.println(tx.compareTo(tx2));
        StdOut.println(tx.compareTo(tx3));
    }

    public static void exercise14(String who, Date date, double amount) {
        Transaction tx = new Transaction(who, date, amount);
        Transaction tx1 = new Transaction(who, date, amount);
        Transaction tx2 = new Transaction(who, date, amount - 1);
        Transaction tx3 = null;
        Object tx4 = new Date(2000, 1, 1);
        Object tx5 = new Transaction(who, date, amount);
        StdOut.println(tx.equals(tx));
        StdOut.println(tx.equals(tx1));
        StdOut.println(tx.equals(tx2));
        StdOut.println(tx.equals(tx3));
        StdOut.println(tx.equals(tx4));
        StdOut.println(tx.equals(tx5));
    }

    public static void exercise16() {
        Rational r1 = new Rational(1, 2);
        Rational r2 = new Rational(1, 3);
        StdOut.println(r1.plus(r2));
        StdOut.println(r1.minus(r2));
        StdOut.println(r1.times(r2));
        StdOut.println(r1.divides(r2));
        StdOut.println(r1.equals(r2));
    }

    public static void exercise17() {
        try {
            Rational r = new Rational(1, 0);
            StdOut.println(r);
        } catch (AssertionError e) {
            StdOut.println(e.getMessage());
        }
        try {
            Rational r1 = new Rational(1, 1);
            Rational r2 = new Rational(0, 1);
            Rational r3 = r1.divides(r2);
            StdOut.println(r3);
        } catch (AssertionError e) {
            StdOut.println(e.getMessage());
        }
    }

    public static void exercise19() {
        Date date = new Date("2021/07/16");
        Transaction tx = new Transaction("Alice 2021/07/16 19.98");
        StdOut.println(date);
        StdOut.println(tx);
    }

    public static void main(String[] args) {
    }

}

/**
 * Classes used above.
 */
class Point2D {

    private double x;
    private double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point2D(double x_l, double x_r, double y_l, double y_r) {
        this.x = StdRandom.uniform(x_l, x_r);
        this.y = StdRandom.uniform(y_l, y_r);
    }

    public void draw() {
        StdDraw.point(this.x, this.y);
    }

    public double distTo(Point2D p) {
        return Math.sqrt(Math.pow(this.x - p.x, 2.0) + Math.pow(this.y - p.y, 2.0));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Point2D [x=" + x + ", y=" + y + "]";
    }

}

class Interval1D {

    private double l;
    private double r;

    public Interval1D() {
        this.l = StdIn.readDouble();
        this.r = StdIn.readDouble();
    }

    public boolean intersect(Interval1D i1d) {
        return this.l < i1d.l && i1d.l < this.r || this.l < i1d.r && i1d.r < this.r;
    }

    @Override
    public String toString() {
        return "Interval1D [l=" + l + ", r=" + r + "]";
    }

}

class Interval2D {

    private double lb_x, lb_y;
    private double ru_x, ru_y;

    public Interval2D(double min, double max) {
        double w = StdRandom.uniform(min, max);
        double h = StdRandom.uniform(min, max);
        this.lb_x = StdRandom.uniform(0.0, 1.0 - w);
        this.lb_y = StdRandom.uniform(0.0, 1.0 - h);
        this.ru_x = this.lb_x + w;
        this.ru_y = this.lb_y + h;
    }

    public void makeOffset(double x_, double y_) {
        this.lb_x += x_;
        this.ru_x += x_;
        this.lb_y += y_;
        this.ru_y += y_;
    }

    public void draw() {
        StdDraw.rectangle((this.lb_x + this.ru_x) / 2.0, (this.lb_y + this.ru_y) / 2.0,
                Math.abs(this.lb_x - this.ru_x) / 2.0, Math.abs(this.lb_y - this.ru_y) / 2.0);
    }

    public boolean contains(Point2D p) {
        return this.lb_x < p.getX() && p.getX() < this.ru_x && this.lb_y < p.getY() && p.getY() < this.ru_y;
    }

    public boolean contains(Interval2D i2d) {
        if (this.lb_x <= i2d.lb_x && this.lb_y <= i2d.lb_y && this.ru_x >= i2d.ru_x && this.ru_y >= i2d.ru_y) {
            return true;
        }
        return false;
    }

    public boolean intersect(Interval2D i2d) {
        return this.contains(new Point2D(i2d.lb_x, i2d.lb_y)) || this.contains(new Point2D(i2d.ru_x, i2d.ru_y))
                || this.contains(new Point2D(i2d.lb_x, i2d.ru_y)) || this.contains(new Point2D(i2d.ru_x, i2d.lb_y));
    }

    @Override
    public String toString() {
        return "Interval2D [lb_x=" + lb_x + ", lb_y=" + lb_y + ", ru_x=" + ru_x + ", ru_y=" + ru_y + "]";
    }

}

class Counter {

    private final String id;
    private int count = 0;

    public Counter(String id) {
        this.id = id;
    }

    public void increment() {
        ++this.count;
    }

    public int tally() {
        return this.count;
    }

    @Override
    public String toString() {
        return "Counter [count=" + count + ", id=" + id + "]";
    }

}

class VisualCounter {

    private final String id;
    private final int maxOps, maxAbs;
    private int op = 0, count = 0;

    public VisualCounter(String id, int N, int max) {
        this.id = id;
        this.maxOps = N;
        this.maxAbs = max;
    }

    public boolean increment() {
        if (this.maxOps == this.op || this.maxAbs == this.count) {
            return false;
        }
        ++this.op;
        ++this.count;
        return true;
    }

    public boolean decrement() {
        if (this.maxOps == this.op || this.maxAbs == -this.count) {
            return false;
        }
        ++this.op;
        --this.count;
        return true;
    }

    public void draw() {
        StdDraw.point(op, count);
    }

    @Override
    public String toString() {
        return "VisualCounter [count=" + count + ", id=" + id + ", maxAbs=" + maxAbs + ", maxOps=" + maxOps + ", op="
                + op + "]";
    }

}

class SmartDate {

    private final static String[] DAY_PREFIX = { "Satur", "Sun", "Mon", "Tues", "Wednes", "Thurs", "Fri" };
    private final static String DAY_SUFFIX = "day";
    private final static int[] DAY_ACCUMULATION = { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334 };

    private final int year;
    private final int month;
    private final int day;

    public SmartDate(int y, int m, int d) throws InvalidDateException {
        boolean valid = true;
        valid &= (y > 0) && (m > 0) && (m < 13) && (d > 0) && (d < 32);
        if (valid && (4 == m || 6 == m || 9 == m || 11 == m)) {
            valid = (30 == d);
        }
        if (valid && 2 == m) {
            valid = ((SmartDate.isLeapYear(y) ? 29 : 28) == d);
        }
        if (!valid) {
            throw new InvalidDateException(y, m, d);
        }
        this.year = y;
        this.month = m;
        this.day = d;
    }

    public String dayOfTheWeek() {
        int days = 365 * (this.year - 2000) + SmartDate.DAY_ACCUMULATION[this.month - 1] + this.day - 1;
        int endYear = (this.month > 2 ? this.year : this.year - 1);
        for (int y = 2000; y <= endYear; y += 4) {
            if (SmartDate.isLeapYear(y)) {
                ++days;
            }
        }
        return SmartDate.DAY_PREFIX[days % 7] + SmartDate.DAY_SUFFIX;
    }

    private static boolean isLeapYear(int year) {
        return (0 == year % 400 || 0 != year % 100 && 0 == year % 4);
    }

    @Override
    public String toString() {
        return "SmartDate [day=" + day + ", month=" + month + ", year=" + year + "]";
    }

}

class InvalidDateException extends Exception {

    private final String invalid;

    public InvalidDateException(int y, int m, int d) {
        this.invalid = String.format("%d/%d/%d", y, m, d);
    }

    @Override
    public String getMessage() {
        return String.format("InvalidDateException: invalid date value [%s]", this.invalid);
    }

    @Override
    public String toString() {
        return "InvalidDateException [invalid=" + invalid + "]";
    }

}

class Date {

    private final int year;
    private final int month;
    private final int day;

    public Date(int y, int m, int d) {
        this.year = y;
        this.month = m;
        this.day = d;
    }

    public Date(String date) {
        String[] fields = date.split("/");
        this.year = Integer.parseInt(fields[0]);
        this.month = Integer.parseInt(fields[1]);
        this.day = Integer.parseInt(fields[2]);
    }

    public int year() {
        return this.year;
    }

    public int month() {
        return this.month;
    }

    public int day() {
        return this.day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o || this.getClass() != o.getClass()) {
            return false;
        }
        Date that = (Date) o;
        return this.year == that.year && this.month == that.month && this.day == that.day;
    }

    @Override
    public String toString() {
        return "Date [day=" + day + ", month=" + month + ", year=" + year + "]";
    }

}

class Transaction implements Comparable<Transaction> {

    private final String who;
    private final Date date;
    private final double amount;

    public Transaction(String w, Date d, double a) {
        this.who = w;
        this.date = d;
        this.amount = a;
    }

    public Transaction(String tx) {
        String[] fields = tx.split(" ");
        this.who = fields[0];
        this.date = new Date(fields[1]);
        this.amount = Double.parseDouble(fields[2]);
    }

    public String who() {
        return this.who;
    }

    public Date date() {
        return this.date;
    }

    public double amount() {
        return this.amount;
    }

    @Override
    public String toString() {
        return "Transaction [amount=" + amount + ", date=" + date + ", who=" + who + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o || this.getClass() != o.getClass()) {
            return false;
        }
        Transaction that = (Transaction) o;
        return this.who == that.who && this.date == that.date && this.amount == that.amount;
    }

    @Override
    public int compareTo(Transaction o) {
        return (this.amount == o.amount ? 0 : this.amount < o.amount ? -1 : 1);
    }

}

class Rational {

    private final long numerator;
    private final long denominator;

    public Rational(long n, long d) {
        assert 0 != d : "AssertionError: denominator is [0]";
        long gcd = Rational.gcd(n, d);
        this.numerator = n / gcd;
        this.denominator = d / gcd;
    }

    public Rational plus(Rational b) {
        long n = this.numerator * b.denominator + b.numerator * this.denominator;
        long d = this.denominator * b.denominator;
        return new Rational(n, d);
    }

    public Rational minus(Rational b) {
        long n = this.numerator * b.denominator - b.numerator * this.denominator;
        long d = this.denominator * b.denominator;
        return new Rational(n, d);
    }

    public Rational times(Rational b) {
        long n = this.numerator * b.numerator;
        long d = this.denominator * b.denominator;
        return new Rational(n, d);
    }

    public Rational divides(Rational b) {
        assert 0 != b.numerator : "AssertionError: divisor is [0]";
        long n = this.numerator * b.denominator;
        long d = this.denominator * b.numerator;
        return new Rational(n, d);
    }

    private static long gcd(long p, long q) {
        return (0 == q ? p : Rational.gcd(q, p % q));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o || this.getClass() != o.getClass()) {
            return false;
        }
        Rational r = (Rational) o;
        return this.numerator == r.numerator && this.denominator == r.denominator;
    }

    @Override
    public String toString() {
        return "Rational [denominator=" + denominator + ", numerator=" + numerator + "]";
    }

}

package chapter1.section2;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Point2D {

    private final double x;
    private final double y;

    public Point2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public Point2D(final double x_l, final double x_r, final double y_l, final double y_r) {
        this.x = StdRandom.uniform(x_l, x_r);
        this.y = StdRandom.uniform(y_l, y_r);
    }

    public void draw() {
        StdDraw.point(this.x, this.y);
    }

    public double distTo(final Point2D p) {
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

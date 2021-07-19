package chapter1.section2;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Interval2D {

    private double lb_x, lb_y;
    private double ru_x, ru_y;

    public Interval2D(final double min, final double max) {
        final double w = StdRandom.uniform(min, max);
        final double h = StdRandom.uniform(min, max);
        this.lb_x = StdRandom.uniform(0.0, 1.0 - w);
        this.lb_y = StdRandom.uniform(0.0, 1.0 - h);
        this.ru_x = this.lb_x + w;
        this.ru_y = this.lb_y + h;
    }

    public void makeOffset(final double x_, final double y_) {
        this.lb_x += x_;
        this.ru_x += x_;
        this.lb_y += y_;
        this.ru_y += y_;
    }

    public void draw() {
        StdDraw.rectangle((this.lb_x + this.ru_x) / 2.0, (this.lb_y + this.ru_y) / 2.0,
                Math.abs(this.lb_x - this.ru_x) / 2.0, Math.abs(this.lb_y - this.ru_y) / 2.0);
    }

    public boolean contains(final Point2D p) {
        return this.lb_x < p.getX() && p.getX() < this.ru_x && this.lb_y < p.getY() && p.getY() < this.ru_y;
    }

    public boolean contains(final Interval2D i2d) {
        if (this.lb_x <= i2d.lb_x && this.lb_y <= i2d.lb_y && this.ru_x >= i2d.ru_x && this.ru_y >= i2d.ru_y) {
            return true;
        }
        return false;
    }

    public boolean intersect(final Interval2D i2d) {
        return this.contains(new Point2D(i2d.lb_x, i2d.lb_y)) || this.contains(new Point2D(i2d.ru_x, i2d.ru_y))
                || this.contains(new Point2D(i2d.lb_x, i2d.ru_y)) || this.contains(new Point2D(i2d.ru_x, i2d.lb_y));
    }

    @Override
    public String toString() {
        return "Interval2D [lb_x=" + lb_x + ", lb_y=" + lb_y + ", ru_x=" + ru_x + ", ru_y=" + ru_y + "]";
    }

}

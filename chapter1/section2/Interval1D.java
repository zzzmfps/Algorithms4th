package chapter1.section2;

import edu.princeton.cs.algs4.StdIn;

public class Interval1D {

    private final double l;
    private final double r;

    public Interval1D() {
        this.l = StdIn.readDouble();
        this.r = StdIn.readDouble();
    }

    public boolean intersect(final Interval1D i1d) {
        return this.l < i1d.l && i1d.l < this.r || this.l < i1d.r && i1d.r < this.r;
    }

    @Override
    public String toString() {
        return "Interval1D [l=" + l + ", r=" + r + "]";
    }

}

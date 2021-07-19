package chapter1.section2;

import edu.princeton.cs.algs4.StdDraw;

public class VisualCounter {

    private final String id;
    private final int maxOps, maxAbs;
    private int op = 0, count = 0;

    public VisualCounter(final String id, final int N, final int max) {
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

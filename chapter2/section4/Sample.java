package chapter2.section4;

import common.Pair;
import edu.princeton.cs.algs4.StdRandom;

public class Sample {

    private double[] heap;
    private double sum;
    private int offset;

    public Sample(double[] p) {
        this.heap = new double[2 * p.length];
        for (int i = p.length; i < this.heap.length; ++i) {
            this.heap[i] = p[i - p.length];
            this.sum += p[i - p.length];
        }
        for (int i = this.heap.length - 1; i > 0; --i) {
            this.heap[i / 2] += this.heap[i];
        }
        this.offset = p.length;
    }

    public Pair<Integer, Double> random() {
        int i, j;
        for (i = 1; (j = 2 * i) < this.heap.length; i = j) {
            if (j + 1 == this.heap.length) {
                continue;
            }
            double p = this.heap[j] + this.heap[j + 1];
            double q = StdRandom.uniform(0.0, p);
            if (q > this.heap[j]) {
                ++j;
            }
        }
        return new Pair<>(i - this.offset, this.heap[i] / this.sum);
    }

    public void change(int i, double v) {
        double diff = v - this.heap[i + this.offset];
        for (int j = i + this.offset; j > 0; j /= 2) {
            this.heap[j] += diff;
        }
    }

}

package chapter1.section5;

import java.util.Arrays;

public class WeightedQuickUnion extends QuickUnion {

    protected int[] sizes;

    public WeightedQuickUnion(int N) {
        super(N);
        this.sizes = new int[N];
        Arrays.fill(this.sizes, 1);
    }

    @Override
    public void union(int p, int q) {
        int r1 = this.find(p);
        int r2 = this.find(q);
        if (r1 != r2) {
            if (this.sizes[r1] < this.sizes[r2]) {
                this.id[r1] = r2;
                this.sizes[r2] += this.sizes[r1];
            } else {
                this.id[r2] = r1;
                this.sizes[r1] += this.sizes[r2];
            }
            --this.count;
        }
    }

}

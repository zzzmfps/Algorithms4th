package chapter1.section5;

import java.util.Arrays;

public class HeightWeightedQuickUnion extends QuickUnion {

    private int[] heights;

    public HeightWeightedQuickUnion(int N) {
        super(N);
        this.heights = new int[N];
        Arrays.fill(this.heights, 1);
    }

    @Override
    public void union(int p, int q) {
        int r1 = this.find(p);
        int r2 = this.find(q);
        if (r1 != r2) {
            if (this.heights[r1] < this.heights[r2]) {
                this.id[r1] = r2;
                this.heights[r2] += 1;
            } else {
                this.id[r2] = r1;
                this.heights[r1] += 1;
            }
            --this.count;
        }
    }

}

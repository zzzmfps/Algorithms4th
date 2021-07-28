package chapter1.section5;

import convention.UnionSetConv;

public class QuickUnion implements UnionSetConv {

    protected int[] id;
    protected int count;

    public QuickUnion(int N) {
        this.id = new int[N];
        for (int i = 1; i < N; ++i) {
            this.id[i] = i;
        }
        this.count = N;
    }

    @Override
    public int find(int p) {
        while (p != this.id[p]) {
            p = this.id[p];
        }
        return p;
    }

    @Override
    public void union(int p, int q) {
        int r1 = this.find(p);
        int r2 = this.find(q);
        if (r1 != r2) {
            this.id[r1] = r2;
            --this.count;
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return this.find(p) == this.find(q);
    }

    @Override
    public int count() {
        return this.count;
    }

}

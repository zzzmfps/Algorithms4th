package chapter1.section5;

import convention.UnionSetConv;

public class QuickFind implements UnionSetConv {

    private int[] id;
    private int count;

    public QuickFind(int N) {
        this.id = new int[N];
        for (int i = 1; i < N; ++i) {
            this.id[i] = i;
        }
        this.count = N;
    }

    @Override
    public int find(int p) {
        return this.id[p];
    }

    @Override
    public void union(int p, int q) {
        int r1 = this.find(p);
        int r2 = this.find(q);
        if (r1 != r2) {
            for (int i = 0; i < this.id.length; ++i) {
                if (this.id[i] == r1) {
                    this.id[i] = r2;
                }
            }
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

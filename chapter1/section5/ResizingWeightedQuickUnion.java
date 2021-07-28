package chapter1.section5;

public class ResizingWeightedQuickUnion extends WeightedQuickUnion {

    public ResizingWeightedQuickUnion(int N) {
        super(N);
    }

    public int newSite() { // what is this?
        return 1;
    }

    @Override
    public void union(int p, int q) {
        if (p >= this.id.length || q >= this.id.length) {
            this.resize(1 + Math.max(p, q));
        }
        super.union(p, q);
    }

    private void resize(int newSize) {
        int oldSize = this.id.length;
        int[] newId = new int[newSize];
        int[] newSizes = new int[newSize];
        for (int i = 0; i < oldSize; ++i) {
            newId[i] = this.id[i];
            newSizes[i] = this.sizes[i];
        }
        for (int i = oldSize; i < newSize; ++i) {
            newId[i] = i;
            newSizes[i] = 1;
        }
        this.id = newId;
        this.sizes = newSizes;
        this.count += newSize - oldSize;
    }

}

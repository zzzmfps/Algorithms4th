package chapter1.section5;

public class CompressedWeightedQuickUnion extends WeightedQuickUnion {

    public CompressedWeightedQuickUnion(int N) {
        super(N);
    }

    @Override
    public int find(int p) {
        if (p == this.id[p]) {
            return p;
        }
        int root = this.find(this.id[p]);
        return this.id[p] = root;
    }

}

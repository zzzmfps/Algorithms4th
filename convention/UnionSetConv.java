package convention;

public interface UnionSetConv {

    public int find(int value);

    public void union(int p, int q);

    public boolean connected(int p, int q);

    public int count();

}

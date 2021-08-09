package chapter2.section5;

public class Square implements Comparable<Square> {

    private static final int ROW_COL = 3;
    private static final String CORRECT = "123456780";
    private static final int[][] NEXT = { { 1, 3 }, { 0, 2, 4 }, { 1, 5 }, { 0, 4, 6 }, { 1, 3, 5, 7 }, { 2, 4, 8 },
            { 3, 7 }, { 4, 6, 8 }, { 5, 7 } };

    private String seq;
    private int zero, step;

    public Square(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ROW_COL; ++i) {
            for (int j = 0; j < ROW_COL; ++j) {
                sb.append(String.valueOf(matrix[i][j]));
            }
        }
        this.seq = sb.toString();
        this.zero = this.seq.indexOf('0');
        this.step = 0;
    }

    public Square(String seq, int step) {
        this.seq = seq;
        this.zero = seq.indexOf('0');
        this.step = step;
    }

    public String getSeq() {
        return this.seq;
    }

    public boolean check() {
        return CORRECT.equals(this.seq);
    }

    public Square[] move() {
        Square[] moved = new Square[Square.NEXT[this.zero].length];
        for (int i = 0; i < moved.length; ++i) {
            moved[i] = new Square(this.swap(this.seq, this.zero, Square.NEXT[this.zero][i]), this.step + 1);
        }
        return moved;
    }

    private String swap(String str, int i, int j) {
        if (i > j) {
            return this.swap(str, j, i);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(0, i));
        sb.append(str.charAt(j));
        sb.append(str.substring(i + 1, j));
        sb.append(str.charAt(i));
        sb.append(str.substring(j + 1));
        return sb.toString();
    }

    @Override
    public int compareTo(Square o) {
        int sc1 = this.eval(), sc2 = o.eval();
        return sc1 == sc2 ? 0 : sc1 < sc2 ? -1 : 1;
    }

    private int eval() {
        int s1 = this.step, s2 = 0;
        for (int i = Square.ROW_COL * Square.ROW_COL - 1; i > -1; --i) {
            if (Square.CORRECT.charAt(i) != this.seq.charAt(i)) {
                ++s2;
            }
        }
        return Math.max(s1, s2) + s2; // step >= wrong grids
    }

    @Override
    public String toString() {
        String tpl = "Square [seq=%s, step=%d, zero=%d, eval=%d]";
        return String.format(tpl, this.seq, this.step, this.zero, this.eval());
    }

}

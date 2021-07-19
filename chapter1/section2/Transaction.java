package chapter1.section2;

public class Transaction implements Comparable<Transaction> {

    private final String who;
    private final Date date;
    private final double amount;

    public Transaction(final String w, final Date d, final double a) {
        this.who = w;
        this.date = d;
        this.amount = a;
    }

    public Transaction(final String tx) {
        final String[] fields = tx.split(" ");
        this.who = fields[0];
        this.date = new Date(fields[1]);
        this.amount = Double.parseDouble(fields[2]);
    }

    public String who() {
        return this.who;
    }

    public Date date() {
        return this.date;
    }

    public double amount() {
        return this.amount;
    }

    @Override
    public String toString() {
        return "Transaction [amount=" + amount + ", date=" + date + ", who=" + who + "]";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (null == o || this.getClass() != o.getClass()) {
            return false;
        }
        final Transaction that = (Transaction) o;
        return this.who == that.who && this.date == that.date && this.amount == that.amount;
    }

    @Override
    public int compareTo(final Transaction o) {
        return (this.amount == o.amount ? 0 : this.amount < o.amount ? -1 : 1);
    }

}

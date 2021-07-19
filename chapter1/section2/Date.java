package chapter1.section2;

public class Date {

    private final int year;
    private final int month;
    private final int day;

    public Date(final int y, final int m, final int d) {
        this.year = y;
        this.month = m;
        this.day = d;
    }

    public Date(final String date) {
        final String[] fields = date.split("/");
        this.year = Integer.parseInt(fields[0]);
        this.month = Integer.parseInt(fields[1]);
        this.day = Integer.parseInt(fields[2]);
    }

    public int year() {
        return this.year;
    }

    public int month() {
        return this.month;
    }

    public int day() {
        return this.day;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (null == o || this.getClass() != o.getClass()) {
            return false;
        }
        final Date that = (Date) o;
        return this.year == that.year && this.month == that.month && this.day == that.day;
    }

    @Override
    public String toString() {
        return "Date [day=" + day + ", month=" + month + ", year=" + year + "]";
    }

}

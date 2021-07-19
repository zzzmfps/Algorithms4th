package chapter1.section2;

public class SmartDate {

    private final static String[] DAY_PREFIX = { "Satur", "Sun", "Mon", "Tues", "Wednes", "Thurs", "Fri" };
    private final static String DAY_SUFFIX = "day";
    private final static int[] DAY_ACCUMULATION = { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334 };

    private final int year;
    private final int month;
    private final int day;

    public SmartDate(final int y, final int m, final int d) throws InvalidDateException {
        boolean valid = true;
        valid &= (y > 0) && (m > 0) && (m < 13) && (d > 0) && (d < 32);
        if (valid && (4 == m || 6 == m || 9 == m || 11 == m)) {
            valid = (30 == d);
        }
        if (valid && 2 == m) {
            valid = ((SmartDate.isLeapYear(y) ? 29 : 28) == d);
        }
        if (!valid) {
            throw new InvalidDateException(y, m, d);
        }
        this.year = y;
        this.month = m;
        this.day = d;
    }

    public String dayOfTheWeek() {
        int days = 365 * (this.year - 2000) + SmartDate.DAY_ACCUMULATION[this.month - 1] + this.day - 1;
        final int endYear = (this.month > 2 ? this.year : this.year - 1);
        for (int y = 2000; y <= endYear; y += 4) {
            if (SmartDate.isLeapYear(y)) {
                ++days;
            }
        }
        return SmartDate.DAY_PREFIX[days % 7] + SmartDate.DAY_SUFFIX;
    }

    private static boolean isLeapYear(final int year) {
        return (0 == year % 400 || 0 != year % 100 && 0 == year % 4);
    }

    @Override
    public String toString() {
        return "SmartDate [day=" + day + ", month=" + month + ", year=" + year + "]";
    }

}

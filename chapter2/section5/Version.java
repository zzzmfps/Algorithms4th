package chapter2.section5;

public class Version implements Comparable<Version> {

    private final int[] version;

    public Version(final int... vers) {
        this.version = new int[vers.length];
        for (int i = 0; i < vers.length; ++i) {
            this.version[i] = vers[i];
        }
    }

    public Version(final String ver) {
        final String[] vers = ver.split("\\.");
        this.version = new int[vers.length];
        for (int i = 0; i < vers.length; ++i) {
            this.version[i] = Integer.parseInt(vers[i]);
        }
    }

    @Override
    public int compareTo(final Version o) {
        final int minLength = Math.min(this.version.length, o.version.length);
        for (int i = 0; i < minLength; ++i) {
            if (this.version[i] == o.version[i]) {
                continue;
            }
            return this.version[i] < o.version[i] ? -1 : 1;
        }
        if (this.version.length == o.version.length) {
            return 0;
        }
        return this.version.length < o.version.length ? -1 : 1;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(String.valueOf(this.version[0]));
        for (int i = 1; i < this.version.length; ++i) {
            sb.append(".");
            sb.append(String.valueOf(this.version[i]));
        }
        return sb.toString();
    }

}

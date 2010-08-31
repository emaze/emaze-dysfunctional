package net.emaze.disfunctional.concepts;

import java.util.Comparator;

public class CompareToBuilder {

    private int comparison;

    public CompareToBuilder() {
        comparison = 0;
    }

    public CompareToBuilder appendSuper(int superCompareTo) {
        if (comparison != 0) {
            return this;
        }
        comparison = superCompareTo;
        return this;
    }

    public CompareToBuilder append(Object lhs, Object rhs) {
        return append(lhs, rhs, null);
    }

    public CompareToBuilder append(Object lhs, Object rhs, Comparator comparator) {
        if (comparison != 0) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null) {
            comparison = -1;
            return this;
        }
        if (rhs == null) {
            comparison = +1;
            return this;
        }
        if (lhs.getClass().isArray()) {
            // switch on type of array, to dispatch to the correct handler
            // handles multi dimensional arrays
            // throws a ClassCastException if rhs is not the correct array type
            if (lhs instanceof long[]) {
                append((long[]) lhs, (long[]) rhs);
            } else if (lhs instanceof int[]) {
                append((int[]) lhs, (int[]) rhs);
            } else if (lhs instanceof short[]) {
                append((short[]) lhs, (short[]) rhs);
            } else if (lhs instanceof char[]) {
                append((char[]) lhs, (char[]) rhs);
            } else if (lhs instanceof byte[]) {
                append((byte[]) lhs, (byte[]) rhs);
            } else if (lhs instanceof double[]) {
                append((double[]) lhs, (double[]) rhs);
            } else if (lhs instanceof float[]) {
                append((float[]) lhs, (float[]) rhs);
            } else if (lhs instanceof boolean[]) {
                append((boolean[]) lhs, (boolean[]) rhs);
            } else {
                // not an array of primitives
                // throws a ClassCastException if rhs is not an array
                append((Object[]) lhs, (Object[]) rhs, comparator);
            }
        } else {
            // the simple case, not an array, just test the element
            if (comparator == null) {
                comparison = ((Comparable) lhs).compareTo(rhs);
            } else {
                comparison = comparator.compare(lhs, rhs);
            }
        }
        return this;
    }

    public CompareToBuilder append(long lhs, long rhs) {
        if (comparison != 0) {
            return this;
        }
        comparison = ((lhs < rhs) ? -1 : ((lhs > rhs) ? 1 : 0));
        return this;
    }

    public CompareToBuilder append(int lhs, int rhs) {
        if (comparison != 0) {
            return this;
        }
        comparison = ((lhs < rhs) ? -1 : ((lhs > rhs) ? 1 : 0));
        return this;
    }

    public CompareToBuilder append(short lhs, short rhs) {
        if (comparison != 0) {
            return this;
        }
        comparison = ((lhs < rhs) ? -1 : ((lhs > rhs) ? 1 : 0));
        return this;
    }

    public CompareToBuilder append(char lhs, char rhs) {
        if (comparison != 0) {
            return this;
        }
        comparison = ((lhs < rhs) ? -1 : ((lhs > rhs) ? 1 : 0));
        return this;
    }

    public CompareToBuilder append(byte lhs, byte rhs) {
        if (comparison != 0) {
            return this;
        }
        comparison = ((lhs < rhs) ? -1 : ((lhs > rhs) ? 1 : 0));
        return this;
    }

    public CompareToBuilder append(double lhs, double rhs) {
        if (comparison != 0) {
            return this;
        }
        comparison = new StrictOrderingDoubleComparator().compare(lhs, rhs);
        return this;
    }

    public CompareToBuilder append(float lhs, float rhs) {
        if (comparison != 0) {
            return this;
        }
        comparison = new StrictOrderingFloatComparator().compare(lhs, rhs);
        return this;
    }

    public CompareToBuilder append(boolean lhs, boolean rhs) {
        if (comparison != 0) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == false) {
            comparison = -1;
        } else {
            comparison = +1;
        }
        return this;
    }

    public CompareToBuilder append(Object[] lhs, Object[] rhs) {
        return append(lhs, rhs, null);
    }

    public CompareToBuilder append(Object[] lhs, Object[] rhs, Comparator comparator) {
        if (comparison != 0) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null) {
            comparison = -1;
            return this;
        }
        if (rhs == null) {
            comparison = +1;
            return this;
        }
        if (lhs.length != rhs.length) {
            comparison = (lhs.length < rhs.length) ? -1 : +1;
            return this;
        }
        for (int i = 0; i < lhs.length && comparison == 0; i++) {
            append(lhs[i], rhs[i], comparator);
        }
        return this;
    }

    public CompareToBuilder append(long[] lhs, long[] rhs) {
        if (comparison != 0) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null) {
            comparison = -1;
            return this;
        }
        if (rhs == null) {
            comparison = +1;
            return this;
        }
        if (lhs.length != rhs.length) {
            comparison = (lhs.length < rhs.length) ? -1 : +1;
            return this;
        }
        for (int i = 0; i < lhs.length && comparison == 0; i++) {
            append(lhs[i], rhs[i]);
        }
        return this;
    }

    public CompareToBuilder append(int[] lhs, int[] rhs) {
        if (comparison != 0) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null) {
            comparison = -1;
            return this;
        }
        if (rhs == null) {
            comparison = +1;
            return this;
        }
        if (lhs.length != rhs.length) {
            comparison = (lhs.length < rhs.length) ? -1 : +1;
            return this;
        }
        for (int i = 0; i < lhs.length && comparison == 0; i++) {
            append(lhs[i], rhs[i]);
        }
        return this;
    }

    public CompareToBuilder append(short[] lhs, short[] rhs) {
        if (comparison != 0) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null) {
            comparison = -1;
            return this;
        }
        if (rhs == null) {
            comparison = +1;
            return this;
        }
        if (lhs.length != rhs.length) {
            comparison = (lhs.length < rhs.length) ? -1 : +1;
            return this;
        }
        for (int i = 0; i < lhs.length && comparison == 0; i++) {
            append(lhs[i], rhs[i]);
        }
        return this;
    }

    public CompareToBuilder append(char[] lhs, char[] rhs) {
        if (comparison != 0) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null) {
            comparison = -1;
            return this;
        }
        if (rhs == null) {
            comparison = +1;
            return this;
        }
        if (lhs.length != rhs.length) {
            comparison = (lhs.length < rhs.length) ? -1 : +1;
            return this;
        }
        for (int i = 0; i < lhs.length && comparison == 0; i++) {
            append(lhs[i], rhs[i]);
        }
        return this;
    }

    public CompareToBuilder append(byte[] lhs, byte[] rhs) {
        if (comparison != 0) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null) {
            comparison = -1;
            return this;
        }
        if (rhs == null) {
            comparison = +1;
            return this;
        }
        if (lhs.length != rhs.length) {
            comparison = (lhs.length < rhs.length) ? -1 : +1;
            return this;
        }
        for (int i = 0; i < lhs.length && comparison == 0; i++) {
            append(lhs[i], rhs[i]);
        }
        return this;
    }

    public CompareToBuilder append(double[] lhs, double[] rhs) {
        if (comparison != 0) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null) {
            comparison = -1;
            return this;
        }
        if (rhs == null) {
            comparison = +1;
            return this;
        }
        if (lhs.length != rhs.length) {
            comparison = (lhs.length < rhs.length) ? -1 : +1;
            return this;
        }
        for (int i = 0; i < lhs.length && comparison == 0; i++) {
            append(lhs[i], rhs[i]);
        }
        return this;
    }

    public CompareToBuilder append(float[] lhs, float[] rhs) {
        if (comparison != 0) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null) {
            comparison = -1;
            return this;
        }
        if (rhs == null) {
            comparison = +1;
            return this;
        }
        if (lhs.length != rhs.length) {
            comparison = (lhs.length < rhs.length) ? -1 : +1;
            return this;
        }
        for (int i = 0; i < lhs.length && comparison == 0; i++) {
            append(lhs[i], rhs[i]);
        }
        return this;
    }

    public CompareToBuilder append(boolean[] lhs, boolean[] rhs) {
        if (comparison != 0) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null) {
            comparison = -1;
            return this;
        }
        if (rhs == null) {
            comparison = +1;
            return this;
        }
        if (lhs.length != rhs.length) {
            comparison = (lhs.length < rhs.length) ? -1 : +1;
            return this;
        }
        for (int i = 0; i < lhs.length && comparison == 0; i++) {
            append(lhs[i], rhs[i]);
        }
        return this;
    }

    public int toComparison() {
        return comparison;
    }

}

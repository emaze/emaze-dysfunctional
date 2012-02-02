package net.emaze.dysfunctional.order;

import java.util.Comparator;

public class CompareToBuilder {

    private Order comparison;

    public CompareToBuilder() {
        comparison = Order.EQ;
    }

    public CompareToBuilder appendSuper(int superCompareTo) {
        if (comparison != Order.EQ) {
            return this;
        }
        comparison = Order.of(superCompareTo);
        return this;
    }

    public CompareToBuilder append(Object lhs, Object rhs) {
        return append(lhs, rhs, null);
    }

    public CompareToBuilder append(Object lhs, Object rhs, Comparator comparator) {
        if (comparison != Order.EQ) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null) {
            comparison = Order.LT;
            return this;
        }
        if (rhs == null) {
            comparison = Order.GT;
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
                comparison = Order.of(((Comparable) lhs).compareTo(rhs));
            } else {
                comparison = Order.of(comparator.compare(lhs, rhs));
            }
        }
        return this;
    }

    public CompareToBuilder append(long lhs, long rhs) {
        if (comparison != Order.EQ) {
            return this;
        }
        comparison = Order.of(Long.valueOf(lhs).compareTo(rhs));
        return this;
    }

    public CompareToBuilder append(int lhs, int rhs) {
        if (comparison != Order.EQ) {
            return this;
        }
        comparison = Order.of(Integer.valueOf(lhs).compareTo(rhs));
        return this;
    }

    public CompareToBuilder append(short lhs, short rhs) {
        if (comparison != Order.EQ) {
            return this;
        }
        comparison = Order.of(Short.valueOf(lhs).compareTo(rhs));
        return this;
    }

    public CompareToBuilder append(char lhs, char rhs) {
        if (comparison != Order.EQ) {
            return this;
        }
        comparison = Order.of(Character.valueOf(lhs).compareTo(rhs));
        return this;
    }

    public CompareToBuilder append(byte lhs, byte rhs) {
        if (comparison != Order.EQ) {
            return this;
        }
        comparison = Order.of(Byte.valueOf(lhs).compareTo(rhs));
        return this;
    }

    public CompareToBuilder append(double lhs, double rhs) {
        if (comparison != Order.EQ) {
            return this;
        }
        comparison = Order.of(new StrictOrderingDoubleComparator().compare(lhs, rhs));
        return this;
    }

    public CompareToBuilder append(float lhs, float rhs) {
        if (comparison != Order.EQ) {
            return this;
        }
        comparison = Order.of(new StrictOrderingFloatComparator().compare(lhs, rhs));
        return this;
    }

    public CompareToBuilder append(boolean lhs, boolean rhs) {
        if (comparison != Order.EQ) {
            return this;
        }
        comparison = Order.of(Boolean.valueOf(lhs).compareTo(rhs));
        return this;
    }

    public CompareToBuilder append(Object[] lhs, Object[] rhs) {
        return append(lhs, rhs, null);
    }

    public CompareToBuilder append(Object[] lhs, Object[] rhs, Comparator comparator) {
        if (comparison != Order.EQ) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null) {
            comparison = Order.LT;
            return this;
        }
        if (rhs == null) {
            comparison = Order.GT;
            return this;
        }
        if (lhs.length != rhs.length) {
            comparison = Order.of(Integer.valueOf(lhs.length).compareTo(rhs.length));
            return this;
        }
        for (int i = 0; i < lhs.length && comparison == Order.EQ; i++) {
            append(lhs[i], rhs[i], comparator);
        }
        return this;
    }

    public CompareToBuilder append(long[] lhs, long[] rhs) {
        if (comparison != Order.EQ) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null) {
            comparison = Order.LT;
            return this;
        }
        if (rhs == null) {
            comparison = Order.GT;
            return this;
        }
        if (lhs.length != rhs.length) {
            comparison = Order.of(Integer.valueOf(lhs.length).compareTo(rhs.length));
            return this;
        }
        for (int i = 0; i < lhs.length && comparison == Order.EQ; i++) {
            append(lhs[i], rhs[i]);
        }
        return this;
    }

    public CompareToBuilder append(int[] lhs, int[] rhs) {
        if (comparison != Order.EQ) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null) {
            comparison = Order.LT;
            return this;
        }
        if (rhs == null) {
            comparison = Order.GT;
            return this;
        }
        if (lhs.length != rhs.length) {
            comparison = Order.of(Integer.valueOf(lhs.length).compareTo(rhs.length));
            return this;
        }
        for (int i = 0; i < lhs.length && comparison == Order.EQ; i++) {
            append(lhs[i], rhs[i]);
        }
        return this;
    }

    public CompareToBuilder append(short[] lhs, short[] rhs) {
        if (comparison != Order.EQ) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null) {
            comparison = Order.LT;
            return this;
        }
        if (rhs == null) {
            comparison = Order.GT;
            return this;
        }
        if (lhs.length != rhs.length) {
            comparison = Order.of(Integer.valueOf(lhs.length).compareTo(rhs.length));
            return this;
        }
        for (int i = 0; i < lhs.length && comparison == Order.EQ; i++) {
            append(lhs[i], rhs[i]);
        }
        return this;
    }

    public CompareToBuilder append(char[] lhs, char[] rhs) {
        if (comparison != Order.EQ) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null) {
            comparison = Order.LT;
            return this;
        }
        if (rhs == null) {
            comparison = Order.GT;
            return this;
        }
        if (lhs.length != rhs.length) {
            comparison = Order.of(Integer.valueOf(lhs.length).compareTo(rhs.length));
            return this;
        }
        for (int i = 0; i < lhs.length && comparison == Order.EQ; i++) {
            append(lhs[i], rhs[i]);
        }
        return this;
    }

    public CompareToBuilder append(byte[] lhs, byte[] rhs) {
        if (comparison != Order.EQ) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null) {
            comparison = Order.LT;
            return this;
        }
        if (rhs == null) {
            comparison = Order.GT;
            return this;
        }
        if (lhs.length != rhs.length) {
            comparison = Order.of(Integer.valueOf(lhs.length).compareTo(rhs.length));
            return this;
        }
        for (int i = 0; i < lhs.length && comparison == Order.EQ; i++) {
            append(lhs[i], rhs[i]);
        }
        return this;
    }

    public CompareToBuilder append(double[] lhs, double[] rhs) {
        if (comparison != Order.EQ) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null) {
            comparison = Order.LT;
            return this;
        }
        if (rhs == null) {
            comparison = Order.GT;
            return this;
        }
        if (lhs.length != rhs.length) {
            comparison = Order.of(Integer.valueOf(lhs.length).compareTo(rhs.length));
            return this;
        }
        for (int i = 0; i < lhs.length && comparison == Order.EQ; i++) {
            append(lhs[i], rhs[i]);
        }
        return this;
    }

    public CompareToBuilder append(float[] lhs, float[] rhs) {
        if (comparison != Order.EQ) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null) {
            comparison = Order.LT;
            return this;
        }
        if (rhs == null) {
            comparison = Order.GT;
            return this;
        }
        if (lhs.length != rhs.length) {
            comparison = Order.of(Integer.valueOf(lhs.length).compareTo(rhs.length));
            return this;
        }
        for (int i = 0; i < lhs.length && comparison == Order.EQ; i++) {
            append(lhs[i], rhs[i]);
        }
        return this;
    }

    public CompareToBuilder append(boolean[] lhs, boolean[] rhs) {
        if (comparison != Order.EQ) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null) {
            comparison = Order.LT;
            return this;
        }
        if (rhs == null) {
            comparison = Order.GT;
            return this;
        }
        if (lhs.length != rhs.length) {
            comparison = Order.of(Integer.valueOf(lhs.length).compareTo(rhs.length));
            return this;
        }
        for (int i = 0; i < lhs.length && comparison == Order.EQ; i++) {
            append(lhs[i], rhs[i]);
        }
        return this;
    }

    public int toComparison() {
        return comparison.order();
    }

    public Order toOrder() {
        return comparison;
    }
}

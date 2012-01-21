package net.emaze.dysfunctional.order;

import java.util.Comparator;

/**
 *
 * @author rferranti
 */
public enum Order {

    LT(-1), EQ(0), GT(1);
    private int order;

    Order(int order) {
        this.order = order;
    }

    public int order() {
        return order;
    }

    public boolean isLte() {
        return this == LT || this == EQ;
    }

    public boolean isGte() {
        return this == GT || this == EQ;
    }

    public static Order of(int order) {
        return Order.values()[order + 1];
    }

    public static <T> Order of(Comparator<T> comparator, T lhs, T rhs) {
        return Order.values()[comparator.compare(lhs, rhs) + 1];
    }
}
